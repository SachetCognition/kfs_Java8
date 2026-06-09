package org.kuali.kfs.module.ld.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class PositionDataId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String positionNumber;
    private java.sql.Date effectiveDate;

    public PositionDataId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionDataId other = (PositionDataId) o;
        return Objects.equals(positionNumber, other.positionNumber) && Objects.equals(effectiveDate, other.effectiveDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionNumber, effectiveDate);
    }
}
