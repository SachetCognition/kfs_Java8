package org.kuali.kfs.module.ld.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class LaborOriginEntryFieldUtilTest extends KfsUnitTestBase {

    private LaborOriginEntryFieldUtil fieldUtil;

    @BeforeEach
    void setUp() {
        fieldUtil = new LaborOriginEntryFieldUtil();
    }

    @Test
    void testGetBusinessObjectClassReturnsLaborOriginEntry() {
        assertThat(fieldUtil.getBusinessObjectClass()).isEqualTo(LaborOriginEntry.class);
    }

    @Test
    void testGetOrderedPropertiesIsNotEmpty() {
        String[] props = fieldUtil.getOrderedProperties();
        assertThat(props).isNotNull().isNotEmpty();
    }

    @Test
    void testGetOrderedPropertiesStartsWithFiscalYear() {
        String[] props = fieldUtil.getOrderedProperties();
        assertThat(props[0]).isEqualTo(KFSPropertyConstants.UNIVERSITY_FISCAL_YEAR);
    }

    @Test
    void testGetOrderedPropertiesContainsLaborFields() {
        String[] props = fieldUtil.getOrderedProperties();
        assertThat(props).contains(
                KFSPropertyConstants.POSITION_NUMBER,
                KFSPropertyConstants.EMPLID,
                KFSPropertyConstants.EARN_CODE,
                KFSPropertyConstants.PAY_GROUP
        );
    }

    @Test
    void testOrderedPropertiesHasExpectedCount() {
        String[] props = fieldUtil.getOrderedProperties();
        assertThat(props.length).isGreaterThan(30);
    }

    @Test
    void testOrderedPropertiesContainsChartCode() {
        String[] props = fieldUtil.getOrderedProperties();
        assertThat(props).contains(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE);
    }

    @Test
    void testOrderedPropertiesContainsAccountNumber() {
        String[] props = fieldUtil.getOrderedProperties();
        assertThat(props).contains(KFSPropertyConstants.ACCOUNT_NUMBER);
    }

    @Test
    void testOrderedPropertiesContainsDocumentNumber() {
        String[] props = fieldUtil.getOrderedProperties();
        assertThat(props).contains(KFSPropertyConstants.DOCUMENT_NUMBER);
    }

    @Test
    void testOrderedPropertiesContainsFinancialObjectCode() {
        String[] props = fieldUtil.getOrderedProperties();
        assertThat(props).contains(KFSPropertyConstants.FINANCIAL_OBJECT_CODE);
    }
}
