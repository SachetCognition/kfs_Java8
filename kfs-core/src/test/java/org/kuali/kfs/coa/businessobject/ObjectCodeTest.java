package org.kuali.kfs.coa.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class ObjectCodeTest extends KfsUnitTestBase {

    private ObjectCode objectCode;

    @BeforeEach
    void setUp() {
        objectCode = new ObjectCode();
    }

    @Nested
    @DisplayName("Constructors")
    class ConstructorTests {

        @Test
        void defaultConstructorInitializesObjectLevel() {
            assertThat(objectCode.getFinancialObjectLevel()).isNotNull();
        }

        @Test
        void defaultConstructorInitializesObjectType() {
            assertThat(objectCode.getFinancialObjectType()).isNotNull();
        }

        @Test
        void parameterizedConstructorSetsFields() {
            ObjectCode oc = new ObjectCode(2025, "BL", "5000");
            assertThat(oc.getUniversityFiscalYear()).isEqualTo(2025);
            assertThat(oc.getChartOfAccountsCode()).isEqualTo("BL");
            assertThat(oc.getFinancialObjectCode()).isEqualTo("5000");
            assertThat(oc.isActive()).isTrue();
        }
    }

    @Nested
    @DisplayName("Getters and setters")
    class GetterSetterTests {

        @Test
        void universityFiscalYear() {
            objectCode.setUniversityFiscalYear(2024);
            assertThat(objectCode.getUniversityFiscalYear()).isEqualTo(2024);
        }

        @Test
        void chartOfAccountsCode() {
            objectCode.setChartOfAccountsCode("UA");
            assertThat(objectCode.getChartOfAccountsCode()).isEqualTo("UA");
        }

        @Test
        void financialObjectCode() {
            objectCode.setFinancialObjectCode("6100");
            assertThat(objectCode.getFinancialObjectCode()).isEqualTo("6100");
        }

        @Test
        void financialObjectCodeName() {
            objectCode.setFinancialObjectCodeName("Travel Expenses");
            assertThat(objectCode.getFinancialObjectCodeName()).isEqualTo("Travel Expenses");
        }

        @Test
        void financialObjectCodeShortName() {
            objectCode.setFinancialObjectCodeShortName("TRAVEL");
            assertThat(objectCode.getFinancialObjectCodeShortName()).isEqualTo("TRAVEL");
        }

        @Test
        void active() {
            objectCode.setActive(false);
            assertThat(objectCode.isActive()).isFalse();
        }

        @Test
        void financialObjectLevelCode() {
            objectCode.setFinancialObjectLevelCode("TRVL");
            assertThat(objectCode.getFinancialObjectLevelCode()).isEqualTo("TRVL");
        }

        @Test
        void financialObjectTypeCode() {
            objectCode.setFinancialObjectTypeCode("EX");
            assertThat(objectCode.getFinancialObjectTypeCode()).isEqualTo("EX");
        }

        @Test
        void financialObjectSubTypeCode() {
            objectCode.setFinancialObjectSubTypeCode("NA");
            assertThat(objectCode.getFinancialObjectSubTypeCode()).isEqualTo("NA");
        }

        @Test
        void financialBudgetAggregationCd() {
            objectCode.setFinancialBudgetAggregationCd("O");
            assertThat(objectCode.getFinancialBudgetAggregationCd()).isEqualTo("O");
        }

        @Test
        void nextYearFinancialObjectCode() {
            objectCode.setNextYearFinancialObjectCode("6200");
            assertThat(objectCode.getNextYearFinancialObjectCode()).isEqualTo("6200");
        }

        @Test
        void reportsToChartOfAccountsCode() {
            objectCode.setReportsToChartOfAccountsCode("BL");
            assertThat(objectCode.getReportsToChartOfAccountsCode()).isEqualTo("BL");
        }

        @Test
        void reportsToFinancialObjectCode() {
            objectCode.setReportsToFinancialObjectCode("5000");
            assertThat(objectCode.getReportsToFinancialObjectCode()).isEqualTo("5000");
        }

        @Test
        void finObjMandatoryTrnfrelimCd() {
            objectCode.setFinObjMandatoryTrnfrelimCd("N");
            assertThat(objectCode.getFinObjMandatoryTrnfrelimCd()).isEqualTo("N");
        }

        @Test
        void financialFederalFundedCode() {
            objectCode.setFinancialFederalFundedCode("N");
            assertThat(objectCode.getFinancialFederalFundedCode()).isEqualTo("N");
        }

        @Test
        void historicalFinancialObjectCode() {
            objectCode.setHistoricalFinancialObjectCode("4999");
            assertThat(objectCode.getHistoricalFinancialObjectCode()).isEqualTo("4999");
        }
    }

    @Nested
    @DisplayName("KualiCode interface")
    class KualiCodeTests {

        @Test
        void getCodeReturnsFinancialObjectCode() {
            objectCode.setFinancialObjectCode("5000");
            assertThat(objectCode.getCode()).isEqualTo("5000");
        }

        @Test
        void getNameReturnsFinancialObjectCodeName() {
            objectCode.setFinancialObjectCodeName("Supplies");
            assertThat(objectCode.getName()).isEqualTo("Supplies");
        }

        @Test
        void setCodeSetsChartOfAccountsCode() {
            objectCode.setCode("BL");
            assertThat(objectCode.getChartOfAccountsCode()).isEqualTo("BL");
        }

        @Test
        void setNameSetsFinancialObjectCodeName() {
            objectCode.setName("Equipment");
            assertThat(objectCode.getFinancialObjectCodeName()).isEqualTo("Equipment");
        }
    }

    @Nested
    @DisplayName("Reference objects")
    class ReferenceObjectTests {

        @Test
        void chartOfAccounts() {
            Chart chart = new Chart();
            chart.setChartOfAccountsCode("BL");
            objectCode.setChartOfAccounts(chart);
            assertThat(objectCode.getChartOfAccounts().getChartOfAccountsCode()).isEqualTo("BL");
        }

        @Test
        void financialObjectLevel() {
            ObjectLevel ol = new ObjectLevel();
            objectCode.setFinancialObjectLevel(ol);
            assertThat(objectCode.getFinancialObjectLevel()).isSameAs(ol);
        }

        @Test
        void financialObjectType() {
            ObjectType ot = new ObjectType();
            objectCode.setFinancialObjectType(ot);
            assertThat(objectCode.getFinancialObjectType()).isSameAs(ot);
        }

        @Test
        void reportsToFinancialObject() {
            ObjectCode rto = new ObjectCode(2025, "BL", "4000");
            objectCode.setReportsToFinancialObject(rto);
            assertThat(objectCode.getReportsToFinancialObject()).isSameAs(rto);
        }
    }
}
