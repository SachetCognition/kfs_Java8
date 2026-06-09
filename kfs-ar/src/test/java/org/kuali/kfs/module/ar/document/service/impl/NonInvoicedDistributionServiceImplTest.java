package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.businessobject.NonInvoicedDistribution;
import org.kuali.kfs.module.ar.document.CustomerInvoiceDocument;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
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
import static org.mockito.Mockito.when;

class NonInvoicedDistributionServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private NonInvoicedDistributionServiceImpl service;

    @Test
    void getNonInvoicedDistributionsForInvoice_byDocumentNumber_shouldReturnResults() {
        NonInvoicedDistribution dist = new NonInvoicedDistribution();
        when(businessObjectService.findMatching(eq(NonInvoicedDistribution.class), any(Map.class)))
                .thenReturn(Arrays.asList(dist));

        Collection<NonInvoicedDistribution> result = service.getNonInvoicedDistributionsForInvoice("DOC001");
        assertThat(result).hasSize(1);
    }

    @Test
    void getNonInvoicedDistributionsForInvoice_byDocumentNumber_shouldReturnEmptyWhenNoResults() {
        when(businessObjectService.findMatching(eq(NonInvoicedDistribution.class), any(Map.class)))
                .thenReturn(Collections.emptyList());

        Collection<NonInvoicedDistribution> result = service.getNonInvoicedDistributionsForInvoice("DOC001");
        assertThat(result).isEmpty();
    }

    @Test
    void getNonInvoicedDistributionsForInvoice_byDocument_shouldDelegateToStringVersion() {
        CustomerInvoiceDocument invoice = mock(CustomerInvoiceDocument.class);
        when(invoice.getDocumentNumber()).thenReturn("DOC001");

        NonInvoicedDistribution dist = new NonInvoicedDistribution();
        when(businessObjectService.findMatching(eq(NonInvoicedDistribution.class), any(Map.class)))
                .thenReturn(Arrays.asList(dist));

        Collection<NonInvoicedDistribution> result = service.getNonInvoicedDistributionsForInvoice(invoice);
        assertThat(result).hasSize(1);
    }
}
