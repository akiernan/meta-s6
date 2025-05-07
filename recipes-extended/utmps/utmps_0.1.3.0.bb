SUMMARY = "utmps - a secure utmpx and wtmp implementation"
HOMEPAGE = "https://skarnet.org/software/utmps/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=4b65b4eb09bd509d4ad707a0eb42ff61"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/utmps/utmps-${PV}.tar.gz"
SRC_URI[sha256sum] = "b4a8a7864e51ab05f0f8d50a85dc0634fffecc22ec85f293774a79697f3b1d49"

inherit skarnet

RRECOMMENDS:${PN} = "s6"
