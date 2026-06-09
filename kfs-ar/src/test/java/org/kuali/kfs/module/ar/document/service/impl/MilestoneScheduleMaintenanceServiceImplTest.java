package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.businessobject.InvoiceMilestone;
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

class MilestoneScheduleMaintenanceServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private ContractsGrantsInvoiceDocumentService contractsGrantsInvoiceDocumentService;

    @InjectMocks
    private MilestoneScheduleMaintenanceServiceImpl service;

    @Test
    void hasMilestoneBeenCopiedToInvoice_shouldReturnFalseWhenNoInvoiceMilestones() {
        when(businessObjectService.findMatching(eq(InvoiceMilestone.class), any(Map.class)))
                .thenReturn(new ArrayList<>());

        boolean result = service.hasMilestoneBeenCopiedToInvoice(1L, "MS001");
        assertThat(result).isFalse();
    }

    @Test
    void hasMilestoneBeenCopiedToInvoice_shouldReturnTrueWhenEffectiveInvoiceExists() {
        InvoiceMilestone milestone = new InvoiceMilestone();
        milestone.setDocumentNumber("DOC001");
        when(businessObjectService.findMatching(eq(InvoiceMilestone.class), any(Map.class)))
                .thenReturn(Arrays.asList(milestone));
        when(contractsGrantsInvoiceDocumentService.isInvoiceDocumentEffective("DOC001"))
                .thenReturn(true);

        boolean result = service.hasMilestoneBeenCopiedToInvoice(1L, "MS001");
        assertThat(result).isTrue();
    }

    @Test
    void hasMilestoneBeenCopiedToInvoice_shouldReturnFalseWhenInvoiceNotEffective() {
        InvoiceMilestone milestone = new InvoiceMilestone();
        milestone.setDocumentNumber("DOC001");
        when(businessObjectService.findMatching(eq(InvoiceMilestone.class), any(Map.class)))
                .thenReturn(Arrays.asList(milestone));
        when(contractsGrantsInvoiceDocumentService.isInvoiceDocumentEffective("DOC001"))
                .thenReturn(false);

        boolean result = service.hasMilestoneBeenCopiedToInvoice(1L, "MS001");
        assertThat(result).isFalse();
    }

    @Test
    void hasMilestoneBeenCopiedToInvoice_shouldCacheEffectiveDocumentNumbers() {
        InvoiceMilestone m1 = new InvoiceMilestone();
        m1.setDocumentNumber("DOC001");
        InvoiceMilestone m2 = new InvoiceMilestone();
        m2.setDocumentNumber("DOC001");
        when(businessObjectService.findMatching(eq(InvoiceMilestone.class), any(Map.class)))
                .thenReturn(Arrays.asList(m1, m2));
        when(contractsGrantsInvoiceDocumentService.isInvoiceDocumentEffective("DOC001"))
                .thenReturn(true);

        boolean result = service.hasMilestoneBeenCopiedToInvoice(1L, "MS001");
        assertThat(result).isTrue();
    }
}
