SUMMARY = "s6-linux-utils - tiny Linux-specific utilities"
HOMEPAGE = "https://skarnet.org/software/s6-linux-utils/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=ea5b4b9a81f21793db1a769bee7302f3"

DEPENDS = "skalibs execline"

SRC_URI = "https://skarnet.org/software/s6-linux-utils/s6-linux-utils-${PV}.tar.gz"
SRC_URI[sha256sum] = "16e1a5b5a2b4a98674b4a9719612adb79588e3c20c4083360278ea38f2d32129"

EXTRA_OECONF = "--with-seed-dir=${localstatedir}/lib/rngseed"

PACKAGECONFIG ?= "multicall"
PACKAGECONFIG[multicall] = "--enable-multicall,--disable-multicall"
PACKAGECONFIG[nsss] = "--enable-nsss,--disable-nsss,nsss"

inherit skarnet

BBCLASSEXTEND = "native"
