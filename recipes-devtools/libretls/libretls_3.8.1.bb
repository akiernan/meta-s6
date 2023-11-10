SUMMARY = "LibreTLS — libtls for OpenSSL"
HOMEPAGE = "https://git.causal.agency/libretls/about/"

LICENSE = "ISC & BSD-3-Clause & MIT"
LIC_FILES_CHKSUM = "file://README.7;beginline=101;endline=128;md5=5bd825d475549c83e245b08267244b71"

SRC_URI = "https://causal.agency/libretls/libretls-${PV}.tar.gz"
SRC_URI[sha256sum] = "3bc9fc0e61827ee2f608e5e44993a8fda6d610b80a1e01a9c75610cc292997b5"

DEPENDS += "openssl"

inherit autotools manpages pkgconfig

PACKAGECONFIG ?= ""
PACKAGECONFIG[manpages] = ""
