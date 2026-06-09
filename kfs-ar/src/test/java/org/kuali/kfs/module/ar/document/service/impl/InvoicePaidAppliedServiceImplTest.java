package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.businessobject.InvoicePaidApplied;
import org.kuali.kfs.module.ar.document.CustomerInvoiceDocument;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class InvoicePaidAppliedServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private UniversityDateService universityDateService;

    @InjectMocks
    private InvoicePaidAppliedServiceImpl service;

    @Test
    void clearDocumentPaidAppliedsFromDatabase_shouldDeleteMatchingRecords() {
        service.clearDocumentPaidAppliedsFromDatabase("DOC001");
        verify(businessObjectService).deleteMatching(eq(InvoicePaidApplied.class), any(Map.class));
    }

    @Test
    void getNumberOfInvoicePaidAppliedsForInvoiceDetail_shouldReturnCount() {
        when(businessObjectService.countMatching(eq(InvoicePaidApplied.class), any(Map.class)))
                .thenReturn(5);

        Integer result = service.getNumberOfInvoicePaidAppliedsForInvoiceDetail("INV001", 1);
        assertThat(result).isEqualTo(5);
    }

    @Test
    void getNumberOfInvoicePaidAppliedsForInvoiceDetail_shouldReturnZeroWhenNoneExist() {
        when(businessObjectService.countMatching(eq(InvoicePaidApplied.class), any(Map.class)))
                .thenReturn(0);

        Integer result = service.getNumberOfInvoicePaidAppliedsForInvoiceDetail("INV001", 1);
        assertThat(result).isZero();
    }

    @Test
    void getInvoicePaidAppliedsFromSpecificDocument_shouldReturnMatchingResults() {
        InvoicePaidApplied ipa = new InvoicePaidApplied();
        when(businessObjectService.findMatching(eq(InvoicePaidApplied.class), any(Map.class)))
                .thenReturn(Arrays.asList(ipa));

        Collection<InvoicePaidApplied> result = service.getInvoicePaidAppliedsFromSpecificDocument("DOC001", "INV001");
        assertThat(result).hasSize(1);
    }

    @Test
    void doesInvoiceHaveAppliedAmounts_shouldReturnFalseWhenNoResults() {
        CustomerInvoiceDocument doc = mock(CustomerInvoiceDocument.class);
        when(doc.getDocumentNumber()).thenReturn("DOC001");
        when(businessObjectService.findMatching(eq(InvoicePaidApplied.class), any(Map.class)))
                .thenReturn(Collections.EMPTY_LIST);

        boolean result = service.doesInvoiceHaveAppliedAmounts(doc);
        assertThat(result).isFalse();
    }

    @Test
    void doesInvoiceHaveAppliedAmounts_shouldReturnTrueWhenNonDiscountExists() {
        CustomerInvoiceDocument doc = mock(CustomerInvoiceDocument.class);
        when(doc.getDocumentNumber()).thenReturn("DOC001");

        InvoicePaidApplied ipa = new InvoicePaidApplied();
        ipa.setDocumentNumber("PAY001");
        when(businessObjectService.findMatching(eq(InvoicePaidApplied.class), any(Map.class)))
                .thenReturn(Arrays.asList(ipa));

        boolean result = service.doesInvoiceHaveAppliedAmounts(doc);
        assertThat(result).isTrue();
    }

    @Test
    void getInvoicePaidAppliedsForInvoice_byDocNumber_shouldReturnResults() {
        InvoicePaidApplied ipa = new InvoicePaidApplied();
        when(businessObjectService.findMatching(eq(InvoicePaidApplied.class), any(Map.class)))
                .thenReturn(Arrays.asList(ipa));

        Collection<InvoicePaidApplied> result = service.getInvoicePaidAppliedsForInvoice("DOC001");
        assertThat(result).hasSize(1);
    }

    @Test
    void getInvoicePaidAppliedsForInvoice_byDocument_shouldDelegateToStringVersion() {
        CustomerInvoiceDocument doc = mock(CustomerInvoiceDocument.class);
        when(doc.getDocumentNumber()).thenReturn("DOC001");

        InvoicePaidApplied ipa = new InvoicePaidApplied();
        when(businessObjectService.findMatching(eq(InvoicePaidApplied.class), any(Map.class)))
                .thenReturn(Arrays.asList(ipa));

        Collection<InvoicePaidApplied> result = service.getInvoicePaidAppliedsForInvoice(doc);
        assertThat(result).hasSize(1);
    }
}
