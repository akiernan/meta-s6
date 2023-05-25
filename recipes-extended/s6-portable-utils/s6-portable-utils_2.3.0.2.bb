SUMMARY = "s6-portable-utils - tiny general Unix utilities"
HOMEPAGE = "https://skarnet.org/software/s6-portable-utils/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=34f73ee8aab2e0ca56980313bfd7a7bb"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/s6-portable-utils/s6-portable-utils-${PV}.tar.gz"
SRC_URI[sha256sum] = "8714269134f012650daac040e5646372dea5658b9c0d89a7ca03bb2abec4d1e8"

inherit skarnet

BBCLASSEXTEND = "native"
