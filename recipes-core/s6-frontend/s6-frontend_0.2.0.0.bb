SUMMARY = "s6-frontend - a unified command-line frontend to the s6 init system"
HOMEPAGE = "https://skarnet.org/software/s6-frontend/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=bf2e714ed36534bb7976bdbae107223b"

DEPENDS = "skalibs execline s6 s6-rc"

SRC_URI = "https://skarnet.org/software/s6-frontend/s6-frontend-${PV}.tar.gz \
           file://s6.conf"
SRC_URI[sha256sum] = "4bce4e9bec8c1de9e3ee5ef764580421504a3c6303eff96997b69d1b89219536"

PACKAGECONFIG ?= "s6-linux-init"
PACKAGECONFIG[nsss] = "--enable-nsss,--disable-nsss,nsss"
PACKAGECONFIG[s6-linux-init] = "--enable-s6li,--disable-s6li,s6-linux-init"
PACKAGECONFIG[util-linux] = "--enable-util-linux,--disable-util-linux,,util-linux"

# the configure default derives the first store from ${prefix}, which
# is empty on a non-usrmerge system
EXTRA_OECONF = "--with-store-list=${datadir}/s6/sources:${sysconfdir}/s6/sources"

inherit skarnet

do_install:append() {
	install -D -m 0644 ${UNPACKDIR}/s6.conf ${D}${sysconfdir}/s6.conf
}

CONFFILES:${PN} = "${sysconfdir}/s6.conf"

RDEPENDS:${PN} += "\
    execline \
    s6 \
    s6-rc \
"
