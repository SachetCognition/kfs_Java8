package org.kuali.kfs.module.ec.document;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ec.businessobject.EffortCertificationDetail;
import org.kuali.kfs.module.ec.businessobject.EffortCertificationReportDefinition;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;

class EffortCertificationDocumentTest extends KfsUnitTestBase {

    private EffortCertificationDocument document;

    @BeforeEach
    void setUp() {
        // Use mock with CALLS_REAL_METHODS to bypass the parent constructor
        // that depends on Rice ResourceLoader
        document = mock(EffortCertificationDocument.class, CALLS_REAL_METHODS);
    }

    @Nested
    @DisplayName("Getter/Setter tests")
    class GetterSetterTests {

        @Test
        void shouldSetAndGetEffortCertificationReportNumber() {
            document.setEffortCertificationReportNumber("A01");
            assertThat(document.getEffortCertificationReportNumber()).isEqualTo("A01");
        }

        @Test
        void shouldSetAndGetEffortCertificationDocumentCode() {
            document.setEffortCertificationDocumentCode(true);
            assertThat(document.getEffortCertificationDocumentCode()).isTrue();
        }

        @Test
        void shouldSetAndGetUniversityFiscalYear() {
            document.setUniversityFiscalYear(2024);
            assertThat(document.getUniversityFiscalYear()).isEqualTo(2024);
        }

        @Test
        void shouldSetAndGetOrganizationCode() {
            document.setOrganizationCode("ORG1");
            assertThat(document.getOrganizationCode()).isEqualTo("ORG1");
        }

        @Test
        void shouldSetAndGetEmplid() {
            document.setEmplid("EMP001");
            assertThat(document.getEmplid()).isEqualTo("EMP001");
        }

        @Test
        void shouldSetAndGetEffortCertificationDetailLines() {
            List<EffortCertificationDetail> lines = new ArrayList<>();
            document.setEffortCertificationDetailLines(lines);
            assertThat(document.getEffortCertificationDetailLines()).isSameAs(lines);
        }

        @Test
        void shouldSetAndGetReportDefinition() {
            EffortCertificationReportDefinition def = new EffortCertificationReportDefinition();
            document.setEffortCertificationReportDefinition(def);
            assertThat(document.getEffortCertificationReportDefinition()).isSameAs(def);
        }
    }

    @Nested
    @DisplayName("Total calculations")
    class TotalCalculations {

        @BeforeEach
        void setUpLines() {
            EffortCertificationDetail line1 = new EffortCertificationDetail();
            line1.setEffortCertificationPayrollAmount(new KualiDecimal(3000));
            line1.setEffortCertificationOriginalPayrollAmount(new KualiDecimal(2800));
            line1.setEffortCertificationUpdatedOverallPercent(60);
            line1.setEffortCertificationCalculatedOverallPercent(55);
            line1.setOriginalFringeBenefitAmount(new KualiDecimal(300));

            EffortCertificationDetail line2 = new EffortCertificationDetail();
            line2.setEffortCertificationPayrollAmount(new KualiDecimal(2000));
            line2.setEffortCertificationOriginalPayrollAmount(new KualiDecimal(1800));
            line2.setEffortCertificationUpdatedOverallPercent(40);
            line2.setEffortCertificationCalculatedOverallPercent(45);
            line2.setOriginalFringeBenefitAmount(new KualiDecimal(200));

            List<EffortCertificationDetail> lines = new ArrayList<>();
            lines.add(line1);
            lines.add(line2);
            document.setEffortCertificationDetailLines(lines);
        }

        @Test
        void shouldCalculateTotalEffortPercent() {
            assertThat(document.getTotalEffortPercent()).isEqualTo(100);
        }

        @Test
        void shouldCalculateTotalOriginalEffortPercent() {
            assertThat(document.getTotalOriginalEffortPercent()).isEqualTo(100);
        }

        @Test
        void shouldCalculateTotalPayrollAmount() {
            assertThat(document.getTotalPayrollAmount()).isEqualTo(new KualiDecimal(5000));
        }

        @Test
        void shouldCalculateTotalOriginalPayrollAmount() {
            assertThat(document.getTotalOriginalPayrollAmount()).isEqualTo(new KualiDecimal(4600));
        }
    }

    @Nested
    @DisplayName("getDocumentTotalAmount static method")
    class DocumentTotalAmount {

        @Test
        void shouldReturnTotalPayrollAmount() {
            EffortCertificationDetail line1 = new EffortCertificationDetail();
            line1.setEffortCertificationPayrollAmount(new KualiDecimal(1500));
            EffortCertificationDetail line2 = new EffortCertificationDetail();
            line2.setEffortCertificationPayrollAmount(new KualiDecimal(2500));

            List<EffortCertificationDetail> lines = new ArrayList<>();
            lines.add(line1);
            lines.add(line2);
            document.setEffortCertificationDetailLines(lines);

            assertThat(EffortCertificationDocument.getDocumentTotalAmount(document))
                    .isEqualTo(new KualiDecimal(4000));
        }

        @Test
        void shouldReturnZeroForEmptyLines() {
            document.setEffortCertificationDetailLines(new ArrayList<>());
            assertThat(EffortCertificationDocument.getDocumentTotalAmount(document))
                    .isEqualTo(KualiDecimal.ZERO);
        }
    }

    @Nested
    @DisplayName("getEffortCertificationDetailWithMaxPayrollAmount")
    class MaxPayrollAmountTests {

        @Test
        void shouldReturnLineWithMaxAmount() {
            EffortCertificationDetail line1 = new EffortCertificationDetail();
            line1.setEffortCertificationPayrollAmount(new KualiDecimal(1000));
            EffortCertificationDetail line2 = new EffortCertificationDetail();
            line2.setEffortCertificationPayrollAmount(new KualiDecimal(3000));
            EffortCertificationDetail line3 = new EffortCertificationDetail();
            line3.setEffortCertificationPayrollAmount(new KualiDecimal(2000));

            List<EffortCertificationDetail> lines = new ArrayList<>();
            lines.add(line1);
            lines.add(line2);
            lines.add(line3);
            document.setEffortCertificationDetailLines(lines);

            List<EffortCertificationDetail> result = document.getEffortCertificationDetailWithMaxPayrollAmount();
            assertThat(result).hasSize(1);
            assertThat(result.get(0).getEffortCertificationPayrollAmount()).isEqualTo(new KualiDecimal(3000));
        }

        @Test
        void shouldReturnMultipleLinesWhenTied() {
            EffortCertificationDetail line1 = new EffortCertificationDetail();
            line1.setEffortCertificationPayrollAmount(new KualiDecimal(5000));
            EffortCertificationDetail line2 = new EffortCertificationDetail();
            line2.setEffortCertificationPayrollAmount(new KualiDecimal(5000));
            EffortCertificationDetail line3 = new EffortCertificationDetail();
            line3.setEffortCertificationPayrollAmount(new KualiDecimal(2000));

            List<EffortCertificationDetail> lines = new ArrayList<>();
            lines.add(line1);
            lines.add(line2);
            lines.add(line3);
            document.setEffortCertificationDetailLines(lines);

            List<EffortCertificationDetail> result = document.getEffortCertificationDetailWithMaxPayrollAmount();
            assertThat(result).hasSize(2);
        }

        @Test
        void shouldReturnEmptyListForNoLines() {
            document.setEffortCertificationDetailLines(new ArrayList<>());
            assertThat(document.getEffortCertificationDetailWithMaxPayrollAmount()).isEmpty();
        }
    }

    @Nested
    @DisplayName("Federal total calculations")
    class FederalTotalTests {

        @Test
        void shouldCalculateFederalTotalEffortPercent() throws Exception {
            EffortCertificationDetail fedLine = new EffortCertificationDetail();
            fedLine.setEffortCertificationUpdatedOverallPercent(40);
            fedLine.setEffortCertificationCalculatedOverallPercent(38);
            setFederalIndicator(fedLine, true);
            fedLine.setEffortCertificationPayrollAmount(new KualiDecimal(1000));
            fedLine.setOriginalFringeBenefitAmount(new KualiDecimal(100));

            EffortCertificationDetail nonFedLine = new EffortCertificationDetail();
            nonFedLine.setEffortCertificationUpdatedOverallPercent(60);
            nonFedLine.setEffortCertificationCalculatedOverallPercent(62);
            setFederalIndicator(nonFedLine, false);
            nonFedLine.setEffortCertificationPayrollAmount(new KualiDecimal(2000));
            nonFedLine.setOriginalFringeBenefitAmount(new KualiDecimal(200));

            List<EffortCertificationDetail> lines = new ArrayList<>();
            lines.add(fedLine);
            lines.add(nonFedLine);
            document.setEffortCertificationDetailLines(lines);

            assertThat(document.getFederalTotalEffortPercent()).isEqualTo(40);
        }

        @Test
        void shouldCalculateFederalTotalOriginalEffortPercent() throws Exception {
            EffortCertificationDetail fedLine = new EffortCertificationDetail();
            fedLine.setEffortCertificationCalculatedOverallPercent(45);
            fedLine.setEffortCertificationUpdatedOverallPercent(45);
            setFederalIndicator(fedLine, true);
            fedLine.setEffortCertificationPayrollAmount(new KualiDecimal(1000));
            fedLine.setOriginalFringeBenefitAmount(new KualiDecimal(50));

            EffortCertificationDetail nonFedLine = new EffortCertificationDetail();
            nonFedLine.setEffortCertificationCalculatedOverallPercent(55);
            nonFedLine.setEffortCertificationUpdatedOverallPercent(55);
            setFederalIndicator(nonFedLine, false);
            nonFedLine.setEffortCertificationPayrollAmount(new KualiDecimal(2000));
            nonFedLine.setOriginalFringeBenefitAmount(new KualiDecimal(100));

            List<EffortCertificationDetail> lines = new ArrayList<>();
            lines.add(fedLine);
            lines.add(nonFedLine);
            document.setEffortCertificationDetailLines(lines);

            assertThat(document.getFederalTotalOriginalEffortPercent()).isEqualTo(45);
        }

        @Test
        void shouldReturnZeroFederalTotalsWhenNoFederalLines() throws Exception {
            EffortCertificationDetail nonFedLine = new EffortCertificationDetail();
            nonFedLine.setEffortCertificationUpdatedOverallPercent(100);
            nonFedLine.setEffortCertificationCalculatedOverallPercent(100);
            setFederalIndicator(nonFedLine, false);
            nonFedLine.setEffortCertificationPayrollAmount(new KualiDecimal(5000));
            nonFedLine.setOriginalFringeBenefitAmount(new KualiDecimal(500));

            List<EffortCertificationDetail> lines = new ArrayList<>();
            lines.add(nonFedLine);
            document.setEffortCertificationDetailLines(lines);

            assertThat(document.getFederalTotalEffortPercent()).isEqualTo(0);
            assertThat(document.getFederalTotalOriginalEffortPercent()).isEqualTo(0);
        }
    }

    private static void setFederalIndicator(EffortCertificationDetail detail, boolean value) throws Exception {
        Field field = EffortCertificationDetail.class.getDeclaredField("federalOrFederalPassThroughIndicator");
        field.setAccessible(true);
        field.set(detail, Boolean.valueOf(value));
    }
}
