SUMMARY = "nsssd - a secure NSS-like implementation for small libcs"
HOMEPAGE = "https://skarnet.org/software/nsss/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=bf2e714ed36534bb7976bdbae107223b"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/nsss/nsss-${PV}.tar.gz"
SRC_URI[sha256sum] = "14da444a836d24b6a286fac88a502bdaa296a4d328f09d5297568ad3b3e02685"

inherit skarnet

BBCLASSEXTEND = "native"
