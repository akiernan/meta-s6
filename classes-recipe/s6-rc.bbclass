inherit python3native

# The package-manager service store, per s6-frontend's default storelist;
# the local admin store is ${sysconfdir}/s6/sources
s6_rc_sourcedir = "${datadir}/s6/sources"

FILES:${PN} += "${s6_rc_sourcedir}"

DEPENDS += "s6-rc execline s6-portable-utils"

PACKAGE_WRITE_DEPS += "s6-rc-native"

# On-device database updates go through the s6-rc repository: sync it
# with the service stores (creating it on first use), then commit and
# install the working set; its auto-generated default bundle is built from
# the flag-essential and flag-recommended markers in the stores.
s6_rc_source_postinst() {
if test -n "$D"; then
	$INTERCEPT_DIR/postinst_intercept update_s6_db ${PKG} mlprefix=${MLPREFIX} binprefix=${MLPREFIX}
else
	if test -d ${localstatedir}/lib/s6-rc/repository; then
		s6-rc-repo-sync
	else
		mkdir -p ${localstatedir}/lib/s6-rc
		s6-rc-repo-init ${datadir}/s6/sources ${sysconfdir}/s6/sources
		s6-rc-set-new current
	fi
	s6-rc-set-fix -u current
	s6-rc-set-commit current
	s6-rc-set-install -b current
fi
}

s6_rc_source_postrm() {
if test -n "$D"; then
	$INTERCEPT_DIR/postinst_intercept update_s6_db ${PKG} mlprefix=${MLPREFIX} binprefix=${MLPREFIX}
else
	if test -d ${localstatedir}/lib/s6-rc/repository; then
		s6-rc-repo-sync
	else
		mkdir -p ${localstatedir}/lib/s6-rc
		s6-rc-repo-init ${datadir}/s6/sources ${sysconfdir}/s6/sources
		s6-rc-set-new current
	fi
	s6-rc-set-fix -u current
	s6-rc-set-commit current
	s6-rc-set-install -b current
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

s6_rc_generate_services () {
	nativepython3 - <<EOF
import tomllib

from collections.abc import MutableMapping
from pathlib import Path

def flatten(dictionary, parent_key=list()):
    items = []
    for key, value in dictionary.items():
        new_key = parent_key + [key]
        if isinstance(value, MutableMapping):
            items.extend(flatten(value, new_key))
        else:
            items.append((new_key, value))
    return items

for path in Path("${D}${s6_rc_sourcedir}").glob("*.toml"):
    with path.open(mode="rb") as f:
        data = tomllib.load(f)

        for k, v in flatten(data):
            p = Path("${D}${s6_rc_sourcedir}").joinpath(*k)
            p.parent.mkdir(parents=True, exist_ok=True)
            if isinstance(v, bool):
                # flags are tested for presence, so false means no file
                if v:
                    p.open(mode="w").close()
            else:
                if isinstance(v, list):
                    v = "\n".join(v)
                elif isinstance(v, int):
                    v = f"{v}"
                if not v.endswith("\n"):
                    v += "\n"
                p.write_text(v)

    path.unlink()
EOF
}

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
do_install[postfuncs] += "s6_rc_generate_services s6_rc_explode_dirfiles"
