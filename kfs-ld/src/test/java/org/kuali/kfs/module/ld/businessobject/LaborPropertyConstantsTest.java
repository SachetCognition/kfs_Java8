package org.kuali.kfs.module.ld.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ld.LaborPropertyConstants;
import org.kuali.kfs.module.ld.LaborPropertyConstants.AccountingPeriodProperties;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class LaborPropertyConstantsTest extends KfsUnitTestBase {

    @Test
    void testAccountingPeriodPropertiesCodeToArrayHas13Elements() {
        String[] codes = AccountingPeriodProperties.codeToArray();
        assertThat(codes).hasSize(13);
    }

    @Test
    void testAccountingPeriodPropertiesNamesToArrayHas13Elements() {
        String[] names = AccountingPeriodProperties.namesToArray();
        assertThat(names).hasSize(13);
    }

    @Test
    void testAccountingPeriodPropertiesToArrayHas13Elements() {
        AccountingPeriodProperties[] props = AccountingPeriodProperties.toArray();
        assertThat(props).hasSize(13);
    }

    @Test
    void testAccountingPeriodPropertiesCodeToArrayStartsWithJulyCode() {
        String[] codes = AccountingPeriodProperties.codeToArray();
        assertThat(codes[0]).isEqualTo(AccountingPeriodProperties.JULY.periodCode);
    }

    @Test
    void testAccountingPeriodPropertiesNamesToArrayStartsWithJulyName() {
        String[] names = AccountingPeriodProperties.namesToArray();
        assertThat(names[0]).isEqualTo(AccountingPeriodProperties.JULY.propertyName);
    }

    @Test
    void testAccountingPeriodPropertiesEndsWithYearEnd() {
        String[] codes = AccountingPeriodProperties.codeToArray();
        assertThat(codes[12]).isEqualTo(AccountingPeriodProperties.YEAR_END.periodCode);
    }

    @Test
    void testStaticConstantsAreCorrect() {
        assertThat(LaborPropertyConstants.EARN_CODE).isEqualTo("earnCode");
        assertThat(LaborPropertyConstants.GRADE).isEqualTo("grade");
        assertThat(LaborPropertyConstants.HRMS_COMPANY).isEqualTo("hrmsCompany");
        assertThat(LaborPropertyConstants.PAY_GROUP).isEqualTo("payGroup");
    }
}
