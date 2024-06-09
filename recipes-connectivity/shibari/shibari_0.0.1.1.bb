SUMMARY = "shibari - a collection of DNS tools"
HOMEPAGE = "https://skarnet.org/software/shibari/"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=c0cd202fb2a4f577c68e43b50d696a7b"

DEPENDS = "skalibs s6 s6-dns"

SRC_URI = "https://skarnet.org/software/shibari/shibari-${PV}.tar.gz"
SRC_URI[sha256sum] = "08fe84bcf7bf4cce8598302b5e555d30d79cbc3b811087e0c61c59a518cb0f78"

inherit skarnet
