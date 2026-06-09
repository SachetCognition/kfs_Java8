package org.kuali.kfs.module.ar.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.document.service.CustomerService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.service.MaintenanceDocumentDictionaryService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KualiModuleService;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerDocumentServiceImplTest extends KfsUnitTestBase {

    @Mock private CustomerService customerService;
    @Mock private DocumentService documentService;
    @Mock private DataDictionaryService dataDictionaryService;
    @Mock private KualiModuleService kualiModuleService;
    @Mock private MaintenanceDocumentDictionaryService maintenanceDocumentDictionaryService;
    @Mock private WorkflowDocumentService workflowDocumentService;

    @InjectMocks
    private CustomerDocumentServiceImpl service;

    @Test
    void serviceInitialization_shouldCreateInstance() {
        assertThat(service).isNotNull();
    }
}
