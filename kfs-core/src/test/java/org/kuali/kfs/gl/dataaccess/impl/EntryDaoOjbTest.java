package org.kuali.kfs.gl.dataaccess.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.dataaccess.EntryDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class EntryDaoOjbTest extends KfsUnitTestBase {

    @Test
    void implementsEntryDao() {
        assertThat(EntryDao.class).isAssignableFrom(EntryDaoOjb.class);
    }

    @Test
    void classHasPurgeYearByChartMethod() throws NoSuchMethodException {
        assertThat(EntryDaoOjb.class.getMethod("purgeYearByChart", String.class, int.class)).isNotNull();
    }
}
