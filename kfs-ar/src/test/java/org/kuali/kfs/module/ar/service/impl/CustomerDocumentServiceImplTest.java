package org.kuali.kfs.module.ar.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.businessobject.Customer;
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
import static org.mockito.Mockito.when;

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
    void truncateField_shouldReturnOriginalWhenShorterThanMax() {
        when(dataDictionaryService.getAttributeMaxLength(Customer.class, "customerName"))
                .thenReturn(40);

        String result = service.truncateField(Customer.class, "customerName", "Short Name");
        assertThat(result).isEqualTo("Short Name");
    }

    @Test
    void truncateField_shouldTruncateWhenLongerThanMax() {
        when(dataDictionaryService.getAttributeMaxLength(Customer.class, "customerName"))
                .thenReturn(10);

        String result = service.truncateField(Customer.class, "customerName", "This Is A Very Long Customer Name");
        assertThat(result).hasSize(10);
        assertThat(result).isEqualTo("This Is A ");
    }

    @Test
    void truncateField_shouldReturnOriginalWhenExactLength() {
        when(dataDictionaryService.getAttributeMaxLength(Customer.class, "customerName"))
                .thenReturn(5);

        String result = service.truncateField(Customer.class, "customerName", "ABCDE");
        assertThat(result).isEqualTo("ABCDE");
    }

    @Test
    void truncateField_shouldReturnBlankWhenValueIsBlank() {
        String result = service.truncateField(Customer.class, "customerName", "");
        assertThat(result).isEmpty();
    }

    @Test
    void truncateField_shouldReturnNullWhenValueIsNull() {
        String result = service.truncateField(Customer.class, "customerName", null);
        assertThat(result).isNull();
    }

    @Test
    void truncateField_shouldReturnOriginalWhenMaxLengthIsNull() {
        when(dataDictionaryService.getAttributeMaxLength(Customer.class, "customerName"))
                .thenReturn(null);

        String result = service.truncateField(Customer.class, "customerName", "Any Value");
        assertThat(result).isEqualTo("Any Value");
    }
}
