SUMMARY = "s6-linux-utils - tiny Linux-specific utilities"
HOMEPAGE = "https://skarnet.org/software/s6-linux-utils/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=d096eb937732001e90b6c48fe07906c0"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/s6-linux-utils/s6-linux-utils-${PV}.tar.gz"
SRC_URI[sha256sum] = "86a8c2df4bf13e17fb04b86b60dd2ab355f47bc4bf3c3821637cf9f599addfd8"

inherit skarnet

BBCLASSEXTEND = "native"
