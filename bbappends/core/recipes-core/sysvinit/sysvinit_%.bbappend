FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

require ${@bb.utils.contains('VIRTUAL-RUNTIME_init_manager', 'sysvinit', '${BPN}_s6-svscan.inc', '', d)}
require ${@bb.utils.contains('S6_LINUX_INIT_SERVICE_MANAGER', 'sysvinit-rc', '${BPN}_sysvinit-rc.inc', '', d)}
