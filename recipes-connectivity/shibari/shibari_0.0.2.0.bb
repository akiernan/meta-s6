SUMMARY = "shibari - a collection of DNS tools"
HOMEPAGE = "https://skarnet.org/software/shibari/"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=90040c8fb9e16390be015a30350df5e4"

DEPENDS = "skalibs s6 s6-dns"

SRC_URI = "https://skarnet.org/software/shibari/shibari-${PV}.tar.gz"
SRC_URI[sha256sum] = "6607dece9927801d41a73b69bbdc4aacefec3f95d8c4a301c5b3761b566809a5"

inherit skarnet
