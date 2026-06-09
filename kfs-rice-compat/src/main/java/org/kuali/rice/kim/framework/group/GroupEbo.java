package org.kuali.rice.kim.framework.group;

public interface GroupEbo extends org.kuali.rice.kim.api.group.GroupContract,org.kuali.rice.krad.bo.ExternalizableBusinessObject {
    java.lang.String getNamespaceCode();
    java.lang.String getName();
    java.lang.String getDescription();
    java.lang.String getKimTypeId();
    java.util.Map<java.lang.String, java.lang.String> getAttributes();
    java.lang.String getId();
    java.lang.Long getVersionNumber();
    java.lang.String getObjectId();
    boolean isActive();
}
