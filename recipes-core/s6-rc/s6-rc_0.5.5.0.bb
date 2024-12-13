SUMMARY = "s6-rc - a dependency-based init script management system"
HOMEPAGE = "https://skarnet.org/software/s6-rc/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=b5c681c988101d08f8d99ffaf1ad1873"

DEPENDS = "skalibs execline s6"

SRC_URI = "https://skarnet.org/software/s6-rc/s6-rc-${PV}.tar.gz"
SRC_URI[sha256sum] = "c3b5f1b8d3acea60a20a59fb25280b69a6aac0bb3afdab21d2edeada77b2df01"

inherit skarnet

BBCLASSEXTEND = "native"
