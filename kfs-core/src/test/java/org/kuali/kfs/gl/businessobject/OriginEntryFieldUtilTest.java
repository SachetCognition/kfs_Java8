package org.kuali.kfs.gl.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class OriginEntryFieldUtilTest extends KfsUnitTestBase {

    @Test
    void getBusinessObjectClass_returnsOriginEntryFull() {
        OriginEntryFieldUtil fieldUtil = new OriginEntryFieldUtil();
        assertThat(fieldUtil.getBusinessObjectClass()).isEqualTo(OriginEntryFull.class);
    }

    @Test
    void getOrderedProperties_containsExpectedFields() {
        OriginEntryFieldUtil fieldUtil = new OriginEntryFieldUtil();
        String[] props = fieldUtil.getOrderedProperties();

        assertThat(props).isNotEmpty();
        assertThat(props[0]).isEqualTo(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR);
        assertThat(props[1]).isEqualTo(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE);
        assertThat(props[2]).isEqualTo(KFSPropertyConstants.ACCOUNT_NUMBER);
    }

    @Test
    void getOrderedProperties_has25Fields() {
        OriginEntryFieldUtil fieldUtil = new OriginEntryFieldUtil();
        String[] props = fieldUtil.getOrderedProperties();

        assertThat(props).hasSize(25);
    }

    @Test
    void getOrderedProperties_containsFinancialFields() {
        OriginEntryFieldUtil fieldUtil = new OriginEntryFieldUtil();
        String[] props = fieldUtil.getOrderedProperties();

        assertThat(props).contains(
            KFSPropertyConstants.FINANCIAL_OBJECT_CODE,
            KFSPropertyConstants.FINANCIAL_BALANCE_TYPE_CODE,
            KFSPropertyConstants.DOCUMENT_NUMBER,
            KFSPropertyConstants.TRANSACTION_LEDGER_ENTRY_AMOUNT,
            KFSPropertyConstants.TRANSACTION_DEBIT_CREDIT_CODE
        );
    }

    @Test
    void getOrderedProperties_lastFieldIsEncumbranceCode() {
        OriginEntryFieldUtil fieldUtil = new OriginEntryFieldUtil();
        String[] props = fieldUtil.getOrderedProperties();

        assertThat(props[props.length - 1]).isEqualTo(KFSPropertyConstants.TRANSACTION_ENCUMBRANCE_UPDT_CD);
    }
}
