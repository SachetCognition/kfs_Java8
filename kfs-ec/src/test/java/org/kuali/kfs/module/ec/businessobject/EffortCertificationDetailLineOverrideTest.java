package org.kuali.kfs.module.ec.businessobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.businessobject.AccountingLineOverride;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class EffortCertificationDetailLineOverrideTest extends KfsUnitTestBase {

    @Test
    @DisplayName("REFRESH_FIELDS should contain account")
    void refreshFieldsShouldContainAccount() {
        assertThat(EffortCertificationDetailLineOverride.REFRESH_FIELDS)
                .contains(KFSPropertyConstants.ACCOUNT);
    }

    @Test
    @DisplayName("populateFromInput should set NONE when no override")
    void populateFromInputShouldSetNoneWhenNoOverride() {
        EffortCertificationDetail detail = new EffortCertificationDetail();
        detail.setAccountExpiredOverride(false);

        EffortCertificationDetailLineOverride.populateFromInput(detail);

        assertThat(detail.getOverrideCode()).isEqualTo(AccountingLineOverride.CODE.NONE);
    }

    @Test
    @DisplayName("populateFromInput should set expired account code when override true")
    void populateFromInputShouldSetExpiredAccountWhenTrue() {
        EffortCertificationDetail detail = new EffortCertificationDetail();
        detail.setAccountExpiredOverride(true);

        EffortCertificationDetailLineOverride.populateFromInput(detail);

        assertThat(detail.getOverrideCode()).isEqualTo(AccountingLineOverride.CODE.EXPIRED_ACCOUNT);
    }

    @Test
    @DisplayName("processForOutput should set override flags from code")
    void processForOutputShouldSetFlags() {
        EffortCertificationDetail detail = new EffortCertificationDetail();
        detail.setOverrideCode(AccountingLineOverride.CODE.EXPIRED_ACCOUNT);
        detail.setAccount(null);

        EffortCertificationDetailLineOverride.processForOutput(detail);

        assertThat(detail.isAccountExpiredOverride()).isTrue();
    }

    @Test
    @DisplayName("processForOutput with NONE code should clear override flag")
    void processForOutputWithNoneShouldClearFlags() {
        EffortCertificationDetail detail = new EffortCertificationDetail();
        detail.setOverrideCode(AccountingLineOverride.CODE.NONE);
        detail.setAccount(null);

        EffortCertificationDetailLineOverride.processForOutput(detail);

        assertThat(detail.isAccountExpiredOverride()).isFalse();
    }

    @Test
    @DisplayName("determineNeededOverrides should return NONE for null account")
    void determineNeededOverridesShouldReturnNoneForNull() {
        EffortCertificationDetail detail = new EffortCertificationDetail();
        detail.setAccount(null);

        AccountingLineOverride override = EffortCertificationDetailLineOverride.determineNeededOverrides(detail);
        assertThat(override.getCode()).isEqualTo(AccountingLineOverride.CODE.NONE);
    }
}
