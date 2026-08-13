SUMMARY = "s6-networking - small network and client-server tools"
HOMEPAGE = "https://skarnet.org/software/s6-networking/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=ea5b4b9a81f21793db1a769bee7302f3"

DEPENDS = "skalibs s6 s6-dns"

SRC_URI = "https://skarnet.org/software/s6-networking/s6-networking-${PV}.tar.gz"
SRC_URI[sha256sum] = "6f011c33ba0586ce58feee0cf854a0b087e9082fdbd24abb786148463830f341"

PACKAGECONFIG ?= "execline openssl"
PACKAGECONFIG[bearssl] = "--enable-ssl=bearssl,,bearssl"
PACKAGECONFIG[execline] = "--enable-execline,--disable-execline,execline"
PACKAGECONFIG[openssl] = "--enable-ssl=libtls,,libretls"

EXTRA_OECONF += "${@'--disable-ssl' if (bb.utils.filter('PACKAGECONFIG', 'bearssl openssl', d) == '') else ''}"

inherit skarnet

BBCLASSEXTEND = "native"
