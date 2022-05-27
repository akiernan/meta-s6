CONFIGUREOPTS = " \
    --build=${BUILD_SYS} \
    --host=${HOST_SYS} \
    --target=${TARGET_SYS} \
    --prefix=${root_prefix} \
    --exec-prefix=${exec_prefix} \
    --dynlibdir=${libdir} \
    --includedir=${includedir} \
    --datadir=${sysconfdir} \
    --sysdepdir=${libdir}/${BPN}/sysdeps \
    --enable-shared \
    ${EXTRA_OECONF} \
    ${PACKAGECONFIG_CONFARGS} \
"
CONFIGUREOPTS_append_class-target = "\
    --with-sysdeps=${STAGING_LIBDIR}/skalibs/sysdeps \
    --with-lib=${STAGING_LIBDIR} \
    --with-dynlib=${STAGING_LIBDIR} \
"
CONFIGUREOPTS_append_class-native = "\
    --with-sysdeps=${STAGING_LIBDIR_NATIVE}/skalibs/sysdeps \
    --with-lib=${STAGING_LIBDIR_NATIVE} \
    --with-dynlib=${STAGING_LIBDIR_NATIVE} \
"

do_configure() {
	${S}/configure ${CONFIGUREOPTS}
}

do_compile() {
	oe_runmake
}

do_install() {
	oe_runmake DESTDIR=${D} install
}
