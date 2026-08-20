inherit s6-rc

SRC_URI += "file://psplash.toml"

S6_RC_SERVICE_FILES = "psplash.toml"

# the service definition is execline, so it needs the tools it names
RDEPENDS:${PN} += "execline s6-portable-utils s6-linux-utils"
