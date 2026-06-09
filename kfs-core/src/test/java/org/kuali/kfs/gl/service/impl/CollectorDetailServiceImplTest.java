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

    @InjectMocks
    private CollectorDetailServiceImpl collectorDetailService;

    @Mock
    private CollectorDetailDao collectorDetailDao;

    @Test
    void purgeYearByChart_delegatesToDao() {
        collectorDetailService.purgeYearByChart("BL", 2020);

        verify(collectorDetailDao).purgeYearByChart("BL", 2020);
    }

    @Test
    void purgeYearByChart_withDifferentParams_delegatesToDao() {
        collectorDetailService.purgeYearByChart("UA", 2023);

        verify(collectorDetailDao).purgeYearByChart("UA", 2023);
    }

    @Test
    void getNextCreateSequence_delegatesToDao() {
        Date date = Date.valueOf("2024-01-15");
        when(collectorDetailDao.getMaxCreateSequence(date)).thenReturn(42);

        Integer result = collectorDetailService.getNextCreateSequence(date);

        assertThat(result).isEqualTo(42);
        verify(collectorDetailDao).getMaxCreateSequence(date);
    }

    @Test
    void getNextCreateSequence_returnsNull_whenDaoReturnsNull() {
        Date date = Date.valueOf("2024-06-01");
        when(collectorDetailDao.getMaxCreateSequence(date)).thenReturn(null);

        Integer result = collectorDetailService.getNextCreateSequence(date);

        assertThat(result).isNull();
    }
}
