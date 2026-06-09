package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.businessobject.InvoiceBill;
import org.kuali.kfs.module.ar.document.service.ContractsGrantsInvoiceDocumentService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class PredeterminedBillingScheduleMaintenanceServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private ContractsGrantsInvoiceDocumentService contractsGrantsInvoiceDocumentService;

    @InjectMocks
    private PredeterminedBillingScheduleMaintenanceServiceImpl service;

    @Test
    void hasBillBeenCopiedToInvoice_shouldReturnFalseWhenNoInvoiceBills() {
        when(businessObjectService.findMatching(eq(InvoiceBill.class), any(Map.class)))
                .thenReturn(new ArrayList<>());

        boolean result = service.hasBillBeenCopiedToInvoice(1L, "BILL001");
        assertThat(result).isFalse();
    }

    @Test
    void hasBillBeenCopiedToInvoice_shouldReturnTrueWhenEffectiveInvoiceExists() {
        InvoiceBill bill = new InvoiceBill();
        bill.setDocumentNumber("DOC001");
        when(businessObjectService.findMatching(eq(InvoiceBill.class), any(Map.class)))
                .thenReturn(Arrays.asList(bill));
        when(contractsGrantsInvoiceDocumentService.isInvoiceDocumentEffective("DOC001"))
                .thenReturn(true);

        boolean result = service.hasBillBeenCopiedToInvoice(1L, "BILL001");
        assertThat(result).isTrue();
    }

    @Test
    void hasBillBeenCopiedToInvoice_shouldReturnFalseWhenInvoiceNotEffective() {
        InvoiceBill bill = new InvoiceBill();
        bill.setDocumentNumber("DOC001");
        when(businessObjectService.findMatching(eq(InvoiceBill.class), any(Map.class)))
                .thenReturn(Arrays.asList(bill));
        when(contractsGrantsInvoiceDocumentService.isInvoiceDocumentEffective("DOC001"))
                .thenReturn(false);

        boolean result = service.hasBillBeenCopiedToInvoice(1L, "BILL001");
        assertThat(result).isFalse();
    }

    @Test
    void hasBillBeenCopiedToInvoice_shouldCacheEffectiveDocumentNumbers() {
        InvoiceBill b1 = new InvoiceBill();
        b1.setDocumentNumber("DOC001");
        InvoiceBill b2 = new InvoiceBill();
        b2.setDocumentNumber("DOC001");
        when(businessObjectService.findMatching(eq(InvoiceBill.class), any(Map.class)))
                .thenReturn(Arrays.asList(b1, b2));
        when(contractsGrantsInvoiceDocumentService.isInvoiceDocumentEffective("DOC001"))
                .thenReturn(true);

        boolean result = service.hasBillBeenCopiedToInvoice(1L, "BILL001");
        assertThat(result).isTrue();
    }
}
