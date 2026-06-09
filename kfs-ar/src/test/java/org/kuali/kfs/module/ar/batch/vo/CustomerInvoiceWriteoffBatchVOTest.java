package org.kuali.kfs.module.ar.batch.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerInvoiceWriteoffBatchVOTest extends KfsUnitTestBase {

    private CustomerInvoiceWriteoffBatchVO batchVO;

    @BeforeEach
    void setUp() {
        batchVO = new CustomerInvoiceWriteoffBatchVO();
    }

    @Test
    void testDefaultConstructor() {
        assertThat(batchVO.getInvoiceNumbers()).isNotNull().isEmpty();
        assertThat(batchVO.getSubmittedOn()).isEqualTo("Unknown");
    }

    @Test
    void testConstructorWithPrincipalName() {
        CustomerInvoiceWriteoffBatchVO vo = new CustomerInvoiceWriteoffBatchVO("admin");
        assertThat(vo.getSubmittedByPrincipalName()).isEqualTo("admin");
        assertThat(vo.getSubmittedOn()).isEqualTo("Unknown");
        assertThat(vo.getInvoiceNumbers()).isNotNull().isEmpty();
    }

    @Test
    void testConstructorWithPrincipalNameAndDate() {
        CustomerInvoiceWriteoffBatchVO vo = new CustomerInvoiceWriteoffBatchVO("admin", "2024-03-15");
        assertThat(vo.getSubmittedByPrincipalName()).isEqualTo("admin");
        assertThat(vo.getSubmittedOn()).isEqualTo("2024-03-15");
    }

    @Test
    void testAddInvoiceNumber() {
        batchVO.addInvoiceNumber("INV001");
        batchVO.addInvoiceNumber("INV002");
        assertThat(batchVO.getInvoiceNumbers()).hasSize(2).contains("INV001", "INV002");
    }

    @Test
    void testAddDuplicateInvoiceNumber() {
        batchVO.addInvoiceNumber("INV001");
        batchVO.addInvoiceNumber("INV001");
        assertThat(batchVO.getInvoiceNumbers()).hasSize(1);
    }

    @Test
    void testSubmittedByPrincipalName() {
        batchVO.setSubmittedByPrincipalName("user1");
        assertThat(batchVO.getSubmittedByPrincipalName()).isEqualTo("user1");
    }

    @Test
    void testSubmittedOn() {
        batchVO.setSubmittedOn("2024-04-01");
        assertThat(batchVO.getSubmittedOn()).isEqualTo("2024-04-01");
    }

    @Test
    void testNote() {
        batchVO.setNote("Batch writeoff for quarter end");
        assertThat(batchVO.getNote()).isEqualTo("Batch writeoff for quarter end");
    }

    @Test
    void testSetInvoiceNumbers() {
        Set<String> numbers = new HashSet<>();
        numbers.add("INV100");
        numbers.add("INV200");
        batchVO.setInvoiceNumbers(numbers);
        assertThat(batchVO.getInvoiceNumbers()).hasSize(2).contains("INV100", "INV200");
    }
}
