SUMMARY = "s6-networking - small network and client-server tools"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=d096eb937732001e90b6c48fe07906c0"

DEPENDS = "skalibs s6 s6-dns"

SRC_URI = "https://skarnet.org/software/s6-networking/s6-networking-${PV}.tar.gz"
SRC_URI[sha256sum] = "2507c51fea5a2d07a7a77300a6502f2af4a04b6da15131f20bf984b99091ff41"

PACKAGECONFIG ??= "execline openssl"
PACKAGECONFIG[execline] = "--enable-execline,--disable-execline,execline"
PACKAGECONFIG[openssl] = "--enable-ssl=libtls,--disable-ssl,libretls"

inherit skarnet

BBCLASSEXTEND = "native"
