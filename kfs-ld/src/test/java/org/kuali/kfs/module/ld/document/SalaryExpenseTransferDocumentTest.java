package org.kuali.kfs.module.ld.document;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ld.businessobject.ErrorCertification;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.objenesis.ObjenesisStd;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class SalaryExpenseTransferDocumentTest extends KfsUnitTestBase {

    private SalaryExpenseTransferDocument document;

    @BeforeEach
    void setUp() {
        document = new ObjenesisStd().newInstance(SalaryExpenseTransferDocument.class);
    }

    @Test
    void testSetAndGetApprovalObjectCodeBalances() {
        Map<String, KualiDecimal> balances = new HashMap<>();
        balances.put("5000", new KualiDecimal(1000));
        document.setApprovalObjectCodeBalances(balances);
        assertThat(document.getApprovalObjectCodeBalances()).containsEntry("5000", new KualiDecimal(1000));
    }

    @Test
    void testApprovalObjectCodeBalancesOverwrite() {
        Map<String, KualiDecimal> first = new HashMap<>();
        first.put("5000", new KualiDecimal(100));
        document.setApprovalObjectCodeBalances(first);

        Map<String, KualiDecimal> second = new HashMap<>();
        second.put("6000", new KualiDecimal(200));
        document.setApprovalObjectCodeBalances(second);

        assertThat(document.getApprovalObjectCodeBalances())
                .containsEntry("6000", new KualiDecimal(200))
                .doesNotContainKey("5000");
    }

    @Test
    void testSetAndGetErrorCertification() {
        ErrorCertification cert = new ErrorCertification();
        cert.setDocumentNumber("DOC123");
        document.setErrorCertification(cert);
        assertThat(document.getErrorCertification()).isNotNull();
        assertThat(document.getErrorCertification().getDocumentNumber()).isEqualTo("DOC123");
    }

    @Test
    void testErrorCertificationReplacement() {
        ErrorCertification cert1 = new ErrorCertification();
        cert1.setDocumentNumber("DOC1");
        document.setErrorCertification(cert1);

        ErrorCertification cert2 = new ErrorCertification();
        cert2.setDocumentNumber("DOC2");
        document.setErrorCertification(cert2);

        assertThat(document.getErrorCertification().getDocumentNumber()).isEqualTo("DOC2");
    }
}
