package org.kuali.kfs.module.ar.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.integration.cg.ContractsAndGrantsBillingAward;
import org.kuali.kfs.module.ar.ArConstants;
import org.kuali.kfs.module.ar.document.service.ContractsGrantsInvoiceDocumentService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KualiModuleService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccountsReceivableModuleBillingServiceImplTest extends KfsUnitTestBase {

    @Mock private BusinessObjectService businessObjectService;
    @Mock private ContractsGrantsInvoiceDocumentService contractsGrantsInvoiceDocumentService;
    @Mock private KualiModuleService kualiModuleService;
    @Mock private ConfigurationService configurationService;
    @Mock private ParameterService parameterService;

    @InjectMocks
    private AccountsReceivableModuleBillingServiceImpl service;

    @Test
    void getAwardBilledToDateAmount_shouldUseMilestoneForMilestoneBilling() {
        ContractsAndGrantsBillingAward award = mock(ContractsAndGrantsBillingAward.class);
        when(award.getBillingFrequencyCode()).thenReturn(ArConstants.MILESTONE_BILLING_SCHEDULE_CODE);
        when(award.getProposalNumber()).thenReturn(1L);
        when(contractsGrantsInvoiceDocumentService.getMilestonesBilledToDateAmount(1L)).thenReturn(new KualiDecimal(500));

        KualiDecimal result = service.getAwardBilledToDateAmount(award);
        assertThat(result).isEqualTo(new KualiDecimal(500));
    }

    @Test
    void getAwardBilledToDateAmount_shouldUsePredeterminedForPredeterminedBilling() {
        ContractsAndGrantsBillingAward award = mock(ContractsAndGrantsBillingAward.class);
        when(award.getBillingFrequencyCode()).thenReturn(ArConstants.PREDETERMINED_BILLING_SCHEDULE_CODE);
        when(award.getProposalNumber()).thenReturn(2L);
        when(contractsGrantsInvoiceDocumentService.getPredeterminedBillingBilledToDateAmount(2L)).thenReturn(new KualiDecimal(300));

        KualiDecimal result = service.getAwardBilledToDateAmount(award);
        assertThat(result).isEqualTo(new KualiDecimal(300));
    }

    @Test
    void getAwardBilledToDateAmount_shouldUseProposalNumberForOtherBilling() {
        ContractsAndGrantsBillingAward award = mock(ContractsAndGrantsBillingAward.class);
        when(award.getBillingFrequencyCode()).thenReturn("MNTH");
        when(award.getProposalNumber()).thenReturn(3L);
        when(contractsGrantsInvoiceDocumentService.getAwardBilledToDateAmountByProposalNumber(3L)).thenReturn(new KualiDecimal(700));

        KualiDecimal result = service.getAwardBilledToDateAmount(award);
        assertThat(result).isEqualTo(new KualiDecimal(700));
    }

    @Test
    void serviceInitialization_shouldCreateInstance() {
        assertThat(service).isNotNull();
    }
}
