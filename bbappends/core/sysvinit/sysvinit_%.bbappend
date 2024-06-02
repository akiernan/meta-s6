FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
    file://s6-svscanboot \
    file://crash \
    file://finish \
    file://run \
"

do_install:append() {
	if [ "${VIRTUAL-RUNTIME_init_manager}" = "sysvinit" ]; then
		install -Dm0755 ${WORKDIR}/s6-svscanboot ${D}${sbindir}/s6-svscanboot
		install -Dm0755 ${WORKDIR}/crash ${D}${sysconfdir}/s6-linux-init/current/run-image/service/.s6-svscan/crash
		install -Dm0755 ${WORKDIR}/finish ${D}${sysconfdir}/s6-linux-init/current/run-image/service/.s6-svscan/finish
		install -Dm0755 ${WORKDIR}/run ${D}${sysconfdir}/s6-linux-init/current/run-image/service/s6-svscan-log/run
	fi
}

RDEPENDS_${PN} += "\
    ${@ 's6-linux-init-common execline' if d.getVar('VIRTUAL-RUNTIME_init_manager') == 'sysvinit' else '' } \
"

FILES_${PN} += "\
    ${sbindir}/s6-svscanboot \
"
