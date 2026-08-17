require ${@bb.utils.contains('VIRTUAL-RUNTIME_init_manager', 'sysvinit', '${BPN}_s6-svscan.inc', '', d)}
