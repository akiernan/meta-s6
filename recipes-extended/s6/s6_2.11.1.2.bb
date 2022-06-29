SUMMARY = "s6 - a process supervision suite"
HOMEPAGE = "https://skarnet.org/software/s6/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=41280dbee09dab174bbebae98f1fdb47"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/s6/s6-${PV}.tar.gz"
SRC_URI[sha256sum] = "6c1474be3e889dac392cee307abe015cd4be0c85c725c84ea7f184f0e34503a2"

PACKAGECONFIG ?= "execline"
PACKAGECONFIG[execline] = "--enable-execline,--disable-execline,execline"

inherit skarnet

BBCLASSEXTEND = "native"
