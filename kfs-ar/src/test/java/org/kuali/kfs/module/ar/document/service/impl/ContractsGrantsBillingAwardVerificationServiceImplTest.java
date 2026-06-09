package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.service.AccountingPeriodService;
import org.kuali.kfs.integration.cg.ContractsAndGrantsBillingAward;
import org.kuali.kfs.integration.cg.ContractsAndGrantsBillingAwardAccount;
import org.kuali.kfs.module.ar.ArConstants;
import org.kuali.kfs.module.ar.batch.service.VerifyBillingFrequencyService;
import org.kuali.kfs.module.ar.document.ContractsGrantsInvoiceDocument;
import org.kuali.kfs.module.ar.document.service.ContractsGrantsInvoiceDocumentService;
import org.kuali.kfs.module.ar.document.service.CustomerService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.document.service.FinancialSystemDocumentService;
import org.kuali.kfs.sys.service.OptionsService;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KualiModuleService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ContractsGrantsBillingAwardVerificationServiceImplTest extends KfsUnitTestBase {

    @Mock private AccountingPeriodService accountingPeriodService;
    @Mock private BusinessObjectService businessObjectService;
    @Mock private ContractsGrantsInvoiceDocumentService contractsGrantsInvoiceDocumentService;
    @Mock private CustomerService customerService;
    @Mock private FinancialSystemDocumentService financialSystemDocumentService;
    @Mock private KualiModuleService kualiModuleService;
    @Mock private ParameterService parameterService;
    @Mock private VerifyBillingFrequencyService verifyBillingFrequencyService;
    @Mock private UniversityDateService universityDateService;
    @Mock private OptionsService optionsService;

    @InjectMocks
    private ContractsGrantsBillingAwardVerificationServiceImpl service;

    @Test
    void isBillingFrequencySetCorrectly_shouldReturnFalseWhenBlank() {
        ContractsAndGrantsBillingAward award = mock(ContractsAndGrantsBillingAward.class);
        when(award.getBillingFrequencyCode()).thenReturn("");

        assertThat(service.isBillingFrequencySetCorrectly(award)).isFalse();
    }

    @Test
    void isBillingFrequencySetCorrectly_shouldReturnFalseForPredeterminedWithMultipleAccounts() {
        ContractsAndGrantsBillingAward award = mock(ContractsAndGrantsBillingAward.class);
        when(award.getBillingFrequencyCode()).thenReturn(ArConstants.PREDETERMINED_BILLING_SCHEDULE_CODE);
        ContractsAndGrantsBillingAwardAccount acct1 = mock(ContractsAndGrantsBillingAwardAccount.class);
        ContractsAndGrantsBillingAwardAccount acct2 = mock(ContractsAndGrantsBillingAwardAccount.class);
        when(award.getActiveAwardAccounts()).thenReturn(Arrays.asList(acct1, acct2));

        assertThat(service.isBillingFrequencySetCorrectly(award)).isFalse();
    }

    @Test
    void isBillingFrequencySetCorrectly_shouldReturnTrueForPredeterminedWithOneAccount() {
        ContractsAndGrantsBillingAward award = mock(ContractsAndGrantsBillingAward.class);
        when(award.getBillingFrequencyCode()).thenReturn(ArConstants.PREDETERMINED_BILLING_SCHEDULE_CODE);
        ContractsAndGrantsBillingAwardAccount acct = mock(ContractsAndGrantsBillingAwardAccount.class);
        when(award.getActiveAwardAccounts()).thenReturn(Arrays.asList(acct));

        assertThat(service.isBillingFrequencySetCorrectly(award)).isTrue();
    }

    @Test
    void isBillingFrequencySetCorrectly_shouldReturnTrueForNonSpecialCode() {
        ContractsAndGrantsBillingAward award = mock(ContractsAndGrantsBillingAward.class);
        when(award.getBillingFrequencyCode()).thenReturn("MNTH");

        assertThat(service.isBillingFrequencySetCorrectly(award)).isTrue();
    }

    @Test
    void isAwardFinalInvoiceAlreadyBuilt_shouldReturnTrueWhenFinalBilled() {
        ContractsAndGrantsBillingAward award = mock(ContractsAndGrantsBillingAward.class);
        ContractsAndGrantsBillingAwardAccount acct = mock(ContractsAndGrantsBillingAwardAccount.class);
        when(acct.isFinalBilledIndicator()).thenReturn(true);
        when(award.getActiveAwardAccounts()).thenReturn(Arrays.asList(acct));

        assertThat(service.isAwardFinalInvoiceAlreadyBuilt(award)).isTrue();
    }

    @Test
    void isAwardFinalInvoiceAlreadyBuilt_shouldReturnFalseWhenNotFinalBilled() {
        ContractsAndGrantsBillingAward award = mock(ContractsAndGrantsBillingAward.class);
        ContractsAndGrantsBillingAwardAccount acct = mock(ContractsAndGrantsBillingAwardAccount.class);
        when(acct.isFinalBilledIndicator()).thenReturn(false);
        when(award.getActiveAwardAccounts()).thenReturn(Arrays.asList(acct));

        assertThat(service.isAwardFinalInvoiceAlreadyBuilt(award)).isFalse();
    }

    @Test
    void isAwardFinalInvoiceAlreadyBuilt_shouldReturnFalseWhenNoAccounts() {
        ContractsAndGrantsBillingAward award = mock(ContractsAndGrantsBillingAward.class);
        when(award.getActiveAwardAccounts()).thenReturn(Collections.<ContractsAndGrantsBillingAwardAccount>emptyList());

        assertThat(service.isAwardFinalInvoiceAlreadyBuilt(award)).isFalse();
    }

    @Test
    void isInvoiceInProgress_shouldReturnTrueWhenPendingInvoicesExist() {
        ContractsAndGrantsBillingAward award = mock(ContractsAndGrantsBillingAward.class);
        when(award.getProposalNumber()).thenReturn(1L);
        Set<String> statuses = new HashSet<String>(Arrays.<String>asList("R", "S", "E"));
        when(financialSystemDocumentService.getPendingDocumentStatuses()).thenReturn(statuses);
        when(businessObjectService.countMatching(eq(ContractsGrantsInvoiceDocument.class), any(Map.class)))
                .thenReturn(1);

        assertThat(service.isInvoiceInProgress(award)).isTrue();
    }

    @Test
    void isInvoiceInProgress_shouldReturnFalseWhenNoPendingInvoices() {
        ContractsAndGrantsBillingAward award = mock(ContractsAndGrantsBillingAward.class);
        when(award.getProposalNumber()).thenReturn(1L);
        Set<String> statuses = new HashSet<String>(Arrays.<String>asList("R", "S", "E"));
        when(financialSystemDocumentService.getPendingDocumentStatuses()).thenReturn(statuses);
        when(businessObjectService.countMatching(eq(ContractsGrantsInvoiceDocument.class), any(Map.class)))
                .thenReturn(0);

        assertThat(service.isInvoiceInProgress(award)).isFalse();
    }
}
