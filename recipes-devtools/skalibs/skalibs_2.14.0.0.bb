SUMMARY = "skalibs is a C library used by all the skarnet.org packages"
HOMEPAGE = "https://skarnet.org/software/skalibs/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=34f73ee8aab2e0ca56980313bfd7a7bb"

SRC_URI = "https://skarnet.org/software/skalibs/skalibs-${PV}.tar.gz"
SRC_URI[sha256sum] = "18d316b5660b59f4171a3853e3aa3abf8cafe7b328fe4be394833d6abc646909"

EXTRA_OECONF = "--with-sysdep-devurandom=yes --with-sysdep-posixspawnearlyreturn=no"

PACKAGECONFIG ?= "${@bb.utils.filter('DISTRO_FEATURES', 'ipv6', d)}"
PACKAGECONFIG[ipv6] = "--enable-ipv6,--disable-ipv6"

inherit skarnet

FILES:${PN}-dev += "${libdir}/${BPN}/sysdeps"

BBCLASSEXTEND = "native"
