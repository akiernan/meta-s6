# enable s6-linux-init/sysvinit for init
# this is clearly a lie, but the easiest way to ensure we have the necessary
# infrastructure
DISTRO_FEATURES:append = " sysvinit"
DISTRO_FEATURES_BACKFILL_CONSIDERED:append = " systemd"
VIRTUAL-RUNTIME_init_manager ??= "s6-linux-init"
VIRTUAL-RUNTIME_initscripts ??= "initscripts"
VIRTUAL-RUNTIME_login_manager ??= "busybox"
S6_LINUX_INIT_SERVICE_MANAGER ??= "sysvinit-rc"
