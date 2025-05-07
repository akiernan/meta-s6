SUMMARY = "nsssd - a secure NSS-like implementation for small libcs"
HOMEPAGE = "https://skarnet.org/software/nsss/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=4b65b4eb09bd509d4ad707a0eb42ff61"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/nsss/nsss-${PV}.tar.gz"
SRC_URI[sha256sum] = "f221a31c1cee881e992a86dfe296332253c7a9fc65f60d88829cc8e8948420f4"

inherit skarnet

BBCLASSEXTEND = "native"
