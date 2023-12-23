SUMMARY = "s6-networking - small network and client-server tools"
HOMEPAGE = "https://skarnet.org/software/s6-networking/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=34f73ee8aab2e0ca56980313bfd7a7bb"

DEPENDS = "skalibs s6 s6-dns"

SRC_URI = "https://skarnet.org/software/s6-networking/s6-networking-${PV}.tar.gz"
SRC_URI[sha256sum] = "dfa4964d4f1bdae9ab5fc4508769fd6fefc33a527d5153a3777c6b046eeea564"

PACKAGECONFIG ?= "execline openssl"
PACKAGECONFIG[bearssl] = "--enable-ssl=bearssl,,bearssl"
PACKAGECONFIG[execline] = "--enable-execline,--disable-execline,execline"
PACKAGECONFIG[openssl] = "--enable-ssl=libtls,,libretls"

EXTRA_OECONF += "${@'--disable-ssl' if (bb.utils.filter('PACKAGECONFIG', 'bearssl openssl', d) == '') else ''}"

inherit skarnet

BBCLASSEXTEND = "native"
