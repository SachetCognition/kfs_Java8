package org.kuali.kfs.pdp.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.core.api.util.type.KualiInteger;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentAccountDetailTest extends KfsUnitTestBase {

    private PaymentAccountDetail accountDetail;

    @BeforeEach
    void setUp() {
        accountDetail = new PaymentAccountDetail();
    }

    @Test
    void testDefaultConstructor() {
        assertThat(accountDetail.getAccountHistory()).isNotNull().isEmpty();
    }

    @Test
    void testGettersAndSetters() {
        KualiInteger id = new KualiInteger(1);
        accountDetail.setId(id);
        accountDetail.setFinChartCode("BL");
        accountDetail.setAccountNbr("1234567");
        accountDetail.setSubAccountNbr("001");
        accountDetail.setFinObjectCode("5000");
        accountDetail.setFinSubObjectCode("100");
        accountDetail.setOrgReferenceId("ORG001");
        accountDetail.setProjectCode("PROJ001");
        accountDetail.setAccountNetAmount(new KualiDecimal(500.00));

        assertThat(accountDetail.getId()).isEqualTo(id);
        assertThat(accountDetail.getFinChartCode()).isEqualTo("BL");
        assertThat(accountDetail.getAccountNbr()).isEqualTo("1234567");
        assertThat(accountDetail.getSubAccountNbr()).isEqualTo("001");
        assertThat(accountDetail.getFinObjectCode()).isEqualTo("5000");
        assertThat(accountDetail.getFinSubObjectCode()).isEqualTo("100");
        assertThat(accountDetail.getOrgReferenceId()).isEqualTo("ORG001");
        assertThat(accountDetail.getProjectCode()).isEqualTo("PROJ001");
        assertThat(accountDetail.getAccountNetAmount()).isEqualTo(new KualiDecimal(500.00));
    }

    @Test
    void testPaymentDetailAssociation() {
        PaymentDetail pd = new PaymentDetail();
        KualiInteger pdId = new KualiInteger(42);
        accountDetail.setPaymentDetail(pd);
        accountDetail.setPaymentDetailId(pdId);

        assertThat(accountDetail.getPaymentDetail()).isSameAs(pd);
        assertThat(accountDetail.getPaymentDetailId()).isEqualTo(pdId);
    }

    @Test
    void testAccountHistory() {
        List<PaymentAccountHistory> history = new ArrayList<>();
        history.add(new PaymentAccountHistory());
        accountDetail.setAccountHistory(history);

        assertThat(accountDetail.getAccountHistory()).hasSize(1);
    }

    @Test
    void testAccountNetAmountNull() {
        assertThat(accountDetail.getAccountNetAmount()).isNull();
    }
}
