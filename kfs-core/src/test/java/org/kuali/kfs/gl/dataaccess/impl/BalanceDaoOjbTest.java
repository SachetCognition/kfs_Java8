package org.kuali.kfs.gl.dataaccess.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.dataaccess.BalanceDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.util.Collection;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BalanceDaoOjbTest extends KfsUnitTestBase {

    @Test
    void implementsBalanceDao() {
        assertThat(BalanceDao.class).isAssignableFrom(BalanceDaoOjb.class);
    }

    @Test
    void classHasGetGlSummaryMethod() throws NoSuchMethodException {
        assertThat(BalanceDaoOjb.class.getMethod("getGlSummary", int.class, Collection.class)).isNotNull();
    }

    @Test
    void classHasFindBalanceMethod() throws NoSuchMethodException {
        assertThat(BalanceDaoOjb.class.getMethod("findBalance", Map.class, boolean.class, Collection.class)).isNotNull();
    }

    @Test
    void classHasPurgeMethod() throws NoSuchMethodException {
        assertThat(BalanceDaoOjb.class.getMethod("purgeYearByChart", String.class, int.class)).isNotNull();
    }

    @Test
    void classHasFindBalancesForFiscalYear() throws NoSuchMethodException {
        assertThat(BalanceDaoOjb.class.getMethod("findBalancesForFiscalYear", Integer.class)).isNotNull();
    }

    @Test
    void classHasCountBalancesForFiscalYear() throws NoSuchMethodException {
        assertThat(BalanceDaoOjb.class.getMethod("countBalancesForFiscalYear", Integer.class)).isNotNull();
    }
}
