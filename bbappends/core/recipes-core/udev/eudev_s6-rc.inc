inherit s6-rc

SRC_URI += "file://udev.toml"

S6_RC_SERVICE_FILES = "udev.toml"
