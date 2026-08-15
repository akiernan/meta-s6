SUMMARY = "s6-linux-init - tools to create an s6-based Linux init system"
HOMEPAGE = "https://skarnet.org/software/s6-linux-init/"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=397588315e6b6414b74abe203749f1ee"

DEPENDS = "skalibs execline qemu-native s6"

SRC_URI = "https://skarnet.org/software/s6-linux-init/s6-linux-init-${PV}.tar.gz \
           file://rcS-default \
           file://rc.init-sysvinit-rc \
           file://rc.shutdown-sysvinit-rc \
           file://runlevel-sysvinit-rc \
           file://rc.init-s6-rc \
           file://rc.shutdown-s6-rc \
           file://runlevel-s6-rc"
SRC_URI[sha256sum] = "6fad014da162c0c81924197c57d16e1a75c133b34a20e423431a1b741e907b1d"

inherit qemu skarnet update-alternatives useradd

PACKAGECONFIG ?= ""
PACKAGECONFIG[nsss] = "--enable-nsss,--disable-nsss,nsss"
PACKAGECONFIG[utmps] = "--enable-utmps,--disable-utmps,utmps"

EXTRA_OECONF += "--skeldir=${sysconfdir}/${BPN}/skel"

PACKAGE_BEFORE_PN = "${PN}-common"

USERADD_PACKAGES = "${PN}-common"
USERADD_PARAM:${PN}-common = "--system --home /run/uncaught-logs \
                              --no-create-home --shell /sbin/nologin \
                              --user-group catchlog"

S6_LINUX_INIT_EARLY_GETTY ??= ""
S6_LINUX_INIT_INITDEFAULT ??= "${@ '5' if d.getVar('S6_LINUX_INIT_SERVICE_MANAGER') == "sysvinit-rc" else ''}"

EXTRA_S6_LINUX_INIT_MAKER ??= "-s /run/kernel_env"

do_install:append:class-target () {
	if [ -n "${S6_LINUX_INIT_SERVICE_MANAGER}" ]; then
		install -d ${D}${sysconfdir} ${D}${sysconfdir}/default ${D}${sysconfdir}/init.d
		sed -e \
			's:#PSPLASH_TEXT#:${@bb.utils.contains("PACKAGECONFIG","psplash-text-updates","yes","no", d)}:g' \
			${UNPACKDIR}/rcS-default > ${D}${sysconfdir}/default/rcS
		chmod 0644 ${D}${sysconfdir}/default/rcS
		install -d -m 0755 ${D}${sysconfdir}/s6-linux-init/skel
		for i in rc.init rc.shutdown runlevel; do
			install -m 0755 ${UNPACKDIR}/$i-${S6_LINUX_INIT_SERVICE_MANAGER} ${D}${sysconfdir}/s6-linux-init/skel/$i
		done
	fi
	${@qemu_wrapper_cmdline(d, '${STAGING_DIR_TARGET}', ['${D}${libdir}', '${STAGING_DIR_TARGET}/${base_libdir}', '${STAGING_DIR_TARGET}/${libdir}'])} \
	${D}${bindir}/s6-linux-init-maker \
		-c "${sysconfdir}/s6-linux-init/current" \
		-u catchlog \
		${@ "-G '${S6_LINUX_INIT_EARLY_GETTY}'" if d.getVar('S6_LINUX_INIT_EARLY_GETTY', True) else ''} \
		-p "${bindir}:${sbindir}${@bb.utils.contains('DISTRO_FEATURES','usrmerge','',':${base_bindir}:${base_sbindir}',d)}" \
		${@ "-D '${S6_LINUX_INIT_INITDEFAULT}'" if d.getVar('S6_LINUX_INIT_INITDEFAULT', True) else ''} \
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
"
RDEPENDS:${PN}:append:class-target = "\
    ${PN}-common \
    ${@ 'initscripts sysvinit-rc' if d.getVar('S6_LINUX_INIT_SERVICE_MANAGER') == 'sysvinit-rc' else ''} \
    ${@ 's6-rc s6-rc-initscripts s6-frontend' if d.getVar('S6_LINUX_INIT_SERVICE_MANAGER') == 's6-rc' else ''} \
"

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

PACKAGE_ARCH = "${MACHINE_ARCH}"

BBCLASSEXTEND = "native"
