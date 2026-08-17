require ${@bb.utils.contains('VIRTUAL-RUNTIME_init_manager', 's6-linux-init', '${BPN}_s6-linux-init.inc', '', d)}
require ${@bb.utils.contains('S6_LINUX_INIT_SERVICE_MANAGER', 's6-rc', '${BPN}_s6-rc.inc', '', d)}
