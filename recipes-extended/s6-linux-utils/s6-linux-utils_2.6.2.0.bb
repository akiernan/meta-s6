SUMMARY = "s6-linux-utils - tiny Linux-specific utilities"
HOMEPAGE = "https://skarnet.org/software/s6-linux-utils/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=34f73ee8aab2e0ca56980313bfd7a7bb"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/s6-linux-utils/s6-linux-utils-${PV}.tar.gz"
SRC_URI[sha256sum] = "8f944633ca87d3d23e0f03c9c383d1502d508c9bac16d38c3fbf723a5eab2bb7"

inherit skarnet

BBCLASSEXTEND = "native"
