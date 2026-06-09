package org.kuali.kfs.pdp.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.core.api.util.type.KualiInteger;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

class BatchTest extends KfsUnitTestBase {

    private Batch batch;

    @BeforeEach
    void setUp() {
        batch = new Batch();
    }

    @Test
    void testDefaultConstructor() {
        assertThat(batch).isNotNull();
    }

    @Test
    void testGettersAndSetters() {
        KualiInteger id = new KualiInteger(1);
        batch.setId(id);

        CustomerProfile cp = new CustomerProfile();
        batch.setCustomerProfile(cp);

        KualiInteger customerId = new KualiInteger(10);
        batch.setCustomerFileCreateTimestamp(new Timestamp(System.currentTimeMillis()));
        batch.setPaymentCount(new KualiInteger(5));
        batch.setPaymentTotalAmount(new KualiDecimal(1000));
        batch.setSubmiterUserId("testUser");
        batch.setFileProcessTimestamp(new Timestamp(System.currentTimeMillis()));

        assertThat(batch.getId()).isEqualTo(id);
        assertThat(batch.getCustomerProfile()).isSameAs(cp);
        assertThat(batch.getPaymentCount()).isEqualTo(new KualiInteger(5));
        assertThat(batch.getPaymentTotalAmount()).isEqualTo(new KualiDecimal(1000));
        assertThat(batch.getSubmiterUserId()).isEqualTo("testUser");
        assertThat(batch.getCustomerFileCreateTimestamp()).isNotNull();
        assertThat(batch.getFileProcessTimestamp()).isNotNull();
    }
}
