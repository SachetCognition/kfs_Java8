package org.kuali.kfs.coa.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class ObjectConsolidationTest extends KfsUnitTestBase {

    private ObjectConsolidation oc;

    @BeforeEach
    void setUp() {
        oc = new ObjectConsolidation();
    }

    @Nested
    @DisplayName("Getters and setters")
    class GetterSetterTests {

        @Test
        void chartOfAccountsCode() {
            oc.setChartOfAccountsCode("BL");
            assertThat(oc.getChartOfAccountsCode()).isEqualTo("BL");
        }

        @Test
        void finConsolidationObjectCode() {
            oc.setFinConsolidationObjectCode("OPEX");
            assertThat(oc.getFinConsolidationObjectCode()).isEqualTo("OPEX");
        }

        @Test
        void finConsolidationObjectName() {
            oc.setFinConsolidationObjShortName("OpExp");
            assertThat(oc.getFinConsolidationObjShortName()).isEqualTo("OpExp");
        }

        @Test
        void active() {
            oc.setActive(true);
            assertThat(oc.isActive()).isTrue();
            oc.setActive(false);
            assertThat(oc.isActive()).isFalse();
        }

        @Test
        void financialReportingSortCode() {
            oc.setFinancialReportingSortCode("B");
            assertThat(oc.getFinancialReportingSortCode()).isEqualTo("B");
        }
    }

    @Nested
    @DisplayName("Reference objects")
    class ReferenceObjectTests {

        @Test
        void chartOfAccounts() {
            Chart chart = new Chart();
            oc.setChartOfAccounts(chart);
            assertThat(oc.getChartOfAccounts()).isSameAs(chart);
        }
    }
}
