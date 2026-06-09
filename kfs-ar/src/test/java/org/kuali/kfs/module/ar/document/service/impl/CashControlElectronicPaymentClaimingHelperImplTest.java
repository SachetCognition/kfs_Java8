package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.document.service.AccountsReceivableDocumentHeaderService;
import org.kuali.kfs.module.ar.document.service.CashControlDocumentService;
import org.kuali.kfs.sys.service.ElectronicPaymentClaimingService;
import org.kuali.kfs.sys.service.FinancialSystemUserService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.service.DocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class CashControlElectronicPaymentClaimingHelperImplTest extends KfsUnitTestBase {

    @Mock private DataDictionaryService dataDictionaryService;
    @Mock private DocumentService documentService;
    @Mock private ElectronicPaymentClaimingService electronicPaymentClaimingService;
    @Mock private CashControlDocumentService cashControlDocumentService;
    @Mock private ConfigurationService kualiConfigurationService;
    @Mock private FinancialSystemUserService financialSystemUserService;
    @Mock private AccountsReceivableDocumentHeaderService accountsReceivableDocumentHeaderService;

    @InjectMocks
    private CashControlElectronicPaymentClaimingHelperImpl service;

    @Test
    void getClaimingDocumentWorkflowDocumentType_shouldReturnCorrectType() {
        String docType = service.getClaimingDocumentWorkflowDocumentType();
        assertThat(docType).isNotNull();
    }

    @Test
    void isDocumentReferenceValid_shouldReturnFalseForNullReference() {
        assertThat(service.isDocumentReferenceValid(null)).isFalse();
    }

    @Test
    void isDocumentReferenceValid_shouldReturnFalseForBlankReference() {
        assertThat(service.isDocumentReferenceValid("")).isFalse();
    }
}
