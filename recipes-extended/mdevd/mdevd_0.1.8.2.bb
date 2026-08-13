SUMMARY = "mdevd - a mdev-compatible hotplug manager daemon"
HOMEPAGE = "https://skarnet.org/software/mdevd/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=00f0789e79a05a58895c51ad18687349"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/mdevd/mdevd-${PV}.tar.gz"
SRC_URI[sha256sum] = "ce1ae0149b6a57a34f608218fd6181aa6aa68135cac2f4d931b5b417b072e244"

PACKAGECONFIG ?= ""
PACKAGECONFIG[nsss] = "--enable-nsss,--disable-nsss,nsss"

inherit skarnet

RRECOMMENDS:${PN} = "execline"
