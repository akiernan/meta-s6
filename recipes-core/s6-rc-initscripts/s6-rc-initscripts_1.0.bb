LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

INHIBIT_DEFAULT_DEPS = "1"

SRC_URI = "\
    file://00.toml \
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
	install -m 0644 00.toml init-dev.toml init-disks.toml init-tty.toml klogd.toml  \
		mount-dev-hugepages.toml mount-dev-mqueue.toml mount-dev-pts.toml  \
		mount-proc-sys-fs-binfmt_misc.toml mount-proc.toml mount-sys-fs-bpf.toml  \
		mount-sys-fs-cgroup.toml mount-sys-fs-fuse-connections.toml  \
		mount-sys-fs-pstore.toml mount-sys-kernel-config.toml  \
		mount-sys-kernel-debug.toml mount-sys-kernel-tracing.toml mount-sys.toml  \
		mount-tmp.toml mount-var-volatile.toml  \
		populate-volatile.toml rngseed.toml set-date.toml syslogd.toml \
		${D}${s6_rc_sourcedir}
}

# initscripts only splits populate-volatile.sh out for us when s6-rc is the
# service manager, so ask for it under the same condition the split is made.
# Resolve that here rather than inline in RDEPENDS, where inline python leaves
# do_package with a basehash which does not survive being recomputed.
S6_RC_POPULATE_VOLATILE := "${@bb.utils.contains('S6_LINUX_INIT_SERVICE_MANAGER', 's6-rc', 'initscripts-populate-volatile', '', d)}"

RDEPENDS:${PN} += "\
    s6-frontend \
    s6-linux-utils \
    s6-portable-utils \
    s6-rc-getty-generator \
    ${S6_RC_POPULATE_VOLATILE} \
"
