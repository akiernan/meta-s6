inherit s6-rc

SRC_URI += "file://dbus.toml"

S6_RC_SERVICE_FILES = "dbus.toml"
