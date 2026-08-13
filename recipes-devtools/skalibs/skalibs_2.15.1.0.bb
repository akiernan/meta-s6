SUMMARY = "skalibs is a C library used by all the skarnet.org packages"
HOMEPAGE = "https://skarnet.org/software/skalibs/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=ea5b4b9a81f21793db1a769bee7302f3"

SRC_URI = "https://skarnet.org/software/skalibs/skalibs-${PV}.tar.gz"
SRC_URI[sha256sum] = "f9c905e74935c6fe911c7e344e3e89d5fbd2014c1a04650b524b15ce9b5635d1"

EXTRA_OECONF = "\
    --with-sysdep-devurandom=yes \
    --with-sysdep-posixspawnearlyreturn=no \
    --with-sysdep-procselfexe=/proc/self/exe \
    --with-sysdep-selectinfinite=yes \
    --with-default-path=${bindir}${@bb.utils.contains('DISTRO_FEATURES','usrmerge','',':${base_bindir}',d)} \
"

PACKAGECONFIG ?= "${@bb.utils.filter('DISTRO_FEATURES', 'ipv6', d)}"
PACKAGECONFIG[ipv6] = "--enable-ipv6,--disable-ipv6"

inherit skarnet

FILES:${PN}-dev += "${libdir}/${BPN}/sysdeps"

BBCLASSEXTEND = "native"
