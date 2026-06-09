package org.kuali.kfs.module.tem.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("GroupTraveler Business Object")
class GroupTravelerTest extends KfsUnitTestBase {

    private GroupTraveler groupTraveler;

    @BeforeEach
    void setUp() {
        groupTraveler = new GroupTraveler();
    }

    @Test
    @DisplayName("should set and get id")
    void testId() {
        groupTraveler.setId(1);
        assertThat(groupTraveler.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("should set and get documentNumber")
    void testDocumentNumber() {
        groupTraveler.setDocumentNumber("TR001234");
        assertThat(groupTraveler.getDocumentNumber()).isEqualTo("TR001234");
    }

    @Test
    @DisplayName("should set and get financialDocumentLineNumber")
    void testFinancialDocumentLineNumber() {
        groupTraveler.setFinancialDocumentLineNumber(5);
        assertThat(groupTraveler.getFinancialDocumentLineNumber()).isEqualTo(5);
    }

    @Test
    @DisplayName("should set and get name")
    void testName() {
        groupTraveler.setName("John Smith");
        assertThat(groupTraveler.getName()).isEqualTo("John Smith");
    }

    @Test
    @DisplayName("should set and get groupTravelerTypeCode")
    void testGroupTravelerTypeCode() {
        groupTraveler.setGroupTravelerTypeCode("EMP");
        assertThat(groupTraveler.getGroupTravelerTypeCode()).isEqualTo("EMP");
    }

    @Test
    @DisplayName("should set and get groupTravelerEmpId")
    void testGroupTravelerEmpId() {
        groupTraveler.setGroupTravelerEmpId("E12345");
        assertThat(groupTraveler.getGroupTravelerEmpId()).isEqualTo("E12345");
    }

    @Test
    @DisplayName("should handle null values gracefully")
    void testNullValues() {
        assertThat(groupTraveler.getId()).isNull();
        assertThat(groupTraveler.getDocumentNumber()).isNull();
        assertThat(groupTraveler.getName()).isNull();
        assertThat(groupTraveler.getGroupTravelerTypeCode()).isNull();
        assertThat(groupTraveler.getGroupTravelerEmpId()).isNull();
    }
}
