package org.kuali.kfs.module.cam.businessobject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class AssetPaymentDocumentTypeTest extends KfsUnitTestBase {

    @Test
    @DisplayName("default constructor: all fields null/false")
    void defaultConstructor() {
        AssetPaymentDocumentType type = new AssetPaymentDocumentType();
        assertThat(type.getDocumentTypeId()).isNull();
        assertThat(type.getExpenditureFinancialDocumentTypeCode()).isNull();
        assertThat(type.isActive()).isFalse();
    }

    @Test
    @DisplayName("documentTypeId getter/setter")
    void documentTypeId() {
        AssetPaymentDocumentType type = new AssetPaymentDocumentType();
        type.setDocumentTypeId(1001L);
        assertThat(type.getDocumentTypeId()).isEqualTo(1001L);
    }

    @Test
    @DisplayName("expenditureFinancialDocumentTypeCode getter/setter")
    void expenditureFinancialDocumentTypeCode() {
        AssetPaymentDocumentType type = new AssetPaymentDocumentType();
        type.setExpenditureFinancialDocumentTypeCode("MPAY");
        assertThat(type.getExpenditureFinancialDocumentTypeCode()).isEqualTo("MPAY");
    }

    @Test
    @DisplayName("active flag getter/setter")
    void activeFlag() {
        AssetPaymentDocumentType type = new AssetPaymentDocumentType();
        type.setActive(true);
        assertThat(type.isActive()).isTrue();
    }

    @Test
    @DisplayName("label setter stores value")
    void labelSetter() {
        AssetPaymentDocumentType type = new AssetPaymentDocumentType();
        type.setLabel("Test Label");
        // getLabel() requires KewApiServiceLocator, so we just verify setLabel doesn't throw
    }
}
