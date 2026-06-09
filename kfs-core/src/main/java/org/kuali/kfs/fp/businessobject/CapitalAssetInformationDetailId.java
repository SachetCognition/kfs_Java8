package org.kuali.kfs.fp.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class CapitalAssetInformationDetailId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Integer capitalAssetLineNumber;
    private Integer itemLineNumber;

    public CapitalAssetInformationDetailId() {}

    public CapitalAssetInformationDetailId(String documentNumber, Integer capitalAssetLineNumber, Integer itemLineNumber) {
        this.documentNumber = documentNumber;
        this.capitalAssetLineNumber = capitalAssetLineNumber;
        this.itemLineNumber = itemLineNumber;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public Integer getCapitalAssetLineNumber() { return capitalAssetLineNumber; }
    public void setCapitalAssetLineNumber(Integer capitalAssetLineNumber) { this.capitalAssetLineNumber = capitalAssetLineNumber; }

    public Integer getItemLineNumber() { return itemLineNumber; }
    public void setItemLineNumber(Integer itemLineNumber) { this.itemLineNumber = itemLineNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CapitalAssetInformationDetailId that = (CapitalAssetInformationDetailId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(capitalAssetLineNumber, that.capitalAssetLineNumber) && Objects.equals(itemLineNumber, that.itemLineNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, capitalAssetLineNumber, itemLineNumber);
    }
}
