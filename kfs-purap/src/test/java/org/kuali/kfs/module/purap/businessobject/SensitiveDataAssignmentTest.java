package org.kuali.kfs.module.purap.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SensitiveDataAssignmentTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor() {
        SensitiveDataAssignment sda = new SensitiveDataAssignment();
        assertThat(sda.getSensitiveDataAssignmentIdentifier()).isNull();
        assertThat(sda.getPurapDocumentIdentifier()).isNull();
        assertThat(sda.getSensitiveDataAssignmentReasonText()).isNull();
        assertThat(sda.getSensitiveDataAssignmentPersonIdentifier()).isNull();
    }

    @Test
    void settersAndGetters() {
        SensitiveDataAssignment sda = new SensitiveDataAssignment();
        sda.setSensitiveDataAssignmentIdentifier(1);
        sda.setPurapDocumentIdentifier(100);
        sda.setSensitiveDataAssignmentReasonText("Test reason");
        sda.setSensitiveDataAssignmentPersonIdentifier("admin");
        Date date = new Date();
        sda.setSensitiveDataAssignmentChangeDate(date);

        assertThat(sda.getSensitiveDataAssignmentIdentifier()).isEqualTo(1);
        assertThat(sda.getPurapDocumentIdentifier()).isEqualTo(100);
        assertThat(sda.getSensitiveDataAssignmentReasonText()).isEqualTo("Test reason");
        assertThat(sda.getSensitiveDataAssignmentPersonIdentifier()).isEqualTo("admin");
        assertThat(sda.getSensitiveDataAssignmentChangeDate()).isEqualTo(date);
    }

    @Test
    void setAndGetSensitiveDataAssignmentDetails() {
        SensitiveDataAssignment sda = new SensitiveDataAssignment();
        List<SensitiveDataAssignmentDetail> details = new ArrayList<>();
        SensitiveDataAssignmentDetail detail = new SensitiveDataAssignmentDetail();
        detail.setSensitiveDataCode("HAZMAT");
        details.add(detail);
        sda.setSensitiveDataAssignmentDetails(details);
        assertThat(sda.getSensitiveDataAssignmentDetails()).hasSize(1);
    }
}
