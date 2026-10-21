SUMMARY = "Boot-time getty generator for s6 (follows the active kernel consoles)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS = "s6-native"

SRC_URI = "\
    file://getty-generator.toml \
    file://getty-instance-run \
    file://s6-getty-generator \
"

SERIAL_TERM ?= "vt102"
S6_GETTY_BAUDRATE ?= "115200"
S6_GETTY_VT_BAUDRATE ?= "38400"
S6_GETTY_MAX_INSTANCES ?= "16"

inherit s6-rc update-rc.d

S = "${UNPACKDIR}"

INHIBIT_DEFAULT_DEPS = "1"

do_compile[noexec] = "1"

S6_LINUX_INIT_SERVICEDIR = "${sysconfdir}/s6-linux-init/current/run-image/service"

do_install() {
	install -d -m 0755 ${D}${libexecdir}
	install -m 0755 ${UNPACKDIR}/s6-getty-generator ${D}${libexecdir}/s6-getty-generator

	# getty-srv: an instanced service, one instance per console
	install -d -m 0755 ${WORKDIR}/getty-template
	sed -e 's/@BAUDRATE@/${S6_GETTY_BAUDRATE}/' \
	    -e 's/@VT_BAUDRATE@/${S6_GETTY_VT_BAUDRATE}/' \
	    -e 's/@TERM@/${SERIAL_TERM}/' \
		${UNPACKDIR}/getty-instance-run > ${WORKDIR}/getty-template/run
	chmod 0755 ${WORKDIR}/getty-template/run
	printf 'SIGHUP\n' > ${WORKDIR}/getty-template/down-signal

	# Under s6-rc, built with s6-instance-maker in its s6-rc source
	# definition mode
	rm -rf ${WORKDIR}/getty-instanced
	s6-instance-maker -c ${S6_GETTY_MAX_INSTANCES} -r getty-srv \
		${WORKDIR}/getty-template ${WORKDIR}/getty-instanced
	install -d -m 0755 ${D}${s6_rc_sourcedir}
	cp -R --preserve=mode,links ${WORKDIR}/getty-instanced/getty-srv ${D}${s6_rc_sourcedir}/
	install -d -m 0755 ${D}${s6_rc_sourcedir}/getty-srv/dependencies.d
	touch ${D}${s6_rc_sourcedir}/getty-srv/dependencies.d/init-dev
	# the generator needs it, so it belongs in the boot set in its own right
	touch ${D}${s6_rc_sourcedir}/getty-srv/flag-recommended

	install -m 0644 ${UNPACKDIR}/getty-generator.toml ${D}${s6_rc_sourcedir}
	install -d -m 0755 ${D}${s6_rc_sourcedir}/init-tty/contents.d
	touch ${D}${s6_rc_sourcedir}/init-tty/contents.d/getty-srv
	touch ${D}${s6_rc_sourcedir}/init-tty/contents.d/getty-generator

	# Under sysvinit rc, a plain servicedir in s6-linux-init's run-image,
	# which s6-svscan supervises from boot at the same /run/service/getty-srv
	# the generator expects
	# the run-image wants a plain one
	rm -rf ${WORKDIR}/getty-run-image
	install -d -m 0755 ${WORKDIR}/getty-run-image
	s6-instance-maker -c ${S6_GETTY_MAX_INSTANCES} \
		${WORKDIR}/getty-template ${WORKDIR}/getty-run-image/getty-srv
	install -d -m 0755 ${D}${S6_LINUX_INIT_SERVICEDIR}
	cp -R --preserve=mode,links ${WORKDIR}/getty-run-image/getty-srv ${D}${S6_LINUX_INIT_SERVICEDIR}/

	# rc runs it with start or stop, which is exactly the generator's
	# interface, so the init script is the generator itself
	install -d -m 0755 ${D}${INIT_D_DIR}
	ln -sr ${D}${libexecdir}/s6-getty-generator ${D}${INIT_D_DIR}/getty-generator
}

# Where sysvinit's inittab would put them: in runlevels 1 to 5, once rc has
# finished bringing the runlevel up
INITSCRIPT_PACKAGES = "${PN}-sysvinit-rc"
INITSCRIPT_NAME:${PN}-sysvinit-rc = "getty-generator"
INITSCRIPT_PARAMS:${PN}-sysvinit-rc = "start 99 1 2 3 4 5 . stop 01 0 6 ."

PACKAGE_BEFORE_PN = "${PN}-s6-rc ${PN}-sysvinit-rc"

SUMMARY:${PN}-s6-rc = "getty generator service definitions for s6-rc"
FILES:${PN}-s6-rc = "${s6_rc_sourcedir}"
RDEPENDS:${PN}-s6-rc = "${PN}"

SUMMARY:${PN}-sysvinit-rc = "getty generator for s6-linux-init with sysvinit rc"
FILES:${PN}-sysvinit-rc = "${S6_LINUX_INIT_SERVICEDIR} ${INIT_D_DIR}"
RDEPENDS:${PN}-sysvinit-rc = "${PN} execline"

RDEPENDS:${PN} += "s6"
