package org.kuali.rice.kim.impl.common.delegate;
public class DelegateMemberBo extends org.kuali.rice.krad.bo.PersistableBusinessObjectBase
        implements org.kuali.rice.kim.api.common.delegate.DelegateMemberContract {
    public DelegateMemberBo() {}
    public String getDelegationMemberId() { return null; }
    public String getDelegationId() { return null; }
    public String getMemberId() { return null; }
    public String getRoleMemberId() { return null; }
    public org.kuali.rice.core.api.membership.MemberType getType() { return null; }
    public java.util.Map<String, String> getQualifier() { return new java.util.HashMap<>(); }
    public static org.kuali.rice.kim.api.common.delegate.DelegateMember to(DelegateMemberBo dmb) { return new org.kuali.rice.kim.api.common.delegate.DelegateMember(); }
    public boolean isActive(org.joda.time.DateTime activeAsOfDate) { return true; }
    public boolean isActive() { return true; }
    public org.joda.time.DateTime getActiveFromDate() { return null; }
    public org.joda.time.DateTime getActiveToDate() { return null; }
    public java.util.Map<String, String> getAttributes() { return new java.util.HashMap<>(); }
    public Long getVersionNumber() { return null; }
}