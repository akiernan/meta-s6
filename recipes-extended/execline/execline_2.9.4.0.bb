SUMMARY = "execline - an interpreter-less scripting language"
HOMEPAGE = "https://skarnet.org/software/execline/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=34f73ee8aab2e0ca56980313bfd7a7bb"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/execline/execline-${PV}.tar.gz"
SRC_URI[sha256sum] = "9ab55d561539dfa76ff4a97906fa995fc4a288e3de5225cb1a9d8fa9e9ebc49b"

PACKAGECONFIG ?= "multicall"
PACKAGECONFIG[multicall] = "--enable-multicall,--disable-multicall"

inherit skarnet

BBCLASSEXTEND = "native"
