SUMMARY = "s6-networking - small network and client-server tools"
HOMEPAGE = "https://skarnet.org/software/s6-networking/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=5c17f5dc8226509a4f72d6be7ae756b4"

DEPENDS = "skalibs s6 s6-dns"

SRC_URI = "https://skarnet.org/software/s6-networking/s6-networking-${PV}.tar.gz"
SRC_URI[sha256sum] = "a7b33497e72921a59d4c1fc67ce5cc74bd065e09105bf1a79f1fc73cf9a06592"

PACKAGECONFIG ?= "execline openssl"
PACKAGECONFIG[bearssl] = "--enable-ssl=bearssl,,bearssl"
PACKAGECONFIG[execline] = "--enable-execline,--disable-execline,execline"
PACKAGECONFIG[openssl] = "--enable-ssl=libtls,,libretls"

EXTRA_OECONF += "${@'--disable-ssl' if (bb.utils.filter('PACKAGECONFIG', 'bearssl openssl', d) == '') else ''}"

inherit skarnet

BBCLASSEXTEND = "native"
