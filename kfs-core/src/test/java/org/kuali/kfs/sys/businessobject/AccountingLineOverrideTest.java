package org.kuali.kfs.sys.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AccountingLineOverrideTest extends KfsUnitTestBase {

    // ──────────────────── isValidCode ───────────────────────────────

    @Test
    void isValidCode_NONE_returnsTrue() {
        assertThat(AccountingLineOverride.isValidCode(AccountingLineOverride.CODE.NONE)).isTrue();
    }

    @Test
    void isValidCode_BLANK_returnsTrue() {
        assertThat(AccountingLineOverride.isValidCode(AccountingLineOverride.CODE.BLANK)).isTrue();
    }

    @Test
    void isValidCode_EXPIRED_ACCOUNT_returnsTrue() {
        assertThat(AccountingLineOverride.isValidCode(AccountingLineOverride.CODE.EXPIRED_ACCOUNT)).isTrue();
    }

    @Test
    void isValidCode_invalid_returnsFalse() {
        assertThat(AccountingLineOverride.isValidCode("BOGUS_CODE")).isFalse();
    }

    @Test
    void isValidCode_null_returnsFalse() {
        assertThat(AccountingLineOverride.isValidCode(null)).isFalse();
    }

    // ──────────────────── valueOf(String) ───────────────────────────

    @Test
    void valueOf_NONE_returnsOverrideWithNoneCode() {
        AccountingLineOverride override = AccountingLineOverride.valueOf(AccountingLineOverride.CODE.NONE);
        assertThat(override.getCode()).isEqualTo(AccountingLineOverride.CODE.NONE);
    }

    @Test
    void valueOf_EXPIRED_ACCOUNT() {
        AccountingLineOverride override = AccountingLineOverride.valueOf(AccountingLineOverride.CODE.EXPIRED_ACCOUNT);
        assertThat(override.getCode()).isEqualTo(AccountingLineOverride.CODE.EXPIRED_ACCOUNT);
        assertThat(override.hasComponent(AccountingLineOverride.COMPONENT.EXPIRED_ACCOUNT)).isTrue();
        assertThat(override.hasComponent(AccountingLineOverride.COMPONENT.NON_BUDGETED_OBJECT)).isFalse();
    }

    @Test
    void valueOf_invalidCode_throws() {
        assertThatThrownBy(() -> AccountingLineOverride.valueOf("INVALID"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // ──────────────────── valueOf(Integer[]) ────────────────────────

    @Test
    void valueOf_components_EXPIRED_ACCOUNT() {
        AccountingLineOverride override = AccountingLineOverride.valueOf(
                new Integer[]{AccountingLineOverride.COMPONENT.EXPIRED_ACCOUNT});
        assertThat(override.getCode()).isEqualTo(AccountingLineOverride.CODE.EXPIRED_ACCOUNT);
    }

    @Test
    void valueOf_components_emptyArray_returnsNoneOrBlank() {
        AccountingLineOverride override = AccountingLineOverride.valueOf(new Integer[]{});
        assertThat(override).isNotNull();
    }

    @Test
    void valueOf_components_multipleValid() {
        AccountingLineOverride override = AccountingLineOverride.valueOf(
                new Integer[]{
                        AccountingLineOverride.COMPONENT.EXPIRED_ACCOUNT,
                        AccountingLineOverride.COMPONENT.NON_BUDGETED_OBJECT
                });
        assertThat(override.getCode()).isEqualTo(AccountingLineOverride.CODE.EXPIRED_ACCOUNT_AND_NON_BUDGETED_OBJECT);
    }

    // ──────────────────── hasComponent ──────────────────────────────

    @Test
    void hasComponent_present_returnsTrue() {
        AccountingLineOverride override = AccountingLineOverride.valueOf(AccountingLineOverride.CODE.EXPIRED_ACCOUNT);
        assertThat(override.hasComponent(AccountingLineOverride.COMPONENT.EXPIRED_ACCOUNT)).isTrue();
    }

    @Test
    void hasComponent_absent_returnsFalse() {
        AccountingLineOverride override = AccountingLineOverride.valueOf(AccountingLineOverride.CODE.EXPIRED_ACCOUNT);
        assertThat(override.hasComponent(AccountingLineOverride.COMPONENT.NON_BUDGETED_OBJECT)).isFalse();
    }

    @Test
    void hasComponent_NONE_allAbsent() {
        AccountingLineOverride override = AccountingLineOverride.valueOf(AccountingLineOverride.CODE.NONE);
        assertThat(override.hasComponent(AccountingLineOverride.COMPONENT.EXPIRED_ACCOUNT)).isFalse();
        assertThat(override.hasComponent(AccountingLineOverride.COMPONENT.NON_BUDGETED_OBJECT)).isFalse();
        assertThat(override.hasComponent(AccountingLineOverride.COMPONENT.TRANSACTION_EXCEEDS_REMAINING_BUDGET)).isFalse();
    }

    // ──────────────────── isValidComponentSet ───────────────────────

    @Test
    void isValidComponentSet_validSingle() {
        assertThat(AccountingLineOverride.isValidComponentSet(
                new Integer[]{AccountingLineOverride.COMPONENT.EXPIRED_ACCOUNT})).isTrue();
    }

    @Test
    void isValidComponentSet_validCombo() {
        assertThat(AccountingLineOverride.isValidComponentSet(
                new Integer[]{AccountingLineOverride.COMPONENT.EXPIRED_ACCOUNT, AccountingLineOverride.COMPONENT.NON_BUDGETED_OBJECT})).isTrue();
    }

    @Test
    void isValidComponentSet_emptyArray() {
        assertThat(AccountingLineOverride.isValidComponentSet(new Integer[]{})).isTrue();
    }

    // ──────────────────── mask ──────────────────────────────────────

    @Test
    void mask_retainsCommonComponents() {
        AccountingLineOverride full = AccountingLineOverride.valueOf(AccountingLineOverride.CODE.EXPIRED_ACCOUNT_AND_NON_BUDGETED_OBJECT);
        AccountingLineOverride maskOverride = AccountingLineOverride.valueOf(AccountingLineOverride.CODE.EXPIRED_ACCOUNT);

        AccountingLineOverride result = full.mask(maskOverride);
        assertThat(result.hasComponent(AccountingLineOverride.COMPONENT.EXPIRED_ACCOUNT)).isTrue();
        assertThat(result.hasComponent(AccountingLineOverride.COMPONENT.NON_BUDGETED_OBJECT)).isFalse();
    }

    @Test
    void isValidMask_validCombination_returnsTrue() {
        AccountingLineOverride full = AccountingLineOverride.valueOf(AccountingLineOverride.CODE.EXPIRED_ACCOUNT_AND_NON_BUDGETED_OBJECT);
        AccountingLineOverride maskOverride = AccountingLineOverride.valueOf(AccountingLineOverride.CODE.EXPIRED_ACCOUNT);
        assertThat(full.isValidMask(maskOverride)).isTrue();
    }

    // ──────────────────── toString ──────────────────────────────────

    @Test
    void toString_containsCode() {
        AccountingLineOverride override = AccountingLineOverride.valueOf(AccountingLineOverride.CODE.NONE);
        assertThat(override.toString()).contains("NONE");
    }

    // ──────────────────── all defined codes ─────────────────────────

    @Test
    void allCodes_areValid() {
        String[] codes = {
                AccountingLineOverride.CODE.BLANK,
                AccountingLineOverride.CODE.NONE,
                AccountingLineOverride.CODE.EXPIRED_ACCOUNT,
                AccountingLineOverride.CODE.NON_BUDGETED_OBJECT,
                AccountingLineOverride.CODE.TRANSACTION_EXCEEDS_REMAINING_BUDGET,
                AccountingLineOverride.CODE.EXPIRED_ACCOUNT_AND_NON_BUDGETED_OBJECT,
                AccountingLineOverride.CODE.NON_BUDGETED_OBJECT_AND_TRANSACTION_EXCEEDS_REMAINING_BUDGET,
                AccountingLineOverride.CODE.EXPIRED_ACCOUNT_AND_TRANSACTION_EXCEEDS_REMAINING_BUDGET,
                AccountingLineOverride.CODE.EXPIRED_ACCOUNT_AND_NON_BUDGETED_OBJECT_AND_TRANSACTION_EXCEEDS_REMAINING_BUDGET,
                AccountingLineOverride.CODE.NON_FRINGE_ACCOUNT_USED,
                AccountingLineOverride.CODE.EXPIRED_ACCOUNT_AND_NON_FRINGE_ACCOUNT_USED
        };
        for (String code : codes) {
            assertThat(AccountingLineOverride.isValidCode(code))
                    .as("Code '%s' should be valid", code)
                    .isTrue();
        }
    }
}
