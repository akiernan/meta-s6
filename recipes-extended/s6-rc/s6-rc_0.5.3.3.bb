SUMMARY = "s6-rc - a dependency-based init script management system"
HOMEPAGE = "https://skarnet.org/software/s6-rc/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=0280da83329b0ff1dfa49e1444f1cd97"

DEPENDS = "skalibs execline s6"

SRC_URI = "https://skarnet.org/software/s6-rc/s6-rc-${PV}.tar.gz"
SRC_URI[sha256sum] = "3398f10c0632e39bd69e48ab56d56fde6b48c7f0200ead03a26fb2830908652a"

inherit skarnet

BBCLASSEXTEND = "native"
