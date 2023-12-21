SUMMARY = "s6-dns - a DNS client suite"
HOMEPAGE = "https://skarnet.org/software/s6-dns/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=34f73ee8aab2e0ca56980313bfd7a7bb"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/s6-dns/s6-dns-${PV}.tar.gz"
SRC_URI[sha256sum] = "cf0258574ec7d62b654e0c2ad78af4c7d67ac4c9cb37f781480f597e54b30f6d"

inherit skarnet

BBCLASSEXTEND = "native"
