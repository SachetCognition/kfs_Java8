package org.kuali.kfs.fp.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.fp.businessobject.CashDrawer;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CashDrawerServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private CashDrawerServiceImpl cashDrawerService;

    private CashDrawer drawer;

    @BeforeEach
    void setUp() {
        drawer = new CashDrawer();
        drawer.setCampusCode("BL");
        drawer.setStatusCode(KFSConstants.CashDrawerConstants.STATUS_OPEN);
        drawer.setReferenceFinancialDocumentNumber("DOC-001");
    }

    @Test
    void closeCashDrawerSetsStatusToClosedAndClearsDocRef() {
        cashDrawerService.closeCashDrawer(drawer);

        assertThat(drawer.getStatusCode()).isEqualTo(KFSConstants.CashDrawerConstants.STATUS_CLOSED);
        assertThat(drawer.getReferenceFinancialDocumentNumber()).isNull();
        verify(businessObjectService).save(drawer);
    }

    @Test
    void closeCashDrawerByCampusCodeRetrievesAndCloses() {
        Map<String, Object> keyMap = new HashMap<>();
        keyMap.put("campusCode", "BL");
        when(businessObjectService.findByPrimaryKey(eq(CashDrawer.class), any(Map.class))).thenReturn(drawer);

        cashDrawerService.closeCashDrawer("BL");

        assertThat(drawer.getStatusCode()).isEqualTo(KFSConstants.CashDrawerConstants.STATUS_CLOSED);
        verify(businessObjectService).save(drawer);
    }

    @Test
    void openCashDrawerSetsStatusAndDocRef() {
        drawer.setStatusCode(KFSConstants.CashDrawerConstants.STATUS_CLOSED);
        CashDrawer result = cashDrawerService.openCashDrawer(drawer, "DOC-002");

        assertThat(result.getStatusCode()).isEqualTo(KFSConstants.CashDrawerConstants.STATUS_OPEN);
        assertThat(result.getReferenceFinancialDocumentNumber()).isEqualTo("DOC-002");
        verify(businessObjectService).save(drawer);
    }

    @Test
    void openCashDrawerWithBlankDocumentIdThrowsException() {
        assertThatThrownBy(() -> cashDrawerService.openCashDrawer(drawer, ""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("invalid (blank) documentId");
    }

    @Test
    void openCashDrawerWithNullDocumentIdThrowsException() {
        assertThatThrownBy(() -> cashDrawerService.openCashDrawer(drawer, null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void openCashDrawerByCampusCodeRetrievesAndOpens() {
        when(businessObjectService.findByPrimaryKey(eq(CashDrawer.class), any(Map.class))).thenReturn(drawer);

        CashDrawer result = cashDrawerService.openCashDrawer("BL", "DOC-003");

        assertThat(result.getStatusCode()).isEqualTo(KFSConstants.CashDrawerConstants.STATUS_OPEN);
        assertThat(result.getReferenceFinancialDocumentNumber()).isEqualTo("DOC-003");
    }

    @Test
    void lockCashDrawerChangesStatusFromOpenToLocked() {
        drawer.setStatusCode(KFSConstants.CashDrawerConstants.STATUS_OPEN);
        drawer.setReferenceFinancialDocumentNumber("DOC-001");

        cashDrawerService.lockCashDrawer(drawer, "DOC-001");

        assertThat(drawer.getStatusCode()).isEqualTo(KFSConstants.CashDrawerConstants.STATUS_LOCKED);
        verify(businessObjectService).save(drawer);
    }

    @Test
    void lockCashDrawerThrowsWhenNotOpen() {
        drawer.setStatusCode(KFSConstants.CashDrawerConstants.STATUS_CLOSED);

        assertThatThrownBy(() -> cashDrawerService.lockCashDrawer(drawer, "DOC-001"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("cannot be locked because it is not open");
    }

    @Test
    void lockCashDrawerThrowsWhenDocumentMismatch() {
        drawer.setStatusCode(KFSConstants.CashDrawerConstants.STATUS_OPEN);
        drawer.setReferenceFinancialDocumentNumber("DOC-001");

        assertThatThrownBy(() -> cashDrawerService.lockCashDrawer(drawer, "DOC-999"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("cannot be locked because it was opened by document");
    }

    @Test
    void lockCashDrawerWithBlankDocumentIdThrowsException() {
        assertThatThrownBy(() -> cashDrawerService.lockCashDrawer(drawer, ""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void unlockCashDrawerChangesStatusFromLockedToOpen() {
        drawer.setStatusCode(KFSConstants.CashDrawerConstants.STATUS_LOCKED);
        drawer.setReferenceFinancialDocumentNumber("DOC-001");

        cashDrawerService.unlockCashDrawer(drawer, "DOC-001");

        assertThat(drawer.getStatusCode()).isEqualTo(KFSConstants.CashDrawerConstants.STATUS_OPEN);
        verify(businessObjectService).save(drawer);
    }

    @Test
    void unlockCashDrawerThrowsWhenNotLocked() {
        drawer.setStatusCode(KFSConstants.CashDrawerConstants.STATUS_OPEN);

        assertThatThrownBy(() -> cashDrawerService.unlockCashDrawer(drawer, "DOC-001"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("cannot be unlocked because it is not locked");
    }

    @Test
    void unlockCashDrawerThrowsWhenDocumentMismatch() {
        drawer.setStatusCode(KFSConstants.CashDrawerConstants.STATUS_LOCKED);
        drawer.setReferenceFinancialDocumentNumber("DOC-001");

        assertThatThrownBy(() -> cashDrawerService.unlockCashDrawer(drawer, "DOC-999"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("cannot be unlocked because it was locked by document");
    }

    @Test
    void unlockCashDrawerWithBlankDocumentIdThrowsException() {
        assertThatThrownBy(() -> cashDrawerService.unlockCashDrawer(drawer, ""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getByCampusCodeDelegatesToBusinessObjectService() {
        when(businessObjectService.findByPrimaryKey(eq(CashDrawer.class), any(Map.class))).thenReturn(drawer);

        CashDrawer result = cashDrawerService.getByCampusCode("BL");

        assertThat(result).isSameAs(drawer);
    }

    @Test
    void getByCampusCodeWithBlankCodeThrowsException() {
        assertThatThrownBy(() -> cashDrawerService.getByCampusCode(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("invalid (blank) campusCode");
    }

    @Test
    void saveWithNullCashDrawerThrowsException() {
        assertThatThrownBy(() -> cashDrawerService.closeCashDrawer((CashDrawer) null))
                .isInstanceOf(Exception.class);
    }

    @Test
    void getCoinTotalReturnsZeroForNullDrawer() {
        assertThat(cashDrawerService.getCoinTotal(null)).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void getCoinTotalReturnsZeroForDrawerWithNullAmounts() {
        CashDrawer emptyDrawer = new CashDrawer();
        assertThat(cashDrawerService.getCoinTotal(emptyDrawer)).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void getCurrencyTotalReturnsZeroForNullDrawer() {
        assertThat(cashDrawerService.getCurrencyTotal(null)).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void getCurrencyTotalReturnsZeroForDrawerWithNullAmounts() {
        CashDrawer emptyDrawer = new CashDrawer();
        assertThat(cashDrawerService.getCurrencyTotal(emptyDrawer)).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void lockCashDrawerByCampusCodeRetrievesAndLocks() {
        drawer.setStatusCode(KFSConstants.CashDrawerConstants.STATUS_OPEN);
        drawer.setReferenceFinancialDocumentNumber("DOC-001");
        when(businessObjectService.findByPrimaryKey(eq(CashDrawer.class), any(Map.class))).thenReturn(drawer);

        cashDrawerService.lockCashDrawer("BL", "DOC-001");

        assertThat(drawer.getStatusCode()).isEqualTo(KFSConstants.CashDrawerConstants.STATUS_LOCKED);
    }

    @Test
    void unlockCashDrawerByCampusCodeRetrievesAndUnlocks() {
        drawer.setStatusCode(KFSConstants.CashDrawerConstants.STATUS_LOCKED);
        drawer.setReferenceFinancialDocumentNumber("DOC-001");
        when(businessObjectService.findByPrimaryKey(eq(CashDrawer.class), any(Map.class))).thenReturn(drawer);

        cashDrawerService.unlockCashDrawer("BL", "DOC-001");

        assertThat(drawer.getStatusCode()).isEqualTo(KFSConstants.CashDrawerConstants.STATUS_OPEN);
    }
}
