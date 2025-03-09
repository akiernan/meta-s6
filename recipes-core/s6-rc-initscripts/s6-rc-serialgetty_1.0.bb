SUMMARY = "Serial terminal support for s6-rc (using SERIAL_CONSOLES)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "\
    file://getty.toml \
    file://ok-init.toml \
"

SERIAL_TERM ?= "vt102"

inherit s6-rc

S = "${WORKDIR}/sources"
UNPACKDIR = "${S}"

INHIBIT_DEFAULT_DEPS = "1"

do_compile[noexec] = "1"

do_install() {
	CONSOLES="${SERIAL_CONSOLES}"
	if [ -n "$CONSOLES" ]; then
		install -d ${D}${s6_rc_sourcedir}
		install ${UNPACKDIR}/ok-init.toml ${D}${s6_rc_sourcedir}/
		install -d -m 0755 ${D}${s6_rc_sourcedir}/init-tty
		echo "bundle" >${D}${s6_rc_sourcedir}/init-tty/type
		install -d -m 0755 ${D}${s6_rc_sourcedir}/init-tty/contents.d

		for s in $CONSOLES; do
			speed=$(echo $s | cut -d\; -f 1)
			device=$(echo $s | cut -d\; -f 2)

			sed -e <${UNPACKDIR}/getty.toml >${D}${s6_rc_sourcedir}/getty-$device.toml \
				"s/@SPEED@/$speed/; s/@DEVICE@/$device/; s/@TERM@/${SERIAL_TERM}/"
			touch ${D}${s6_rc_sourcedir}/init-tty/contents.d/getty-$device
		done
	fi
}

# This is a machine specific file
PACKAGE_ARCH = "${MACHINE_ARCH}"

ALLOW_EMPTY:${PN} = "1"
