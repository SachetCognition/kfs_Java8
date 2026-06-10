package org.kuali.kfs.fp.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class CapitalAssetAccountsGroupDetailsId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Integer capitalAssetLineNumber;
    private Integer capitalAssetAccountLineNumber;
    private Integer sequenceNumber;

    public CapitalAssetAccountsGroupDetailsId() {}

    public CapitalAssetAccountsGroupDetailsId(String documentNumber, Integer capitalAssetLineNumber, Integer capitalAssetAccountLineNumber, Integer sequenceNumber) {
        this.documentNumber = documentNumber;
        this.capitalAssetLineNumber = capitalAssetLineNumber;
        this.capitalAssetAccountLineNumber = capitalAssetAccountLineNumber;
        this.sequenceNumber = sequenceNumber;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public Integer getCapitalAssetLineNumber() { return capitalAssetLineNumber; }
    public void setCapitalAssetLineNumber(Integer capitalAssetLineNumber) { this.capitalAssetLineNumber = capitalAssetLineNumber; }

    public Integer getCapitalAssetAccountLineNumber() { return capitalAssetAccountLineNumber; }
    public void setCapitalAssetAccountLineNumber(Integer capitalAssetAccountLineNumber) { this.capitalAssetAccountLineNumber = capitalAssetAccountLineNumber; }

    public Integer getSequenceNumber() { return sequenceNumber; }
    public void setSequenceNumber(Integer sequenceNumber) { this.sequenceNumber = sequenceNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CapitalAssetAccountsGroupDetailsId that = (CapitalAssetAccountsGroupDetailsId) o;
        return Objects.equals(documentNumber, that.documentNumber) && Objects.equals(capitalAssetLineNumber, that.capitalAssetLineNumber) && Objects.equals(capitalAssetAccountLineNumber, that.capitalAssetAccountLineNumber) && Objects.equals(sequenceNumber, that.sequenceNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, capitalAssetLineNumber, capitalAssetAccountLineNumber, sequenceNumber);
    }
}
