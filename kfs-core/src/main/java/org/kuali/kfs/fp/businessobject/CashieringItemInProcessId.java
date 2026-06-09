package org.kuali.kfs.fp.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class CashieringItemInProcessId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String campusCode;
    private Integer itemIdentifier;

    public CashieringItemInProcessId() {}

    public CashieringItemInProcessId(String campusCode, Integer itemIdentifier) {
        this.campusCode = campusCode;
        this.itemIdentifier = itemIdentifier;
    }

    public String getCampusCode() { return campusCode; }
    public void setCampusCode(String campusCode) { this.campusCode = campusCode; }

    public Integer getItemIdentifier() { return itemIdentifier; }
    public void setItemIdentifier(Integer itemIdentifier) { this.itemIdentifier = itemIdentifier; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashieringItemInProcessId that = (CashieringItemInProcessId) o;
        return Objects.equals(campusCode, that.campusCode) && Objects.equals(itemIdentifier, that.itemIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(campusCode, itemIdentifier);
    }
}
