package org.kuali.kfs.module.purap.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class PurchaseOrderSensitiveDataTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor() {
        PurchaseOrderSensitiveData posd = new PurchaseOrderSensitiveData();
        assertThat(posd.getPurapDocumentIdentifier()).isNull();
        assertThat(posd.getRequisitionIdentifier()).isNull();
        assertThat(posd.getSensitiveDataCode()).isNull();
    }

    @Test
    void threeArgConstructor() {
        PurchaseOrderSensitiveData posd = new PurchaseOrderSensitiveData(100, 200, "HAZMAT");
        assertThat(posd.getPurapDocumentIdentifier()).isEqualTo(100);
        assertThat(posd.getRequisitionIdentifier()).isEqualTo(200);
        assertThat(posd.getSensitiveDataCode()).isEqualTo("HAZMAT");
    }

    @Test
    void settersAndGetters() {
        PurchaseOrderSensitiveData posd = new PurchaseOrderSensitiveData();
        posd.setPurapDocumentIdentifier(50);
        posd.setRequisitionIdentifier(60);
        posd.setSensitiveDataCode("CHEM");

        assertThat(posd.getPurapDocumentIdentifier()).isEqualTo(50);
        assertThat(posd.getRequisitionIdentifier()).isEqualTo(60);
        assertThat(posd.getSensitiveDataCode()).isEqualTo("CHEM");
    }

    @Test
    void sensitiveDataReference() {
        PurchaseOrderSensitiveData posd = new PurchaseOrderSensitiveData();
        SensitiveData sd = new SensitiveData();
        sd.setSensitiveDataCode("BIO");
        posd.setSensitiveData(sd);
        assertThat(posd.getSensitiveData().getSensitiveDataCode()).isEqualTo("BIO");
    }
}
