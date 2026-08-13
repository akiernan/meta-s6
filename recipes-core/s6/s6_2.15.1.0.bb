SUMMARY = "s6 - a process supervision suite"
HOMEPAGE = "https://skarnet.org/software/s6/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=ea5b4b9a81f21793db1a769bee7302f3"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/s6/s6-${PV}.tar.gz"
SRC_URI[sha256sum] = "eab9c46e22b66b16135f9a05ec68a0ea287d9060b84d10defaaa2caad158ab52"

PACKAGECONFIG ?= "execline"
PACKAGECONFIG[execline] = "--enable-execline,--disable-execline,execline"
PACKAGECONFIG[nsss] = "--enable-nsss,--disable-nsss,nsss"

inherit skarnet

BBCLASSEXTEND = "native"
