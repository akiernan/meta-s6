SUMMARY = "s6-linux-init - tools to create an s6-based Linux init system"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=c2becd2c2579701b65222d136ce1c138"

DEPENDS = "skalibs execline s6 s6-linux-init-native"

SRC_URI = "https://skarnet.org/software/s6-linux-init/s6-linux-init-${PV}.tar.gz"
SRC_URI[sha256sum] = "c906e57ebfe300dc17cfbfb9c254af59968762dfd162bfe064b4ce2bd695a776"

inherit skarnet useradd

PACKAGE_BEFORE_PN = "${PN}-common"

USERADD_PACKAGES = "${PN}-common"
USERADD_PARAM:${PN}-common = "--system --home /run/uncaught-logs \
                              --no-create-home --shell /bin/false \
                              --user-group catchlog"

do_install:append:class-target () {
	s6-linux-init-maker \
	  -u catchlog \
	  -G "sleep 86400" \
	  -1 \
	  -L \
	  -p "/usr/bin:/usr/sbin:/bin:/sbin" \
	  -m 022 \
	  -s /run/kernel_env \
	  -f "${D}${sysconfdir}/s6-linux-init/skel" \
	  "${D}${sysconfdir}/s6-linux-init/current"

	# this is a horrible frig... we probably really want a cross s6-linux-init-maker
	for f in $(find ${D}${sysconfdir}/s6-linux-init/current/bin ${D}${sysconfdir}/s6-linux-init/current/run-image/service -type f); do
		sed -e "1s:#!.*/execlineb:#!${bindir}/execlineb:" -i ${f}
	done
	ls -lR ${D}${sysconfdir}/s6-linux-init/current
}

split_s6_svscan () {
	# We need to split the files inside .s6-svscan from the directory
	# itself, so we recreate the directory as part of packaging
	install -d -m 0755 ${PKGDEST}/${PN}-common/${sysconfdir}/s6-linux-init/current/run-image/service/.s6-svscan
}
PACKAGEFUNCS += "split_s6_svscan"

FILES:${PN}-common = "\
    ${sysconfdir}/s6-linux-init/current/run-image/service/s6-svscan-log/fifo \
    ${sysconfdir}/s6-linux-init/current/run-image/service/s6-svscan-log/notification-fd \
    ${sysconfdir}/s6-linux-init/current/run-image/uncaught-logs \
"

RDEPENDS:${PN} += "\
    execline \
    s6 \
"
RDEPENDS:${PN}:append:class-target = " ${PN}-common"

RDEPENDS:${PN}-common += "\
    execline \
    s6 \
"

BBCLASSEXTEND = "native"
