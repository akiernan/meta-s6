SUMMARY = "tipidee - a minimalistic web server"
HOMEPAGE = "https://skarnet.org/software/tipidee/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=f6264321b2c44ec371d117ef7b80ff58"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/tipidee/tipidee-${PV}.tar.gz"
SRC_URI[sha256sum] = "d1d93a93ce942a02768a85f91f65e881af52f92c0ccbd345acad8a10aa0dc420"

inherit skarnet

RRECOMMENDS:${PN} = "s6-networking"
