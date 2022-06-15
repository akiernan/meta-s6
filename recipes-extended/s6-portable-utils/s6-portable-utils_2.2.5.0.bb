SUMMARY = "s6-portable-utils - tiny general Unix utilities"
HOMEPAGE = "https://skarnet.org/software/s6-portable-utils/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=41280dbee09dab174bbebae98f1fdb47"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/s6-portable-utils/s6-portable-utils-${PV}.tar.gz"
SRC_URI[sha256sum] = "ebb39f8934fd36f25d31352e61bbd94dc02b1e9f04411850d2fd9658bd916e36"

inherit skarnet

BBCLASSEXTEND = "native"
