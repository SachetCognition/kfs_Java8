package org.kuali.kfs.coa.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.AccountingPeriod;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class AccountingPeriodServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private AccountingPeriodServiceImpl accountingPeriodService;

    @Test
    void getAllAccountingPeriods_returnsAll() {
        AccountingPeriod period = new AccountingPeriod();
        when(businessObjectService.findAll(AccountingPeriod.class)).thenReturn(Arrays.asList(period));

        Collection<AccountingPeriod> result = accountingPeriodService.getAllAccountingPeriods();
        assertThat(result).hasSize(1);
    }

    @Test
    void getAllAccountingPeriods_noResults_returnsEmpty() {
        when(businessObjectService.findAll(AccountingPeriod.class))
                .thenReturn(Collections.<AccountingPeriod>emptyList());

        Collection<AccountingPeriod> result = accountingPeriodService.getAllAccountingPeriods();
        assertThat(result).isEmpty();
    }

    @Test
    void getOpenAccountingPeriods_returnsOnlyActive() {
        AccountingPeriod period = new AccountingPeriod();
        when(businessObjectService.findMatchingOrderBy(eq(AccountingPeriod.class), any(Map.class), anyString(), eq(true)))
                .thenReturn(Arrays.asList(period));

        Collection<AccountingPeriod> result = accountingPeriodService.getOpenAccountingPeriods();
        assertThat(result).hasSize(1);
    }

    @Test
    void getByPeriodAndYear_returnsMatchingPeriod() {
        AccountingPeriod expected = new AccountingPeriod();
        when(businessObjectService.findByPrimaryKey(eq(AccountingPeriod.class), any(Map.class))).thenReturn(expected);

        AccountingPeriod result = accountingPeriodService.getByPeriod("01", 2024);
        assertThat(result).isSameAs(expected);
    }

    @Test
    void getByPeriodAndYear_notFound_returnsNull() {
        when(businessObjectService.findByPrimaryKey(eq(AccountingPeriod.class), any(Map.class))).thenReturn(null);

        AccountingPeriod result = accountingPeriodService.getByPeriod("99", 2024);
        assertThat(result).isNull();
    }
}
