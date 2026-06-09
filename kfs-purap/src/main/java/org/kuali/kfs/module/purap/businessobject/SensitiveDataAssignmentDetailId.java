package org.kuali.kfs.module.purap.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class SensitiveDataAssignmentDetailId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer sensitiveDataAssignmentIdentifier;
    private String sensitiveDataCode;

    public SensitiveDataAssignmentDetailId() {
    }

    public SensitiveDataAssignmentDetailId(Integer sensitiveDataAssignmentIdentifier, String sensitiveDataCode) {
        this.sensitiveDataAssignmentIdentifier = sensitiveDataAssignmentIdentifier;
        this.sensitiveDataCode = sensitiveDataCode;
    }

    public Integer getSensitiveDataAssignmentIdentifier() {
        return sensitiveDataAssignmentIdentifier;
    }

    public void setSensitiveDataAssignmentIdentifier(Integer sensitiveDataAssignmentIdentifier) {
        this.sensitiveDataAssignmentIdentifier = sensitiveDataAssignmentIdentifier;
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
        SensitiveDataAssignmentDetailId that = (SensitiveDataAssignmentDetailId) o;
        return Objects.equals(sensitiveDataAssignmentIdentifier, that.sensitiveDataAssignmentIdentifier) && Objects.equals(sensitiveDataCode, that.sensitiveDataCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensitiveDataAssignmentIdentifier, sensitiveDataCode);
    }
}
