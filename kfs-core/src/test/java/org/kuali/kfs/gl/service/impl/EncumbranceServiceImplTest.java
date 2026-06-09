package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.dataaccess.EncumbranceDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EncumbranceServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private EncumbranceServiceImpl encumbranceService;

    @Mock
    private EncumbranceDao encumbranceDao;

    @Test
    void purgeYearByChart_delegatesToDao() {
        encumbranceService.purgeYearByChart("BL", 2020);

        verify(encumbranceDao).purgeYearByChart("BL", 2020);
    }

    @Test
    void getAllEncumbrances_delegatesToDao() {
        Iterator<?> mockIterator = Collections.emptyIterator();
        when(encumbranceDao.getAllEncumbrances()).thenReturn(mockIterator);

        Iterator<?> result = encumbranceService.getAllEncumbrances();

        assertThat(result).isSameAs(mockIterator);
        verify(encumbranceDao).getAllEncumbrances();
    }

    @Test
    void getSummarizedEncumbrances_included_delegatesToDao() {
        Iterator<?> mockIterator = Collections.emptyIterator();
        when(encumbranceDao.getSummarizedEncumbrances("GLPE", true)).thenReturn(mockIterator);

        Iterator<?> result = encumbranceService.getSummarizedEncumbrances("GLPE", true);

        assertThat(result).isSameAs(mockIterator);
        verify(encumbranceDao).getSummarizedEncumbrances("GLPE", true);
    }

    @Test
    void getSummarizedEncumbrances_excluded_delegatesToDao() {
        Iterator<?> mockIterator = Collections.emptyIterator();
        when(encumbranceDao.getSummarizedEncumbrances("GLPE", false)).thenReturn(mockIterator);

        Iterator<?> result = encumbranceService.getSummarizedEncumbrances("GLPE", false);

        assertThat(result).isSameAs(mockIterator);
        verify(encumbranceDao).getSummarizedEncumbrances("GLPE", false);
    }

    @Test
    void findOpenEncumbrance_delegatesToDao() {
        Map<String, Object> fieldValues = new HashMap<>();
        fieldValues.put("chartOfAccountsCode", "BL");
        Iterator<?> mockIterator = Collections.emptyIterator();
        when(encumbranceDao.findOpenEncumbrance(fieldValues, true)).thenReturn(mockIterator);

        Iterator<?> result = encumbranceService.findOpenEncumbrance(fieldValues, true);

        assertThat(result).isSameAs(mockIterator);
    }

    @Test
    void findOpenEncumbrance_excludeZero_delegatesToDao() {
        Map<String, Object> fieldValues = new HashMap<>();
        Iterator<?> mockIterator = Collections.emptyIterator();
        when(encumbranceDao.findOpenEncumbrance(fieldValues, false)).thenReturn(mockIterator);

        Iterator<?> result = encumbranceService.findOpenEncumbrance(fieldValues, false);

        assertThat(result).isSameAs(mockIterator);
    }

    @Test
    void getOpenEncumbranceRecordCount_delegatesToDao() {
        Map<String, Object> fieldValues = new HashMap<>();
        when(encumbranceDao.getOpenEncumbranceRecordCount(fieldValues, true)).thenReturn(5);

        Integer count = encumbranceService.getOpenEncumbranceRecordCount(fieldValues, true);

        assertThat(count).isEqualTo(5);
    }

    @Test
    void hasSummarizedOpenEncumbranceRecords_returnsTrue_delegatesToDao() {
        Map<String, Object> fieldValues = new HashMap<>();
        when(encumbranceDao.hasSummarizedOpenEncumbranceRecords(fieldValues, false)).thenReturn(true);

        boolean result = encumbranceService.hasSummarizedOpenEncumbranceRecords(fieldValues, false);

        assertThat(result).isTrue();
    }

    @Test
    void hasSummarizedOpenEncumbranceRecords_returnsFalse_delegatesToDao() {
        Map<String, Object> fieldValues = new HashMap<>();
        when(encumbranceDao.hasSummarizedOpenEncumbranceRecords(fieldValues, true)).thenReturn(false);

        boolean result = encumbranceService.hasSummarizedOpenEncumbranceRecords(fieldValues, true);

        assertThat(result).isFalse();
    }
}
