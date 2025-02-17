FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

do_install:append() {
	if [ "${VIRTUAL-RUNTIME_init_manager}" = "sysvinit" ]; then
		sed -e '/^~~:/a\\n# Start the s6 supervision tree.\ns6:12345:respawn:/usr/sbin/s6-svscanboot\n\n# Wait for the supervision tree to be operational before launching services.\ns6n:12345:wait:/bin/sh -c "until test -s /run/s6_ready ; do sleep .1 ; done ; s6-rc-init /run/service"' -i ${D}${sysconfdir}/inittab
	fi
}

RDEPENDS:${PN} += "${@ 's6 s6-rc execline' if d.getVar('VIRTUAL-RUNTIME_init_manager') == 'sysvinit' else '' }"
