do_install:append () {
	if [ "${VIRTUAL-RUNTIME_init_manager}" = "s6-linux-init" ]; then
		# These are all artefacts of how sysvinit brings the system down, they conflict with
		# s6-linux-init, so remove them
		rm ${D}${sysconfdir}/rc[016].d/[SK][0-9][0-9]halt ${D}${sysconfdir}/init.d/halt
		rm ${D}${sysconfdir}/rc[016].d/[SK][0-9][0-9]reboot ${D}${sysconfdir}/init.d/reboot
		rm ${D}${sysconfdir}/rc[016].d/[SK][0-9][0-9]sendsigs ${D}${sysconfdir}/init.d/sendsigs
		rm ${D}${sysconfdir}/rc[016].d/[SK][0-9][0-9]umountfs ${D}${sysconfdir}/init.d/umountfs
	fi
}

PACKAGE_BEFORE_PN += "${PN}-populate-volatile"

FILES:${PN}-populate-volatile = "\
    ${sysconfdir}/default/volatiles \
    ${sysconfdir}/init.d/populate-volatile.sh \
    ${sysconfdir}/rc[0-6S].d/[SK][0-9][0-9]populate-volatile.sh \
"
RDEPENDS:${PN} += "${PN}-populate-volatile"

pkg_postinst:${PN}-populate-volatile () {
	if type systemctl >/dev/null 2>/dev/null; then
		if [ -n "$D" ]; then
			OPTS="--root=$D"
		fi
		systemctl $OPTS mask populate-volatile.service
	fi

	# Delete any old volatile cache script, as directories may have moved
	if [ -z "$D" ]; then
		rm -f "${sysconfdir}/volatile.cache"
	fi
}
MASKED_SCRIPTS:remove = "populate-volatile"
