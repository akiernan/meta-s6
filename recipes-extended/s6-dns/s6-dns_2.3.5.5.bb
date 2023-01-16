SUMMARY = "s6-dns - a DNS client suite"
HOMEPAGE = "https://skarnet.org/software/s6-dns/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=34f73ee8aab2e0ca56980313bfd7a7bb"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/s6-dns/s6-dns-${PV}.tar.gz"
SRC_URI[sha256sum] = "56979b5d5125c38071a80b5e3df0d4a6b2a7c52bb863a2410b6e3d797ffe1ee8"

inherit skarnet

BBCLASSEXTEND = "native"
