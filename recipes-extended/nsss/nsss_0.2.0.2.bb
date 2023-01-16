SUMMARY = "nsssd - a secure NSS-like implementation for small libcs"
HOMEPAGE = "https://skarnet.org/software/nsss/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=3d51c36fc24946e3b9febf4e348aac97"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/nsss/nsss-${PV}.tar.gz"
SRC_URI[sha256sum] = "7ba5ea9a2b528e83612750e4891cd9ba56d5c44f530abc42453a8b9390a6c2ec"

inherit skarnet

BBCLASSEXTEND = "native"
