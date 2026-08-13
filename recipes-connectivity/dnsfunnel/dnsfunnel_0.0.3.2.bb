SUMMARY = "dnsfunnel - a small local DNS forwarder"
HOMEPAGE = "https://skarnet.org/software/dnsfunnel/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=adbcdd6b69a8d27f4c9e26b61bc961b0"

DEPENDS = "skalibs s6-dns"

SRC_URI = "https://skarnet.org/software/dnsfunnel/dnsfunnel-${PV}.tar.gz"
SRC_URI[sha256sum] = "aa44a1680a5cba44bf23315661a3328c918ab83ffdd25a13e262ef21ed81a10b"

inherit skarnet
