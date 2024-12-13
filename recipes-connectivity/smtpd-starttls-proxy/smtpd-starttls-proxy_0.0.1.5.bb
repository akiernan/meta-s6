SUMMARY = "smtpd-starttls-proxy - a STARTTLS implementation for mail servers"
HOMEPAGE = "https://skarnet.org/software/smtpd-starttls-proxy/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=8283b3de13b10dc6605bf0270b3f0668"

DEPENDS = "skalibs s6"

SRC_URI = "https://skarnet.org/software/smtpd-starttls-proxy/smtpd-starttls-proxy-${PV}.tar.gz"
SRC_URI[sha256sum] = "c6667271b4a2a73f07980bd5369ef18368735a0cf42898d5a5ac970152707484"

inherit skarnet

RRECOMMENDS:${PN} = "s6-networking"
