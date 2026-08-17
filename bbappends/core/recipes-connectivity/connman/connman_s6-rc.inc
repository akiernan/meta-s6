inherit s6-rc

# --noperror only matters to a connmand supervised in the foreground, so the
# patch belongs with the service definition rather than with the recipe
SRC_URI += "\
    file://0001-main-Add-noperror-to-disable-LOG_PERROR-with-nodaemo.patch \
    file://connman.toml \
"

S6_RC_SERVICE_FILES = "connman.toml"
