FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

require ${@bb.utils.contains('S6_LINUX_INIT_SERVICE_MANAGER', 's6-rc', '${BPN}_s6-rc.inc', '', d)}
