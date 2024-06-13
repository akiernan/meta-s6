SUMMARY = "s6-rc - a dependency-based init script management system"
HOMEPAGE = "https://skarnet.org/software/s6-rc/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=b5c681c988101d08f8d99ffaf1ad1873"

DEPENDS = "skalibs execline s6"

SRC_URI = "https://skarnet.org/software/s6-rc/s6-rc-${PV}.tar.gz"
SRC_URI[sha256sum] = "e3272796a947904de37ab34ec10430e26107b8ef05210d81059c8b36203e6a9f"

inherit skarnet

BBCLASSEXTEND = "native"
