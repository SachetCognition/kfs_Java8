package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.dataaccess.EncumbranceDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EncumbranceServiceImplTest extends KfsUnitTestBase {

    @Mock
    private EncumbranceDao encumbranceDao;

    @InjectMocks
    private EncumbranceServiceImpl encumbranceService;

    @Test
    void purgeYearByChart_delegatesToDao() {
        encumbranceService.purgeYearByChart("BL", 2024);
        verify(encumbranceDao).purgeYearByChart("BL", 2024);
    }

    @Test
    void getAllEncumbrances_delegatesToDao() {
        Iterator expected = Collections.emptyIterator();
        when(encumbranceDao.getAllEncumbrances()).thenReturn(expected);

        Iterator result = encumbranceService.getAllEncumbrances();
        assertThat(result).isSameAs(expected);
    }
}
