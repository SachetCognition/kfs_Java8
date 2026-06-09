package org.kuali.kfs.module.ec.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;

class EffortCertificationDocumentBuildTest extends KfsUnitTestBase {

    private EffortCertificationDocumentBuild docBuild;

    @BeforeEach
    void setUp() {
        // Use mock with CALLS_REAL_METHODS to bypass the parent constructor
        // (FinancialSystemTransactionalDocumentBase) that needs Rice ResourceLoader
        docBuild = mock(EffortCertificationDocumentBuild.class, CALLS_REAL_METHODS);
    }

    @Test
    @DisplayName("Should set and get build number")
    void shouldSetAndGetBuildNumber() {
        docBuild.setEffortCertificationBuildNumber(100L);
        assertThat(docBuild.getEffortCertificationBuildNumber()).isEqualTo(100L);
    }

    @Test
    @DisplayName("Should set and get detail lines build list")
    void shouldSetAndGetDetailLinesBuild() {
        List<EffortCertificationDetailBuild> lines = new ArrayList<>();
        EffortCertificationDetailBuild line = new EffortCertificationDetailBuild();
        line.setEffortCertificationBuildNumber(1L);
        lines.add(line);

        docBuild.setEffortCertificationDetailLinesBuild(lines);
        assertThat(docBuild.getEffortCertificationDetailLinesBuild()).hasSize(1);
        assertThat(docBuild.getEffortCertificationDetailLinesBuild().get(0).getEffortCertificationBuildNumber()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Should inherit document fields from EffortCertificationDocument")
    void shouldInheritDocumentFields() {
        docBuild.setEmplid("EMP001");
        docBuild.setUniversityFiscalYear(2024);
        docBuild.setEffortCertificationReportNumber("A01");
        docBuild.setEffortCertificationDocumentCode(true);

        assertThat(docBuild.getEmplid()).isEqualTo("EMP001");
        assertThat(docBuild.getUniversityFiscalYear()).isEqualTo(2024);
        assertThat(docBuild.getEffortCertificationReportNumber()).isEqualTo("A01");
        assertThat(docBuild.getEffortCertificationDocumentCode()).isTrue();
    }
}
