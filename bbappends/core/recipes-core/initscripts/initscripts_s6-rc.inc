# s6-rc-initscripts runs populate-volatile.sh from a oneshot of its own, so the
# script has to be installable without the rest of initscripts' boot sequence
PACKAGE_BEFORE_PN += "${PN}-populate-volatile"

FILES:${PN}-populate-volatile = "\
    ${sysconfdir}/default/volatiles \
    ${sysconfdir}/init.d/populate-volatile.sh \
    ${sysconfdir}/rc[0-6S].d/[SK][0-9][0-9]populate-volatile.sh \
"
RDEPENDS:${PN} += "${PN}-populate-volatile"

pkg_postinst:${PN}-populate-volatile () {
	# Delete any old volatile cache script, as directories may have moved
	if [ -z "$D" ]; then
		rm -f "${sysconfdir}/volatile.cache"
	fi
}
