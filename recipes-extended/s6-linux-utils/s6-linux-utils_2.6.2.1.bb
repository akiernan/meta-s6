SUMMARY = "s6-linux-utils - tiny Linux-specific utilities"
HOMEPAGE = "https://skarnet.org/software/s6-linux-utils/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=c92b5c6593e97d6cc9bcb4892128e2b8"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/s6-linux-utils/s6-linux-utils-${PV}.tar.gz"
SRC_URI[sha256sum] = "915f167294d36ca9240a62281c7299b5ad5d558b8d45d888761135749d9fd234"

EXTRA_OECONF = "--with-seed-dir=${localstatedir}/lib/rngseed"

PACKAGECONFIG ?= "multicall"
PACKAGECONFIG[multicall] = "--enable-multicall,--disable-multicall"
PACKAGECONFIG[nsss] = "--enable-nsss,--disable-nsss,nsss"

inherit skarnet

BBCLASSEXTEND = "native"
