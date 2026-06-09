package org.kuali.kfs.module.bc.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class BudgetConstructionActiveJobMoveId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String principalId;
    private String emplid;
    private Integer employeeRecord;
    private java.sql.Date effectiveDate;
    private Integer effectiveSequence;

    public BudgetConstructionActiveJobMoveId() {}

    public String getPrincipalId() { return principalId; }
    public void setPrincipalId(String principalId) { this.principalId = principalId; }

    public String getEmplid() { return emplid; }
    public void setEmplid(String emplid) { this.emplid = emplid; }

    public Integer getEmployeeRecord() { return employeeRecord; }
    public void setEmployeeRecord(Integer employeeRecord) { this.employeeRecord = employeeRecord; }

    public java.sql.Date getEffectiveDate() { return effectiveDate; }
    public void setEffectiveDate(java.sql.Date effectiveDate) { this.effectiveDate = effectiveDate; }

    public Integer getEffectiveSequence() { return effectiveSequence; }
    public void setEffectiveSequence(Integer effectiveSequence) { this.effectiveSequence = effectiveSequence; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetConstructionActiveJobMoveId that = (BudgetConstructionActiveJobMoveId) o;
        return Objects.equals(principalId, that.principalId) && Objects.equals(emplid, that.emplid) && Objects.equals(employeeRecord, that.employeeRecord) && Objects.equals(effectiveDate, that.effectiveDate) && Objects.equals(effectiveSequence, that.effectiveSequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(principalId, emplid, employeeRecord, effectiveDate, effectiveSequence);
    }
}
