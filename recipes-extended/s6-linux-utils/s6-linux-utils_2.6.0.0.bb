SUMMARY = "s6-linux-utils - tiny Linux-specific utilities"
HOMEPAGE = "https://skarnet.org/software/s6-linux-utils/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=41280dbee09dab174bbebae98f1fdb47"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/s6-linux-utils/s6-linux-utils-${PV}.tar.gz"
SRC_URI[sha256sum] = "6c7132734a0c8287002ee6910c469f175a97d766a8023c0c33af8ba92643ed59"

inherit skarnet

BBCLASSEXTEND = "native"
