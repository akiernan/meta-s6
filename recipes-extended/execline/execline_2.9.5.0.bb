SUMMARY = "execline - an interpreter-less scripting language"
HOMEPAGE = "https://skarnet.org/software/execline/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=c92b5c6593e97d6cc9bcb4892128e2b8"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/execline/execline-${PV}.tar.gz"
SRC_URI[sha256sum] = "c1eb0d3a2f4e9f5751452631617a147f532ac2dd4a07c564e33f1612d2de837e"

PACKAGECONFIG ?= "multicall"
PACKAGECONFIG[multicall] = "--enable-multicall,--disable-multicall"

inherit skarnet

BBCLASSEXTEND = "native"
