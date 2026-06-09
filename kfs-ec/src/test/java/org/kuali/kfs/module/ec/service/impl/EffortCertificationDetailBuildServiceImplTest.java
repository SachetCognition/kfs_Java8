package org.kuali.kfs.module.ec.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.kuali.kfs.coa.businessobject.A21SubAccount;
import org.kuali.kfs.coa.businessobject.SubAccount;
import org.kuali.kfs.coa.service.AccountService;
import org.kuali.kfs.integration.ld.LaborLedgerBalance;
import org.kuali.kfs.module.ec.EffortConstants;
import org.kuali.kfs.module.ec.businessobject.EffortCertificationDetailBuild;
import org.kuali.kfs.module.ec.businessobject.EffortCertificationReportDefinition;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.datadictionary.DataDictionary;

public class EffortCertificationDetailBuildServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private EffortCertificationDetailBuildServiceImpl detailBuildService;

    @Mock
    private LaborLedgerBalance ledgerBalance;

    @Mock
    private EffortCertificationReportDefinition reportDefinition;

    @Mock
    private AccountService accountService;

    @Mock
    private DataDictionaryService dataDictionaryService;

    @Mock
    private DataDictionary dataDictionary;

    @Mock
    private ConfigurationService configurationService;

    private MockedStatic<SpringContext> springContextMock;

    @BeforeEach
    void setUp() {
        springContextMock = Mockito.mockStatic(SpringContext.class);
        springContextMock.when(new MockedStatic.Verification() {
            @Override
            public void apply() throws Throwable {
                SpringContext.getBean(AccountService.class);
            }
        }).thenReturn(accountService);
        springContextMock.when(new MockedStatic.Verification() {
            @Override
            public void apply() throws Throwable {
                SpringContext.getBean(DataDictionaryService.class);
            }
        }).thenReturn(dataDictionaryService);
        springContextMock.when(new MockedStatic.Verification() {
            @Override
            public void apply() throws Throwable {
                SpringContext.getBean(ConfigurationService.class);
            }
        }).thenReturn(configurationService);
        lenient().when(dataDictionaryService.getDataDictionary()).thenReturn(dataDictionary);
        lenient().when(dataDictionaryService.getAttributeMaxLength(any(Class.class), any(String.class))).thenReturn(5);
        lenient().when(accountService.accountsCanCrossCharts()).thenReturn(true);

        when(ledgerBalance.getUniversityFiscalYear()).thenReturn(2014);
        when(ledgerBalance.getAccountNumber()).thenReturn("1234567");
        when(ledgerBalance.getChartOfAccountsCode()).thenReturn("BL");
        when(ledgerBalance.getPositionNumber()).thenReturn("00012345");
        when(ledgerBalance.getFinancialObjectCode()).thenReturn("2400");

        Map<Integer, Set<String>> reportPeriods = new HashMap<Integer, Set<String>>();
        reportPeriods.put(2014, Collections.singleton("01"));
        when(reportDefinition.getReportPeriods()).thenReturn(reportPeriods);

        when(ledgerBalance.getAmountByPeriod("01")).thenReturn(new KualiDecimal(1000));
    }

    @AfterEach
    void tearDown() {
        if (springContextMock != null) {
            springContextMock.close();
        }
    }

    @Test
    void testGenerateDetailBuild_setsBasicFields() {
        EffortCertificationDetailBuild detail = detailBuildService.generateDetailBuild(2014, ledgerBalance, reportDefinition);

        assertNotNull(detail);
        assertEquals(Integer.valueOf(2014), detail.getUniversityFiscalYear());
        assertEquals("1234567", detail.getAccountNumber());
        assertEquals("BL", detail.getChartOfAccountsCode());
        assertEquals("00012345", detail.getPositionNumber());
        assertEquals("2400", detail.getFinancialObjectCode());
    }

    @Test
    void testGenerateDetailBuild_setsPayrollAmounts() {
        EffortCertificationDetailBuild detail = detailBuildService.generateDetailBuild(2014, ledgerBalance, reportDefinition);

        assertEquals(new KualiDecimal(1000), detail.getEffortCertificationPayrollAmount());
        assertEquals(new KualiDecimal(1000), detail.getEffortCertificationOriginalPayrollAmount());
    }

    @Test
    void testGenerateDetailBuild_initializesPercentToZero() {
        EffortCertificationDetailBuild detail = detailBuildService.generateDetailBuild(2014, ledgerBalance, reportDefinition);

        assertEquals(0, detail.getEffortCertificationCalculatedOverallPercent());
        assertEquals(0, detail.getEffortCertificationUpdatedOverallPercent());
    }

    @Test
    void testGenerateDetailBuild_nullSubAccount_setsDashValues() {
        when(ledgerBalance.getSubAccount()).thenReturn(null);

        EffortCertificationDetailBuild detail = detailBuildService.generateDetailBuild(2014, ledgerBalance, reportDefinition);

        assertEquals(KFSConstants.getDashSubAccountNumber(), detail.getSubAccountNumber());
        assertEquals(EffortConstants.DASH_CHART_OF_ACCOUNTS_CODE, detail.getSourceChartOfAccountsCode());
        assertEquals(EffortConstants.DASH_ACCOUNT_NUMBER, detail.getSourceAccountNumber());
        assertNull(detail.getCostShareSourceSubAccountNumber());
    }

    @Test
    void testGenerateDetailBuild_expenseSubAccountType_setsDashValues() {
        SubAccount subAccount = mock(SubAccount.class);
        A21SubAccount a21 = mock(A21SubAccount.class);
        when(ledgerBalance.getSubAccount()).thenReturn(subAccount);
        when(subAccount.getA21SubAccount()).thenReturn(a21);
        when(a21.getSubAccountTypeCode()).thenReturn(EffortConstants.ELIGIBLE_EXPENSE_SUB_ACCOUNT_TYPE_CODES.get(0));

        EffortCertificationDetailBuild detail = detailBuildService.generateDetailBuild(2014, ledgerBalance, reportDefinition);

        assertEquals(KFSConstants.getDashSubAccountNumber(), detail.getSubAccountNumber());
        assertEquals(EffortConstants.DASH_CHART_OF_ACCOUNTS_CODE, detail.getSourceChartOfAccountsCode());
        assertEquals(EffortConstants.DASH_ACCOUNT_NUMBER, detail.getSourceAccountNumber());
        assertNull(detail.getCostShareSourceSubAccountNumber());
    }

    @Test
    void testGenerateDetailBuild_costShareSubAccountType_populatesCostShareFields() {
        SubAccount subAccount = mock(SubAccount.class);
        A21SubAccount a21 = mock(A21SubAccount.class);
        when(ledgerBalance.getSubAccount()).thenReturn(subAccount);
        when(subAccount.getA21SubAccount()).thenReturn(a21);
        when(a21.getSubAccountTypeCode()).thenReturn(EffortConstants.ELIGIBLE_COST_SHARE_SUB_ACCOUNT_TYPE_CODES.get(0));
        when(a21.getCostShareChartOfAccountCode()).thenReturn("UA");
        when(a21.getCostShareSourceAccountNumber()).thenReturn("9999999");
        when(a21.getCostShareSourceSubAccountNumber()).thenReturn("SUB1");
        when(ledgerBalance.getSubAccountNumber()).thenReturn("CS001");

        EffortCertificationDetailBuild detail = detailBuildService.generateDetailBuild(2014, ledgerBalance, reportDefinition);

        assertEquals("CS001", detail.getSubAccountNumber());
        assertEquals("UA", detail.getSourceChartOfAccountsCode());
        assertEquals("9999999", detail.getSourceAccountNumber());
        assertEquals("SUB1", detail.getCostShareSourceSubAccountNumber());
    }

    @Test
    void testGenerateDetailBuild_unknownSubAccountType_setsDashSourceFields() {
        SubAccount subAccount = mock(SubAccount.class);
        A21SubAccount a21 = mock(A21SubAccount.class);
        when(ledgerBalance.getSubAccount()).thenReturn(subAccount);
        when(subAccount.getA21SubAccount()).thenReturn(a21);
        when(a21.getSubAccountTypeCode()).thenReturn("XX");
        when(ledgerBalance.getSubAccountNumber()).thenReturn("OTHER");

        EffortCertificationDetailBuild detail = detailBuildService.generateDetailBuild(2014, ledgerBalance, reportDefinition);

        assertEquals("OTHER", detail.getSubAccountNumber());
        assertEquals(EffortConstants.DASH_CHART_OF_ACCOUNTS_CODE, detail.getSourceChartOfAccountsCode());
        assertEquals(EffortConstants.DASH_ACCOUNT_NUMBER, detail.getSourceAccountNumber());
        assertNull(detail.getCostShareSourceSubAccountNumber());
    }

    @Test
    void testGenerateDetailBuild_nullA21SubAccountTypeCode_setsDashValues() {
        SubAccount subAccount = mock(SubAccount.class);
        A21SubAccount a21 = mock(A21SubAccount.class);
        when(ledgerBalance.getSubAccount()).thenReturn(subAccount);
        when(subAccount.getA21SubAccount()).thenReturn(a21);
        when(a21.getSubAccountTypeCode()).thenReturn(null);

        EffortCertificationDetailBuild detail = detailBuildService.generateDetailBuild(2014, ledgerBalance, reportDefinition);

        assertEquals(KFSConstants.getDashSubAccountNumber(), detail.getSubAccountNumber());
        assertEquals(EffortConstants.DASH_CHART_OF_ACCOUNTS_CODE, detail.getSourceChartOfAccountsCode());
        assertEquals(EffortConstants.DASH_ACCOUNT_NUMBER, detail.getSourceAccountNumber());
    }

    @Test
    void testGenerateDetailBuild_zeroPayrollAmount() {
        when(ledgerBalance.getAmountByPeriod("01")).thenReturn(KualiDecimal.ZERO);

        EffortCertificationDetailBuild detail = detailBuildService.generateDetailBuild(2014, ledgerBalance, reportDefinition);

        assertEquals(KualiDecimal.ZERO, detail.getEffortCertificationPayrollAmount());
        assertEquals(KualiDecimal.ZERO, detail.getEffortCertificationOriginalPayrollAmount());
    }
}
