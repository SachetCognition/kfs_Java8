package org.kuali.kfs.module.ec.document.validation.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.module.ec.businessobject.EffortCertificationDetail;
import org.kuali.kfs.module.ec.document.EffortCertificationDocument;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EffortCertificationDocumentRuleUtilTest extends KfsUnitTestBase {

    @Nested
    @DisplayName("isValidPercent")
    class IsValidPercent {

        @Test
        void shouldReturnTrueForZero() {
            assertThat(EffortCertificationDocumentRuleUtil.isValidPercent(0)).isTrue();
        }

        @Test
        void shouldReturnTrueForHundred() {
            assertThat(EffortCertificationDocumentRuleUtil.isValidPercent(100)).isTrue();
        }

        @Test
        void shouldReturnTrueForFifty() {
            assertThat(EffortCertificationDocumentRuleUtil.isValidPercent(50)).isTrue();
        }

        @Test
        void shouldReturnFalseForNegative() {
            assertThat(EffortCertificationDocumentRuleUtil.isValidPercent(-1)).isFalse();
        }

        @Test
        void shouldReturnFalseForOver100() {
            assertThat(EffortCertificationDocumentRuleUtil.isValidPercent(101)).isFalse();
        }
    }

    @Nested
    @DisplayName("isPayrollAmountNonnegative")
    class IsPayrollAmountNonnegative {

        @Test
        void shouldReturnTrueForZero() {
            assertThat(EffortCertificationDocumentRuleUtil.isPayrollAmountNonnegative(KualiDecimal.ZERO)).isTrue();
        }

        @Test
        void shouldReturnTrueForPositive() {
            assertThat(EffortCertificationDocumentRuleUtil.isPayrollAmountNonnegative(new KualiDecimal(100))).isTrue();
        }

        @Test
        void shouldReturnFalseForNegative() {
            assertThat(EffortCertificationDocumentRuleUtil.isPayrollAmountNonnegative(new KualiDecimal(-1))).isFalse();
        }
    }

    @Nested
    @DisplayName("isOriginalEffortPercentSameAsCurrentEffortPercent")
    class IsOriginalSameAsCurrent {

        @Test
        void shouldReturnTrueWhenSame() {
            assertThat(EffortCertificationDocumentRuleUtil
                    .isOriginalEffortPercentSameAsCurrentEffortPercent(50, 50)).isTrue();
        }

        @Test
        void shouldReturnFalseWhenDifferent() {
            assertThat(EffortCertificationDocumentRuleUtil
                    .isOriginalEffortPercentSameAsCurrentEffortPercent(50, 60)).isFalse();
        }
    }

    @Nested
    @DisplayName("hasNonnegativePayrollAmount")
    class HasNonnegativePayrollAmount {

        @Test
        void shouldReturnTrueForPositiveAmount() {
            EffortCertificationDetail detail = new EffortCertificationDetail();
            detail.setEffortCertificationPayrollAmount(new KualiDecimal(500));
            assertThat(EffortCertificationDocumentRuleUtil.hasNonnegativePayrollAmount(detail)).isTrue();
        }

        @Test
        void shouldReturnTrueForZeroAmount() {
            EffortCertificationDetail detail = new EffortCertificationDetail();
            detail.setEffortCertificationPayrollAmount(KualiDecimal.ZERO);
            assertThat(EffortCertificationDocumentRuleUtil.hasNonnegativePayrollAmount(detail)).isTrue();
        }

        @Test
        void shouldReturnFalseForNegativeAmount() {
            EffortCertificationDetail detail = new EffortCertificationDetail();
            detail.setEffortCertificationPayrollAmount(new KualiDecimal(-100));
            assertThat(EffortCertificationDocumentRuleUtil.hasNonnegativePayrollAmount(detail)).isFalse();
        }

        @Test
        void shouldReturnFalseForNullAmount() {
            EffortCertificationDetail detail = new EffortCertificationDetail();
            detail.setEffortCertificationPayrollAmount(null);
            assertThat(EffortCertificationDocumentRuleUtil.hasNonnegativePayrollAmount(detail)).isFalse();
        }
    }

    @Nested
    @DisplayName("hasValidEffortPercent")
    class HasValidEffortPercent {

        @Test
        void shouldReturnTrueForValidPercent() {
            EffortCertificationDetail detail = new EffortCertificationDetail();
            detail.setEffortCertificationUpdatedOverallPercent(50);
            assertThat(EffortCertificationDocumentRuleUtil.hasValidEffortPercent(detail)).isTrue();
        }

        @Test
        void shouldReturnFalseForInvalidPercent() {
            EffortCertificationDetail detail = new EffortCertificationDetail();
            detail.setEffortCertificationUpdatedOverallPercent(150);
            assertThat(EffortCertificationDocumentRuleUtil.hasValidEffortPercent(detail)).isFalse();
        }

        @Test
        void shouldReturnFalseForNullPercent() {
            EffortCertificationDetail detail = new EffortCertificationDetail();
            detail.setEffortCertificationUpdatedOverallPercent(null);
            assertThat(EffortCertificationDocumentRuleUtil.hasValidEffortPercent(detail)).isFalse();
        }
    }

    @Nested
    @DisplayName("isPayrollAmountChangedFromOriginal (detail)")
    class PayrollAmountChangedFromOriginalDetail {

        @Test
        void shouldReturnTrueWhenAmountsAreDifferent() {
            EffortCertificationDetail detail = new EffortCertificationDetail();
            detail.setEffortCertificationPayrollAmount(new KualiDecimal(5000));
            detail.setEffortCertificationOriginalPayrollAmount(new KualiDecimal(4000));
            assertThat(EffortCertificationDocumentRuleUtil.isPayrollAmountChangedFromOriginal(detail)).isTrue();
        }

        @Test
        void shouldReturnFalseWhenAmountsAreEqual() {
            EffortCertificationDetail detail = new EffortCertificationDetail();
            detail.setEffortCertificationPayrollAmount(new KualiDecimal(5000));
            detail.setEffortCertificationOriginalPayrollAmount(new KualiDecimal(5000));
            assertThat(EffortCertificationDocumentRuleUtil.isPayrollAmountChangedFromOriginal(detail)).isFalse();
        }
    }

    @Nested
    @DisplayName("isPayrollAmountChangedFromOriginal (document)")
    class PayrollAmountChangedFromOriginalDocument {

        private EffortCertificationDocument document;

        @BeforeEach
        void setUp() {
            document = mock(EffortCertificationDocument.class, CALLS_REAL_METHODS);
        }

        @Test
        void shouldReturnTrueWhenAnyLineChanged() {
            EffortCertificationDetail line = new EffortCertificationDetail();
            line.setEffortCertificationPayrollAmount(new KualiDecimal(5000));
            line.setEffortCertificationOriginalPayrollAmount(new KualiDecimal(4000));

            List<EffortCertificationDetail> lines = new ArrayList<>();
            lines.add(line);
            document.setEffortCertificationDetailLines(lines);

            assertThat(EffortCertificationDocumentRuleUtil.isPayrollAmountChangedFromOriginal(document)).isTrue();
        }

        @Test
        void shouldReturnFalseWhenNoLinesChanged() {
            EffortCertificationDetail line = new EffortCertificationDetail();
            line.setEffortCertificationPayrollAmount(new KualiDecimal(5000));
            line.setEffortCertificationOriginalPayrollAmount(new KualiDecimal(5000));

            List<EffortCertificationDetail> lines = new ArrayList<>();
            lines.add(line);
            document.setEffortCertificationDetailLines(lines);

            assertThat(EffortCertificationDocumentRuleUtil.isPayrollAmountChangedFromOriginal(document)).isFalse();
        }
    }

    @Nested
    @DisplayName("isPayrollAmountChangedFromPersisted (detail)")
    class PayrollAmountChangedFromPersistedDetail {

        @Test
        void shouldReturnTrueWhenPersistedDiffers() {
            EffortCertificationDetail detail = new EffortCertificationDetail();
            detail.setEffortCertificationPayrollAmount(new KualiDecimal(5000));
            detail.setPersistedPayrollAmount(new KualiDecimal(4000));
            assertThat(EffortCertificationDocumentRuleUtil.isPayrollAmountChangedFromPersisted(detail)).isTrue();
        }

        @Test
        void shouldReturnFalseWhenPersistedSame() {
            EffortCertificationDetail detail = new EffortCertificationDetail();
            detail.setEffortCertificationPayrollAmount(new KualiDecimal(5000));
            detail.setPersistedPayrollAmount(new KualiDecimal(5000));
            assertThat(EffortCertificationDocumentRuleUtil.isPayrollAmountChangedFromPersisted(detail)).isFalse();
        }

        @Test
        void shouldReturnFalseWhenPersistedNull() {
            EffortCertificationDetail detail = new EffortCertificationDetail();
            detail.setEffortCertificationPayrollAmount(new KualiDecimal(5000));
            detail.setPersistedPayrollAmount(null);
            assertThat(EffortCertificationDocumentRuleUtil.isPayrollAmountChangedFromPersisted(detail)).isFalse();
        }
    }

    @Nested
    @DisplayName("isTotalEffortPercentageAs100")
    class TotalEffortPercentageAs100 {

        private EffortCertificationDocument document;

        @BeforeEach
        void setUp() {
            document = mock(EffortCertificationDocument.class, CALLS_REAL_METHODS);
        }

        @Test
        void shouldReturnTrueWhenTotalIs100() {
            EffortCertificationDetail line1 = new EffortCertificationDetail();
            line1.setEffortCertificationUpdatedOverallPercent(60);
            EffortCertificationDetail line2 = new EffortCertificationDetail();
            line2.setEffortCertificationUpdatedOverallPercent(40);

            List<EffortCertificationDetail> lines = new ArrayList<>();
            lines.add(line1);
            lines.add(line2);
            document.setEffortCertificationDetailLines(lines);

            assertThat(EffortCertificationDocumentRuleUtil.isTotalEffortPercentageAs100(document)).isTrue();
        }

        @Test
        void shouldReturnFalseWhenTotalIsNot100() {
            EffortCertificationDetail line1 = new EffortCertificationDetail();
            line1.setEffortCertificationUpdatedOverallPercent(50);

            List<EffortCertificationDetail> lines = new ArrayList<>();
            lines.add(line1);
            document.setEffortCertificationDetailLines(lines);

            assertThat(EffortCertificationDocumentRuleUtil.isTotalEffortPercentageAs100(document)).isFalse();
        }
    }

    @Nested
    @DisplayName("isTotalPayrollAmountOverChanged")
    class TotalPayrollAmountOverChanged {

        private EffortCertificationDocument document;

        @BeforeEach
        void setUp() {
            document = mock(EffortCertificationDocument.class, CALLS_REAL_METHODS);
        }

        @Test
        void shouldReturnTrueWhenChangeExceedsLimit() {
            EffortCertificationDetail line = new EffortCertificationDetail();
            line.setEffortCertificationPayrollAmount(new KualiDecimal(10000));
            line.setEffortCertificationOriginalPayrollAmount(new KualiDecimal(5000));
            line.setEffortCertificationUpdatedOverallPercent(100);
            line.setEffortCertificationCalculatedOverallPercent(100);
            line.setOriginalFringeBenefitAmount(new KualiDecimal(100));

            List<EffortCertificationDetail> lines = new ArrayList<>();
            lines.add(line);
            document.setEffortCertificationDetailLines(lines);

            assertThat(EffortCertificationDocumentRuleUtil
                    .isTotalPayrollAmountOverChanged(document, 100.0)).isTrue();
        }

        @Test
        void shouldReturnFalseWhenChangeWithinLimit() {
            EffortCertificationDetail line = new EffortCertificationDetail();
            line.setEffortCertificationPayrollAmount(new KualiDecimal(5000));
            line.setEffortCertificationOriginalPayrollAmount(new KualiDecimal(5000));
            line.setEffortCertificationUpdatedOverallPercent(100);
            line.setEffortCertificationCalculatedOverallPercent(100);
            line.setOriginalFringeBenefitAmount(new KualiDecimal(100));

            List<EffortCertificationDetail> lines = new ArrayList<>();
            lines.add(line);
            document.setEffortCertificationDetailLines(lines);

            assertThat(EffortCertificationDocumentRuleUtil
                    .isTotalPayrollAmountOverChanged(document, 100.0)).isFalse();
        }
    }

    @Nested
    @DisplayName("hasDetailLine")
    class HasDetailLine {

        private EffortCertificationDocument document;

        @BeforeEach
        void setUp() {
            document = mock(EffortCertificationDocument.class, CALLS_REAL_METHODS);
        }

        @Test
        void shouldReturnTrueWhenDocumentHasLines() {
            List<EffortCertificationDetail> lines = new ArrayList<>();
            lines.add(new EffortCertificationDetail());
            document.setEffortCertificationDetailLines(lines);
            assertThat(EffortCertificationDocumentRuleUtil.hasDetailLine(document)).isTrue();
        }

        @Test
        void shouldReturnFalseWhenDocumentHasNoLines() {
            document.setEffortCertificationDetailLines(new ArrayList<>());
            assertThat(EffortCertificationDocumentRuleUtil.hasDetailLine(document)).isFalse();
        }

        @Test
        void shouldReturnFalseWhenLinesNull() {
            document.setEffortCertificationDetailLines(null);
            assertThat(EffortCertificationDocumentRuleUtil.hasDetailLine(document)).isFalse();
        }
    }

    @Nested
    @DisplayName("canExpiredAccountBeUsed")
    class CanExpiredAccountBeUsed {

        @Test
        void shouldReturnTrueWhenAccountNotExpired() {
            EffortCertificationDetail detail = new EffortCertificationDetail();
            Account account = new Account();
            account.setAccountExpirationDate(null);
            detail.setAccount(account);
            assertThat(EffortCertificationDocumentRuleUtil.canExpiredAccountBeUsed(detail)).isTrue();
        }

        @Test
        void shouldReturnTrueWhenAccountNull() {
            EffortCertificationDetail detail = new EffortCertificationDetail();
            detail.setAccount(null);
            assertThat(EffortCertificationDocumentRuleUtil.canExpiredAccountBeUsed(detail)).isTrue();
        }
    }

    @Nested
    @DisplayName("hasClosedAccount")
    class HasClosedAccount {

        @Test
        void shouldReturnTrueWhenAccountClosed() {
            EffortCertificationDetail detail = new EffortCertificationDetail();
            Account account = new Account();
            account.setActive(false);
            detail.setAccount(account);
            assertThat(EffortCertificationDocumentRuleUtil.hasClosedAccount(detail)).isTrue();
        }

        @Test
        void shouldReturnFalseWhenAccountActive() {
            EffortCertificationDetail detail = new EffortCertificationDetail();
            Account account = new Account();
            account.setActive(true);
            detail.setAccount(account);
            assertThat(EffortCertificationDocumentRuleUtil.hasClosedAccount(detail)).isFalse();
        }
    }

    @Nested
    @DisplayName("isPayrollAmountOverChanged (detail)")
    class PayrollAmountOverChanged {

        @Test
        void shouldReturnFalseWhenPercentsAreDifferent() {
            EffortCertificationDetail detail = new EffortCertificationDetail();
            detail.setEffortCertificationPayrollAmount(new KualiDecimal(10000));
            detail.setEffortCertificationOriginalPayrollAmount(new KualiDecimal(5000));
            detail.setEffortCertificationCalculatedOverallPercent(50);
            detail.setEffortCertificationUpdatedOverallPercent(60);
            assertThat(EffortCertificationDocumentRuleUtil
                    .isPayrollAmountOverChanged(detail, new KualiDecimal(10000), 0.1)).isFalse();
        }

        @Test
        void shouldReturnTrueWhenPercentsEqualAndChangeExceedsLimit() {
            EffortCertificationDetail detail = new EffortCertificationDetail();
            detail.setEffortCertificationPayrollAmount(new KualiDecimal(3000));
            detail.setEffortCertificationOriginalPayrollAmount(new KualiDecimal(5000));
            detail.setEffortCertificationCalculatedOverallPercent(50);
            detail.setEffortCertificationUpdatedOverallPercent(50);
            assertThat(EffortCertificationDocumentRuleUtil
                    .isPayrollAmountOverChanged(detail, new KualiDecimal(10000), 0.01)).isTrue();
        }

        @Test
        void shouldReturnFalseWhenPercentsEqualAndChangeWithinLimit() {
            EffortCertificationDetail detail = new EffortCertificationDetail();
            detail.setEffortCertificationPayrollAmount(new KualiDecimal(5000));
            detail.setEffortCertificationOriginalPayrollAmount(new KualiDecimal(5000));
            detail.setEffortCertificationCalculatedOverallPercent(50);
            detail.setEffortCertificationUpdatedOverallPercent(50);
            assertThat(EffortCertificationDocumentRuleUtil
                    .isPayrollAmountOverChanged(detail, new KualiDecimal(10000), 0.1)).isFalse();
        }
    }

    @Nested
    @DisplayName("applyDefaultValues")
    class ApplyDefaultValues {

        @Test
        void shouldNotOverwriteExistingValues() {
            EffortCertificationDetail detail = new EffortCertificationDetail();
            detail.setEffortCertificationPayrollAmount(new KualiDecimal(5000));
            detail.setEffortCertificationOriginalPayrollAmount(new KualiDecimal(4000));
            detail.setEffortCertificationCalculatedOverallPercent(75);
            detail.setEffortCertificationUpdatedOverallPercent(80);
            detail.setSubAccountNumber("SUB1");
            detail.setCostShareSourceSubAccountNumber("CSUB");
            detail.setSourceChartOfAccountsCode("BL");
            detail.setSourceAccountNumber("1234567");
            detail.setUniversityFiscalYear(2024);

            EffortCertificationDocumentRuleUtil.applyDefaultValues(detail);

            assertThat(detail.getEffortCertificationPayrollAmount()).isEqualTo(new KualiDecimal(5000));
            assertThat(detail.getEffortCertificationOriginalPayrollAmount()).isEqualTo(new KualiDecimal(4000));
            assertThat(detail.getEffortCertificationCalculatedOverallPercent()).isEqualTo(75);
            assertThat(detail.getEffortCertificationUpdatedOverallPercent()).isEqualTo(80);
        }
    }

    @Nested
    @DisplayName("isEffortPercentChangedFromPersisted (detail)")
    class EffortPercentChangedFromPersisted {

        @Test
        void shouldReturnTrueWhenPercentsDiffer() {
            EffortCertificationDetail detail = new EffortCertificationDetail();
            detail.setEffortCertificationUpdatedOverallPercent(60);
            detail.setPersistedEffortPercent(50);
            assertThat(EffortCertificationDocumentRuleUtil.isEffortPercentChangedFromPersisted(detail)).isTrue();
        }

        @Test
        void shouldReturnFalseWhenPercentsSame() {
            EffortCertificationDetail detail = new EffortCertificationDetail();
            detail.setEffortCertificationUpdatedOverallPercent(50);
            detail.setPersistedEffortPercent(50);
            assertThat(EffortCertificationDocumentRuleUtil.isEffortPercentChangedFromPersisted(detail)).isFalse();
        }
    }
}
