SUMMARY = "nsssd - a secure NSS-like implementation for small libcs"
HOMEPAGE = "https://skarnet.org/software/nsss/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=b976f486158496f7f88fab3dbfcfa5e4"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/nsss/nsss-${PV}.tar.gz"
SRC_URI[sha256sum] = "7d984ea627b3a8d7649d9bf24c79693f338e911d2b11040b89c4febfb1b3125b"

inherit skarnet

BBCLASSEXTEND = "native"
