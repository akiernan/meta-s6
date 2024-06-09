SUMMARY = "tipidee - a minimalistic web server"
HOMEPAGE = "https://skarnet.org/software/tipidee/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=c0cd202fb2a4f577c68e43b50d696a7b"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/tipidee/tipidee-${PV}.tar.gz"
SRC_URI[sha256sum] = "d9e91fc719879a43d54329b99b02c6649c54e428e77bc96a0573506b52bf1422"

inherit skarnet

RRECOMMENDS:${PN} = "s6-networking"
