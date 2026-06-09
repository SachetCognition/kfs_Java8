package org.kuali.kfs.gl.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

class EncumbranceTest extends KfsUnitTestBase {

    private Encumbrance encumbrance;

    @BeforeEach
    void setUp() {
        encumbrance = new Encumbrance();
    }

    @Test
    void defaultConstructor_createsEmptyObject() {
        assertThat(encumbrance.getUniversityFiscalYear()).isNull();
        assertThat(encumbrance.getChartOfAccountsCode()).isNull();
        assertThat(encumbrance.getAccountNumber()).isNull();
    }

    @Test
    void settersAndGetters_fiscalYearAndChart() {
        encumbrance.setUniversityFiscalYear(2024);
        encumbrance.setChartOfAccountsCode("BL");
        encumbrance.setAccountNumber("1234567");

        assertThat(encumbrance.getUniversityFiscalYear()).isEqualTo(2024);
        assertThat(encumbrance.getChartOfAccountsCode()).isEqualTo("BL");
        assertThat(encumbrance.getAccountNumber()).isEqualTo("1234567");
    }

    @Test
    void settersAndGetters_subAccountAndObjectCodes() {
        encumbrance.setSubAccountNumber("SUB1");
        encumbrance.setObjectCode("5000");
        encumbrance.setSubObjectCode("001");

        assertThat(encumbrance.getSubAccountNumber()).isEqualTo("SUB1");
        assertThat(encumbrance.getObjectCode()).isEqualTo("5000");
        assertThat(encumbrance.getSubObjectCode()).isEqualTo("001");
    }

    @Test
    void settersAndGetters_balanceTypeAndDocumentInfo() {
        encumbrance.setBalanceTypeCode("EX");
        encumbrance.setDocumentTypeCode("GLPE");
        encumbrance.setOriginCode("01");
        encumbrance.setDocumentNumber("DOC001");

        assertThat(encumbrance.getBalanceTypeCode()).isEqualTo("EX");
        assertThat(encumbrance.getDocumentTypeCode()).isEqualTo("GLPE");
        assertThat(encumbrance.getOriginCode()).isEqualTo("01");
        assertThat(encumbrance.getDocumentNumber()).isEqualTo("DOC001");
    }

    @Test
    void settersAndGetters_amounts() {
        encumbrance.setAccountLineEncumbranceAmount(new KualiDecimal(1000));
        encumbrance.setAccountLineEncumbranceClosedAmount(new KualiDecimal(300));

        assertThat(encumbrance.getAccountLineEncumbranceAmount()).isEqualTo(new KualiDecimal(1000));
        assertThat(encumbrance.getAccountLineEncumbranceClosedAmount()).isEqualTo(new KualiDecimal(300));
    }

    @Test
    void settersAndGetters_encumbranceDescription() {
        encumbrance.setTransactionEncumbranceDescription("Test encumbrance");
        assertThat(encumbrance.getTransactionEncumbranceDescription()).isEqualTo("Test encumbrance");
    }

    @Test
    void settersAndGetters_encumbranceDate() {
        Date date = Date.valueOf("2024-06-15");
        encumbrance.setTransactionEncumbranceDate(date);
        assertThat(encumbrance.getTransactionEncumbranceDate()).isEqualTo(date);
    }

    @Test
    void settersAndGetters_purgeCode() {
        encumbrance.setAccountLineEncumbrancePurgeCode("Y");
        assertThat(encumbrance.getAccountLineEncumbrancePurgeCode()).isEqualTo("Y");
    }
}
