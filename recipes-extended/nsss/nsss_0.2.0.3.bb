SUMMARY = "nsssd - a secure NSS-like implementation for small libcs"
HOMEPAGE = "https://skarnet.org/software/nsss/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=3d51c36fc24946e3b9febf4e348aac97"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/nsss/nsss-${PV}.tar.gz"
SRC_URI[sha256sum] = "b1e397ed5b327611a7c2c8c1dc60e91fef353c54fead464f887720bc0914148e"

inherit skarnet

BBCLASSEXTEND = "native"
