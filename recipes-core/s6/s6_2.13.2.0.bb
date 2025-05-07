SUMMARY = "s6 - a process supervision suite"
HOMEPAGE = "https://skarnet.org/software/s6/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=5c17f5dc8226509a4f72d6be7ae756b4"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/s6/s6-${PV}.tar.gz"
SRC_URI[sha256sum] = "c5114b8042716bb70691406931acb0e2796d83b41cbfb5c8068dce7a02f99a45"

PACKAGECONFIG ?= "execline"
PACKAGECONFIG[execline] = "--enable-execline,--disable-execline,execline"
PACKAGECONFIG[nsss] = "--enable-nsss,--disable-nsss,nsss"

inherit skarnet

BBCLASSEXTEND = "native"
