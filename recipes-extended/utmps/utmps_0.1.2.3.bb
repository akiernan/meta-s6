SUMMARY = "utmps - a secure utmpx and wtmp implementation"
HOMEPAGE = "https://skarnet.org/software/utmps/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=97a21eece1f23a77df40063449656bcc"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/utmps/utmps-${PV}.tar.gz"
SRC_URI[sha256sum] = "318ac799ed17c3fbf4281085b4b071facbd35c29852a5c643c24fa2869fc0545"

inherit skarnet

RRECOMMENDS:${PN} = "s6"
