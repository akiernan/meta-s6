# /OR/ s6_rc_sourcedir = "${nonarch_base_libdir}/s6-rc/source"
s6_rc_sourcedir = "${sysconfdir}/s6-rc/source"

DEPENDS += "s6-rc execline s6-portable-utils"

PACKAGE_WRITE_DEPS += "qemu-native"

s6_rc_source_postinst() {
if test -n "$D"; then
	$INTERCEPT_DIR/postinst_intercept update_s6_db ${PKG} mlprefix=${MLPREFIX} binprefix=${MLPREFIX}
else
	stamp=`s6-clock`
	s6-rc-compile ${sysconfdir}/s6-rc/compiled-$stamp ${s6_rc_sourcedir}
	s6-rc-update ${sysconfdir}/s6-rc/compiled-$stamp
	olddb=`s6-linkname -f ${sysconfdir}/s6-rc/compiled`
	s6-ln -nsf compiled-$stamp ${sysconfdir}/s6-rc/compiled
	rm -rf $olddb
fi
}

s6_rc_source_postrm() {
if test -n "$D"; then
	$INTERCEPT_DIR/postinst_intercept update_s6_db ${PKG} mlprefix=${MLPREFIX} binprefix=${MLPREFIX}
else
	stamp=`s6-clock`
	s6-rc-compile ${sysconfdir}/s6-rc/compiled-$stamp ${s6_rc_sourcedir}
	s6-rc-update ${sysconfdir}/s6-rc/compiled-$stamp
	olddb=`s6-linkname -f ${sysconfdir}/s6-rc/compiled`
	s6-ln -nsf compiled-$stamp ${sysconfdir}/s6-rc/compiled
	rm -rf $olddb
fi
}

s6_rc_populate_packages[vardeps] += "s6_rc_source_postinst s6_rc_source_postrm"

python s6_rc_populate_packages () {
    packages = d.getVar('PACKAGES').split()
    pkgdest =  d.getVar('PKGDEST')
    s6_rc_sourcedir = d.getVar('s6_rc_sourcedir')

    for pkg in packages:
        sysconfdir = d.getVar('sysconfdir')
        source_dir = '{pkgdest}/{pkg}{s6_rc_sourcedir}'.format(**locals())
        if not os.path.exists(source_dir):
            continue

        mlprefix = d.getVar('MLPREFIX') or ""
        rdepends_pkg = 'RDEPENDS:{pkg}'.format(**locals())
        rdepends = d.getVar(rdepends_pkg) or ""
        rdepends += ' {mlprefix}s6-rc {mlprefix}execline {mlprefix}s6-portable-utils'.format(**locals())
        d.setVar(rdepends_pkg, rdepends)

        postinst_pkg = 'pkg_postinst:{pkg}'.format(**locals())
        postinst = d.getVar(postinst_pkg)
        if not postinst:
            postinst = '#!/bin/sh\n'
        postinst += d.getVar('s6_rc_source_postinst')
        d.setVar(postinst_pkg, postinst)

        postrm_pkg = 'pkg_postrm:{pkg}'.format(**locals())
        postrm = d.getVar(postrm_pkg)
        if not postrm:
            postrm = '#!/bin/sh\n'
        postrm += d.getVar('s6_rc_source_postrm')
        d.setVar(postrm_pkg, postrm)
}

PACKAGESPLITFUNCS += " s6_rc_populate_packages"

python s6_rc_explode_dirfiles () {
    from glob import glob
    s6_rc_sourcedir = oe.path.join(d.getVar("D"), d.getVar('s6_rc_sourcedir'))
    for dir in ('contents', 'dependencies'):
        for d in glob("{}/*/{}".format(s6_rc_sourcedir, dir)):
            dirname = "{}.d".format(d)
            os.makedirs(dirname, exist_ok=True)
            with open(d) as f:
                for line in f:
                    dep = line.strip()
                    if dep:
                        with open(oe.path.join(dirname, os.path.basename(dep)), "w"):
                            pass
            os.remove(d)
}
do_install[postfuncs] += "s6_rc_explode_dirfiles"
