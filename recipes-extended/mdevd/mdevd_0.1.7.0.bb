SUMMARY = "mdevd - a mdev-compatible hotplug manager daemon"
HOMEPAGE = "https://skarnet.org/software/mdevd/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=65002497793ffd12ad4c6062c84d6ff7"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/mdevd/mdevd-${PV}.tar.gz"
SRC_URI[sha256sum] = "ec966eec39879f33c785343373021c44f887c836a08fcaf1d63412e3bdbfca32"

PACKAGECONFIG ?= ""
PACKAGECONFIG[nsss] = "--enable-nsss,--disable-nsss,nsss"

inherit skarnet

RRECOMMENDS:${PN} = "execline"
