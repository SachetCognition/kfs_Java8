package org.kuali.kfs.module.purap.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class PurchaseOrderSensitiveDataId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer purapDocumentIdentifier;
    private String sensitiveDataCode;

    public PurchaseOrderSensitiveDataId() {
    }

    public PurchaseOrderSensitiveDataId(Integer purapDocumentIdentifier, String sensitiveDataCode) {
        this.purapDocumentIdentifier = purapDocumentIdentifier;
        this.sensitiveDataCode = sensitiveDataCode;
    }

    public Integer getPurapDocumentIdentifier() {
        return purapDocumentIdentifier;
    }

    public void setPurapDocumentIdentifier(Integer purapDocumentIdentifier) {
        this.purapDocumentIdentifier = purapDocumentIdentifier;
    }

    public String getSensitiveDataCode() {
        return sensitiveDataCode;
    }

    public void setSensitiveDataCode(String sensitiveDataCode) {
        this.sensitiveDataCode = sensitiveDataCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseOrderSensitiveDataId that = (PurchaseOrderSensitiveDataId) o;
        return Objects.equals(purapDocumentIdentifier, that.purapDocumentIdentifier) && Objects.equals(sensitiveDataCode, that.sensitiveDataCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purapDocumentIdentifier, sensitiveDataCode);
    }
}
