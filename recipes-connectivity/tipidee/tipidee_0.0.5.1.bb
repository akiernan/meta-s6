SUMMARY = "tipidee - a minimalistic web server"
HOMEPAGE = "https://skarnet.org/software/tipidee/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=c0cd202fb2a4f577c68e43b50d696a7b"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/tipidee/tipidee-${PV}.tar.gz"
SRC_URI[sha256sum] = "228a995683a8cd4a1de9a7cb3f8d83a43830886c687895d0a3d1c5033f90362b"

inherit skarnet

RRECOMMENDS:${PN} = "s6-networking"
