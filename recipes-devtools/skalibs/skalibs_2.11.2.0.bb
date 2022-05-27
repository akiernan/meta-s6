SUMMARY = "skalibs is a C library used by all the skarnet.org packages"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=d096eb937732001e90b6c48fe07906c0"

SRC_URI = "https://skarnet.org/software/skalibs/skalibs-${PV}.tar.gz"
SRC_URI[sha256sum] = "649cf3236fe3103f45366b6196b1bcd0457c9c17ca86f2b80007696a2baa7b77"

EXTRA_OECONF = "--with-sysdep-devurandom=yes"

PACKAGECONFIG ?= "${@bb.utils.filter('DISTRO_FEATURES', 'ipv6', d)}"
PACKAGECONFIG[ipv6] = "--enable-ipv6,--disable-ipv6"

inherit skarnet

FILES_${PN}-dev += "${libdir}/${BPN}/sysdeps"

BBCLASSEXTEND = "native"
