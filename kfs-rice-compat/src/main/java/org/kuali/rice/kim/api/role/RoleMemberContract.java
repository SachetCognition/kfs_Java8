package org.kuali.rice.kim.api.role;
public interface RoleMemberContract {
    String getMemberId();
    String getRoleId();
    String getId();
    boolean isActive(org.joda.time.DateTime activeAsOfDate);
}
