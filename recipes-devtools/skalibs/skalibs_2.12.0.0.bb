SUMMARY = "skalibs is a C library used by all the skarnet.org packages"
HOMEPAGE = "https://skarnet.org/software/skalibs/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=41280dbee09dab174bbebae98f1fdb47"

SRC_URI = "https://skarnet.org/software/skalibs/skalibs-${PV}.tar.gz"
SRC_URI[sha256sum] = "e6d724b4c628f093df75c98f1274d8bd6c0ecdb09cc6816d3268bacb58647f30"

EXTRA_OECONF = "--with-sysdep-devurandom=yes"

PACKAGECONFIG ?= "${@bb.utils.filter('DISTRO_FEATURES', 'ipv6', d)}"
PACKAGECONFIG[ipv6] = "--enable-ipv6,--disable-ipv6"

inherit skarnet

FILES:${PN}-dev += "${libdir}/${BPN}/sysdeps"

BBCLASSEXTEND = "native"
