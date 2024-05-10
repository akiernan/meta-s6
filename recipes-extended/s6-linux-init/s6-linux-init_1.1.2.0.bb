SUMMARY = "s6-linux-init - tools to create an s6-based Linux init system"
HOMEPAGE = "https://skarnet.org/software/s6-linux-init/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=0280da83329b0ff1dfa49e1444f1cd97"

DEPENDS = "skalibs execline qemu-native s6"

SRC_URI = "https://skarnet.org/software/s6-linux-init/s6-linux-init-${PV}.tar.gz \
           file://rcS-default \
           file://rc \
           file://rcS \
           file://rc.init-sysvinit \
           file://rc.shutdown-sysvinit \
           file://rc.shutdown.final-sysvinit \
           file://runlevel-sysvinit \
           file://rc.init-s6-rc \
           file://rc.shutdown-s6-rc \
           file://rc.shutdown.final-s6-rc \
           file://runlevel-s6-rc"
SRC_URI[sha256sum] = "11ae08d0a66210b5e56b6baee0f6b9b1b69fbedb05fda1285b115a31ca46c77f"

inherit qemu skarnet update-alternatives useradd

PACKAGE_BEFORE_PN = "${PN}-common"

USERADD_PACKAGES = "${PN}-common"
USERADD_PARAM:${PN}-common = "--system --home /run/uncaught-logs \
                              --no-create-home --shell /sbin/nologin \
                              --user-group catchlog"

S6_LINUX_INIT_EARLY_GETTY ?= ""

EXTRA_S6_LINUX_INIT_MAKER ?= ""

do_install:append:class-target () {
	if [ -n "${S6_LINUX_INIT_SERVICE_MANAGER}" ]; then
		case "${S6_LINUX_INIT_SERVICE_MANAGER}" in
		sysvinit)
			install -d ${D}${sysconfdir} ${D}${sysconfdir}/default ${D}${sysconfdir}/init.d
			install -m 0755 ${UNPACKDIR}/rc ${D}${sysconfdir}/init.d
			install -m 0755 ${UNPACKDIR}/rcS ${D}${sysconfdir}/init.d
			sed -e \
				's:#PSPLASH_TEXT#:${@bb.utils.contains("PACKAGECONFIG","psplash-text-updates","yes","no", d)}:g' \
				${UNPACKDIR}/rcS-default > ${D}${sysconfdir}/default/rcS
			chmod 0644 ${D}${sysconfdir}/default/rcS
			;;
		s6-rc)
			;;
		esac
		install -d -m 0755 ${D}${sysconfdir}/s6-linux-init/skel
		for i in rc.init rc.shutdown rc.shutdown.final runlevel; do
			install -m 0755 ${UNPACKDIR}/$i-${S6_LINUX_INIT_SERVICE_MANAGER} ${D}${sysconfdir}/s6-linux-init/skel/$i
		done
	fi
	${@qemu_wrapper_cmdline(d, '${STAGING_DIR_TARGET}', ['${D}${libdir}', '${STAGING_DIR_TARGET}/${base_libdir}', '${STAGING_DIR_TARGET}/${libdir}'])} \
	${D}${bindir}/s6-linux-init-maker \
		-c "${sysconfdir}/s6-linux-init/current" \
		-u catchlog \
		${@ "-G '${S6_LINUX_INIT_EARLY_GETTY}'" if d.getVar('S6_LINUX_INIT_EARLY_GETTY', True) else ''} \
		-p "${bindir}:${sbindir}${@bb.utils.contains('DISTRO_FEATURES','usrmerge','',':${base_bindir}:${base_sbindir}',d)}" \
		-m 022 \
		-D 5 \
		-s /run/kernel_env \
		-f "${D}${sysconfdir}/s6-linux-init/skel" \
		${EXTRA_S6_LINUX_INIT_MAKER} \
		"${D}${sysconfdir}/s6-linux-init/current"

	chown -R root:root "${D}${sysconfdir}"
	chown catchlog:catchlog "${D}${sysconfdir}/s6-linux-init/current/run-image/uncaught-logs"

	install -d -m 0755 ${D}${base_sbindir}
	for i in init halt poweroff reboot shutdown telinit; do
		ln -sr ${D}${sysconfdir}/s6-linux-init/current/bin/$i ${D}${base_sbindir}/$i
	done
}

split_s6_svscan () {
	# We need to split the files inside .s6-svscan from the directory
	# itself, so we recreate the directory as part of packaging
	install -d -m 0755 ${PKGDEST}/${PN}-common/${sysconfdir}/s6-linux-init/current/run-image/service/.s6-svscan
}
PACKAGEFUNCS += "split_s6_svscan"

FILES:${PN}-common = "\
    ${sysconfdir}/s6-linux-init/current/run-image/service/s6-svscan-log/fifo \
    ${sysconfdir}/s6-linux-init/current/run-image/service/s6-svscan-log/notification-fd \
    ${sysconfdir}/s6-linux-init/current/run-image/uncaught-logs \
"

RDEPENDS:${PN} += "\
    execline \
    s6 \
    ${@ 'initscripts' if d.getVar('S6_LINUX_INIT_SERVICE_MANAGER') == 'sysvinit' else ''} \
    ${@ 's6-rc' if d.getVar('S6_LINUX_INIT_SERVICE_MANAGER') == 's6-rc' else ''} \
"
RDEPENDS:${PN}:append:class-target = " ${PN}-common"

RDEPENDS:${PN}-common += "\
    execline \
    s6 \
"

ALTERNATIVE_PRIORITY = "300"

ALTERNATIVE:${PN} = "init halt poweroff reboot shutdown telinit"

ALTERNATIVE_LINK_NAME[init] = "${base_sbindir}/init"
ALTERNATIVE_LINK_NAME[halt] = "${base_sbindir}/halt"
ALTERNATIVE_LINK_NAME[poweroff] = "${base_sbindir}/poweroff"
ALTERNATIVE_LINK_NAME[reboot] = "${base_sbindir}/reboot"
ALTERNATIVE_LINK_NAME[shutdown] = "${base_sbindir}/shutdown"
ALTERNATIVE_LINK_NAME[telinit] = "${base_sbindir}/telinit"

BBCLASSEXTEND = "native"
