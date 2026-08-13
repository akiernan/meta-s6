SUMMARY = "s6-portable-utils - tiny general Unix utilities"
HOMEPAGE = "https://skarnet.org/software/s6-portable-utils/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=ea5b4b9a81f21793db1a769bee7302f3"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/s6-portable-utils/s6-portable-utils-${PV}.tar.gz"
SRC_URI[sha256sum] = "cfb90186d0c0eb204e1e5c6f9379e99413c546bccf38bb6e76177f82371aa3aa"

PACKAGECONFIG ?= "multicall"
PACKAGECONFIG[multicall] = "--enable-multicall,--disable-multicall"

inherit skarnet

BBCLASSEXTEND = "native"
