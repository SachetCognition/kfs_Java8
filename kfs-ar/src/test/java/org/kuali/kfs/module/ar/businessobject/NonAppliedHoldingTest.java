package org.kuali.kfs.module.ar.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class NonAppliedHoldingTest extends KfsUnitTestBase {

    private NonAppliedHolding holding;

    @BeforeEach
    void setUp() {
        holding = new NonAppliedHolding();
    }

    @Test
    void testReferenceFinancialDocumentNumber() {
        holding.setReferenceFinancialDocumentNumber("DOC001");
        assertThat(holding.getReferenceFinancialDocumentNumber()).isEqualTo("DOC001");
    }

    @Test
    void testFinancialDocumentLineAmount() {
        KualiDecimal amount = new KualiDecimal(500);
        holding.setFinancialDocumentLineAmount(amount);
        assertThat(holding.getFinancialDocumentLineAmount()).isEqualTo(amount);
    }

    @Test
    void testCustomerNumber() {
        holding.setCustomerNumber("CUST001");
        assertThat(holding.getCustomerNumber()).isEqualTo("CUST001");
    }

    @Test
    void testDefaultValues() {
        assertThat(holding.getReferenceFinancialDocumentNumber()).isNull();
        assertThat(holding.getCustomerNumber()).isNull();
    }
}
