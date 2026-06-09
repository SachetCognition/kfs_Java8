package org.kuali.kfs.module.cab.businessobject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class AccountLineGroupTest extends KfsUnitTestBase {

    private static class ConcreteAccountLineGroup extends AccountLineGroup {
    }

    private ConcreteAccountLineGroup createGroup(Integer fiscalYear, String chart, String account,
                                                  String subAccount, String objectCode, String subObjectCode,
                                                  String periodCode, String docNumber, String refDocNumber,
                                                  String orgRefId, String projectCode) {
        ConcreteAccountLineGroup group = new ConcreteAccountLineGroup();
        group.setUniversityFiscalYear(fiscalYear);
        group.setChartOfAccountsCode(chart);
        group.setAccountNumber(account);
        group.setSubAccountNumber(subAccount);
        group.setFinancialObjectCode(objectCode);
        group.setFinancialSubObjectCode(subObjectCode);
        group.setUniversityFiscalPeriodCode(periodCode);
        group.setDocumentNumber(docNumber);
        group.setReferenceFinancialDocumentNumber(refDocNumber);
        group.setOrganizationReferenceId(orgRefId);
        group.setProjectCode(projectCode);
        return group;
    }

    @Test
    @DisplayName("equals: same fields returns true")
    void equals_sameFields() {
        AccountLineGroup g1 = createGroup(2024, "UA", "1234567", "12345", "7000", "001", "01", "DOC1", "REF1", "ORG1", "PROJ1");
        AccountLineGroup g2 = createGroup(2024, "UA", "1234567", "12345", "7000", "001", "01", "DOC1", "REF1", "ORG1", "PROJ1");
        assertThat(g1).isEqualTo(g2);
    }

    @Test
    @DisplayName("equals: different fields returns false")
    void equals_differentFields() {
        AccountLineGroup g1 = createGroup(2024, "UA", "1234567", "12345", "7000", "001", "01", "DOC1", "REF1", "ORG1", "PROJ1");
        AccountLineGroup g2 = createGroup(2024, "BL", "1234567", "12345", "7000", "001", "01", "DOC1", "REF1", "ORG1", "PROJ1");
        assertThat(g1).isNotEqualTo(g2);
    }

    @Test
    @DisplayName("equals: reflexive")
    void equals_reflexive() {
        AccountLineGroup g1 = createGroup(2024, "UA", "1234567", null, "7000", null, "01", "DOC1", null, null, null);
        assertThat(g1).isEqualTo(g1);
    }

    @Test
    @DisplayName("equals: null returns false")
    void equals_null() {
        AccountLineGroup g1 = createGroup(2024, "UA", "1234567", null, "7000", null, "01", "DOC1", null, null, null);
        assertThat(g1).isNotEqualTo(null);
    }

    @Test
    @DisplayName("equals: filler dashes treated as empty string")
    void equals_fillerDashes() {
        AccountLineGroup g1 = createGroup(2024, "UA", "1234567", "-----", "7000", null, "01", "DOC1", null, null, null);
        AccountLineGroup g2 = createGroup(2024, "UA", "1234567", null, "7000", null, "01", "DOC1", null, null, null);
        assertThat(g1).isEqualTo(g2);
    }

    @Test
    @DisplayName("equals: dash-filled vs empty string treated as equal")
    void equals_dashVsEmpty() {
        AccountLineGroup g1 = createGroup(2024, "UA", "1234567", "---", "7000", "", "01", "DOC1", "", "", "");
        AccountLineGroup g2 = createGroup(2024, "UA", "1234567", null, "7000", null, "01", "DOC1", null, null, null);
        assertThat(g1).isEqualTo(g2);
    }

    @Test
    @DisplayName("hashCode: same fields produce same hash")
    void hashCode_sameFields() {
        AccountLineGroup g1 = createGroup(2024, "UA", "1234567", "12345", "7000", "001", "01", "DOC1", "REF1", "ORG1", "PROJ1");
        AccountLineGroup g2 = createGroup(2024, "UA", "1234567", "12345", "7000", "001", "01", "DOC1", "REF1", "ORG1", "PROJ1");
        assertThat(g1.hashCode()).isEqualTo(g2.hashCode());
    }

    @Test
    @DisplayName("amount getter/setter")
    void amountGetterSetter() {
        ConcreteAccountLineGroup g = new ConcreteAccountLineGroup();
        KualiDecimal amount = new KualiDecimal(1000);
        g.setAmount(amount);
        assertThat(g.getAmount()).isEqualTo(amount);
    }

    @Test
    @DisplayName("all getters/setters work correctly")
    void allGettersSetters() {
        ConcreteAccountLineGroup g = new ConcreteAccountLineGroup();
        g.setUniversityFiscalYear(2025);
        g.setChartOfAccountsCode("BL");
        g.setAccountNumber("9876543");
        g.setSubAccountNumber("54321");
        g.setFinancialObjectCode("8000");
        g.setFinancialSubObjectCode("002");
        g.setUniversityFiscalPeriodCode("06");
        g.setDocumentNumber("DOC9");
        g.setReferenceFinancialDocumentNumber("REF9");
        g.setOrganizationReferenceId("ORG9");
        g.setProjectCode("PROJ9");

        assertThat(g.getUniversityFiscalYear()).isEqualTo(2025);
        assertThat(g.getChartOfAccountsCode()).isEqualTo("BL");
        assertThat(g.getAccountNumber()).isEqualTo("9876543");
        assertThat(g.getSubAccountNumber()).isEqualTo("54321");
        assertThat(g.getFinancialObjectCode()).isEqualTo("8000");
        assertThat(g.getFinancialSubObjectCode()).isEqualTo("002");
        assertThat(g.getUniversityFiscalPeriodCode()).isEqualTo("06");
        assertThat(g.getDocumentNumber()).isEqualTo("DOC9");
        assertThat(g.getReferenceFinancialDocumentNumber()).isEqualTo("REF9");
        assertThat(g.getOrganizationReferenceId()).isEqualTo("ORG9");
        assertThat(g.getProjectCode()).isEqualTo("PROJ9");
    }

    @Test
    @DisplayName("equals: different fiscal year returns false")
    void equals_differentFiscalYear() {
        AccountLineGroup g1 = createGroup(2024, "UA", "1234567", null, "7000", null, "01", "DOC1", null, null, null);
        AccountLineGroup g2 = createGroup(2025, "UA", "1234567", null, "7000", null, "01", "DOC1", null, null, null);
        assertThat(g1).isNotEqualTo(g2);
    }
}
