# sysvinit is pid 1, so inittab is where the s6 supervision tree gets started
# and where s6-rc waits for it to come up
do_install:append() {
	sed -e '/^~~:/a\\n# Start the s6 supervision tree.\ns6:12345:respawn:/usr/sbin/s6-svscanboot\n\n# Wait for the supervision tree to be operational before launching services.\ns6n:12345:wait:/bin/sh -c "until test -s /run/s6_ready ; do sleep .1 ; done ; test -d /etc/s6-rc/compiled/current && s6-rc-init /run/service"' -i ${D}${sysconfdir}/inittab
}

RDEPENDS:${PN} += "s6 s6-rc execline"
