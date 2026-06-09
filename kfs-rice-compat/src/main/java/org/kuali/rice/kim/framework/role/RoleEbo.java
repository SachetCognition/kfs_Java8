package org.kuali.rice.kim.framework.role;

public interface RoleEbo extends org.kuali.rice.kim.api.role.RoleContract,org.kuali.rice.krad.bo.ExternalizableBusinessObject {
    java.lang.String getNamespaceCode();
    java.lang.String getName();
    java.lang.String getDescription();
    java.lang.String getKimTypeId();
    java.lang.Long getVersionNumber();
    java.lang.String getId();
    boolean isActive();
    java.lang.String getObjectId();
}
