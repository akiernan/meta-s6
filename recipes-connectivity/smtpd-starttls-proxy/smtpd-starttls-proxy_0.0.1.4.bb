SUMMARY = "smtpd-starttls-proxy - a STARTTLS implementation for mail servers"
HOMEPAGE = "https://skarnet.org/software/smtpd-starttls-proxy/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=071238f4398a219141d0e61dd62faff5"

DEPENDS = "skalibs s6"

SRC_URI = "https://skarnet.org/software/smtpd-starttls-proxy/smtpd-starttls-proxy-${PV}.tar.gz"
SRC_URI[sha256sum] = "125d74153bb73f76ce424e8beed7c776fb039db173592991f41c4540faeb8382"

inherit skarnet

RRECOMMENDS:${PN} = "s6-networking"
