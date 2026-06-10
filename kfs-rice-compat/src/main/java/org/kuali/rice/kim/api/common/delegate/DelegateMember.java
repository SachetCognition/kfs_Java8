package org.kuali.rice.kim.api.common.delegate;
public class DelegateMember extends org.kuali.rice.core.api.mo.AbstractDataTransferObject implements DelegateMemberContract {
    private String delegationMemberId;
    private String delegationId;
    private String memberId;
    private String roleMemberId;
    private org.kuali.rice.core.api.membership.MemberType type;
    private org.joda.time.DateTime activeFromDate;
    private org.joda.time.DateTime activeToDate;
    private java.util.Map<String, String> attributes;
    private Long versionNumber;

    public String getDelegationMemberId() { return delegationMemberId; }
    public String getDelegationId() { return delegationId; }
    public String getMemberId() { return memberId; }
    public String getRoleMemberId() { return roleMemberId; }
    public org.kuali.rice.core.api.membership.MemberType getType() { return type; }
    public org.joda.time.DateTime getActiveFromDate() { return activeFromDate; }
    public org.joda.time.DateTime getActiveToDate() { return activeToDate; }
    public java.util.Map<String, String> getAttributes() { return attributes != null ? attributes : new java.util.HashMap<>(); }
    public Long getVersionNumber() { return versionNumber; }
    public boolean isActive(org.joda.time.DateTime activeAsOfDate) { return true; }
    public boolean isActive() { return true; }

    public static class Builder implements DelegateMemberContract {
        private String delegationMemberId;
        private String delegationId;
        private String memberId;
        private String roleMemberId;
        private org.kuali.rice.core.api.membership.MemberType type;
        private org.joda.time.DateTime activeFromDate;
        private org.joda.time.DateTime activeToDate;
        private java.util.Map<String, String> attributes;
        private Long versionNumber;

        public static Builder create() { return new Builder(); }
        public static Builder create(DelegateMemberContract contract) {
            Builder b = new Builder();
            if (contract != null) {
                b.delegationMemberId = contract.getDelegationMemberId();
                b.delegationId = contract.getDelegationId();
                b.memberId = contract.getMemberId();
                b.roleMemberId = contract.getRoleMemberId();
                b.type = contract.getType();
                b.activeFromDate = contract.getActiveFromDate();
                b.activeToDate = contract.getActiveToDate();
                b.attributes = contract.getAttributes();
                b.versionNumber = contract.getVersionNumber();
            }
            return b;
        }

        public DelegateMember build() { return new DelegateMember(); }

        public String getDelegationMemberId() { return delegationMemberId; }
        public void setDelegationMemberId(String v) { this.delegationMemberId = v; }
        public String getDelegationId() { return delegationId; }
        public void setDelegationId(String v) { this.delegationId = v; }
        public String getMemberId() { return memberId; }
        public void setMemberId(String v) { this.memberId = v; }
        public String getRoleMemberId() { return roleMemberId; }
        public void setRoleMemberId(String v) { this.roleMemberId = v; }
        public org.kuali.rice.core.api.membership.MemberType getType() { return type; }
        public void setType(org.kuali.rice.core.api.membership.MemberType v) { this.type = v; }
        public org.joda.time.DateTime getActiveFromDate() { return activeFromDate; }
        public void setActiveFromDate(org.joda.time.DateTime v) { this.activeFromDate = v; }
        public org.joda.time.DateTime getActiveToDate() { return activeToDate; }
        public void setActiveToDate(org.joda.time.DateTime v) { this.activeToDate = v; }
        public java.util.Map<String, String> getAttributes() { return attributes; }
        public void setAttributes(java.util.Map<String, String> v) { this.attributes = v; }
        public Long getVersionNumber() { return versionNumber; }
        public void setVersionNumber(Long v) { this.versionNumber = v; }
        public boolean isActive(org.joda.time.DateTime activeAsOfDate) { return true; }
        public boolean isActive() { return true; }
    }
}
