package org.kuali.kfs.pdp.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.core.api.util.type.KualiInteger;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentFileLoadTest extends KfsUnitTestBase {

    private PaymentFileLoad paymentFileLoad;

    @BeforeEach
    void setUp() {
        paymentFileLoad = new PaymentFileLoad();
    }

    @Test
    void testDefaultConstructor() {
        assertThat(paymentFileLoad.getPaymentGroups()).isNotNull().isEmpty();
        assertThat(paymentFileLoad.isFileThreshold()).isFalse();
        assertThat(paymentFileLoad.isDetailThreshold()).isFalse();
        assertThat(paymentFileLoad.isTaxEmailRequired()).isFalse();
        assertThat(paymentFileLoad.isPassedValidation()).isFalse();
        assertThat(paymentFileLoad.getThresholdPaymentDetails()).isNotNull().isEmpty();
    }

    @Test
    void testGetActualPaymentCount_empty() {
        assertThat(paymentFileLoad.getActualPaymentCount()).isZero();
    }

    @Test
    void testGetActualPaymentCount_withGroups() {
        PaymentGroup pg1 = new PaymentGroup();
        pg1.addPaymentDetails(new PaymentDetail());
        pg1.addPaymentDetails(new PaymentDetail());

        PaymentGroup pg2 = new PaymentGroup();
        pg2.addPaymentDetails(new PaymentDetail());

        paymentFileLoad.getPaymentGroups().add(pg1);
        paymentFileLoad.getPaymentGroups().add(pg2);

        assertThat(paymentFileLoad.getActualPaymentCount()).isEqualTo(3);
    }

    @Test
    void testGettersAndSetters_headerFields() {
        paymentFileLoad.setChart("BL");
        paymentFileLoad.setUnit("ACCT");
        paymentFileLoad.setSubUnit("MAIN");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        paymentFileLoad.setCreationDate(ts);

        assertThat(paymentFileLoad.getChart()).isEqualTo("BL");
        assertThat(paymentFileLoad.getUnit()).isEqualTo("ACCT");
        assertThat(paymentFileLoad.getSubUnit()).isEqualTo("MAIN");
        assertThat(paymentFileLoad.getCreationDate()).isEqualTo(ts);
    }

    @Test
    void testGettersAndSetters_trailerFields() {
        paymentFileLoad.setPaymentCount(10);
        paymentFileLoad.setPaymentTotalAmount(new KualiDecimal(5000));

        assertThat(paymentFileLoad.getPaymentCount()).isEqualTo(10);
        assertThat(paymentFileLoad.getPaymentTotalAmount()).isEqualTo(new KualiDecimal(5000));
    }

    @Test
    void testGettersAndSetters_loadVars() {
        KualiInteger batchId = new KualiInteger(99);
        paymentFileLoad.setBatchId(batchId);
        paymentFileLoad.setFileThreshold(true);
        paymentFileLoad.setDetailThreshold(true);
        paymentFileLoad.setTaxEmailRequired(true);
        paymentFileLoad.setPassedValidation(true);

        assertThat(paymentFileLoad.getBatchId()).isEqualTo(batchId);
        assertThat(paymentFileLoad.isFileThreshold()).isTrue();
        assertThat(paymentFileLoad.isDetailThreshold()).isTrue();
        assertThat(paymentFileLoad.isTaxEmailRequired()).isTrue();
        assertThat(paymentFileLoad.isPassedValidation()).isTrue();
    }

    @Test
    void testSetAndGetCustomer() {
        CustomerProfile cp = new CustomerProfile();
        cp.setChartCode("BL");
        paymentFileLoad.setCustomer(cp);
        assertThat(paymentFileLoad.getCustomer()).isSameAs(cp);
    }

    @Test
    void testSetAndGetPaymentGroups() {
        List<PaymentGroup> groups = new ArrayList<>();
        groups.add(new PaymentGroup());
        groups.add(new PaymentGroup());
        paymentFileLoad.setPaymentGroups(groups);
        assertThat(paymentFileLoad.getPaymentGroups()).hasSize(2);
    }

    @Test
    void testSetAndGetThresholdPaymentDetails() {
        List<PaymentDetail> details = new ArrayList<>();
        details.add(new PaymentDetail());
        paymentFileLoad.setThresholdPaymentDetails(details);
        assertThat(paymentFileLoad.getThresholdPaymentDetails()).hasSize(1);
    }
}
