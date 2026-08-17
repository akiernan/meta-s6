do_install:append () {
	# These are all artefacts of how sysvinit brings the system down, they conflict with
	# s6-linux-init, so remove them
	rm ${D}${sysconfdir}/rc[016].d/[SK][0-9][0-9]halt ${D}${sysconfdir}/init.d/halt
	rm ${D}${sysconfdir}/rc[016].d/[SK][0-9][0-9]reboot ${D}${sysconfdir}/init.d/reboot
	rm ${D}${sysconfdir}/rc[016].d/[SK][0-9][0-9]sendsigs ${D}${sysconfdir}/init.d/sendsigs
	rm ${D}${sysconfdir}/rc[016].d/[SK][0-9][0-9]umountfs ${D}${sysconfdir}/init.d/umountfs
}
