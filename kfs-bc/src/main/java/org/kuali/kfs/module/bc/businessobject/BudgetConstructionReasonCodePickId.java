package org.kuali.kfs.module.bc.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class BudgetConstructionReasonCodePickId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String principalId;
    private String appointmentFundingReasonCode;

    public BudgetConstructionReasonCodePickId() {}

    public String getPrincipalId() { return principalId; }
    public void setPrincipalId(String principalId) { this.principalId = principalId; }

    public String getAppointmentFundingReasonCode() { return appointmentFundingReasonCode; }
    public void setAppointmentFundingReasonCode(String appointmentFundingReasonCode) { this.appointmentFundingReasonCode = appointmentFundingReasonCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetConstructionReasonCodePickId that = (BudgetConstructionReasonCodePickId) o;
        return Objects.equals(principalId, that.principalId) && Objects.equals(appointmentFundingReasonCode, that.appointmentFundingReasonCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(principalId, appointmentFundingReasonCode);
    }
}
