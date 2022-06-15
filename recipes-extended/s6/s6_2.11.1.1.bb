SUMMARY = "s6 - a process supervision suite"
HOMEPAGE = "https://skarnet.org/software/s6/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=41280dbee09dab174bbebae98f1fdb47"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/s6/s6-${PV}.tar.gz"
SRC_URI[sha256sum] = "1cef7f7b3a7e01181fbb6fe8300e6ba422d9689007221c78af1f99528acb6c38"

PACKAGECONFIG ?= "execline"
PACKAGECONFIG[execline] = "--enable-execline,--disable-execline,execline"

inherit skarnet

BBCLASSEXTEND = "native"
