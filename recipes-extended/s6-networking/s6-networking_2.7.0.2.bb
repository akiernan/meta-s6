SUMMARY = "s6-networking - small network and client-server tools"
HOMEPAGE = "https://skarnet.org/software/s6-networking/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=c92b5c6593e97d6cc9bcb4892128e2b8"

DEPENDS = "skalibs s6 s6-dns"

SRC_URI = "https://skarnet.org/software/s6-networking/s6-networking-${PV}.tar.gz"
SRC_URI[sha256sum] = "c33c6f1b2be16f89a21af946cfd06242a12f98184c898b755ddb24338671ceb1"

PACKAGECONFIG ?= "execline openssl"
PACKAGECONFIG[bearssl] = "--enable-ssl=bearssl,,bearssl"
PACKAGECONFIG[execline] = "--enable-execline,--disable-execline,execline"
PACKAGECONFIG[openssl] = "--enable-ssl=libtls,,libretls"

EXTRA_OECONF += "${@'--disable-ssl' if (bb.utils.filter('PACKAGECONFIG', 'bearssl openssl', d) == '') else ''}"

inherit skarnet

BBCLASSEXTEND = "native"
