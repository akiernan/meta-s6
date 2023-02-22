SUMMARY = "s6-rc - a dependency-based init script management system"
HOMEPAGE = "https://skarnet.org/software/s6-rc/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=0280da83329b0ff1dfa49e1444f1cd97"

DEPENDS = "skalibs execline s6"

SRC_URI = "https://skarnet.org/software/s6-rc/s6-rc-${PV}.tar.gz"
SRC_URI[sha256sum] = "d7268cab7c5423305cf9598a33d4fcdab8a315952b3ec80f79c85b8cb76158f6"

inherit skarnet

BBCLASSEXTEND = "native"
