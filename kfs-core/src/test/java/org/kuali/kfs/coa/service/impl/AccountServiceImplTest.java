package org.kuali.kfs.coa.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.coa.businessobject.AccountDelegate;
import org.kuali.kfs.coa.dataaccess.AccountDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.doctype.DocumentTypeService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AccountServiceImplTest extends KfsUnitTestBase {

    @Mock private AccountDao accountDao;
    @Mock private DateTimeService dateTimeService;
    @Mock private BusinessObjectService businessObjectService;
    @Mock private ParameterService parameterService;
    @Mock private DocumentTypeService documentTypeService;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Nested
    @DisplayName("getByPrimaryId")
    class GetByPrimaryIdTests {

        @Test
        void returnsAccountWhenFound() {
            Account expected = new Account();
            expected.setChartOfAccountsCode("BL");
            expected.setAccountNumber("1234567");
            when(businessObjectService.findByPrimaryKey(eq(Account.class), any())).thenReturn(expected);

            Account result = accountService.getByPrimaryId("BL", "1234567");

            assertThat(result).isNotNull();
            assertThat(result.getChartOfAccountsCode()).isEqualTo("BL");
            assertThat(result.getAccountNumber()).isEqualTo("1234567");
        }

        @Test
        void returnsNullWhenNotFound() {
            when(businessObjectService.findByPrimaryKey(eq(Account.class), any())).thenReturn(null);

            Account result = accountService.getByPrimaryId("XX", "9999999");

            assertThat(result).isNull();
        }
    }

    @Nested
    @DisplayName("getByPrimaryIdWithCaching")
    class GetByPrimaryIdWithCachingTests {

        @Test
        void returnsNullWhenAccountNotFound() {
            when(businessObjectService.findByPrimaryKey(eq(Account.class), any())).thenReturn(null);

            Account result = accountService.getByPrimaryIdWithCaching("XX", "0000000");

            assertThat(result).isNull();
        }
    }

    @Nested
    @DisplayName("getAccountsThatUserIsResponsibleFor")
    class AccountsForUserTests {

        @Test
        void delegatesToDao() {
            Person person = mock(Person.class);
            Date currentDate = Date.valueOf("2025-06-01");
            when(dateTimeService.getCurrentDate()).thenReturn(currentDate);
            List<Account> expected = Collections.singletonList(new Account());
            when(accountDao.getAccountsThatUserIsResponsibleFor(person, currentDate)).thenReturn(expected);

            List result = accountService.getAccountsThatUserIsResponsibleFor(person);

            assertThat(result).hasSize(1);
        }
    }

    @Nested
    @DisplayName("hasResponsibilityOnAccount")
    class HasResponsibilityTests {

        @Test
        void delegatesToDao() {
            Person person = mock(Person.class);
            Account account = new Account();
            Date sqlDate = Date.valueOf("2025-06-01");
            when(dateTimeService.getCurrentSqlDate()).thenReturn(sqlDate);
            when(accountDao.determineUserResponsibilityOnAccount(person, account, sqlDate)).thenReturn(true);

            boolean result = accountService.hasResponsibilityOnAccount(person, account);

            assertThat(result).isTrue();
        }

        @Test
        void returnsFalseWhenNoResponsibility() {
            Person person = mock(Person.class);
            Account account = new Account();
            Date sqlDate = Date.valueOf("2025-06-01");
            when(dateTimeService.getCurrentSqlDate()).thenReturn(sqlDate);
            when(accountDao.determineUserResponsibilityOnAccount(person, account, sqlDate)).thenReturn(false);

            boolean result = accountService.hasResponsibilityOnAccount(person, account);

            assertThat(result).isFalse();
        }
    }

    @Nested
    @DisplayName("getPrimaryDelegationByExample")
    class PrimaryDelegationTests {

        @Test
        void returnsNullWhenNoDelegationsFound() {
            AccountDelegate example = new AccountDelegate();
            example.setFinancialDocumentTypeCode("DI");
            Date sqlDate = Date.valueOf("2025-06-01");
            when(dateTimeService.getCurrentSqlDate()).thenReturn(sqlDate);
            when(accountDao.getPrimaryDelegationByExample(eq(example), eq(sqlDate), anyString()))
                .thenReturn(new ArrayList<>());

            AccountDelegate result = accountService.getPrimaryDelegationByExample(example, "1000.00");

            assertThat(result).isNull();
        }

        @Test
        void returnsNonRootDelegateFirst() {
            AccountDelegate example = new AccountDelegate();
            example.setFinancialDocumentTypeCode("DI");
            Date sqlDate = Date.valueOf("2025-06-01");
            when(dateTimeService.getCurrentSqlDate()).thenReturn(sqlDate);

            AccountDelegate rootDelegate = new AccountDelegate();
            rootDelegate.setFinancialDocumentTypeCode("ALL");
            AccountDelegate specificDelegate = new AccountDelegate();
            specificDelegate.setFinancialDocumentTypeCode("DI");

            List<AccountDelegate> delegations = Arrays.asList(rootDelegate, specificDelegate);
            when(accountDao.getPrimaryDelegationByExample(eq(example), eq(sqlDate), anyString()))
                .thenReturn(delegations);

            AccountDelegate result = accountService.getPrimaryDelegationByExample(example, "1000.00");

            assertThat(result).isNotNull();
            assertThat(result.getFinancialDocumentTypeCode()).isEqualTo("DI");
        }
    }

    @Nested
    @DisplayName("getSecondaryDelegationsByExample")
    class SecondaryDelegationTests {

        @Test
        void returnsEmptyListWhenNoDelegationsFound() {
            AccountDelegate example = new AccountDelegate();
            example.setFinancialDocumentTypeCode("DI");
            Date sqlDate = Date.valueOf("2025-06-01");
            when(dateTimeService.getCurrentSqlDate()).thenReturn(sqlDate);
            when(accountDao.getSecondaryDelegationsByExample(eq(example), eq(sqlDate), anyString()))
                .thenReturn(new ArrayList<>());

            List result = accountService.getSecondaryDelegationsByExample(example, "500.00");

            assertThat(result).isEmpty();
        }
    }

    @Nested
    @DisplayName("getAllAccounts")
    class GetAllAccountsTests {

        @Test
        void returnsIteratorFromDao() {
            List<Account> accounts = Arrays.asList(new Account(), new Account());
            when(accountDao.getAllAccounts()).thenReturn(accounts.iterator());

            Iterator result = accountService.getAllAccounts();

            assertThat(result).isNotNull();
            int count = 0;
            while (result.hasNext()) { result.next(); count++; }
            assertThat(count).isEqualTo(2);
        }
    }

    @Nested
    @DisplayName("Account access delegations to DAO")
    class DaoAccessTests {

        @Test
        void getActiveAccountsForAccountSupervisor() {
            Date sqlDate = Date.valueOf("2025-06-01");
            when(dateTimeService.getCurrentSqlDate()).thenReturn(sqlDate);
            when(accountDao.getActiveAccountsForAccountSupervisor("P001", sqlDate))
                .thenReturn(Collections.<Account>emptyList().iterator());

            Iterator<Account> result = accountService.getActiveAccountsForAccountSupervisor("P001");
            assertThat(result).isNotNull();
        }

        @Test
        void getActiveAccountsForFiscalOfficer() {
            Date sqlDate = Date.valueOf("2025-06-01");
            when(dateTimeService.getCurrentSqlDate()).thenReturn(sqlDate);
            when(accountDao.getActiveAccountsForFiscalOfficer("P002", sqlDate))
                .thenReturn(Collections.<Account>emptyList().iterator());

            Iterator<Account> result = accountService.getActiveAccountsForFiscalOfficer("P002");
            assertThat(result).isNotNull();
        }

        @Test
        void getExpiredAccountsForAccountSupervisor() {
            Date sqlDate = Date.valueOf("2025-06-01");
            when(dateTimeService.getCurrentSqlDate()).thenReturn(sqlDate);
            when(accountDao.getExpiredAccountsForAccountSupervisor("P001", sqlDate))
                .thenReturn(Collections.<Account>emptyList().iterator());

            Iterator<Account> result = accountService.getExpiredAccountsForAccountSupervisor("P001");
            assertThat(result).isNotNull();
        }

        @Test
        void getExpiredAccountsForFiscalOfficer() {
            Date sqlDate = Date.valueOf("2025-06-01");
            when(dateTimeService.getCurrentSqlDate()).thenReturn(sqlDate);
            when(accountDao.getExpiredAccountsForFiscalOfficer("P002", sqlDate))
                .thenReturn(Collections.<Account>emptyList().iterator());

            Iterator<Account> result = accountService.getExpiredAccountsForFiscalOfficer("P002");
            assertThat(result).isNotNull();
        }

        @Test
        void isPrincipalInAnyWayShapeOrFormAccountManager() {
            when(accountDao.isPrincipalInAnyWayShapeOrFormAccountManager("P001")).thenReturn(true);
            assertThat(accountService.isPrincipalInAnyWayShapeOrFormAccountManager("P001")).isTrue();
        }

        @Test
        void isPrincipalInAnyWayShapeOrFormAccountSupervisor() {
            when(accountDao.isPrincipalInAnyWayShapeOrFormAccountSupervisor("P002")).thenReturn(false);
            assertThat(accountService.isPrincipalInAnyWayShapeOrFormAccountSupervisor("P002")).isFalse();
        }

        @Test
        void isPrincipalInAnyWayShapeOrFormFiscalOfficer() {
            when(accountDao.isPrincipalInAnyWayShapeOrFormFiscalOfficer("P003")).thenReturn(true);
            assertThat(accountService.isPrincipalInAnyWayShapeOrFormFiscalOfficer("P003")).isTrue();
        }

        @Test
        void getAccountsForAccountNumber() {
            Collection<Account> accounts = Collections.singletonList(new Account());
            when(accountDao.getAccountsForAccountNumber("1234567")).thenReturn(accounts);

            Collection<Account> result = accountService.getAccountsForAccountNumber("1234567");
            assertThat(result).hasSize(1);
        }
    }

    @Nested
    @DisplayName("getDefaultLaborBenefitRateCategoryCodeForAccountType")
    class LaborBenefitTests {

        @Test
        void returnsSubParameterValueWhenPresent() {
            when(parameterService.getSubParameterValueAsString(
                eq(Account.class), eq("DEFAULT_BENEFIT_RATE_CATEGORY_CODE_BY_ACCOUNT_TYPE"), eq("EX")))
                .thenReturn("LC");

            String result = accountService.getDefaultLaborBenefitRateCategoryCodeForAccountType("EX");

            assertThat(result).isEqualTo("LC");
        }

        @Test
        void fallsBackToDefaultParameterWhenSubParameterBlank() {
            when(parameterService.getSubParameterValueAsString(
                eq(Account.class), eq("DEFAULT_BENEFIT_RATE_CATEGORY_CODE_BY_ACCOUNT_TYPE"), eq("EX")))
                .thenReturn("");
            when(parameterService.getParameterValueAsString(eq(Account.class), anyString()))
                .thenReturn("DEFAULT");

            String result = accountService.getDefaultLaborBenefitRateCategoryCodeForAccountType("EX");

            assertThat(result).isEqualTo("DEFAULT");
        }

        @Test
        void returnsEmptyStringWhenBothNull() {
            when(parameterService.getSubParameterValueAsString(
                eq(Account.class), eq("DEFAULT_BENEFIT_RATE_CATEGORY_CODE_BY_ACCOUNT_TYPE"), eq("EX")))
                .thenReturn(null);
            when(parameterService.getParameterValueAsString(eq(Account.class), anyString()))
                .thenReturn(null);

            String result = accountService.getDefaultLaborBenefitRateCategoryCodeForAccountType("EX");

            assertThat(result).isEmpty();
        }
    }
}
