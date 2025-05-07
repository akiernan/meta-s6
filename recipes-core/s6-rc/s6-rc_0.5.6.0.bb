SUMMARY = "s6-rc - a dependency-based init script management system"
HOMEPAGE = "https://skarnet.org/software/s6-rc/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=3710923a1cbdcc20ee753ff94bc119ca"

DEPENDS = "skalibs execline s6"

SRC_URI = "https://skarnet.org/software/s6-rc/s6-rc-${PV}.tar.gz"
SRC_URI[sha256sum] = "81277f6805e8d999ad295bf9140a909943b687ffcfb5aa3c4efd84b1a574586e"

inherit skarnet

BBCLASSEXTEND = "native"
