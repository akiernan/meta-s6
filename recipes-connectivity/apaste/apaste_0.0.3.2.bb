SUMMARY = "apaste - a small command-line pastebin"
HOMEPAGE = "https://skarnet.org/software/apaste/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=8e66d5e0bd4955c7492ca635112b491d"

DEPENDS = "skalibs s6-networking"

SRC_URI = "https://skarnet.org/software/apaste/apaste-${PV}.tar.gz"
SRC_URI[sha256sum] = "aaeb5ef5f5ece7764851892b2ad100c073a245d41aba6c184ee64359a4be4503"

inherit skarnet

RDEPENDS:${PN} += "s6-networking"
