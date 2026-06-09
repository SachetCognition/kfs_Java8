package org.kuali.kfs.module.cam.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.coa.businessobject.ObjectCode;
import org.kuali.kfs.integration.cab.CapitalAssetBuilderModuleService;
import org.kuali.kfs.sys.businessobject.SourceAccountingLine;
import org.kuali.kfs.sys.businessobject.TargetAccountingLine;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

public class CapitalAssetManagementModuleServiceImplTest extends KfsUnitTestBase {

    @Mock
    private CapitalAssetBuilderModuleService capitalAssetBuilderModuleService;

    @InjectMocks
    private CapitalAssetManagementModuleServiceImpl service;

    // --- isAccountLineEligibleForCABBatch ---

    @Test
    public void testIsAccountLineEligibleForCABBatch_allCriteriaPass() {
        List<String> includedSubTypes = Arrays.asList("CM", "CF");
        List<String> excludedCharts = Arrays.asList("XX");
        List<String> excludedSubFunds = Arrays.asList("ZZ");

        SourceAccountingLine line = new SourceAccountingLine();
        ObjectCode objectCode = new ObjectCode();
        objectCode.setFinancialObjectSubTypeCode("CM");
        line.setObjectCode(objectCode);
        line.setChartOfAccountsCode("BL");
        Account account = new Account();
        account.setSubFundGroupCode("GF");
        line.setAccount(account);

        assertTrue(service.isAccountLineEligibleForCABBatch(includedSubTypes, excludedCharts, excludedSubFunds, line));
    }

    @Test
    public void testIsAccountLineEligibleForCABBatch_excludedSubType() {
        List<String> includedSubTypes = Arrays.asList("CM", "CF");
        List<String> excludedCharts = Arrays.asList("XX");
        List<String> excludedSubFunds = Arrays.asList("ZZ");

        SourceAccountingLine line = new SourceAccountingLine();
        ObjectCode objectCode = new ObjectCode();
        objectCode.setFinancialObjectSubTypeCode("BD");
        line.setObjectCode(objectCode);
        line.setChartOfAccountsCode("BL");
        Account account = new Account();
        account.setSubFundGroupCode("GF");
        line.setAccount(account);

        assertFalse(service.isAccountLineEligibleForCABBatch(includedSubTypes, excludedCharts, excludedSubFunds, line));
    }

    @Test
    public void testIsAccountLineEligibleForCABBatch_excludedChart() {
        List<String> includedSubTypes = Arrays.asList("CM", "CF");
        List<String> excludedCharts = Arrays.asList("XX");
        List<String> excludedSubFunds = Arrays.asList("ZZ");

        SourceAccountingLine line = new SourceAccountingLine();
        ObjectCode objectCode = new ObjectCode();
        objectCode.setFinancialObjectSubTypeCode("CM");
        line.setObjectCode(objectCode);
        line.setChartOfAccountsCode("XX");
        Account account = new Account();
        account.setSubFundGroupCode("GF");
        line.setAccount(account);

        assertFalse(service.isAccountLineEligibleForCABBatch(includedSubTypes, excludedCharts, excludedSubFunds, line));
    }

    @Test
    public void testIsAccountLineEligibleForCABBatch_excludedSubFund() {
        List<String> includedSubTypes = Arrays.asList("CM", "CF");
        List<String> excludedCharts = new ArrayList<>();
        List<String> excludedSubFunds = Arrays.asList("ZZ");

        SourceAccountingLine line = new SourceAccountingLine();
        ObjectCode objectCode = new ObjectCode();
        objectCode.setFinancialObjectSubTypeCode("CM");
        line.setObjectCode(objectCode);
        line.setChartOfAccountsCode("BL");
        Account account = new Account();
        account.setSubFundGroupCode("ZZ");
        line.setAccount(account);

        assertFalse(service.isAccountLineEligibleForCABBatch(includedSubTypes, excludedCharts, excludedSubFunds, line));
    }

    @Test
    public void testIsAccountLineEligibleForCABBatch_emptyFilters() {
        SourceAccountingLine line = new SourceAccountingLine();
        ObjectCode objectCode = new ObjectCode();
        objectCode.setFinancialObjectSubTypeCode("CM");
        line.setObjectCode(objectCode);
        line.setChartOfAccountsCode("BL");
        Account account = new Account();
        account.setSubFundGroupCode("GF");
        line.setAccount(account);

        assertTrue(service.isAccountLineEligibleForCABBatch(new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), line));
    }

    // --- getCapitalAssetBuilderModuleService ---

    @Test
    public void testGetCapitalAssetBuilderModuleService() {
        assertSame(capitalAssetBuilderModuleService, service.getCapitalAssetBuilderModuleService());
    }
}
