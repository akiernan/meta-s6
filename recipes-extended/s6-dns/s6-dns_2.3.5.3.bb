SUMMARY = "s6-dns - a DNS client suite"
HOMEPAGE = "https://skarnet.org/software/s6-dns/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=d096eb937732001e90b6c48fe07906c0"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/s6-dns/s6-dns-${PV}.tar.gz"
SRC_URI[sha256sum] = "2ac42f6771cc95c5e9b4d50e4713c3be51733d0f615dcd6c71ef1d9e059c76da"

inherit skarnet

BBCLASSEXTEND = "native"
