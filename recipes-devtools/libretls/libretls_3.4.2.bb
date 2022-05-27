SUMMARY = "LibreTLS — libtls for OpenSSL"

LICENSE = "ISC & BSD-3-Clause & MIT"
LIC_FILES_CHKSUM = "file://README.7;md5=d9a4843fffbc2c3478eeb899a2ec454d"

SRC_URI = "https://causal.agency/libretls/libretls-${PV}.tar.gz"
SRC_URI[sha256sum] = "3260ec03ffc7fee50f4c850051d276db635f7f6b36f7cd3f999c4e2c90ec8420"

DEPENDS += "openssl"

inherit autotools pkgconfig
