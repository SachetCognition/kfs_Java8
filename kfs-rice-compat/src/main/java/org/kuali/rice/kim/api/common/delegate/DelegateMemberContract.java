package org.kuali.rice.kim.api.common.delegate;
public interface DelegateMemberContract {
    String getDelegationMemberId();
    String getDelegationId();
    String getMemberId();
    String getRoleMemberId();
    org.kuali.rice.core.api.membership.MemberType getType();
    org.joda.time.DateTime getActiveFromDate();
    org.joda.time.DateTime getActiveToDate();
    java.util.Map<String, String> getAttributes();
    Long getVersionNumber();
    boolean isActive(org.joda.time.DateTime activeAsOfDate);
    boolean isActive();
}
