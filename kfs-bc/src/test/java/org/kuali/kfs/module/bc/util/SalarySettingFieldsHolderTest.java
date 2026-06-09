package org.kuali.kfs.module.bc.util;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class SalarySettingFieldsHolderTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor_allFieldsNull() {
        SalarySettingFieldsHolder holder = new SalarySettingFieldsHolder();
        assertThat(holder.getDocumentNumber()).isNull();
        assertThat(holder.getChartOfAccountsCode()).isNull();
        assertThat(holder.getAccountNumber()).isNull();
        assertThat(holder.getSubAccountNumber()).isNull();
        assertThat(holder.getFinancialObjectCode()).isNull();
        assertThat(holder.getFinancialSubObjectCode()).isNull();
        assertThat(holder.getFinancialBalanceTypeCode()).isNull();
        assertThat(holder.getFinancialObjectTypeCode()).isNull();
        assertThat(holder.getPositionNumber()).isNull();
        assertThat(holder.getEmplid()).isNull();
    }

    @Test
    void setAndGetDocumentNumber() {
        SalarySettingFieldsHolder holder = new SalarySettingFieldsHolder();
        holder.setDocumentNumber("DOC123");
        assertThat(holder.getDocumentNumber()).isEqualTo("DOC123");
    }

    @Test
    void setAndGetChartOfAccountsCode() {
        SalarySettingFieldsHolder holder = new SalarySettingFieldsHolder();
        holder.setChartOfAccountsCode("UA");
        assertThat(holder.getChartOfAccountsCode()).isEqualTo("UA");
    }

    @Test
    void setAndGetAccountNumber() {
        SalarySettingFieldsHolder holder = new SalarySettingFieldsHolder();
        holder.setAccountNumber("1234567");
        assertThat(holder.getAccountNumber()).isEqualTo("1234567");
    }

    @Test
    void setAndGetSubAccountNumber() {
        SalarySettingFieldsHolder holder = new SalarySettingFieldsHolder();
        holder.setSubAccountNumber("SUB01");
        assertThat(holder.getSubAccountNumber()).isEqualTo("SUB01");
    }

    @Test
    void setAndGetFinancialObjectCode() {
        SalarySettingFieldsHolder holder = new SalarySettingFieldsHolder();
        holder.setFinancialObjectCode("5000");
        assertThat(holder.getFinancialObjectCode()).isEqualTo("5000");
    }

    @Test
    void setAndGetFinancialSubObjectCode() {
        SalarySettingFieldsHolder holder = new SalarySettingFieldsHolder();
        holder.setFinancialSubObjectCode("001");
        assertThat(holder.getFinancialSubObjectCode()).isEqualTo("001");
    }

    @Test
    void setAndGetFinancialBalanceTypeCode() {
        SalarySettingFieldsHolder holder = new SalarySettingFieldsHolder();
        holder.setFinancialBalanceTypeCode("AC");
        assertThat(holder.getFinancialBalanceTypeCode()).isEqualTo("AC");
    }

    @Test
    void setAndGetFinancialObjectTypeCode() {
        SalarySettingFieldsHolder holder = new SalarySettingFieldsHolder();
        holder.setFinancialObjectTypeCode("EX");
        assertThat(holder.getFinancialObjectTypeCode()).isEqualTo("EX");
    }

    @Test
    void setAndGetPositionNumber() {
        SalarySettingFieldsHolder holder = new SalarySettingFieldsHolder();
        holder.setPositionNumber("POS001");
        assertThat(holder.getPositionNumber()).isEqualTo("POS001");
    }

    @Test
    void setAndGetEmplid() {
        SalarySettingFieldsHolder holder = new SalarySettingFieldsHolder();
        holder.setEmplid("EMP001");
        assertThat(holder.getEmplid()).isEqualTo("EMP001");
    }
}
