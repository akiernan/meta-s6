SUMMARY = "s6-rc - a dependency-based init script management system"
HOMEPAGE = "https://skarnet.org/software/s6-rc/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=2865f4a7bf0752acdc5a4e517c8b2d2e"

DEPENDS = "skalibs execline s6"

SRC_URI = "https://skarnet.org/software/s6-rc/s6-rc-${PV}.tar.gz"
SRC_URI[sha256sum] = "4f24a4966a4aa353d2bd1a8afca9b88c7b7bd29c46b3a1a7f5305686b9d6e038"

inherit skarnet

BBCLASSEXTEND = "native"
