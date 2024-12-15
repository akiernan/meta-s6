SUMMARY = "s6-portable-utils - tiny general Unix utilities"
HOMEPAGE = "https://skarnet.org/software/s6-portable-utils/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=c92b5c6593e97d6cc9bcb4892128e2b8"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/s6-portable-utils/s6-portable-utils-${PV}.tar.gz"
SRC_URI[sha256sum] = "8dee87b6c89181ef3ed3241fece43f8d79aa137449cae778d3b59e1e91906b08"

PACKAGECONFIG ?= "multicall"
PACKAGECONFIG[multicall] = "--enable-multicall,--disable-multicall"

inherit skarnet

BBCLASSEXTEND = "native"
