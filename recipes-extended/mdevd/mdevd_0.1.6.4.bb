SUMMARY = "mdevd - a mdev-compatible hotplug manager daemon"
HOMEPAGE = "https://skarnet.org/software/mdevd/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=7eeaa4e68fa0d5966d994e8083b29f44"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/mdevd/mdevd-${PV}.tar.gz"
SRC_URI[sha256sum] = "7358ce530b6b9befb715e48190981e9b69a12cca858519e6d2e4b46eab7e2470"

PACKAGECONFIG ?= ""
PACKAGECONFIG[nsss] = "--enable-nsss,--disable-nsss,nsss"

inherit skarnet

RRECOMMENDS:${PN} = "execline"
