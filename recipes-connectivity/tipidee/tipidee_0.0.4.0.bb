SUMMARY = "tipidee - a minimalistic web server"
HOMEPAGE = "https://skarnet.org/software/tipidee/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=c0cd202fb2a4f577c68e43b50d696a7b"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/tipidee/tipidee-${PV}.tar.gz"
SRC_URI[sha256sum] = "1736511a0f080539335c835bf5609d56e91e8fd2b23c540852e5df75c2f3d514"

inherit skarnet

RRECOMMENDS:${PN} = "s6-networking"
