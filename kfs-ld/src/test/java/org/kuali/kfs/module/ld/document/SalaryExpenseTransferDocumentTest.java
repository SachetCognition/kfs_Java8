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
    void testSetAndGetErrorCertification() {
        ErrorCertification cert = new ErrorCertification();
        cert.setDocumentNumber("DOC123");
        document.setErrorCertification(cert);
        assertThat(document.getErrorCertification()).isNotNull();
        assertThat(document.getErrorCertification().getDocumentNumber()).isEqualTo("DOC123");
    }

    @Test
    void testErrorCertificationNullByDefault() {
        assertThat(document.getErrorCertification()).isNull();
    }

    @Test
    void testApprovalObjectCodeBalancesNullByDefault() {
        assertThat(document.getApprovalObjectCodeBalances()).isNull();
    }
}
