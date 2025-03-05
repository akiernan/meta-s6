FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

inherit s6-rc

SRC_URI += "file://udev.toml"

do_install:append() {
	if [ "${S6_LINUX_INIT_SERVICE_MANAGER}" = "s6-rc" ]; then
		install -D ${UNPACKDIR}/udev.toml ${D}${s6_rc_sourcedir}/udev.toml
	fi
}
