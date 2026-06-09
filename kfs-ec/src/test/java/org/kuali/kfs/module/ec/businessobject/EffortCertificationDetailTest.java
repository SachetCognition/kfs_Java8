package org.kuali.kfs.module.ec.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EffortCertificationDetailTest extends KfsUnitTestBase {

    private EffortCertificationDetail detail;

    @BeforeEach
    void setUp() {
        detail = new EffortCertificationDetail();
    }

    @Nested
    @DisplayName("Default constructor")
    class DefaultConstructor {

        @Test
        void shouldInitializePayrollAmountsToZero() {
            assertThat(detail.getEffortCertificationPayrollAmount()).isEqualTo(KualiDecimal.ZERO);
            assertThat(detail.getEffortCertificationOriginalPayrollAmount()).isEqualTo(KualiDecimal.ZERO);
        }

        @Test
        void shouldInitializePercentsToZero() {
            assertThat(detail.getEffortCertificationCalculatedOverallPercent()).isEqualTo(0);
            assertThat(detail.getEffortCertificationUpdatedOverallPercent()).isEqualTo(0);
        }

        @Test
        void shouldSetOriginalFringeBenefitViaConstructor() {
            // originalFringeBenefitAmount is initialized to ZERO in the constructor
            // Note: getOriginalFringeBenefitAmount() triggers recalculation when zero,
            // requiring Spring context, so we test the setter/getter with a non-zero value
            detail.setOriginalFringeBenefitAmount(new KualiDecimal(100));
            assertThat(detail.getOriginalFringeBenefitAmount()).isEqualTo(new KualiDecimal(100));
        }

        @Test
        void shouldInitializeEffectiveDateToEmpty() {
            assertThat(detail.getEffectiveDate()).isEmpty();
        }

        @Test
        void shouldInitializeOverrideCodeToNone() {
            assertThat(detail.getOverrideCode()).isEqualTo("NONE");
        }
    }

    @Nested
    @DisplayName("Copy constructor")
    class CopyConstructor {

        @Test
        void shouldCopyAllFieldsFromSource() throws Exception {
            EffortCertificationDetail source = new EffortCertificationDetail();
            source.setChartOfAccountsCode("BL");
            // setAccountNumber() triggers SpringContext.getBean(), so use reflection
            setFieldValue(source, "accountNumber", "1234567");
            source.setSubAccountNumber("SUB1");
            source.setPositionNumber("POS001");
            source.setFinancialObjectCode("2400");
            source.setSourceChartOfAccountsCode("UA");
            source.setSourceAccountNumber("7654321");
            source.setEffortCertificationPayrollAmount(new KualiDecimal(5000));
            source.setEffortCertificationOriginalPayrollAmount(new KualiDecimal(4500));
            source.setEffortCertificationCalculatedOverallPercent(50);
            source.setEffortCertificationUpdatedOverallPercent(55);
            source.setUniversityFiscalYear(2024);
            source.setCostShareSourceSubAccountNumber("CS1");
            source.setOriginalFringeBenefitAmount(new KualiDecimal(200));
            source.setEffectiveDate("2024-01-01");

            EffortCertificationDetail copy = new EffortCertificationDetail(source);

            assertThat(copy.getChartOfAccountsCode()).isEqualTo("BL");
            assertThat(copy.getAccountNumber()).isEqualTo("1234567");
            assertThat(copy.getSubAccountNumber()).isEqualTo("SUB1");
            assertThat(copy.getPositionNumber()).isEqualTo("POS001");
            assertThat(copy.getFinancialObjectCode()).isEqualTo("2400");
            assertThat(copy.getSourceChartOfAccountsCode()).isEqualTo("UA");
            assertThat(copy.getSourceAccountNumber()).isEqualTo("7654321");
            assertThat(copy.getEffortCertificationPayrollAmount()).isEqualTo(new KualiDecimal(5000));
            assertThat(copy.getEffortCertificationOriginalPayrollAmount()).isEqualTo(new KualiDecimal(4500));
            assertThat(copy.getEffortCertificationCalculatedOverallPercent()).isEqualTo(50);
            assertThat(copy.getEffortCertificationUpdatedOverallPercent()).isEqualTo(55);
            assertThat(copy.getUniversityFiscalYear()).isEqualTo(2024);
            assertThat(copy.getCostShareSourceSubAccountNumber()).isEqualTo("CS1");
            assertThat(copy.getEffectiveDate()).isEqualTo("2024-01-01");
        }

        @Test
        void shouldHandleNullSource() {
            EffortCertificationDetail copy = new EffortCertificationDetail(null);
            assertThat(copy).isNotNull();
        }
    }

    @Nested
    @DisplayName("Getter/Setter coverage")
    class GetterSetter {

        @Test
        void shouldSetAndGetDocumentNumber() {
            detail.setDocumentNumber("DOC001");
            assertThat(detail.getDocumentNumber()).isEqualTo("DOC001");
        }

        @Test
        void shouldSetAndGetChartOfAccountsCode() {
            detail.setChartOfAccountsCode("BL");
            assertThat(detail.getChartOfAccountsCode()).isEqualTo("BL");
        }

        @Test
        void shouldSetAndGetSubAccountNumber() {
            detail.setSubAccountNumber("SUBACCT");
            assertThat(detail.getSubAccountNumber()).isEqualTo("SUBACCT");
        }

        @Test
        void shouldSetAndGetPositionNumber() {
            detail.setPositionNumber("P0012345");
            assertThat(detail.getPositionNumber()).isEqualTo("P0012345");
        }

        @Test
        void shouldSetAndGetFinancialObjectCode() {
            detail.setFinancialObjectCode("4000");
            assertThat(detail.getFinancialObjectCode()).isEqualTo("4000");
        }

        @Test
        void shouldSetAndGetEffectiveDate() {
            detail.setEffectiveDate("2024-06-01");
            assertThat(detail.getEffectiveDate()).isEqualTo("2024-06-01");
        }

        @Test
        void shouldSetAndGetSourceFields() {
            detail.setSourceChartOfAccountsCode("UA");
            detail.setSourceAccountNumber("ACC999");
            assertThat(detail.getSourceChartOfAccountsCode()).isEqualTo("UA");
            assertThat(detail.getSourceAccountNumber()).isEqualTo("ACC999");
        }

        @Test
        void shouldSetAndGetCostShareSourceSubAccountNumber() {
            detail.setCostShareSourceSubAccountNumber("CSSUB");
            assertThat(detail.getCostShareSourceSubAccountNumber()).isEqualTo("CSSUB");
        }

        @Test
        void shouldSetAndGetUniversityFiscalYear() {
            detail.setUniversityFiscalYear(2024);
            assertThat(detail.getUniversityFiscalYear()).isEqualTo(2024);
        }

        @Test
        void shouldSetAndGetOverrideFlags() {
            detail.setAccountExpiredOverride(true);
            detail.setAccountExpiredOverrideNeeded(true);
            assertThat(detail.isAccountExpiredOverride()).isTrue();
            assertThat(detail.isAccountExpiredOverrideNeeded()).isTrue();
        }

        @Test
        void shouldSetAndGetNewLineIndicator() {
            detail.setNewLineIndicator(true);
            assertThat(detail.isNewLineIndicator()).isTrue();
        }

        @Test
        void shouldSetAndGetPersistedPayrollAmount() {
            detail.setPersistedPayrollAmount(new KualiDecimal(1000));
            assertThat(detail.getPersistedPayrollAmount()).isEqualTo(new KualiDecimal(1000));
        }

        @Test
        void shouldSetAndGetPersistedEffortPercent() {
            detail.setPersistedEffortPercent(75);
            assertThat(detail.getPersistedEffortPercent()).isEqualTo(75);
        }

        @Test
        void shouldSetAndGetGroupId() {
            detail.setGroupId("GRP1");
            assertThat(detail.getGroupId()).isEqualTo("GRP1");
        }

        @Test
        void shouldSetAndGetOverrideCode() {
            detail.setOverrideCode("EXPIRED_ACCOUNT");
            assertThat(detail.getOverrideCode()).isEqualTo("EXPIRED_ACCOUNT");
        }
    }

    @Nested
    @DisplayName("Static aggregation methods")
    class StaticAggregation {

        private List<EffortCertificationDetail> detailLines;

        @BeforeEach
        void setUp() {
            EffortCertificationDetail line1 = new EffortCertificationDetail();
            line1.setEffortCertificationPayrollAmount(new KualiDecimal(1000));
            line1.setEffortCertificationOriginalPayrollAmount(new KualiDecimal(900));
            line1.setEffortCertificationUpdatedOverallPercent(40);
            line1.setEffortCertificationCalculatedOverallPercent(35);
            line1.setPersistedPayrollAmount(new KualiDecimal(950));
            line1.setPersistedEffortPercent(38);
            line1.setOriginalFringeBenefitAmount(new KualiDecimal(100));

            EffortCertificationDetail line2 = new EffortCertificationDetail();
            line2.setEffortCertificationPayrollAmount(new KualiDecimal(2000));
            line2.setEffortCertificationOriginalPayrollAmount(new KualiDecimal(1800));
            line2.setEffortCertificationUpdatedOverallPercent(60);
            line2.setEffortCertificationCalculatedOverallPercent(65);
            line2.setPersistedPayrollAmount(new KualiDecimal(1900));
            line2.setPersistedEffortPercent(62);
            line2.setOriginalFringeBenefitAmount(new KualiDecimal(200));

            detailLines = Arrays.asList(line1, line2);
        }

        @Test
        void shouldCalculateTotalEffortPercent() {
            assertThat(EffortCertificationDetail.getTotalEffortPercent(detailLines)).isEqualTo(100);
        }

        @Test
        void shouldCalculateTotalPersistedEffortPercent() {
            assertThat(EffortCertificationDetail.getTotalPersistedEffortPercent(detailLines)).isEqualTo(100);
        }

        @Test
        void shouldCalculateTotalOriginalEffortPercent() {
            assertThat(EffortCertificationDetail.getTotalOriginalEffortPercent(detailLines)).isEqualTo(100);
        }

        @Test
        void shouldCalculateTotalPayrollAmount() {
            assertThat(EffortCertificationDetail.getTotalPayrollAmount(detailLines))
                    .isEqualTo(new KualiDecimal(3000));
        }

        @Test
        void shouldCalculateTotalPersistedPayrollAmount() {
            assertThat(EffortCertificationDetail.getTotalPersistedPayrollAmount(detailLines))
                    .isEqualTo(new KualiDecimal(2850));
        }

        @Test
        void shouldCalculateTotalOriginalPayrollAmount() {
            assertThat(EffortCertificationDetail.getTotalOriginalPayrollAmount(detailLines))
                    .isEqualTo(new KualiDecimal(2700));
        }

        @Test
        void shouldCalculateTotalOriginalFringeBenefit() {
            assertThat(EffortCertificationDetail.getTotalOriginalFringeBenefit(detailLines))
                    .isEqualTo(new KualiDecimal(300));
        }

        @Test
        void shouldReturnZeroForEmptyList() {
            List<EffortCertificationDetail> empty = new ArrayList<>();
            assertThat(EffortCertificationDetail.getTotalPayrollAmount(empty)).isEqualTo(KualiDecimal.ZERO);
            assertThat(EffortCertificationDetail.getTotalEffortPercent(empty)).isEqualTo(0);
            assertThat(EffortCertificationDetail.getTotalOriginalPayrollAmount(empty)).isEqualTo(KualiDecimal.ZERO);
        }
    }

    @Nested
    @DisplayName("Reference object accessors")
    class ReferenceObjects {

        @Test
        void shouldSetAndGetFinancialObject() {
            detail.setFinancialObject(null);
            assertThat(detail.getFinancialObject()).isNull();
        }

        @Test
        void shouldSetAndGetChartOfAccounts() {
            detail.setChartOfAccounts(null);
            assertThat(detail.getChartOfAccounts()).isNull();
        }

        @Test
        void shouldSetAndGetAccount() {
            detail.setAccount(null);
            assertThat(detail.getAccount()).isNull();
        }

        @Test
        void shouldSetAndGetSubAccount() {
            detail.setSubAccount(null);
            assertThat(detail.getSubAccount()).isNull();
        }

        @Test
        void shouldSetAndGetSourceChartOfAccounts() {
            detail.setSourceChartOfAccounts(null);
            assertThat(detail.getSourceChartOfAccounts()).isNull();
        }

        @Test
        void shouldSetAndGetSourceAccount() {
            detail.setSourceAccount(null);
            assertThat(detail.getSourceAccount()).isNull();
        }

        @Test
        void shouldSetAndGetOptions() {
            detail.setOptions(null);
            assertThat(detail.getOptions()).isNull();
        }
    }

    private static void setFieldValue(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}
