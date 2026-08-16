inherit s6-rc

SRC_URI += "file://chrony.toml"

S6_RC_SERVICE_FILES = "chrony.toml"
