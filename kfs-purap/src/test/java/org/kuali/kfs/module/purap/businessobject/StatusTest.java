package org.kuali.kfs.module.purap.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class StatusTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor() {
        Status status = new Status();
        assertThat(status.getStatusCode()).isNull();
        assertThat(status.getStatusDescription()).isNull();
    }

    @Test
    void settersAndGetters() {
        Status status = new Status();
        status.setStatusCode("OPEN");
        status.setStatusDescription("Open");
        status.setOjbConcreteClass("org.kuali.kfs.module.purap.businessobject.Status");

        assertThat(status.getStatusCode()).isEqualTo("OPEN");
        assertThat(status.getStatusDescription()).isEqualTo("Open");
        assertThat(status.getOjbConcreteClass()).isEqualTo("org.kuali.kfs.module.purap.businessobject.Status");
    }

    @Test
    void compareByDescription() {
        Status s1 = new Status();
        s1.setStatusDescription("Apple");
        Status s2 = new Status();
        s2.setStatusDescription("Banana");

        Status comparator = new Status();
        assertThat(comparator.compare(s1, s2)).isNegative();
        assertThat(comparator.compare(s2, s1)).isPositive();
        assertThat(comparator.compare(s1, s1)).isZero();
    }
}
