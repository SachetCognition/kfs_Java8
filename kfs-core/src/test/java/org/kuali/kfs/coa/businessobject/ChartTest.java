package org.kuali.kfs.coa.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class ChartTest extends KfsUnitTestBase {

    private Chart chart;

    @BeforeEach
    void setUp() {
        chart = new Chart();
    }

    @Nested
    @DisplayName("Getters and setters")
    class GetterSetterTests {

        @Test
        void chartOfAccountsCode() {
            chart.setChartOfAccountsCode("BL");
            assertThat(chart.getChartOfAccountsCode()).isEqualTo("BL");
        }

        @Test
        void finChartOfAccountDescription() {
            chart.setFinChartOfAccountDescription("Bloomington Campus");
            assertThat(chart.getFinChartOfAccountDescription()).isEqualTo("Bloomington Campus");
        }

        @Test
        void active() {
            chart.setActive(true);
            assertThat(chart.isActive()).isTrue();

            chart.setActive(false);
            assertThat(chart.isActive()).isFalse();
        }

        @Test
        void finCoaManagerPrincipalId() {
            chart.setFinCoaManagerPrincipalId("manager001");
            assertThat(chart.getFinCoaManagerPrincipalId()).isEqualTo("manager001");
        }

        @Test
        void reportsToChartOfAccountsCode() {
            chart.setReportsToChartOfAccountsCode("UA");
            assertThat(chart.getReportsToChartOfAccountsCode()).isEqualTo("UA");
        }

        @Test
        void finAccountsPayableObjectCode() {
            chart.setFinAccountsPayableObjectCode("9041");
            assertThat(chart.getFinAccountsPayableObjectCode()).isEqualTo("9041");
        }

        @Test
        void finExternalEncumbranceObjCd() {
            chart.setFinExternalEncumbranceObjCd("9891");
            assertThat(chart.getFinExternalEncumbranceObjCd()).isEqualTo("9891");
        }

        @Test
        void finPreEncumbranceObjectCode() {
            chart.setFinPreEncumbranceObjectCode("9893");
            assertThat(chart.getFinPreEncumbranceObjectCode()).isEqualTo("9893");
        }

        @Test
        void financialCashObjectCode() {
            chart.setFinancialCashObjectCode("8000");
            assertThat(chart.getFinancialCashObjectCode()).isEqualTo("8000");
        }

        @Test
        void icrIncomeFinancialObjectCode() {
            chart.setIcrIncomeFinancialObjectCode("1699");
            assertThat(chart.getIcrIncomeFinancialObjectCode()).isEqualTo("1699");
        }

        @Test
        void finAccountsReceivableObjCode() {
            chart.setFinAccountsReceivableObjCode("8111");
            assertThat(chart.getFinAccountsReceivableObjCode()).isEqualTo("8111");
        }

        @Test
        void finInternalEncumbranceObjCd() {
            chart.setFinInternalEncumbranceObjCd("9892");
            assertThat(chart.getFinInternalEncumbranceObjCd()).isEqualTo("9892");
        }

        @Test
        void icrExpenseFinancialObjectCd() {
            chart.setIcrExpenseFinancialObjectCd("5699");
            assertThat(chart.getIcrExpenseFinancialObjectCd()).isEqualTo("5699");
        }

        @Test
        void fundBalanceObjectCode() {
            chart.setFundBalanceObjectCode("9899");
            assertThat(chart.getFundBalanceObjectCode()).isEqualTo("9899");
        }

        @Test
        void incBdgtEliminationsFinObjCd() {
            chart.setIncBdgtEliminationsFinObjCd("9750");
            assertThat(chart.getIncBdgtEliminationsFinObjCd()).isEqualTo("9750");
        }

        @Test
        void expBdgtEliminationsFinObjCd() {
            chart.setExpBdgtEliminationsFinObjCd("9850");
            assertThat(chart.getExpBdgtEliminationsFinObjCd()).isEqualTo("9850");
        }
    }

    @Nested
    @DisplayName("KualiCode interface")
    class KualiCodeTests {

        @Test
        void getCodeReturnsChartCode() {
            chart.setChartOfAccountsCode("BL");
            assertThat(chart.getCode()).isEqualTo("BL");
        }

        @Test
        void getNameReturnsDescription() {
            chart.setFinChartOfAccountDescription("Bloomington");
            assertThat(chart.getName()).isEqualTo("Bloomington");
        }

        @Test
        void setCodeSetsChartCode() {
            chart.setCode("UA");
            assertThat(chart.getChartOfAccountsCode()).isEqualTo("UA");
        }

        @Test
        void setNameSetsDescription() {
            chart.setName("University Administration");
            assertThat(chart.getFinChartOfAccountDescription()).isEqualTo("University Administration");
        }
    }

    @Nested
    @DisplayName("Reference objects")
    class ReferenceObjectTests {

        @Test
        void reportsToChartOfAccounts() {
            Chart parent = new Chart();
            parent.setChartOfAccountsCode("UA");
            chart.setReportsToChartOfAccounts(parent);
            assertThat(chart.getReportsToChartOfAccounts().getChartOfAccountsCode()).isEqualTo("UA");
        }

        @Test
        void finAccountsPayableObject() {
            ObjectCode obj = new ObjectCode(2025, "BL", "9041");
            chart.setFinAccountsPayableObject(obj);
            assertThat(chart.getFinAccountsPayableObject()).isSameAs(obj);
        }

        @Test
        void financialCashObject() {
            ObjectCode obj = new ObjectCode(2025, "BL", "8000");
            chart.setFinancialCashObject(obj);
            assertThat(chart.getFinancialCashObject()).isSameAs(obj);
        }

        @Test
        void fundBalanceObject() {
            ObjectCode obj = new ObjectCode();
            chart.setFundBalanceObject(obj);
            assertThat(chart.getFundBalanceObject()).isSameAs(obj);
        }
    }
}
