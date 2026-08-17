# the sysvinit-rc backend runs /etc/init.d/rc under s6-linux-init, so the two
# scripts have to be installable without sysvinit itself
PACKAGE_BEFORE_PN = "${PN}-rc"
FILES:${PN}-rc = "${sysconfdir}/init.d/rc ${sysconfdir}/init.d/rcS"

RDEPENDS:${PN} += "${PN}-rc"
