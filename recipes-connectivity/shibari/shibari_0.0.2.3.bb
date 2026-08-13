SUMMARY = "shibari - a collection of DNS tools"
HOMEPAGE = "https://skarnet.org/software/shibari/"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=7463eff70700c0f79394bcff8174cb58"

DEPENDS = "skalibs s6 s6-dns"

SRC_URI = "https://skarnet.org/software/shibari/shibari-${PV}.tar.gz"
SRC_URI[sha256sum] = "663b6d902b078bd84dfae0489fd9d32fad18f023456a38f7cfdcfe1972dab349"

inherit skarnet
