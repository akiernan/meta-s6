SUMMARY = "dnsfunnel - a small local DNS forwarder"
HOMEPAGE = "https://skarnet.org/software/dnsfunnel/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=4bf3ab30e438cf2c2cfcce49efd84508"

DEPENDS = "skalibs s6-dns"

SRC_URI = "https://skarnet.org/software/dnsfunnel/dnsfunnel-${PV}.tar.gz"
SRC_URI[sha256sum] = "e22934baf4b7e48fed16d51b0e544971ccee2528d6ea12fcacf097ba816579b0"

inherit skarnet
