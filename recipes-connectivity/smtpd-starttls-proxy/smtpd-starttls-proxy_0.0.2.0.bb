SUMMARY = "smtpd-starttls-proxy - a STARTTLS implementation for mail servers"
HOMEPAGE = "https://skarnet.org/software/smtpd-starttls-proxy/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=c5d7fa95bb48be162b3a94ded3ca24ab"

DEPENDS = "skalibs s6"

SRC_URI = "https://skarnet.org/software/smtpd-starttls-proxy/smtpd-starttls-proxy-${PV}.tar.gz"
SRC_URI[sha256sum] = "b08c3f9e7ac01030618504a348758ec2d6ded994bbaccd36f5c2c801bbbe76c2"

inherit skarnet

RRECOMMENDS:${PN} = "s6-networking"
