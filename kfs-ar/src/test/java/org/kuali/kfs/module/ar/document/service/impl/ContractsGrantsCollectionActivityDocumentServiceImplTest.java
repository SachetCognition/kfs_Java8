package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kew.api.doctype.DocumentTypeService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KualiModuleService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class ContractsGrantsCollectionActivityDocumentServiceImplTest extends KfsUnitTestBase {

    @Mock private DocumentService documentService;
    @Mock private DateTimeService dateTimeService;
    @Mock private BusinessObjectService businessObjectService;
    @Mock private DocumentTypeService documentTypeService;
    @Mock private KualiModuleService kualiModuleService;

    @InjectMocks
    private ContractsGrantsCollectionActivityDocumentServiceImpl service;

    @Test
    void serviceInitialization_shouldCreateInstance() {
        assertThat(service).isNotNull();
    }

    @Test
    void getDocumentService_shouldReturnInjectedService() {
        assertThat(service.getDocumentService()).isSameAs(documentService);
    }

    @Test
    void getBusinessObjectService_shouldReturnInjectedService() {
        assertThat(service.getBusinessObjectService()).isSameAs(businessObjectService);
    }
}
