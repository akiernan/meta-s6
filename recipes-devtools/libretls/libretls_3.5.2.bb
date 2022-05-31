SUMMARY = "LibreTLS — libtls for OpenSSL"
HOMEPAGE = "https://git.causal.agency/libretls/about/"

LICENSE = "ISC & BSD-3-Clause & MIT"
LIC_FILES_CHKSUM = "file://configure.ac;beginline=1;endline=13;md5=600fc47af94bad841e87d56fa8541f46"

SRC_URI = "https://causal.agency/libretls/libretls-${PV}.tar.gz"
SRC_URI[sha256sum] = "59ce9961cb1b1a2859cacb9863eeccc3bbeadf014840a1c61a0ac12ad31bcc9e"

DEPENDS += "openssl"

inherit autotools manpages pkgconfig

PACKAGECONFIG ?= ""
PACKAGECONFIG[manpages] = ""
