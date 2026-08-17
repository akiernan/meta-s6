inherit s6-rc

SRC_URI += "file://wpa-supplicant.toml"

S6_RC_SERVICE_FILES = "wpa-supplicant.toml"

# the ifupdown hooks conflict with running wpa_supplicant supervised
S6_RC_INSTALL_REMOVE = "\
    ${sysconfdir}/network/if-pre-up.d \
    ${sysconfdir}/network/if-post-down.d \
    ${sysconfdir}/network/if-down.d \
"
