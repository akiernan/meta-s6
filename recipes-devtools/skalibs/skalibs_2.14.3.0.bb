SUMMARY = "skalibs is a C library used by all the skarnet.org packages"
HOMEPAGE = "https://skarnet.org/software/skalibs/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=c92b5c6593e97d6cc9bcb4892128e2b8"

SRC_URI = "https://skarnet.org/software/skalibs/skalibs-${PV}.tar.gz"
SRC_URI[sha256sum] = "a14aa558c9b09b062fa16acec623b2c8f93d69f5cba4d07f6d0c58913066c427"

EXTRA_OECONF = "--with-sysdep-devurandom=yes --with-sysdep-posixspawnearlyreturn=no \
                --with-default-path=${bindir}${@bb.utils.contains('DISTRO_FEATURES','usrmerge','',':${base_bindir}',d)}"

PACKAGECONFIG ?= "${@bb.utils.filter('DISTRO_FEATURES', 'ipv6', d)}"
PACKAGECONFIG[ipv6] = "--enable-ipv6,--disable-ipv6"

inherit skarnet

FILES:${PN}-dev += "${libdir}/${BPN}/sysdeps"

BBCLASSEXTEND = "native"
