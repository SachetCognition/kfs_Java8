package org.kuali.kfs.module.purap.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class SensitiveDataAssignmentDetailTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor() {
        SensitiveDataAssignmentDetail detail = new SensitiveDataAssignmentDetail();
        assertThat(detail.getSensitiveDataAssignmentIdentifier()).isNull();
        assertThat(detail.getSensitiveDataCode()).isNull();
    }

    @Test
    void twoArgConstructor() {
        SensitiveDataAssignment parent = new SensitiveDataAssignment();
        SensitiveDataAssignmentDetail detail = new SensitiveDataAssignmentDetail("HAZMAT", parent);
        assertThat(detail.getSensitiveDataCode()).isEqualTo("HAZMAT");
        assertThat(detail.getSensitiveDataAssignment()).isSameAs(parent);
    }

    @Test
    void settersAndGetters() {
        SensitiveDataAssignmentDetail detail = new SensitiveDataAssignmentDetail();
        detail.setSensitiveDataAssignmentIdentifier(10);
        detail.setSensitiveDataCode("CHEM");

        SensitiveData sd = new SensitiveData();
        sd.setSensitiveDataCode("CHEM");
        detail.setSensitiveData(sd);

        assertThat(detail.getSensitiveDataAssignmentIdentifier()).isEqualTo(10);
        assertThat(detail.getSensitiveDataCode()).isEqualTo("CHEM");
        assertThat(detail.getSensitiveData().getSensitiveDataCode()).isEqualTo("CHEM");
    }
}
