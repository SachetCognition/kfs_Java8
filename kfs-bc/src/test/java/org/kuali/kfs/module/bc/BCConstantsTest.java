package org.kuali.kfs.module.bc;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.BCConstants.AccountSalarySettingOnlyCause;
import org.kuali.kfs.module.bc.BCConstants.LockStatus;
import org.kuali.kfs.module.bc.BCConstants.MonthSpreadDeleteType;
import org.kuali.kfs.module.bc.BCConstants.OrgSelControlOption;
import org.kuali.kfs.module.bc.BCConstants.OrgSelOpMode;
import org.kuali.kfs.module.bc.BCConstants.RequestImportErrorCode;
import org.kuali.kfs.module.bc.BCConstants.RequestImportFieldSeparator;
import org.kuali.kfs.module.bc.BCConstants.RequestImportFileType;
import org.kuali.kfs.module.bc.BCConstants.RequestImportTextFieldDelimiter;
import org.kuali.kfs.module.bc.BCConstants.SynchronizationCheckType;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class BCConstantsTest extends KfsUnitTestBase {

    @Test
    void lockStatus_containsExpectedValues() {
        assertThat(LockStatus.values()).contains(
                LockStatus.SUCCESS,
                LockStatus.BY_OTHER,
                LockStatus.NO_DOOR,
                LockStatus.OPTIMISTIC_EX,
                LockStatus.FLOCK_FOUND
        );
    }

    @Test
    void orgSelOpMode_containsExpectedValues() {
        assertThat(OrgSelOpMode.values()).contains(
                OrgSelOpMode.SALSET,
                OrgSelOpMode.PULLUP,
                OrgSelOpMode.PUSHDOWN,
                OrgSelOpMode.REPORTS,
                OrgSelOpMode.ACCOUNT
        );
    }

    @Test
    void orgSelControlOption_containsExpectedValues() {
        assertThat(OrgSelControlOption.values()).contains(
                OrgSelControlOption.ORG,
                OrgSelControlOption.SUBORG,
                OrgSelControlOption.BOTH,
                OrgSelControlOption.ORGLEV,
                OrgSelControlOption.MGRLEV,
                OrgSelControlOption.LEVONE,
                OrgSelControlOption.LEVZERO
        );
    }

    @Test
    void monthSpreadDeleteType_containsExpectedValues() {
        assertThat(MonthSpreadDeleteType.values()).contains(
                MonthSpreadDeleteType.EXPENDITURE,
                MonthSpreadDeleteType.REVENUE,
                MonthSpreadDeleteType.NONE
        );
    }

    @Test
    void accountSalarySettingOnlyCause_containsExpectedValues() {
        assertThat(AccountSalarySettingOnlyCause.values()).contains(
                AccountSalarySettingOnlyCause.NONE,
                AccountSalarySettingOnlyCause.FUND,
                AccountSalarySettingOnlyCause.SUBFUND,
                AccountSalarySettingOnlyCause.FUND_AND_SUBFUND
        );
    }

    @Test
    void synchronizationCheckType_hasCodesAndDescriptions() {
        assertThat(SynchronizationCheckType.NONE.typeCode).isEqualTo("NONE");
        assertThat(SynchronizationCheckType.NONE.typeDescription).isEqualTo("No Sync Check");
        assertThat(SynchronizationCheckType.POSN.typeCode).isEqualTo("POSN");
        assertThat(SynchronizationCheckType.EID.typeCode).isEqualTo("EID");
        assertThat(SynchronizationCheckType.ALL.typeCode).isEqualTo("ALL");
    }

    @Test
    void requestImportFileType_containsExpectedValues() {
        assertThat(RequestImportFileType.values()).contains(
                RequestImportFileType.ANNUAL,
                RequestImportFileType.MONTHLY
        );
    }

    @Test
    void requestImportFieldSeparator_hasSeparatorValues() {
        assertThat(RequestImportFieldSeparator.COMMA.getSeparator()).isEqualTo(",");
        assertThat(RequestImportFieldSeparator.TAB.getSeparator()).isEqualTo("\t");
    }

    @Test
    void requestImportTextFieldDelimiter_hasDelimiterValues() {
        for (RequestImportTextFieldDelimiter delimiter : RequestImportTextFieldDelimiter.values()) {
            assertThat(delimiter.getDelimiter()).isNotNull();
        }
    }

    @Test
    void requestImportErrorCode_hasCodes() {
        for (RequestImportErrorCode errorCode : RequestImportErrorCode.values()) {
            assertThat(errorCode.getErrorCode()).isNotNull().isNotEmpty();
            assertThat(errorCode.getMessage()).isNotNull().isNotEmpty();
        }
    }

    @Test
    void constants_haveExpectedStaticValues() {
        assertThat(BCConstants.POSITION_CODE_INACTIVE).isEqualTo("I");
        assertThat(BCConstants.BUDGET_CONSTRUCTION_DOCUMENT_DESCRIPTION).isNotNull();
    }
}
