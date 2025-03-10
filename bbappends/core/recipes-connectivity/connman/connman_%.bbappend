FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

inherit s6-rc

SRC_URI += "\
    file://0001-main-Add-noperror-to-disable-LOG_PERROR-with-nodaemo.patch \
    file://connman.toml \
"

do_install:append() {
	if [ "${S6_LINUX_INIT_SERVICE_MANAGER}" = "s6-rc" ]; then
		install -D ${UNPACKDIR}/connman.toml ${D}${s6_rc_sourcedir}/connman.toml
	fi
}
