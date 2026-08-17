inherit s6-rc

SRC_URI += "file://openssh.toml"

S6_RC_SERVICE_FILES = "openssh.toml"

FILES:${PN}-sshd += "${s6_rc_sourcedir}"
