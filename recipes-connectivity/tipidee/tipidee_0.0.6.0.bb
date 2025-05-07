SUMMARY = "tipidee - a minimalistic web server"
HOMEPAGE = "https://skarnet.org/software/tipidee/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=90040c8fb9e16390be015a30350df5e4"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/tipidee/tipidee-${PV}.tar.gz"
SRC_URI[sha256sum] = "e2add8be1089022e3790241b93ac4a5a3e58dad645f5d919f8cba744cd4a1702"

inherit skarnet

RRECOMMENDS:${PN} = "s6-networking"
