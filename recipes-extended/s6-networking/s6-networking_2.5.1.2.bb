SUMMARY = "s6-networking - small network and client-server tools"
HOMEPAGE = "https://skarnet.org/software/s6-networking/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=34f73ee8aab2e0ca56980313bfd7a7bb"

DEPENDS = "skalibs s6 s6-dns"

SRC_URI = "https://skarnet.org/software/s6-networking/s6-networking-${PV}.tar.gz"
SRC_URI[sha256sum] = "d072deb67a4ca4d7c512640c5f33a766c7b854364949727d3600738c6769f146"

PACKAGECONFIG ?= "execline openssl"
PACKAGECONFIG[execline] = "--enable-execline,--disable-execline,execline"
PACKAGECONFIG[openssl] = "--enable-ssl=libtls,--disable-ssl,libretls"

inherit skarnet

BBCLASSEXTEND = "native"
