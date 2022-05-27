SUMMARY = "s6 - a process supervision suite"
HOMEPAGE = "https://skarnet.org/software/s6/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=d096eb937732001e90b6c48fe07906c0"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/s6/s6-${PV}.tar.gz"
SRC_URI[sha256sum] = "ae64dc2ba208ff80e4ac4792ce90dd526b42bf19c966dc7d8eb9a6870e4bc23a"

PACKAGECONFIG = "execline"
PACKAGECONFIG[execline] = "--enable-execline,--disable-execline,execline"

inherit skarnet

BBCLASSEXTEND = "native"
