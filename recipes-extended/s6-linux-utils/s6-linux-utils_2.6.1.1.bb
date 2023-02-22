SUMMARY = "s6-linux-utils - tiny Linux-specific utilities"
HOMEPAGE = "https://skarnet.org/software/s6-linux-utils/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=34f73ee8aab2e0ca56980313bfd7a7bb"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/s6-linux-utils/s6-linux-utils-${PV}.tar.gz"
SRC_URI[sha256sum] = "d69a0971a1bbe230154a6045531cffdd0eef705bbeebca69f5cdcbe3ac637da9"

inherit skarnet

BBCLASSEXTEND = "native"
