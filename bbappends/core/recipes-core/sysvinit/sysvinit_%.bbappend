FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
    file://s6-svscanboot \
    file://crash \
    file://finish \
    file://run \
"

do_install:append() {
	if [ "${VIRTUAL-RUNTIME_init_manager}" = "sysvinit" ]; then
		install -Dm0755 ${UNPACKDIR}/s6-svscanboot ${D}${sbindir}/s6-svscanboot
		install -Dm0755 ${UNPACKDIR}/crash ${D}${sysconfdir}/s6-linux-init/current/run-image/service/.s6-svscan/crash
		install -Dm0755 ${UNPACKDIR}/finish ${D}${sysconfdir}/s6-linux-init/current/run-image/service/.s6-svscan/finish
		install -Dm0755 ${UNPACKDIR}/run ${D}${sysconfdir}/s6-linux-init/current/run-image/service/s6-svscan-log/run
	fi
}

PACKAGE_BEFORE_PN = "${PN}-rc"
FILES:${PN}-rc = "${sysconfdir}/init.d/rc ${sysconfdir}/init.d/rcS"

RDEPENDS:${PN} += "\
    ${PN}-rc \
    ${@ 's6-linux-init-common execline' if d.getVar('VIRTUAL-RUNTIME_init_manager') == 'sysvinit' else '' } \
"

FILES:${PN} += "\
    ${@ '${sbindir}/s6-svscanboot' if d.getVar('VIRTUAL-RUNTIME_init_manager') == 'sysvinit' else '' } \
"
