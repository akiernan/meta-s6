SUMMARY = "tipidee - a minimalistic web server"
HOMEPAGE = "https://skarnet.org/software/tipidee/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=7463eff70700c0f79394bcff8174cb58"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/tipidee/tipidee-${PV}.tar.gz"
SRC_URI[sha256sum] = "1a396533662ac70bc2b0a0b8c65616ffa7fa201508859300ebb9ad33cda6102d"

inherit skarnet

RRECOMMENDS:${PN} = "s6-networking"
