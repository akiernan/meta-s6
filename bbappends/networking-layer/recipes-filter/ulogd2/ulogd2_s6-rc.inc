inherit s6-rc

SRC_URI += "file://ulogd2.toml"

S6_RC_SERVICE_FILES = "ulogd2.toml"
