package org.kuali.kfs.fp.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.fp.businessobject.CashDrawer;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.verify;

class CashDrawerServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private CashDrawerServiceImpl cashDrawerService;

    @Test
    void closeCashDrawer_setsStatusToClosed() {
        CashDrawer drawer = new CashDrawer();
        drawer.setStatusCode(KFSConstants.CashDrawerConstants.STATUS_OPEN);
        drawer.setReferenceFinancialDocumentNumber("DOC1");

        cashDrawerService.closeCashDrawer(drawer);

        assertThat(drawer.getStatusCode()).isEqualTo(KFSConstants.CashDrawerConstants.STATUS_CLOSED);
        assertThat(drawer.getReferenceFinancialDocumentNumber()).isNull();
        verify(businessObjectService).save(drawer);
    }

    @Test
    void openCashDrawer_blankDocumentId_throwsException() {
        CashDrawer drawer = new CashDrawer();
        try {
            cashDrawerService.openCashDrawer(drawer, "");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).contains("blank");
        }
    }

    @Test
    void openCashDrawer_validDocId_setsStatusToOpen() {
        CashDrawer drawer = new CashDrawer();
        drawer.setStatusCode(KFSConstants.CashDrawerConstants.STATUS_CLOSED);

        CashDrawer result = cashDrawerService.openCashDrawer(drawer, "DOC123");

        assertThat(result.getStatusCode()).isEqualTo(KFSConstants.CashDrawerConstants.STATUS_OPEN);
        assertThat(result.getReferenceFinancialDocumentNumber()).isEqualTo("DOC123");
        verify(businessObjectService).save(drawer);
    }
}
