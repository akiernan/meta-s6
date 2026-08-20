# meta-s6

A Yocto layer packaging the skarnet.org software - s6, s6-rc, s6-linux-init
and friends - and the service definitions to boot a system with them.

## Avoid shell helpers - write execline

Scripting in this layer is execline by default. Every image that uses this
layer already has execline, s6-portable-utils and s6-linux-utils on it, so a
`#!/bin/sh` script is a dependency on something else and a second language for
the reader to hold in their head.

That covers service definitions - `run`, `up`, `down`, `finish` - and helper
programs the layer installs. `up` and `down` are lexed by execlineb at compile
time and are implicitly execline, so they must never carry a shebang; `run` is
a file and needs `#!/usr/bin/execlineb -P`.

Reach for the s6 tools before assuming a POSIX one is needed:

    s6-grep     match a pattern on stdin, including -q for a plain test
    s6-mount    mount, without util-linux
    s6-mkdir    s6-mkfifo, s6-touch, s6-chown, s6-chmod, s6-ln, s6-rmrf
    s6-cat      s6-head, s6-tail, s6-cut, s6-sort, s6-ls, s6-echo
    eltest      the test(1) of execline
    elglob      globbing, with -0 for "no match means no words"
    forx        loop over a list, forstdin over standard input
    backtick    capture output into a variable, with -D for a default
    if / ifelse / foreground / background / redirfd / importas / export

Departing from this is fine when there is a clear reason - say so in a comment
where the code is, not in a commit message. Good reasons look like: the logic
genuinely needs a parser that already exists (leave sysctl.conf to `sysctl -p`
rather than reimplementing its format), or the script is shared verbatim with
something outside this layer. "It was quicker to write in shell" is not one.

## Other things worth knowing

Service definitions are documented in [docs/service-definitions.md](docs/service-definitions.md),
including the TOML convention, which is local to this layer and not something
any skarnet tool knows about. Bare s6-rc source definition directories are
equally valid and can be mixed with the TOML freely.

Bbappends live under `bbappends/<collection>/` and are wired up with
`BBFILES_DYNAMIC`, so they are inert unless that layer is present. Each one is
a gate that requires a `_s6-rc.inc` holding the actual content, so a build
which is not using s6-rc sees an empty append and unchanged signatures.
`FILESEXTRAPATHS` is the exception that stays in the bbappend, because
`THISDIR` has to resolve there - it is hash-excluded, so it costs nothing.

Prescriptions, not bundles: a service is started at boot because it is flagged
`flag-essential` or `flag-recommended`, not because something lists it. Leave
a service unflagged to make it opt-in.
