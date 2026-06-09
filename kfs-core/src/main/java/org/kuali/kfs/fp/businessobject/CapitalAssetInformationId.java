package org.kuali.kfs.fp.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class CapitalAssetInformationId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Integer capitalAssetLineNumber;

    public CapitalAssetInformationId() {}

    public CapitalAssetInformationId(String documentNumber, Integer capitalAssetLineNumber) {
        this.documentNumber = documentNumber;
        this.capitalAssetLineNumber = capitalAssetLineNumber;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public Integer getCapitalAssetLineNumber() { return capitalAssetLineNumber; }
    public void setCapitalAssetLineNumber(Integer capitalAssetLineNumber) { this.capitalAssetLineNumber = capitalAssetLineNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CapitalAssetInformationId that = (CapitalAssetInformationId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(capitalAssetLineNumber, that.capitalAssetLineNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, capitalAssetLineNumber);
    }
}
