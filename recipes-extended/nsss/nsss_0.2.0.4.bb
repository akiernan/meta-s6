SUMMARY = "nsssd - a secure NSS-like implementation for small libcs"
HOMEPAGE = "https://skarnet.org/software/nsss/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=3d51c36fc24946e3b9febf4e348aac97"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/nsss/nsss-${PV}.tar.gz"
SRC_URI[sha256sum] = "39b504f85bd8f6b523d334e5cfa62c02a395db35991b75f206df0abbd6761aad"

inherit skarnet

BBCLASSEXTEND = "native"
