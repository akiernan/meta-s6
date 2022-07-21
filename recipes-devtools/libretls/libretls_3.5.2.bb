SUMMARY = "LibreTLS — libtls for OpenSSL"
HOMEPAGE = "https://git.causal.agency/libretls/about/"

LICENSE = "ISC & BSD-3-Clause & MIT"
LIC_FILES_CHKSUM = "file://README.7;beginline=101;endline=128;md5=5bd825d475549c83e245b08267244b71"

SRC_URI = "https://causal.agency/libretls/libretls-${PV}.tar.gz"
SRC_URI[sha256sum] = "59ce9961cb1b1a2859cacb9863eeccc3bbeadf014840a1c61a0ac12ad31bcc9e"

DEPENDS += "openssl"

inherit autotools manpages pkgconfig

PACKAGECONFIG ?= ""
PACKAGECONFIG[manpages] = ""
