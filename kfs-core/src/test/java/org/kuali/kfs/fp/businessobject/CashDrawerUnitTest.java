package org.kuali.kfs.fp.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class CashDrawerUnitTest extends KfsUnitTestBase {

    private CashDrawer cashDrawer;

    @BeforeEach
    void setUp() {
        cashDrawer = new CashDrawer();
    }

    @Test
    void isOpenReturnsTrueForOpenStatus() {
        cashDrawer.setStatusCode(KFSConstants.CashDrawerConstants.STATUS_OPEN);
        assertThat(cashDrawer.isOpen()).isTrue();
    }

    @Test
    void isOpenReturnsFalseForClosedStatus() {
        cashDrawer.setStatusCode(KFSConstants.CashDrawerConstants.STATUS_CLOSED);
        assertThat(cashDrawer.isOpen()).isFalse();
    }

    @Test
    void isOpenReturnsFalseForLockedStatus() {
        cashDrawer.setStatusCode(KFSConstants.CashDrawerConstants.STATUS_LOCKED);
        assertThat(cashDrawer.isOpen()).isFalse();
    }

    @Test
    void isClosedReturnsTrueForClosedStatus() {
        cashDrawer.setStatusCode(KFSConstants.CashDrawerConstants.STATUS_CLOSED);
        assertThat(cashDrawer.isClosed()).isTrue();
    }

    @Test
    void isClosedReturnsTrueWhenStatusCodeIsNull() {
        cashDrawer.setStatusCode(null);
        assertThat(cashDrawer.isClosed()).isTrue();
    }

    @Test
    void isClosedReturnsTrueWhenRefDocNumberIsNull() {
        cashDrawer.setStatusCode(KFSConstants.CashDrawerConstants.STATUS_OPEN);
        cashDrawer.setReferenceFinancialDocumentNumber(null);
        assertThat(cashDrawer.isClosed()).isTrue();
    }

    @Test
    void isClosedReturnsFalseForOpenStatusWithRefDocNumber() {
        cashDrawer.setStatusCode(KFSConstants.CashDrawerConstants.STATUS_OPEN);
        cashDrawer.setReferenceFinancialDocumentNumber("DOC-001");
        assertThat(cashDrawer.isClosed()).isFalse();
    }

    @Test
    void isLockedReturnsTrueForLockedStatus() {
        cashDrawer.setStatusCode(KFSConstants.CashDrawerConstants.STATUS_LOCKED);
        assertThat(cashDrawer.isLocked()).isTrue();
    }

    @Test
    void isLockedReturnsFalseForOpenStatus() {
        cashDrawer.setStatusCode(KFSConstants.CashDrawerConstants.STATUS_OPEN);
        assertThat(cashDrawer.isLocked()).isFalse();
    }

    @Test
    void setAndGetCampusCode() {
        cashDrawer.setCampusCode("BL");
        assertThat(cashDrawer.getCampusCode()).isEqualTo("BL");
    }

    @Test
    void setAndGetStatusCode() {
        cashDrawer.setStatusCode("O");
        assertThat(cashDrawer.getStatusCode()).isEqualTo("O");
    }

    @Test
    void setAndGetCashDrawerTotalAmount() {
        KualiDecimal total = new KualiDecimal(1500.00);
        cashDrawer.setCashDrawerTotalAmount(total);
        assertThat(cashDrawer.getCashDrawerTotalAmount()).isEqualTo(total);
    }

    @Test
    void setAndGetReferenceFinancialDocumentNumber() {
        cashDrawer.setReferenceFinancialDocumentNumber("DOC-123");
        assertThat(cashDrawer.getReferenceFinancialDocumentNumber()).isEqualTo("DOC-123");
    }

    @Test
    void currencyAmountGettersAndSetters() {
        cashDrawer.setFinancialDocumentHundredDollarAmount(new KualiDecimal(500.00));
        cashDrawer.setFinancialDocumentFiftyDollarAmount(new KualiDecimal(250.00));
        cashDrawer.setFinancialDocumentTwentyDollarAmount(new KualiDecimal(100.00));
        cashDrawer.setFinancialDocumentTenDollarAmount(new KualiDecimal(50.00));
        cashDrawer.setFinancialDocumentFiveDollarAmount(new KualiDecimal(25.00));
        cashDrawer.setFinancialDocumentTwoDollarAmount(new KualiDecimal(10.00));
        cashDrawer.setFinancialDocumentOneDollarAmount(new KualiDecimal(5.00));
        cashDrawer.setFinancialDocumentOtherDollarAmount(new KualiDecimal(3.00));

        assertThat(cashDrawer.getFinancialDocumentHundredDollarAmount()).isEqualTo(new KualiDecimal(500.00));
        assertThat(cashDrawer.getFinancialDocumentFiftyDollarAmount()).isEqualTo(new KualiDecimal(250.00));
        assertThat(cashDrawer.getFinancialDocumentTwentyDollarAmount()).isEqualTo(new KualiDecimal(100.00));
        assertThat(cashDrawer.getFinancialDocumentTenDollarAmount()).isEqualTo(new KualiDecimal(50.00));
        assertThat(cashDrawer.getFinancialDocumentFiveDollarAmount()).isEqualTo(new KualiDecimal(25.00));
        assertThat(cashDrawer.getFinancialDocumentTwoDollarAmount()).isEqualTo(new KualiDecimal(10.00));
        assertThat(cashDrawer.getFinancialDocumentOneDollarAmount()).isEqualTo(new KualiDecimal(5.00));
        assertThat(cashDrawer.getFinancialDocumentOtherDollarAmount()).isEqualTo(new KualiDecimal(3.00));
    }

    @Test
    void coinAmountGettersAndSetters() {
        cashDrawer.setFinancialDocumentHundredCentAmount(new KualiDecimal(5.00));
        cashDrawer.setFinancialDocumentFiftyCentAmount(new KualiDecimal(2.50));
        cashDrawer.setFinancialDocumentTwentyFiveCentAmount(new KualiDecimal(1.25));
        cashDrawer.setFinancialDocumentTenCentAmount(new KualiDecimal(0.50));
        cashDrawer.setFinancialDocumentFiveCentAmount(new KualiDecimal(0.25));
        cashDrawer.setFinancialDocumentOneCentAmount(new KualiDecimal(0.05));
        cashDrawer.setFinancialDocumentOtherCentAmount(new KualiDecimal(0.03));

        assertThat(cashDrawer.getFinancialDocumentHundredCentAmount()).isEqualTo(new KualiDecimal(5.00));
        assertThat(cashDrawer.getFinancialDocumentFiftyCentAmount()).isEqualTo(new KualiDecimal(2.50));
        assertThat(cashDrawer.getFinancialDocumentTwentyFiveCentAmount()).isEqualTo(new KualiDecimal(1.25));
        assertThat(cashDrawer.getFinancialDocumentTenCentAmount()).isEqualTo(new KualiDecimal(0.50));
        assertThat(cashDrawer.getFinancialDocumentFiveCentAmount()).isEqualTo(new KualiDecimal(0.25));
        assertThat(cashDrawer.getFinancialDocumentOneCentAmount()).isEqualTo(new KualiDecimal(0.05));
        assertThat(cashDrawer.getFinancialDocumentOtherCentAmount()).isEqualTo(new KualiDecimal(0.03));
    }

    @Test
    void miscellaneousAdvanceAmountGetterAndSetter() {
        cashDrawer.setFinancialDocumentMiscellaneousAdvanceAmount(new KualiDecimal(42.00));
        assertThat(cashDrawer.getFinancialDocumentMiscellaneousAdvanceAmount()).isEqualTo(new KualiDecimal(42.00));
    }
}
