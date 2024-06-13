SUMMARY = "utmps - a secure utmpx and wtmp implementation"
HOMEPAGE = "https://skarnet.org/software/utmps/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=3d51c36fc24946e3b9febf4e348aac97"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/utmps/utmps-${PV}.tar.gz"
SRC_URI[sha256sum] = "f7ffa3714c65973bb95fbcf1501c06fc0478d93a51cea1b373ec6811c2425f52"

inherit skarnet

RRECOMMENDS:${PN} = "s6"
