# sysvinit is pid 1, so the s6 supervision tree runs under it: s6-svscanboot
# from inittab, and the scan directory s6-linux-init would otherwise set up
SRC_URI += "\
    file://s6-svscanboot \
    file://crash \
    file://finish \
    file://run \
"

do_install:append() {
	install -Dm0755 ${UNPACKDIR}/s6-svscanboot ${D}${sbindir}/s6-svscanboot
	install -Dm0755 ${UNPACKDIR}/crash ${D}${sysconfdir}/s6-linux-init/current/run-image/service/.s6-svscan/crash
	install -Dm0755 ${UNPACKDIR}/finish ${D}${sysconfdir}/s6-linux-init/current/run-image/service/.s6-svscan/finish
	install -Dm0755 ${UNPACKDIR}/run ${D}${sysconfdir}/s6-linux-init/current/run-image/service/s6-svscan-log/run
}

FILES:${PN} += "${sbindir}/s6-svscanboot"

RDEPENDS:${PN} += "s6-linux-init-common execline"
