SUMMARY = "s6 - a process supervision suite"
HOMEPAGE = "https://skarnet.org/software/s6/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=c92b5c6593e97d6cc9bcb4892128e2b8"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/s6/s6-${PV}.tar.gz"
SRC_URI[sha256sum] = "c95d51787602e0c8c8e4f92a710cbdaa4ee797ee886e71342727e2974f95c06b"

PACKAGECONFIG ?= "execline"
PACKAGECONFIG[execline] = "--enable-execline,--disable-execline,execline"

inherit skarnet

BBCLASSEXTEND = "native"
