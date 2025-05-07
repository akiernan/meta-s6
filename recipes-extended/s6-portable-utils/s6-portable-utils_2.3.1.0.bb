SUMMARY = "s6-portable-utils - tiny general Unix utilities"
HOMEPAGE = "https://skarnet.org/software/s6-portable-utils/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=5c17f5dc8226509a4f72d6be7ae756b4"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/s6-portable-utils/s6-portable-utils-${PV}.tar.gz"
SRC_URI[sha256sum] = "04244aa87ae2c412d4999769b5e73cb428afba0c2e8aa9215b3a36e7be2a80f9"

PACKAGECONFIG ?= "multicall"
PACKAGECONFIG[multicall] = "--enable-multicall,--disable-multicall"

inherit skarnet

BBCLASSEXTEND = "native"
