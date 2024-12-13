SUMMARY = "mdevd - a mdev-compatible hotplug manager daemon"
HOMEPAGE = "https://skarnet.org/software/mdevd/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=7eeaa4e68fa0d5966d994e8083b29f44"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/mdevd/mdevd-${PV}.tar.gz"
SRC_URI[sha256sum] = "d5c86420c81b2e4720b41e4f48f727cd5d9a17f4e6317115d21c32099e72a628"

PACKAGECONFIG ?= ""
PACKAGECONFIG[nsss] = "--enable-nsss,--disable-nsss,nsss"

inherit skarnet

RRECOMMENDS:${PN} = "execline"
