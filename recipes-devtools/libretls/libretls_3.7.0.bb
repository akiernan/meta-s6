SUMMARY = "LibreTLS — libtls for OpenSSL"
HOMEPAGE = "https://git.causal.agency/libretls/about/"

LICENSE = "ISC & BSD-3-Clause & MIT"
LIC_FILES_CHKSUM = "file://README.7;beginline=101;endline=128;md5=5bd825d475549c83e245b08267244b71"

SRC_URI = "https://causal.agency/libretls/libretls-${PV}.tar.gz"
SRC_URI[sha256sum] = "9aa5d3a9133932c362075259b0b17bb0c89741fa1b2535136df2ded7a0c13392"

DEPENDS += "openssl"

inherit autotools manpages pkgconfig

PACKAGECONFIG ?= ""
PACKAGECONFIG[manpages] = ""
