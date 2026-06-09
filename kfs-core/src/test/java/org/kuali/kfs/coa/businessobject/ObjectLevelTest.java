package org.kuali.kfs.coa.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class ObjectLevelTest extends KfsUnitTestBase {

    private ObjectLevel objectLevel;

    @BeforeEach
    void setUp() {
        objectLevel = new ObjectLevel();
    }

    @Nested
    @DisplayName("Getters and setters")
    class GetterSetterTests {

        @Test
        void chartOfAccountsCode() {
            objectLevel.setChartOfAccountsCode("BL");
            assertThat(objectLevel.getChartOfAccountsCode()).isEqualTo("BL");
        }

        @Test
        void financialObjectLevelCode() {
            objectLevel.setFinancialObjectLevelCode("TRVL");
            assertThat(objectLevel.getFinancialObjectLevelCode()).isEqualTo("TRVL");
        }

        @Test
        void financialObjectLevelName() {
            objectLevel.setFinancialObjectLevelName("Travel");
            assertThat(objectLevel.getFinancialObjectLevelName()).isEqualTo("Travel");
        }

        @Test
        void financialObjectLevelShortNm() {
            objectLevel.setFinancialObjectLevelShortNm("TRVL");
            assertThat(objectLevel.getFinancialObjectLevelShortNm()).isEqualTo("TRVL");
        }

        @Test
        void active() {
            objectLevel.setActive(true);
            assertThat(objectLevel.isActive()).isTrue();
        }

        @Test
        void financialConsolidationObjectCode() {
            objectLevel.setFinancialConsolidationObjectCode("OPEX");
            assertThat(objectLevel.getFinancialConsolidationObjectCode()).isEqualTo("OPEX");
        }

        @Test
        void financialReportingSortCode() {
            objectLevel.setFinancialReportingSortCode("A");
            assertThat(objectLevel.getFinancialReportingSortCode()).isEqualTo("A");
        }
    }

    @Nested
    @DisplayName("Reference objects")
    class ReferenceObjectTests {

        @Test
        void chartOfAccounts() {
            Chart chart = new Chart();
            objectLevel.setChartOfAccounts(chart);
            assertThat(objectLevel.getChartOfAccounts()).isSameAs(chart);
        }

        @Test
        void financialConsolidationObject() {
            ObjectConsolidation oc = new ObjectConsolidation();
            objectLevel.setFinancialConsolidationObject(oc);
            assertThat(objectLevel.getFinancialConsolidationObject()).isSameAs(oc);
        }
    }
}
