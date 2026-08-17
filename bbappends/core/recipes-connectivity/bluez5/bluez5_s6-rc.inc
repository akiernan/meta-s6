inherit s6-rc

SRC_URI += "file://bluez5.toml"

S6_RC_SERVICE_FILES = "bluez5.toml"
