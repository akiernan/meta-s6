SUMMARY = "Serial terminal support for s6-rc (using SERIAL_CONSOLES)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://tty.toml"

SERIAL_TERM ?= "vt102"

inherit s6-rc

S = "${WORKDIR}/sources"
UNPACKDIR = "${S}"

INHIBIT_DEFAULT_DEPS = "1"

do_compile[noexec] = "1"

do_install() {
	CONSOLES="${SERIAL_CONSOLES}"
	for s in $CONSOLES; do
		speed=$(echo $s | cut -d\; -f 1)
		device=$(echo $s | cut -d\; -f 2)

		install -d ${D}${s6_rc_sourcedir}
		sed -e <${UNPACKDIR}/tty.toml >${D}${s6_rc_sourcedir}/getty-$device.toml \
			"s/@SPEED@/$speed/; s/@DEVICE@/$device/; s/@TERM@/${SERIAL_TERM}/"
	done
}

# This is a machine specific file
PACKAGE_ARCH = "${MACHINE_ARCH}"

ALLOW_EMPTY:${PN} = "1"
