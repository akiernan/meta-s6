SUMMARY = "s6-dns - a DNS client suite"
HOMEPAGE = "https://skarnet.org/software/s6-dns/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=c92b5c6593e97d6cc9bcb4892128e2b8"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/s6-dns/s6-dns-${PV}.tar.gz"
SRC_URI[sha256sum] = "6aee32bb68d01f5109f71e31a283213c668cd3c0e79fb9e469e6cabb58079f2b"

inherit skarnet

BBCLASSEXTEND = "native"
