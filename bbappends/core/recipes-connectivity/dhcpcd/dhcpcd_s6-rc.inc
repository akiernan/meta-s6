inherit s6-rc

SRC_URI += "file://dhcpcd.toml"

S6_RC_SERVICE_FILES = "dhcpcd.toml"
