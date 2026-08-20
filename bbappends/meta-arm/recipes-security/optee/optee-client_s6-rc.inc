FILES:${PN} += "${s6_rc_sourcedir}"

inherit s6-rc

SRC_URI += "file://tee-supplicant.toml"

S6_RC_SERVICE_FILES = "tee-supplicant.toml"
