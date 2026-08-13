# enable s6-linux-init/s6-rc for init
DISTRO_FEATURES_OPTED_OUT:append = " sysvinit systemd"
VIRTUAL-RUNTIME_init_manager ??= "s6-linux-init"
VIRTUAL-RUNTIME_initscripts ??= ""
VIRTUAL-RUNTIME_login_manager ??= "busybox"
VIRTUAL-RUNTIME_dev_manager ??= "udev"
S6_LINUX_INIT_SERVICE_MANAGER ??= "s6-rc"
