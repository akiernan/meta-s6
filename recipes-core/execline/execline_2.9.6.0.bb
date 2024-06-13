SUMMARY = "execline - an interpreter-less scripting language"
HOMEPAGE = "https://skarnet.org/software/execline/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=c92b5c6593e97d6cc9bcb4892128e2b8"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/execline/execline-${PV}.tar.gz"
SRC_URI[sha256sum] = "ba2a27e97c5eb6bd7ca6a0987a8925e44465a5be996daa0d18f8feca37d7571a"

PACKAGECONFIG ?= "multicall"
PACKAGECONFIG[multicall] = "--enable-multicall,--disable-multicall"

inherit skarnet

BBCLASSEXTEND = "native"
