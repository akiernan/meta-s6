SUMMARY = "execline - an interpreter-less scripting language"
HOMEPAGE = "https://skarnet.org/software/execline/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=c92b5c6593e97d6cc9bcb4892128e2b8"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/execline/execline-${PV}.tar.gz"
SRC_URI[sha256sum] = "df750035d0fb21c7265bffb7ed7e1b661de1e842944a2252bdcddc32d0d97217"

PACKAGECONFIG ?= "multicall"
PACKAGECONFIG[multicall] = "--enable-multicall,--disable-multicall"

inherit skarnet

BBCLASSEXTEND = "native"
