package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.service.AccountingPeriodService;
import org.kuali.kfs.gl.businessobject.Reversal;
import org.kuali.kfs.gl.dataaccess.ReversalDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ReversalServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ReversalDao reversalDao;
    @Mock
    private AccountingPeriodService accountingPeriodService;
    @Mock
    private UniversityDateService universityDateService;

    @InjectMocks
    private ReversalServiceImpl reversalService;

    @Test
    void delete_delegatesToDao() {
        Reversal reversal = new Reversal();
        reversalService.delete(reversal);
        verify(reversalDao).delete(reversal);
    }

    @Test
    void getByDate_delegatesToDao() {
        Date date = new Date();
        Iterator expected = Collections.emptyIterator();
        when(reversalDao.getByDate(date)).thenReturn(expected);

        Iterator result = reversalService.getByDate(date);
        assertThat(result).isSameAs(expected);
    }
}
