SUMMARY = "bcnm - a client network manager"
HOMEPAGE = "https://skarnet.org/software/bcnm/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=42390c2cfaf3134057e01cab6b4a6e30"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/bcnm/bcnm-${PV}.tar.gz"
SRC_URI[sha256sum] = "f72d11eee63e5f07b74459abc89fb40e1b96ddeb86556c5c1c2db192b09a51d5"

inherit skarnet
