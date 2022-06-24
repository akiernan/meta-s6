SUMMARY = "skalibs is a C library used by all the skarnet.org packages"
HOMEPAGE = "https://skarnet.org/software/skalibs/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=41280dbee09dab174bbebae98f1fdb47"

SRC_URI = "https://skarnet.org/software/skalibs/skalibs-${PV}.tar.gz"
SRC_URI[sha256sum] = "3e228f72f18d88c17f6c4e0a66881d6d3779427b7e7e889f3142b6f26da30285"

EXTRA_OECONF = "--with-sysdep-devurandom=yes"

PACKAGECONFIG ?= "${@bb.utils.filter('DISTRO_FEATURES', 'ipv6', d)}"
PACKAGECONFIG[ipv6] = "--enable-ipv6,--disable-ipv6"

inherit skarnet

FILES:${PN}-dev += "${libdir}/${BPN}/sysdeps"

BBCLASSEXTEND = "native"
