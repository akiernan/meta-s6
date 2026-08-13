SUMMARY = "smtpd-starttls-proxy - a STARTTLS implementation for mail servers"
HOMEPAGE = "https://skarnet.org/software/smtpd-starttls-proxy/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=d89094b09609d1bfb82b52e4f395d320"

DEPENDS = "skalibs s6"

SRC_URI = "https://skarnet.org/software/smtpd-starttls-proxy/smtpd-starttls-proxy-${PV}.tar.gz"
SRC_URI[sha256sum] = "2ccca8b4040c1c0612a38fc34ff9bf91dfce07127e02edfd0103aae1a08e6b51"

inherit skarnet

RRECOMMENDS:${PN} = "s6-networking"
