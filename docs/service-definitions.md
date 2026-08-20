# Writing service definitions

Services are compiled into an s6-rc database from *source definition
directories*: a directory per service, holding one file per property. That
format is s6-rc's, and is described in s6-rc-compile(1) and in
[repodefs](https://skarnet.org/software/s6-rc/repodefs.html).

The TOML described here is a convenience local to this layer, nothing more.
It exists because a directory of one-line files is tedious to review in a
patch, and it is expanded into exactly that directory at build time. Nothing
on the target ever sees the TOML - it is deleted once expanded - and no
skarnet tool knows anything about it.

## Where definitions live

    /usr/share/s6/sources    the store packages install into (${s6_rc_sourcedir})
    /etc/s6/sources          the store an administrator writes into

A recipe inherits `s6-rc` and installs its definitions into
`${D}${s6_rc_sourcedir}`.

## The TOML is optional, and it is not all or nothing

A definition directory written out by hand is equally valid, and for a
longrun it is very nearly an
[s6 service directory](https://skarnet.org/software/s6/servicedir.html):
`run`, `finish`, `notification-fd`, `down-signal`, `timeout-kill`,
`max-death-tally` and friends all mean what they mean to s6-supervise. The
differences are that s6-rc-compile crafts the servicedir itself rather than
copying the definition directory wholesale - only `data` and `env` are copied
verbatim - and that a definition directory cannot have a `log` subdirectory,
because a logged service is expressed as a producer and a consumer instead.

    do_install:append() {
        install -d ${D}${s6_rc_sourcedir}/foo-srv
        echo longrun > ${D}${s6_rc_sourcedir}/foo-srv/type
        install -m 0755 ${UNPACKDIR}/foo-srv.run ${D}${s6_rc_sourcedir}/foo-srv/run
    }

The two can be mixed freely, and usually should be. The TOML is expanded
after `do_install` has run, into the same directories, so the natural split
is TOML for the properties and a file for the script:

    # foo.toml - no run key
    [foo-srv]
    flag-recommended = true
    type = "longrun"
    notification-fd = 3
    dependencies = [ "populate-volatile" ]

    do_install:append() {
        install -D -m 0755 ${UNPACKDIR}/foo-srv.run \
            ${D}${s6_rc_sourcedir}/foo-srv/run
        install -D -m 0644 ${UNPACKDIR}/foo.toml \
            ${D}${s6_rc_sourcedir}/foo.toml
    }

A script kept as its own file can be edited with syntax highlighting, checked
by a linter, and patched without wrestling with TOML quoting - so anything
beyond a few lines is better off as a file, even when the rest of the service
is described in TOML.

## How the TOML is expanded

Each `*.toml` in the store is flattened and one file is written per leaf
value. A table becomes a directory, a key becomes a file in it, and the value
becomes the contents:

    [foo]                          ->  foo/
    type = "longrun"               ->  foo/type            containing longrun
    notification-fd = 3            ->  foo/notification-fd containing 3
    flag-recommended = true        ->  foo/flag-recommended  (empty file)
    dependencies = ["bar", "baz"]  ->  foo/dependencies    containing bar\nbaz

    [foo.env]                      ->  foo/env/
    LEVEL = "3"                    ->  foo/env/LEVEL       containing 3
    HOME = "/var/lib/foo"          ->  foo/env/HOME        containing /var/lib/foo

    [foo.data]                     ->  foo/data/
    rules = """..."""                ->  foo/data/rules      containing ...

  * a string is written as-is, with a trailing newline added if missing
  * an integer is written as its decimal representation
  * `true` creates an empty file, as s6-rc tests these flags for presence;
    `false` writes nothing at all
  * a list is written one element per line
  * a nested table becomes a subdirectory, which is how `env` and `data` are
    written

`env` and `data` are the only subdirectories s6-rc copies verbatim into the
generated service directory, so anything the service needs to read at runtime
belongs in one of them. Nothing applies `env` for you - s6-supervise does
not - so the run script has to, and because the script's working directory
is the service directory it can name it directly:

    run = """
    #!/usr/bin/execlineb -P

    fdmove -c 2 1
    exec -c
    s6-envdir env
    foo-daemon --nodaemon
    """

Mind the order: `exec -c` clears the environment, so it has to come *before*
`s6-envdir`, not after. Written the other way round the variables are set and
then thrown away.

`contents` and `dependencies` are then exploded into the `contents.d` and
`dependencies.d` directories s6-rc reads, one empty file per entry. Write
them as lists.

## The keys

The keys are s6-rc's. Anything `s6-rc-compile` reads from a source definition
directory can be written as a key; the common ones follow.

### Every service

    type            "oneshot", "longrun" or "bundle" - required
    dependencies    services which must be up first
    flag-essential  always started, cannot be disabled or masked, and
                    s6-rc -d change will not stop it. Its dependencies must
                    themselves be essential
    flag-recommended started at boot unless the administrator disables it
    timeout-up      milliseconds to wait for the service to come up
    timeout-down    milliseconds to wait for it to go down

A service with neither flag is compiled into the database but is not started
at boot. It can be started on demand or pulled in as a dependency, which is
the right prescription for anything optional or machine specific.

### Oneshots

    up              command line run to bring the service up
    down            command line run to take it down

### Longruns

    run             the supervised process
    finish          run when it dies
    notification-fd the descriptor the process writes a newline to when
                    ready; the daemon has to support readiness notification
    down-signal     signal used to stop it, e.g. SIGHUP
    producer-for    the service this one's output is piped to
    consumer-for    the service whose output this one consumes
    pipeline-name   a bundle name covering both ends of the pipeline

### Bundles

    contents        the services the bundle stands for

## up and down are not run scripts

This catches people out, and the two are not interchangeable.

`up` and `down` hold **a single command line**, which s6-rc-compile lexes
with execlineb at compile time and stores in the database in an internal
form. They are therefore implicitly execline and **must not have a shebang** -
a `#!` line would be parsed as a command. They need not be written in
execline: invoking another interpreter is a command line like any other.

    up = "/etc/init.d/populate-volatile.sh"
    up = "/bin/sh -c \"test -e /etc/foo && do-something\""
    up = """
    if { s6-echo starting }
    foreground { helper }
    exit 0
    """

`run`, by contrast, is a file s6-supervise executes, so it **needs a
shebang** and may be in any language. Prefer execline:

    run = """
    #!/usr/bin/execlineb -P

    fdmove -c 2 1
    exec -c
    foo-daemon --nodaemon
    """

## Conventions for supervised processes

  * the daemon must not fork - pass whatever `--nodaemon`, `--foreground` or
    `-D` option it has, so s6 supervises the process itself
  * log to stdout rather than syslog and let a logger service handle it;
    `fdmove -c 2 1` folds stderr in
  * `exec -c` clears the environment before the final exec

## An example

    [dbus-srv]
    flag-recommended = true
    type = "longrun"
    dependencies = [
        "populate-volatile",
    ]
    notification-fd = 3
    producer-for = "dbus-log"
    run = """
    #!/usr/bin/execlineb -P

    fdmove -c 2 1
    dbus-daemon --system --nofork --nosyslog --nopidfile --print-pid=3
    """

    [dbus-log]
    flag-recommended = true
    type = "longrun"
    consumer-for = ["dbus-srv"]
    dependencies = [
        "populate-volatile",
    ]
    notification-fd = 3
    pipeline-name = "dbus-pipeline"
    run = """
    #!/usr/bin/execlineb -P

    s6-log -d3 -- t n1 /var/log/dbus
    """

Both halves of the pipeline are flagged: a service which runs at boot needs
its logger to run at boot, and a set in which an active service depends on
one that is merely usable is inconsistent.

Every logger in this layer uses the same script: `t` for tai64n timestamps,
`n1` to keep one rotated file, and s6-log's own defaults for everything else.
`/var/log` is usually a tmpfs, so a logging directory costs the size of the
current file plus its one archive - a little under 200K with the default
99999 byte file size. Raise it for a service which genuinely needs the
history, rather than everywhere.

## Prescriptions, not bundles

Nothing needs adding to a bundle to make it start at boot. The set started at
boot is generated from the essential and recommended flags when the set is
committed, so flag the service and its dependencies, and leave bundles for
grouping things an administrator wants to act on together.
