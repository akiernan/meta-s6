do_install_append () {
	if [ "${VIRTUAL-RUNTIME_init_manager}" = "s6-linux-init" ]; then
		rm ${D}${sysconfdir}/rc[016].d/[SK][0-9][0-9]halt ${D}${sysconfdir}/init.d/halt
		rm ${D}${sysconfdir}/rc[016].d/[SK][0-9][0-9]reboot ${D}${sysconfdir}/init.d/reboot
		rm ${D}${sysconfdir}/rc[016].d/[SK][0-9][0-9]sendsigs ${D}${sysconfdir}/init.d/sendsigs
		rm ${D}${sysconfdir}/rc[016].d/[SK][0-9][0-9]umountfs ${D}${sysconfdir}/init.d/umountfs
	fi
}
