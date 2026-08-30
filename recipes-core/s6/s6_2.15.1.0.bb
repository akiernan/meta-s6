SUMMARY = "s6 - a process supervision suite"
HOMEPAGE = "https://skarnet.org/software/s6/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=ea5b4b9a81f21793db1a769bee7302f3"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/s6/s6-${PV}.tar.gz"
SRC_URI[sha256sum] = "eab9c46e22b66b16135f9a05ec68a0ea287d9060b84d10defaaa2caad158ab52"

PACKAGECONFIG ?= "execline"
PACKAGECONFIG[execline] = "--enable-execline,--disable-execline,execline"
PACKAGECONFIG[nsss] = "--enable-nsss,--disable-nsss,nsss"

# Emulation of the daemontools and runit command sets, and the symlinks that
# expose them under their original names. All three are off here, and the runit
# one deliberately so: it installs binaries called runit, sv, chpst and runsv,
# which collide head-on with a system running actual runit. Upstream defaults
# the emulations off and the symlinks on, so the symlinks are inert until an
# emulation is built - but a distro that enables one wants to have decided
# about the other, rather than inheriting it.
PACKAGECONFIG[aliases-daemontools] = "--enable-aliases-daemontools,--disable-aliases-daemontools"
PACKAGECONFIG[aliases-runit] = "--enable-aliases-runit,--disable-aliases-runit"
PACKAGECONFIG[aliases-symlinks] = "--enable-aliases-symlinks,--disable-aliases-symlinks"

# Hardcodes BINDIR/foo into the binaries instead of resolving through PATH.
PACKAGECONFIG[absolute-paths] = "--enable-absolute-paths,--disable-absolute-paths"

inherit skarnet

# Split along skarnet's own categories, so an image can take the parts it
# needs. A device that wants s6-log and ucspilogd for their timestamps should
# not also acquire a supervision suite, particularly if it already runs one.
#
# ${PN} keeps the shared library and depends on all of them, so anything that
# installs "s6" keeps getting the whole suite as before.
PACKAGE_BEFORE_PN = " \
    ${PN}-supervision \
    ${PN}-notification \
    ${PN}-utils \
    ${PN}-fifodir \
    ${PN}-ipc \
    ${PN}-sudo \
    ${PN}-logging \
    ${PN}-usertree \
    ${PN}-instance \
    ${PN}-fdholder \
    ${PN}-aliases \
"

SUMMARY:${PN}-supervision = "s6 supervision system"
FILES:${PN}-supervision = " \
    ${bindir}/s6-svscan ${bindir}/s6-supervise ${bindir}/s6-svscanctl \
    ${bindir}/s6-svc ${bindir}/s6-svscanboot ${bindir}/s6-svok \
    ${bindir}/s6-svstat ${bindir}/s6-svperms ${bindir}/s6-svlink \
    ${bindir}/s6-svunlink ${bindir}/s6-svdt ${bindir}/s6-svdt-clear \
    ${bindir}/s6-permafailon ${bindir}/s6-background-watch \
"

SUMMARY:${PN}-notification = "Readiness notification for supervised services"
FILES:${PN}-notification = " \
    ${bindir}/s6-svwait ${bindir}/s6-svlisten1 ${bindir}/s6-svlisten \
    ${bindir}/s6-notifyoncheck ${bindir}/s6-notify-fd-from-socket \
    ${bindir}/s6-notify-socket-from-fd \
"

SUMMARY:${PN}-utils = "Misc utilities for run scripts"
FILES:${PN}-utils = " \
    ${bindir}/s6-envdir ${bindir}/s6-envuidgid ${bindir}/s6-fghack \
    ${bindir}/s6-setlock ${bindir}/s6-setsid ${bindir}/s6-setuidgid \
    ${bindir}/s6-applyuidgid ${bindir}/s6-softlimit ${bindir}/s6-tai64n \
    ${bindir}/s6-tai64nlocal \
"

SUMMARY:${PN}-fifodir = "Fifodir management, notification and subscription"
FILES:${PN}-fifodir = " \
    ${bindir}/s6-mkfifodir ${bindir}/s6-cleanfifodir ${bindir}/s6-ftrig-notify \
    ${bindir}/s6-ftrig-wait ${bindir}/s6-ftrig-listen1 ${bindir}/s6-ftrig-listen \
    ${libexecdir}/s6-ftrigrd \
"

SUMMARY:${PN}-ipc = "Unix domain super-server and access control"
FILES:${PN}-ipc = " \
    ${bindir}/s6-ipcclient ${bindir}/s6-ipcserver \
    ${bindir}/s6-ipcserver-socketbinder ${bindir}/s6-ipcserverd \
    ${bindir}/s6-ioconnect ${bindir}/s6-ipcserver-access ${bindir}/s6-connlimit \
    ${bindir}/s6-accessrules-cdb-from-fs ${bindir}/s6-accessrules-fs-from-cdb \
"

SUMMARY:${PN}-sudo = "suidless privilege gain"
FILES:${PN}-sudo = "${bindir}/s6-sudo ${bindir}/s6-sudoc ${bindir}/s6-sudod"

SUMMARY:${PN}-logging = "s6 logging tools"
FILES:${PN}-logging = "${bindir}/s6-log ${bindir}/s6-socklog ${bindir}/ucspilogd"

SUMMARY:${PN}-usertree = "Management of user supervision trees"
FILES:${PN}-usertree = "${bindir}/s6-usertree-maker"

SUMMARY:${PN}-instance = "Management of dynamic instances"
FILES:${PN}-instance = " \
    ${bindir}/s6-instance-maker ${bindir}/s6-instance-create \
    ${bindir}/s6-instance-delete ${bindir}/s6-instance-control \
    ${bindir}/s6-instance-status ${bindir}/s6-instance-list \
"

SUMMARY:${PN}-fdholder = "fd-holding, the sensible part of socket activation"
FILES:${PN}-fdholder = " \
    ${bindir}/s6-fdholder-daemon ${bindir}/s6-fdholderd \
    ${bindir}/s6-fdholder-store ${bindir}/s6-fdholder-retrieve \
    ${bindir}/s6-fdholder-delete ${bindir}/s6-fdholder-list \
    ${bindir}/s6-fdholder-getdump ${bindir}/s6-fdholder-setdump \
    ${bindir}/s6-fdholder-transferdump \
"

# Only populated when an aliases-* PACKAGECONFIG is on. Separate so that
# enabling the emulation does not silently drop runit-named binaries into an
# image that installs s6 for something else entirely.
SUMMARY:${PN}-aliases = "daemontools and runit command emulation"
FILES:${PN}-aliases = " \
    ${bindir}/s6-alias ${bindir}/s6-alias-sv ${bindir}/s6-alias-chpst \
"

# s6-svscanboot, s6-usertree-maker and s6-instance-maker are execline scripts
# when built with execline, and the shebang is not one OE's dependency scanner
# recognises.
S6_EXECLINE_RDEP = "${@bb.utils.contains('PACKAGECONFIG', 'execline', 'execline', '', d)}"
RDEPENDS:${PN}-supervision += "${S6_EXECLINE_RDEP}"
RDEPENDS:${PN}-usertree += "${S6_EXECLINE_RDEP}"
RDEPENDS:${PN}-instance += "${S6_EXECLINE_RDEP}"

# The shared library stays in ${PN} rather than a package of its own:
# debian.bbclass renames every package in PACKAGES by substring as soon as one
# of them holds a shared library and no binaries, which turned s6-logging into
# libs6auto-logging when it was tried.
ALLOW_EMPTY:${PN}-aliases = "1"
RDEPENDS:${PN} = " \
    ${PN}-supervision ${PN}-notification ${PN}-utils ${PN}-fifodir \
    ${PN}-ipc ${PN}-sudo ${PN}-logging ${PN}-usertree ${PN}-instance \
    ${PN}-fdholder \
"

BBCLASSEXTEND = "native"
