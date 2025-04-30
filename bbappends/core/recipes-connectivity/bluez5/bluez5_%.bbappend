FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

inherit s6-rc

SRC_URI += "\
    file://${BPN}.toml \
"

do_install:append() {
	if [ "${S6_LINUX_INIT_SERVICE_MANAGER}" = "s6-rc" ]; then
		install -D ${UNPACKDIR}/${BPN}.toml ${D}${s6_rc_sourcedir}/${BPN}.toml
	fi
}
