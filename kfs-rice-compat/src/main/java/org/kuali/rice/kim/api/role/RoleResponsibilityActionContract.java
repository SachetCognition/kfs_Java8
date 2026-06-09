package org.kuali.rice.kim.api.role;

public interface RoleResponsibilityActionContract extends org.kuali.rice.core.api.mo.common.Versioned,org.kuali.rice.core.api.mo.common.Identifiable {
    java.lang.String getRoleResponsibilityId();
    java.lang.String getActionTypeCode();
    java.lang.Integer getPriorityNumber();
    java.lang.String getActionPolicyCode();
    java.lang.String getRoleMemberId();
    org.kuali.rice.kim.api.role.RoleResponsibilityContract getRoleResponsibility();
    boolean isForceAction();
}
