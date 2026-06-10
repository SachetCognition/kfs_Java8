package org.kuali.kfs.coa.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectCodeTest extends KfsUnitTestBase {

    private ProjectCode projectCode;

    @BeforeEach
    void setUp() {
        projectCode = new ProjectCode();
    }

    @Nested
    @DisplayName("Getters and setters")
    class GetterSetterTests {

        @Test
        void code() {
            projectCode.setCode("PROJ01");
            assertThat(projectCode.getCode()).isEqualTo("PROJ01");
        }

        @Test
        void name() {
            projectCode.setName("Research Project");
            assertThat(projectCode.getName()).isEqualTo("Research Project");
        }

        @Test
        void active() {
            projectCode.setActive(true);
            assertThat(projectCode.isActive()).isTrue();
            projectCode.setActive(false);
            assertThat(projectCode.isActive()).isFalse();
        }

        @Test
        void chartOfAccountsCode() {
            projectCode.setChartOfAccountsCode("BL");
            assertThat(projectCode.getChartOfAccountsCode()).isEqualTo("BL");
        }

        @Test
        void organizationCode() {
            projectCode.setOrganizationCode("CHEM");
            assertThat(projectCode.getOrganizationCode()).isEqualTo("CHEM");
        }
    }

    @Nested
    @DisplayName("Reference objects")
    class ReferenceObjectTests {

        @Test
        void chart() {
            Chart chart = new Chart();
            projectCode.setChartOfAccounts(chart);
            assertThat(projectCode.getChartOfAccounts()).isSameAs(chart);
        }

        @Test
        void organization() {
            Organization org = new Organization();
            projectCode.setOrganization(org);
            assertThat(projectCode.getOrganization()).isSameAs(org);
        }
    }
}
