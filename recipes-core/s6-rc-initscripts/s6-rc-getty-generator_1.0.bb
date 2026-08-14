SUMMARY = "Boot-time getty generator for s6-rc (follows the active kernel consoles)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS = "s6-native"

SRC_URI = "\
    file://getty-generator.toml \
    file://getty-instance-run \
    file://s6-rc-getty-generator \
"

SERIAL_TERM ?= "vt102"
S6_GETTY_BAUDRATE ?= "115200"
S6_GETTY_VT_BAUDRATE ?= "38400"
S6_GETTY_MAX_INSTANCES ?= "16"

inherit s6-rc

S = "${UNPACKDIR}"

INHIBIT_DEFAULT_DEPS = "1"

do_compile[noexec] = "1"

do_install() {
	install -d -m 0755 ${D}${libexecdir}
	install -m 0755 ${UNPACKDIR}/s6-rc-getty-generator ${D}${libexecdir}/s6-rc-getty-generator

	# getty-srv: an instanced service, one instance per console; built
	# with s6-instance-maker in its s6-rc source definition mode
	install -d -m 0755 ${WORKDIR}/getty-template
	sed -e 's/@BAUDRATE@/${S6_GETTY_BAUDRATE}/' \
	    -e 's/@VT_BAUDRATE@/${S6_GETTY_VT_BAUDRATE}/' \
	    -e 's/@TERM@/${SERIAL_TERM}/' \
		${UNPACKDIR}/getty-instance-run > ${WORKDIR}/getty-template/run
	chmod 0755 ${WORKDIR}/getty-template/run
	printf 'SIGHUP\n' > ${WORKDIR}/getty-template/down-signal

	rm -rf ${WORKDIR}/getty-instanced
	s6-instance-maker -c ${S6_GETTY_MAX_INSTANCES} -r getty-srv \
		${WORKDIR}/getty-template ${WORKDIR}/getty-instanced
	# the generated scripts carry the native execline's shebang path
	find ${WORKDIR}/getty-instanced -type f \
		-exec sed -i '1s|^#!.*/execlineb|#!/usr/bin/execlineb|' {} +

	install -d -m 0755 ${D}${s6_rc_sourcedir}
	cp -R --preserve=mode,links ${WORKDIR}/getty-instanced/getty-srv ${D}${s6_rc_sourcedir}/
	install -d -m 0755 ${D}${s6_rc_sourcedir}/getty-srv/dependencies.d
	touch ${D}${s6_rc_sourcedir}/getty-srv/dependencies.d/init-dev

	install -m 0644 ${UNPACKDIR}/getty-generator.toml ${D}${s6_rc_sourcedir}
	install -d -m 0755 ${D}${s6_rc_sourcedir}/init-tty/contents.d
	touch ${D}${s6_rc_sourcedir}/init-tty/contents.d/getty-srv
	touch ${D}${s6_rc_sourcedir}/init-tty/contents.d/getty-generator
}

RDEPENDS:${PN} += "s6"
