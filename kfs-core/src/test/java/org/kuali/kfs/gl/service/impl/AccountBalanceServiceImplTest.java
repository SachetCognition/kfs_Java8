package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.dataaccess.AccountBalanceDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AccountBalanceServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private AccountBalanceServiceImpl accountBalanceService;

    @Mock
    private AccountBalanceDao accountBalanceDao;

    @Mock
    private ConfigurationService kualiConfigurationService;

    @Mock
    private UniversityDateService universityDateService;

    @Test
    void findConsolidatedAvailableAccountBalance_delegatesToDao() {
        Map<String, Object> fieldValues = new HashMap<>();
        fieldValues.put("chartOfAccountsCode", "BL");
        Iterator<?> mockIterator = Collections.emptyIterator();
        when(accountBalanceDao.findConsolidatedAvailableAccountBalance(fieldValues)).thenReturn(mockIterator);

        Iterator<?> result = accountBalanceService.findConsolidatedAvailableAccountBalance(fieldValues);

        assertThat(result).isSameAs(mockIterator);
        verify(accountBalanceDao).findConsolidatedAvailableAccountBalance(fieldValues);
    }

    @Test
    void findAvailableAccountBalance_delegatesToDao() {
        Map<String, Object> fieldValues = new HashMap<>();
        Iterator<?> mockIterator = Collections.emptyIterator();
        when(accountBalanceDao.findAvailableAccountBalance(fieldValues)).thenReturn(mockIterator);

        Iterator<?> result = accountBalanceService.findAvailableAccountBalance(fieldValues);

        assertThat(result).isSameAs(mockIterator);
        verify(accountBalanceDao).findAvailableAccountBalance(fieldValues);
    }

    @Test
    void purgeYearByChart_delegatesToDao() {
        accountBalanceService.purgeYearByChart("BL", 2020);

        verify(accountBalanceDao).purgeYearByChart("BL", 2020);
    }
}
