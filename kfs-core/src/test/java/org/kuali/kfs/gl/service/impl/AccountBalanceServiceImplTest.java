package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.dataaccess.AccountBalanceDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.OptionsService;
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

    @Mock
    private AccountBalanceDao accountBalanceDao;
    @Mock
    private ConfigurationService kualiConfigurationService;
    @Mock
    private UniversityDateService universityDateService;
    @Mock
    private OptionsService optionsService;

    @InjectMocks
    private AccountBalanceServiceImpl accountBalanceService;

    @Test
    void findConsolidatedAvailableAccountBalance_delegatesToDao() {
        Map fieldValues = new HashMap();
        Iterator expected = Collections.emptyIterator();
        when(accountBalanceDao.findConsolidatedAvailableAccountBalance(fieldValues)).thenReturn(expected);

        Iterator result = accountBalanceService.findConsolidatedAvailableAccountBalance(fieldValues);
        assertThat(result).isSameAs(expected);
    }

    @Test
    void findAvailableAccountBalance_delegatesToDao() {
        Map fieldValues = new HashMap();
        Iterator expected = Collections.emptyIterator();
        when(accountBalanceDao.findAvailableAccountBalance(fieldValues)).thenReturn(expected);

        Iterator result = accountBalanceService.findAvailableAccountBalance(fieldValues);
        assertThat(result).isSameAs(expected);
    }

    @Test
    void purgeYearByChart_delegatesToDao() {
        accountBalanceService.purgeYearByChart("BL", 2024);
        verify(accountBalanceDao).purgeYearByChart("BL", 2024);
    }
}
