SUMMARY = "dnsfunnel - a small local DNS forwarder"
HOMEPAGE = "https://skarnet.org/software/dnsfunnel/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=b27c09be247a1dc85114dd3da6ba8689"

DEPENDS = "skalibs s6-dns"

SRC_URI = "https://skarnet.org/software/dnsfunnel/dnsfunnel-${PV}.tar.gz"
SRC_URI[sha256sum] = "af846c6aaae0d0f33b6ec48dac3c07407a27e668b5282a17e99c5c7325a799f2"

inherit skarnet
