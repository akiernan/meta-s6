LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

INHIBIT_DEFAULT_DEPS = "1"

SRC_URI = "\
    file://00.toml \
    file://default.toml \
    file://klogd.toml \
    file://mount-dev-pts.toml \
    file://mount-dev.toml \
    file://mount-disks.toml \
    file://mount-proc.toml \
    file://mount-sys-configfs.toml \
    file://mount-sys-debugfs.toml \
    file://mount-sys.toml \
    file://ok-all.toml \
    file://ok-init.toml \
    file://ok-lan.toml \
    file://ok-local.toml \
    file://ok-wan.toml \
    file://populate-volatile.toml \
    file://rngseed.toml \
    file://syslogd.toml \
"

S = "${WORKDIR}/sources"
UNPACKDIR = "${S}"

inherit s6-rc

do_install:append() {
	install -d -m 0755 ${D}${s6_rc_sourcedir}
        install -m 0644 00.toml default.toml klogd.toml mount-dev-pts.toml mount-dev.toml mount-disks.toml \
		mount-proc.toml mount-sys-configfs.toml mount-sys-debugfs.toml  mount-sys.toml ok-all.toml \
		ok-init.toml ok-lan.toml ok-local.toml ok-wan.toml populate-volatile.toml rngseed.toml \
		syslogd.toml ${D}${s6_rc_sourcedir}
}

RDEPENDS:${PN} += "s6-linux-utils s6-portable-utils initscripts-populate-volatile s6-rc-serialgetty"
