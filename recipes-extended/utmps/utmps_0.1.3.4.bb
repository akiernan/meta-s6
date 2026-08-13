SUMMARY = "utmps - a secure utmpx and wtmp implementation"
HOMEPAGE = "https://skarnet.org/software/utmps/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=bf2e714ed36534bb7976bdbae107223b"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/utmps/utmps-${PV}.tar.gz"
SRC_URI[sha256sum] = "12dcc102ad6ac81f81aecc4950733db94f35081947054b9b1473cac5c90810bc"

inherit skarnet

RRECOMMENDS:${PN} = "s6"
