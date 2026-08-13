SUMMARY = "s6-rc - a dependency-based init script management system"
HOMEPAGE = "https://skarnet.org/software/s6-rc/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=397588315e6b6414b74abe203749f1ee"

DEPENDS = "skalibs execline s6"

SRC_URI = "https://skarnet.org/software/s6-rc/s6-rc-${PV}.tar.gz"
SRC_URI[sha256sum] = "bf5b8ce0da5a4ee70d642b818b61d9916a7a9b64a457595f388113e54a188688"

inherit skarnet

BBCLASSEXTEND = "native"
