SUMMARY = "shibari - a collection of DNS tools"
HOMEPAGE = "https://skarnet.org/software/shibari/"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=c0cd202fb2a4f577c68e43b50d696a7b"

DEPENDS = "skalibs s6 s6-dns"

SRC_URI = "https://skarnet.org/software/shibari/shibari-${PV}.tar.gz"
SRC_URI[sha256sum] = "375433e4ac2a947aa6b8272e695537d3bd39298521a7a32ceeb4cbbddc6f2982"

inherit skarnet
