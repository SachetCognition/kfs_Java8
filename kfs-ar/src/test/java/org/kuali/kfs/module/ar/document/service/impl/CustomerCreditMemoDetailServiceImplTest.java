package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.businessobject.CustomerCreditMemoDetail;
import org.kuali.kfs.module.ar.document.CustomerCreditMemoDocument;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.mockito.InjectMocks;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CustomerCreditMemoDetailServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private CustomerCreditMemoDetailServiceImpl service;

    @Test
    void recalculateCustomerCreditMemoDetail_withAmount_shouldRecalculateBasedOnAmount() {
        CustomerCreditMemoDetail detail = mock(CustomerCreditMemoDetail.class);
        CustomerCreditMemoDocument document = mock(CustomerCreditMemoDocument.class);

        when(document.getFinancialDocumentReferenceInvoiceNumber()).thenReturn("INV001");
        when(detail.getReferenceInvoiceItemNumber()).thenReturn(1);
        when(detail.getCreditMemoItemQuantity()).thenReturn(null);
        when(detail.getCreditMemoItemTotalAmount()).thenReturn(new KualiDecimal(100));

        service.recalculateCustomerCreditMemoDetail(detail, document);

        verify(detail).recalculateBasedOnEnteredItemAmount(document);
        verify(document).recalculateTotalsBasedOnChangedItemAmount(detail);
    }

    @Test
    void recalculateCustomerCreditMemoDetail_withQuantity_shouldRecalculateBasedOnQuantity() {
        CustomerCreditMemoDetail detail = mock(CustomerCreditMemoDetail.class);
        CustomerCreditMemoDocument document = mock(CustomerCreditMemoDocument.class);

        when(document.getFinancialDocumentReferenceInvoiceNumber()).thenReturn("INV001");
        when(detail.getReferenceInvoiceItemNumber()).thenReturn(1);
        when(detail.getCreditMemoItemQuantity()).thenReturn(new java.math.BigDecimal(5));
        when(detail.getCreditMemoItemTotalAmount()).thenReturn(null);

        service.recalculateCustomerCreditMemoDetail(detail, document);

        verify(detail).recalculateBasedOnEnteredItemQty(document);
        verify(document).recalculateTotalsBasedOnChangedItemAmount(detail);
    }
}
