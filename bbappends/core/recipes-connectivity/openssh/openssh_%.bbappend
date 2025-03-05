FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

inherit s6-rc

SRC_URI += "file://openssh.toml"

do_install:append() {
	if [ "${S6_LINUX_INIT_SERVICE_MANAGER}" = "s6-rc" ]; then
		install -D ${UNPACKDIR}/openssh.toml ${D}${s6_rc_sourcedir}/openssh.toml
	fi
}

FILES:${PN}-sshd += "${s6_rc_sourcedir}"
