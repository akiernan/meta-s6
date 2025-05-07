SUMMARY = "s6-dns - a DNS client suite"
HOMEPAGE = "https://skarnet.org/software/s6-dns/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=5c17f5dc8226509a4f72d6be7ae756b4"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/s6-dns/s6-dns-${PV}.tar.gz"
SRC_URI[sha256sum] = "b6308519f109a67469c4aaafa9df1f009ad0961fa798ffc38f895587e6935729"

inherit skarnet

BBCLASSEXTEND = "native"
