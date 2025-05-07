SUMMARY = "dnsfunnel - a small local DNS forwarder"
HOMEPAGE = "https://skarnet.org/software/dnsfunnel/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=0a096505aff4ea374910e3639b9aed19"

DEPENDS = "skalibs s6-dns"

SRC_URI = "https://skarnet.org/software/dnsfunnel/dnsfunnel-${PV}.tar.gz"
SRC_URI[sha256sum] = "8d96e3bfc6483e4b2f30a26320343ec93fd67438656489a871ce99144526e05f"

inherit skarnet
