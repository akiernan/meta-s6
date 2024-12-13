SUMMARY = "nsssd - a secure NSS-like implementation for small libcs"
HOMEPAGE = "https://skarnet.org/software/nsss/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=97a21eece1f23a77df40063449656bcc"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/nsss/nsss-${PV}.tar.gz"
SRC_URI[sha256sum] = "954c1b25791cc36d07c3e123ec03436d3e296bf5233f1d08bc016d6d7e6279d2"

inherit skarnet

BBCLASSEXTEND = "native"
