package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.integration.cg.ContractsAndGrantsModuleBillingService;
import org.kuali.kfs.module.ar.dataaccess.AwardAccountObjectCodeTotalBilledDao;
import org.kuali.kfs.module.ar.document.service.ContractsGrantsInvoiceDocumentService;
import org.kuali.kfs.module.ar.service.ContractsGrantsInvoiceCreateDocumentService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.OptionsService;
import org.kuali.rice.krad.service.KualiModuleService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class ContractsGrantsLetterOfCreditReviewDocumentServiceImplTest extends KfsUnitTestBase {

    @Mock private AwardAccountObjectCodeTotalBilledDao awardAccountObjectCodeTotalBilledDao;
    @Mock private ContractsGrantsInvoiceCreateDocumentService contractsGrantsInvoiceCreateDocumentService;
    @Mock private ContractsGrantsInvoiceDocumentService contractsGrantsInvoiceDocumentService;
    @Mock private ContractsAndGrantsModuleBillingService contractsAndGrantsModuleBillingService;
    @Mock private KualiModuleService kualiModuleService;
    @Mock private OptionsService optionsService;

    @InjectMocks
    private ContractsGrantsLetterOfCreditReviewDocumentServiceImpl service;

    @Test
    void getContractsGrantsInvoiceDocumentService_shouldReturnInjectedService() {
        assertThat(service.getContractsGrantsInvoiceDocumentService()).isSameAs(contractsGrantsInvoiceDocumentService);
    }

    @Test
    void getAwardAccountObjectCodeTotalBilledDao_shouldReturnInjectedDao() {
        assertThat(service.getAwardAccountObjectCodeTotalBilledDao()).isSameAs(awardAccountObjectCodeTotalBilledDao);
    }
}
