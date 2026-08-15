SUMMARY = "execline - an interpreter-less scripting language"
HOMEPAGE = "https://skarnet.org/software/execline/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=ea5b4b9a81f21793db1a769bee7302f3"

DEPENDS = "skalibs"

SRC_URI = "https://skarnet.org/software/execline/execline-${PV}.tar.gz"
SRC_URI[sha256sum] = "908ed4db3a6b3a23a205d8fd4cf2a71089156f2aeae0f54656045aafad2dee32"

PACKAGECONFIG ?= "multicall"
PACKAGECONFIG[multicall] = "--enable-multicall,--disable-multicall"

EXTRA_OECONF:append:class-native = " --shebangdir=${SKARNET_TARGET_BINDIR}"

inherit skarnet

BBCLASSEXTEND = "native"
