package org.kuali.kfs.sys.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.kew.api.document.WorkflowDocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class FinancialSystemWorkflowHelperServiceImplTest extends KfsUnitTestBase {

    @Mock
    private WorkflowDocumentService workflowDocumentService;

    @InjectMocks
    private FinancialSystemWorkflowHelperServiceImpl service;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(service).isNotNull();
    }
}
