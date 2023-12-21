SUMMARY = "shibari - a collection of DNS tools"
HOMEPAGE = "https://skarnet.org/software/shibari/"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=f6264321b2c44ec371d117ef7b80ff58"

DEPENDS = "skalibs s6 s6-dns"

SRC_URI = "https://skarnet.org/software/shibari/shibari-${PV}.tar.gz"
SRC_URI[sha256sum] = "737d7ef489f2547d88ccf28942e97865c0db4c7ee4fa901551e6e24619c49ddd"

inherit skarnet
