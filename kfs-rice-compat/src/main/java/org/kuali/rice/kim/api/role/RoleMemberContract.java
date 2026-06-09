package org.kuali.rice.kim.api.role;

public interface RoleMemberContract extends org.kuali.rice.core.api.mo.common.Versioned,org.kuali.rice.core.api.mo.common.GloballyUnique,org.kuali.rice.core.api.mo.common.active.InactivatableFromTo,org.kuali.rice.core.api.mo.common.Identifiable {
    java.lang.String getMemberId();
    org.kuali.rice.core.api.membership.MemberType getType();
    java.lang.String getRoleId();
    java.util.Map<java.lang.String, java.lang.String> getAttributes();
    java.util.List<? extends org.kuali.rice.kim.api.role.RoleResponsibilityActionContract> getRoleRspActions();
    java.lang.String getMemberName();
    java.lang.String getMemberNamespaceCode();
}
