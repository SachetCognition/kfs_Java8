package org.kuali.kfs.sys.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class ChartOrgHolderImplTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor_hasNullFields() {
        ChartOrgHolderImpl holder = new ChartOrgHolderImpl();
        assertThat(holder.getChartOfAccountsCode()).isNull();
        assertThat(holder.getOrganizationCode()).isNull();
    }

    @Test
    void parameterizedConstructor_setsFields() {
        ChartOrgHolderImpl holder = new ChartOrgHolderImpl("BL", "ACCT");
        assertThat(holder.getChartOfAccountsCode()).isEqualTo("BL");
        assertThat(holder.getOrganizationCode()).isEqualTo("ACCT");
    }

    @Test
    void setters() {
        ChartOrgHolderImpl holder = new ChartOrgHolderImpl();
        holder.setChartOfAccountsCode("UA");
        holder.setOrganizationCode("ORG1");
        assertThat(holder.getChartOfAccountsCode()).isEqualTo("UA");
        assertThat(holder.getOrganizationCode()).isEqualTo("ORG1");
    }

    @Test
    void equals_sameValues_returnsTrue() {
        ChartOrgHolderImpl h1 = new ChartOrgHolderImpl("BL", "ACCT");
        ChartOrgHolderImpl h2 = new ChartOrgHolderImpl("BL", "ACCT");
        assertThat(h1).isEqualTo(h2);
    }

    @Test
    void equals_differentChart_returnsFalse() {
        ChartOrgHolderImpl h1 = new ChartOrgHolderImpl("BL", "ACCT");
        ChartOrgHolderImpl h2 = new ChartOrgHolderImpl("UA", "ACCT");
        assertThat(h1).isNotEqualTo(h2);
    }

    @Test
    void equals_differentOrg_returnsFalse() {
        ChartOrgHolderImpl h1 = new ChartOrgHolderImpl("BL", "ACCT");
        ChartOrgHolderImpl h2 = new ChartOrgHolderImpl("BL", "DIFF");
        assertThat(h1).isNotEqualTo(h2);
    }

    @Test
    void equals_null_returnsFalse() {
        ChartOrgHolderImpl h1 = new ChartOrgHolderImpl("BL", "ACCT");
        assertThat(h1.equals(null)).isFalse();
    }

    @Test
    void equals_differentType_returnsFalse() {
        ChartOrgHolderImpl h1 = new ChartOrgHolderImpl("BL", "ACCT");
        assertThat(h1.equals("not a holder")).isFalse();
    }

    @Test
    void hashCode_sameForEqualObjects() {
        ChartOrgHolderImpl h1 = new ChartOrgHolderImpl("BL", "ACCT");
        ChartOrgHolderImpl h2 = new ChartOrgHolderImpl("BL", "ACCT");
        assertThat(h1.hashCode()).isEqualTo(h2.hashCode());
    }

    @Test
    void toString_format() {
        ChartOrgHolderImpl holder = new ChartOrgHolderImpl("BL", "ACCT");
        assertThat(holder.toString()).isEqualTo("BL-ACCT");
    }

    @Test
    void toString_withNullValues() {
        ChartOrgHolderImpl holder = new ChartOrgHolderImpl();
        assertThat(holder.toString()).isEqualTo("null-null");
    }
}
