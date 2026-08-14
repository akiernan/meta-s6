LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

INHIBIT_DEFAULT_DEPS = "1"

SRC_URI = "\
    file://00.toml \
    file://default.toml \
    file://init-dev.toml \
    file://init-disks.toml \
    file://init-tty.toml \
    file://klogd.toml \
    file://mount-dev-hugepages.toml \
    file://mount-dev-mqueue.toml \
    file://mount-dev-pts.toml \
    file://mount-proc-sys-fs-binfmt_misc.toml \
    file://mount-proc.toml \
    file://mount-sys-fs-bpf.toml \
    file://mount-sys-fs-cgroup.toml \
    file://mount-sys-fs-fuse-connections.toml \
    file://mount-sys-fs-pstore.toml \
    file://mount-sys-kernel-config.toml \
    file://mount-sys-kernel-debug.toml \
    file://mount-sys-kernel-tracing.toml \
    file://mount-sys.toml \
    file://mount-tmp.toml \
    file://mount-var-volatile.toml \
    file://ok-all.toml \
    file://ok-init.toml \
    file://ok-lan.toml \
    file://ok-local.toml \
    file://ok-wan.toml \
    file://populate-volatile.toml \
    file://rngseed.toml \
    file://set-date.toml \
    file://syslogd.toml \
"

S = "${UNPACKDIR}"

inherit s6-rc

do_install:append() {
	# the (empty) local admin service store
	install -d -m 0755 ${D}${sysconfdir}/s6/sources

	install -d -m 0755 ${D}${s6_rc_sourcedir}
	install -m 0644 00.toml default.toml init-dev.toml init-disks.toml init-tty.toml klogd.toml  \
		mount-dev-hugepages.toml mount-dev-mqueue.toml mount-dev-pts.toml  \
		mount-proc-sys-fs-binfmt_misc.toml mount-proc.toml mount-sys-fs-bpf.toml  \
		mount-sys-fs-cgroup.toml mount-sys-fs-fuse-connections.toml  \
		mount-sys-fs-pstore.toml mount-sys-kernel-config.toml  \
		mount-sys-kernel-debug.toml mount-sys-kernel-tracing.toml mount-sys.toml  \
		mount-tmp.toml mount-var-volatile.toml ok-all.toml ok-init.toml ok-lan.toml  \
		ok-local.toml ok-wan.toml populate-volatile.toml rngseed.toml set-date.toml syslogd.toml \
		${D}${s6_rc_sourcedir}
}

RDEPENDS:${PN} += "s6-linux-utils s6-portable-utils initscripts-populate-volatile s6-rc-getty-generator"
