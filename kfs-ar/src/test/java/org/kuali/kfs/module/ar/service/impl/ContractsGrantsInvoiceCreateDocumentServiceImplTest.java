package org.kuali.kfs.module.ar.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.service.AccountService;
import org.kuali.kfs.coa.service.AccountingPeriodService;
import org.kuali.kfs.integration.cg.ContractsAndGrantsBillingAward;
import org.kuali.kfs.integration.cg.ContractsAndGrantsBillingAwardAccount;
import org.kuali.kfs.integration.cg.ContractsAndGrantsModuleBillingService;
import org.kuali.kfs.module.ar.ArKeyConstants;
import org.kuali.kfs.module.ar.batch.service.VerifyBillingFrequencyService;
import org.kuali.kfs.module.ar.dataaccess.AwardAccountObjectCodeTotalBilledDao;
import org.kuali.kfs.module.ar.document.service.AccountsReceivableDocumentHeaderService;
import org.kuali.kfs.module.ar.document.service.ContractsGrantsBillingAwardVerificationService;
import org.kuali.kfs.module.ar.document.service.ContractsGrantsInvoiceDocumentService;
import org.kuali.kfs.module.ar.document.service.CustomerService;
import org.kuali.kfs.module.ar.service.ContractsGrantsBillingUtilityService;
import org.kuali.kfs.module.ar.service.CostCategoryService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.document.service.FinancialSystemDocumentService;
import org.kuali.kfs.sys.service.OptionsService;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KualiModuleService;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ContractsGrantsInvoiceCreateDocumentServiceImplTest extends KfsUnitTestBase {

    @Mock private AccountService accountService;
    @Mock private AccountingPeriodService accountingPeriodService;
    @Mock private AccountsReceivableDocumentHeaderService accountsReceivableDocumentHeaderService;
    @Mock private AwardAccountObjectCodeTotalBilledDao awardAccountObjectCodeTotalBilledDao;
    @Mock private BusinessObjectService businessObjectService;
    @Mock private ConfigurationService configurationService;
    @Mock private ContractsGrantsBillingAwardVerificationService contractsGrantsBillingAwardVerificationService;
    @Mock private ContractsGrantsBillingUtilityService contractsGrantsBillingUtilityService;
    @Mock private ContractsAndGrantsModuleBillingService contractsAndGrantsModuleBillingService;
    @Mock private ContractsGrantsInvoiceDocumentService contractsGrantsInvoiceDocumentService;
    @Mock private CostCategoryService costCategoryService;
    @Mock private CustomerService customerService;
    @Mock private DateTimeService dateTimeService;
    @Mock private DocumentService documentService;
    @Mock private FinancialSystemDocumentService financialSystemDocumentService;
    @Mock private KualiModuleService kualiModuleService;
    @Mock private ParameterService parameterService;
    @Mock private VerifyBillingFrequencyService verifyBillingFrequencyService;
    @Mock private WorkflowDocumentService workflowDocumentService;
    @Mock private UniversityDateService universityDateService;
    @Mock private OptionsService optionsService;

    @InjectMocks
    private ContractsGrantsInvoiceCreateDocumentServiceImpl service;

    @Test
    void performAwardValidation_shouldAddToInvalidGroupWhenStartDateMissing() {
        ContractsAndGrantsBillingAward award = mock(ContractsAndGrantsBillingAward.class);
        when(award.getAwardBeginningDate()).thenReturn(null);
        when(configurationService.getPropertyValueAsString(ArKeyConstants.CGINVOICE_CREATION_AWARD_START_DATE_MISSING_ERROR))
                .thenReturn("Start date missing");

        Collection<ContractsAndGrantsBillingAward> awards = new ArrayList<ContractsAndGrantsBillingAward>();
        awards.add(award);
        Map<ContractsAndGrantsBillingAward, List<String>> invalidGroup = new HashMap<ContractsAndGrantsBillingAward, List<String>>();
        List<ContractsAndGrantsBillingAward> qualifiedAwards = new ArrayList<ContractsAndGrantsBillingAward>();

        service.performAwardValidation(awards, invalidGroup, qualifiedAwards);

        assertThat(qualifiedAwards).isEmpty();
        assertThat(invalidGroup).containsKey(award);
        assertThat(invalidGroup.get(award)).contains("Start date missing");
    }

    @Test
    void performAwardValidation_shouldAddToInvalidGroupWhenBillingFrequencyMissing() {
        ContractsAndGrantsBillingAward award = mock(ContractsAndGrantsBillingAward.class);
        when(award.getAwardBeginningDate()).thenReturn(new Date(System.currentTimeMillis()));
        when(award.getBillingFrequencyCode()).thenReturn(null);
        when(configurationService.getPropertyValueAsString(ArKeyConstants.CGINVOICE_CREATION_BILLING_FREQUENCY_MISSING_ERROR))
                .thenReturn("Billing frequency missing");

        Collection<ContractsAndGrantsBillingAward> awards = new ArrayList<ContractsAndGrantsBillingAward>();
        awards.add(award);
        Map<ContractsAndGrantsBillingAward, List<String>> invalidGroup = new HashMap<ContractsAndGrantsBillingAward, List<String>>();
        List<ContractsAndGrantsBillingAward> qualifiedAwards = new ArrayList<ContractsAndGrantsBillingAward>();

        service.performAwardValidation(awards, invalidGroup, qualifiedAwards);

        assertThat(qualifiedAwards).isEmpty();
        assertThat(invalidGroup).containsKey(award);
        assertThat(invalidGroup.get(award)).contains("Billing frequency missing");
    }

    @Test
    void performAwardValidation_shouldAddToInvalidGroupWhenBillingPeriodInvalid() {
        ContractsAndGrantsBillingAward award = mock(ContractsAndGrantsBillingAward.class);
        when(award.getAwardBeginningDate()).thenReturn(new Date(System.currentTimeMillis()));
        when(award.getBillingFrequencyCode()).thenReturn("MNTH");
        when(contractsGrantsBillingAwardVerificationService.isValueOfBillingFrequencyValid(award)).thenReturn(true);
        when(verifyBillingFrequencyService.validateBillingFrequency(award)).thenReturn(false);
        when(configurationService.getPropertyValueAsString(ArKeyConstants.CGINVOICE_CREATION_AWARD_INVALID_BILLING_PERIOD))
                .thenReturn("Invalid billing period");

        Collection<ContractsAndGrantsBillingAward> awards = new ArrayList<ContractsAndGrantsBillingAward>();
        awards.add(award);
        Map<ContractsAndGrantsBillingAward, List<String>> invalidGroup = new HashMap<ContractsAndGrantsBillingAward, List<String>>();
        List<ContractsAndGrantsBillingAward> qualifiedAwards = new ArrayList<ContractsAndGrantsBillingAward>();

        service.performAwardValidation(awards, invalidGroup, qualifiedAwards);

        assertThat(qualifiedAwards).isEmpty();
        assertThat(invalidGroup.get(award)).contains("Invalid billing period");
    }

    @Test
    void performAwardValidation_shouldHandleEmptyAwardsCollection() {
        Collection<ContractsAndGrantsBillingAward> awards = new ArrayList<ContractsAndGrantsBillingAward>();
        Map<ContractsAndGrantsBillingAward, List<String>> invalidGroup = new HashMap<ContractsAndGrantsBillingAward, List<String>>();
        List<ContractsAndGrantsBillingAward> qualifiedAwards = new ArrayList<ContractsAndGrantsBillingAward>();

        service.performAwardValidation(awards, invalidGroup, qualifiedAwards);

        assertThat(invalidGroup).isEmpty();
        assertThat(qualifiedAwards).isEmpty();
    }

    @Test
    void performAwardValidation_shouldAddToInvalidGroupWhenAwardExcludedFromInvoicing() {
        ContractsAndGrantsBillingAward award = createValidAwardMock();
        when(award.isExcludedFromInvoicing()).thenReturn(true);
        when(configurationService.getPropertyValueAsString(ArKeyConstants.CGINVOICE_CREATION_AWARD_EXCLUDED_FROM_INVOICING))
                .thenReturn("Award excluded");

        Collection<ContractsAndGrantsBillingAward> awards = new ArrayList<ContractsAndGrantsBillingAward>();
        awards.add(award);
        Map<ContractsAndGrantsBillingAward, List<String>> invalidGroup = new HashMap<ContractsAndGrantsBillingAward, List<String>>();
        List<ContractsAndGrantsBillingAward> qualifiedAwards = new ArrayList<ContractsAndGrantsBillingAward>();

        service.performAwardValidation(awards, invalidGroup, qualifiedAwards);

        assertThat(qualifiedAwards).isEmpty();
        assertThat(invalidGroup).containsKey(award);
        assertThat(invalidGroup.get(award)).contains("Award excluded");
    }

    @Test
    void performAwardValidation_shouldAddToInvalidGroupWhenAwardInactive() {
        ContractsAndGrantsBillingAward award = createValidAwardMock();
        when(award.isActive()).thenReturn(false);
        when(configurationService.getPropertyValueAsString(ArKeyConstants.CGINVOICE_CREATION_AWARD_INACTIVE_ERROR))
                .thenReturn("Award inactive");

        Collection<ContractsAndGrantsBillingAward> awards = new ArrayList<ContractsAndGrantsBillingAward>();
        awards.add(award);
        Map<ContractsAndGrantsBillingAward, List<String>> invalidGroup = new HashMap<ContractsAndGrantsBillingAward, List<String>>();
        List<ContractsAndGrantsBillingAward> qualifiedAwards = new ArrayList<ContractsAndGrantsBillingAward>();

        service.performAwardValidation(awards, invalidGroup, qualifiedAwards);

        assertThat(qualifiedAwards).isEmpty();
        assertThat(invalidGroup).containsKey(award);
        assertThat(invalidGroup.get(award)).contains("Award inactive");
    }

    @Test
    void performAwardValidation_shouldAddToInvalidGroupWhenNoActiveAccounts() {
        ContractsAndGrantsBillingAward award = createValidAwardMock();
        when(award.getActiveAwardAccounts()).thenReturn(new ArrayList<ContractsAndGrantsBillingAwardAccount>());
        when(configurationService.getPropertyValueAsString(ArKeyConstants.CGINVOICE_CREATION_NO_ACTIVE_ACCOUNTS_ASSIGNED_ERROR))
                .thenReturn("No active accounts");

        Collection<ContractsAndGrantsBillingAward> awards = new ArrayList<ContractsAndGrantsBillingAward>();
        awards.add(award);
        Map<ContractsAndGrantsBillingAward, List<String>> invalidGroup = new HashMap<ContractsAndGrantsBillingAward, List<String>>();
        List<ContractsAndGrantsBillingAward> qualifiedAwards = new ArrayList<ContractsAndGrantsBillingAward>();

        service.performAwardValidation(awards, invalidGroup, qualifiedAwards);

        assertThat(qualifiedAwards).isEmpty();
        assertThat(invalidGroup).containsKey(award);
        assertThat(invalidGroup.get(award)).contains("No active accounts");
    }

    @Test
    void performAwardValidation_shouldAddToInvalidGroupWhenFinalInvoiceAlreadyBuilt() {
        ContractsAndGrantsBillingAward award = createValidAwardMock();
        when(contractsGrantsBillingAwardVerificationService.isAwardFinalInvoiceAlreadyBuilt(award)).thenReturn(true);
        when(configurationService.getPropertyValueAsString(ArKeyConstants.CGINVOICE_CREATION_AWARD_FINAL_BILLED_ERROR))
                .thenReturn("Final invoice already built");

        Collection<ContractsAndGrantsBillingAward> awards = new ArrayList<ContractsAndGrantsBillingAward>();
        awards.add(award);
        Map<ContractsAndGrantsBillingAward, List<String>> invalidGroup = new HashMap<ContractsAndGrantsBillingAward, List<String>>();
        List<ContractsAndGrantsBillingAward> qualifiedAwards = new ArrayList<ContractsAndGrantsBillingAward>();

        service.performAwardValidation(awards, invalidGroup, qualifiedAwards);

        assertThat(qualifiedAwards).isEmpty();
        assertThat(invalidGroup).containsKey(award);
        assertThat(invalidGroup.get(award)).contains("Final invoice already built");
    }

    /**
     * Creates a mock award that passes the initial validation checks (has start date,
     * billing frequency, and valid billing period) so tests can focus on validateAward() checks.
     */
    private ContractsAndGrantsBillingAward createValidAwardMock() {
        ContractsAndGrantsBillingAward award = mock(ContractsAndGrantsBillingAward.class);
        when(award.getAwardBeginningDate()).thenReturn(new Date(System.currentTimeMillis()));
        when(award.getBillingFrequencyCode()).thenReturn("MNTH");
        when(award.isExcludedFromInvoicing()).thenReturn(false);
        when(award.isActive()).thenReturn(true);
        when(award.getInvoicingOptionCode()).thenReturn("INV_ACCOUNT");

        List<ContractsAndGrantsBillingAwardAccount> activeAccounts = new ArrayList<ContractsAndGrantsBillingAwardAccount>();
        activeAccounts.add(mock(ContractsAndGrantsBillingAwardAccount.class));
        when(award.getActiveAwardAccounts()).thenReturn(activeAccounts);

        when(contractsGrantsBillingAwardVerificationService.isValueOfBillingFrequencyValid(award)).thenReturn(true);
        when(verifyBillingFrequencyService.validateBillingFrequency(award)).thenReturn(true);
        when(contractsGrantsBillingAwardVerificationService.isBillingFrequencySetCorrectly(award)).thenReturn(true);
        when(contractsGrantsBillingAwardVerificationService.isAwardFinalInvoiceAlreadyBuilt(award)).thenReturn(false);
        when(contractsGrantsBillingAwardVerificationService.hasMilestonesToInvoice(award)).thenReturn(true);
        when(contractsGrantsBillingAwardVerificationService.hasBillsToInvoice(award)).thenReturn(true);
        when(contractsGrantsBillingAwardVerificationService.owningAgencyHasCustomerRecord(award)).thenReturn(true);
        when(contractsGrantsBillingAwardVerificationService.isChartAndOrgSetupForInvoicing(award)).thenReturn(true);
        when(contractsGrantsInvoiceDocumentService.getExpiredAccountsOfAward(award)).thenReturn(new ArrayList());
        when(contractsGrantsInvoiceDocumentService.checkAwardContractControlAccounts(award)).thenReturn(new ArrayList<String>());

        return award;
    }
}
