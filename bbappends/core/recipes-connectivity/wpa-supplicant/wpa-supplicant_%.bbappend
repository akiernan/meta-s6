FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

inherit s6-rc

SRC_URI += "file://wpa-supplicant.toml"

do_install:append() {
	if [ "${S6_LINUX_INIT_SERVICE_MANAGER}" = "s6-rc" ]; then
		rm -rf ${D}${sysconfdir}/network/if-pre-up.d ${D}${sysconfdir}/network/if-post-down.d \
			${D}${sysconfdir}/network/if-down.d

		install -D ${UNPACKDIR}/wpa-supplicant.toml ${D}${s6_rc_sourcedir}/wpa-supplicant.toml
	fi
}
