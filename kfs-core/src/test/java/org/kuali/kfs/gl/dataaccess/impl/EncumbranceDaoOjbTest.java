package org.kuali.kfs.gl.dataaccess.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.businessobject.Encumbrance;
import org.kuali.kfs.gl.dataaccess.EncumbranceDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class EncumbranceDaoOjbTest extends KfsUnitTestBase {

    @Test
    void implementsEncumbranceDao() {
        assertThat(EncumbranceDao.class).isAssignableFrom(EncumbranceDaoOjb.class);
    }

    @Test
    void classHasExpectedMethods() throws NoSuchMethodException {
        assertThat(EncumbranceDaoOjb.class.getMethod("purgeYearByChart", String.class, int.class)).isNotNull();
        assertThat(EncumbranceDaoOjb.class.getMethod("getAllEncumbrances")).isNotNull();
        assertThat(EncumbranceDaoOjb.class.getMethod("findOpenEncumbrance", java.util.Map.class, boolean.class)).isNotNull();
        assertThat(EncumbranceDaoOjb.class.getMethod("getOpenEncumbranceRecordCount", java.util.Map.class, boolean.class)).isNotNull();
    }
}
