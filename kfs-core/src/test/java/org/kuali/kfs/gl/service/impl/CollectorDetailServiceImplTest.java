package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.dataaccess.CollectorDetailDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CollectorDetailServiceImplTest extends KfsUnitTestBase {

    @Mock
    private CollectorDetailDao collectorDetailDao;

    @InjectMocks
    private CollectorDetailServiceImpl collectorDetailService;

    @Test
    void purgeYearByChart_delegatesToDao() {
        collectorDetailService.purgeYearByChart("BL", 2024);
        verify(collectorDetailDao).purgeYearByChart("BL", 2024);
    }

    @Test
    void getNextCreateSequence_delegatesToDao() {
        Date date = new Date(System.currentTimeMillis());
        when(collectorDetailDao.getMaxCreateSequence(date)).thenReturn(Integer.valueOf(5));

        Integer result = collectorDetailService.getNextCreateSequence(date);
        assertThat(result).isEqualTo(Integer.valueOf(5));
    }
}
