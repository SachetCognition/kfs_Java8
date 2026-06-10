package org.kuali.kfs.coa.service.impl;

import java.sql.Date;
import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.AccountingPeriod;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class AccountingPeriodServiceImplTest extends KfsUnitTestBase {

    @Mock private BusinessObjectService businessObjectService;
    @Mock private DateTimeService dateTimeService;

    @InjectMocks
    private AccountingPeriodServiceImpl service;

    @Nested
    @DisplayName("getAllAccountingPeriods")
    class GetAllTests {

        @Test
        void returnsAllPeriods() {
            AccountingPeriod p1 = new AccountingPeriod();
            p1.setUniversityFiscalPeriodCode("01");
            when(businessObjectService.findAll(AccountingPeriod.class))
                .thenReturn(Collections.singletonList(p1));

            Collection<AccountingPeriod> result = service.getAllAccountingPeriods();

            assertThat(result).hasSize(1);
        }
    }

    @Nested
    @DisplayName("getOpenAccountingPeriods")
    class GetOpenTests {

        @Test
        void returnsOpenPeriodsOrderedByEndDate() {
            AccountingPeriod p = new AccountingPeriod();
            p.setActive(true);
            when(businessObjectService.findMatchingOrderBy(eq(AccountingPeriod.class), any(), any(), eq(true)))
                .thenReturn(Collections.singletonList(p));

            Collection<AccountingPeriod> result = service.getOpenAccountingPeriods();

            assertThat(result).hasSize(1);
        }
    }

    @Nested
    @DisplayName("getByPeriod")
    class GetByPeriodTests {

        @Test
        void returnsPeriodByCodeAndYear() {
            AccountingPeriod expected = new AccountingPeriod();
            expected.setUniversityFiscalPeriodCode("01");
            expected.setUniversityFiscalYear(2025);
            when(businessObjectService.findByPrimaryKey(eq(AccountingPeriod.class), any()))
                .thenReturn(expected);

            AccountingPeriod result = service.getByPeriod("01", 2025);

            assertThat(result).isNotNull();
            assertThat(result.getUniversityFiscalPeriodCode()).isEqualTo("01");
        }

        @Test
        void returnsNullWhenNotFound() {
            when(businessObjectService.findByPrimaryKey(eq(AccountingPeriod.class), any()))
                .thenReturn(null);

            assertThat(service.getByPeriod("99", 9999)).isNull();
        }
    }

    @Nested
    @DisplayName("getByStringDate")
    class GetByStringDateTests {

        @Test
        void throwsWhenDateStringIsInvalid() throws Exception {
            when(dateTimeService.convertToSqlDate("not-a-date"))
                .thenThrow(new java.text.ParseException("parse error", 0));

            assertThatThrownBy(() -> service.getByStringDate("not-a-date"))
                .isInstanceOf(RuntimeException.class);
        }
    }

    @Nested
    @DisplayName("compareAccountingPeriodsByDate")
    class CompareTests {

        @Test
        void returnsNegativeWhenFirstIsEarlier() {
            AccountingPeriod p1 = new AccountingPeriod();
            p1.setUniversityFiscalPeriodEndDate(Date.valueOf("2025-07-31"));
            AccountingPeriod p2 = new AccountingPeriod();
            p2.setUniversityFiscalPeriodEndDate(Date.valueOf("2025-08-31"));

            assertThat(service.compareAccountingPeriodsByDate(p1, p2)).isNegative();
        }

        @Test
        void returnsPositiveWhenFirstIsLater() {
            AccountingPeriod p1 = new AccountingPeriod();
            p1.setUniversityFiscalPeriodEndDate(Date.valueOf("2025-09-30"));
            AccountingPeriod p2 = new AccountingPeriod();
            p2.setUniversityFiscalPeriodEndDate(Date.valueOf("2025-08-31"));

            assertThat(service.compareAccountingPeriodsByDate(p1, p2)).isPositive();
        }

        @Test
        void returnsZeroWhenEqual() {
            AccountingPeriod p1 = new AccountingPeriod();
            p1.setUniversityFiscalPeriodEndDate(Date.valueOf("2025-07-31"));
            AccountingPeriod p2 = new AccountingPeriod();
            p2.setUniversityFiscalPeriodEndDate(Date.valueOf("2025-07-31"));

            assertThat(service.compareAccountingPeriodsByDate(p1, p2)).isZero();
        }
    }
}
