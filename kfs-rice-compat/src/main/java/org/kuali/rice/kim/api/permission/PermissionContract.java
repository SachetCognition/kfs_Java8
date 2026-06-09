package org.kuali.rice.kim.api.permission;

public interface PermissionContract extends org.kuali.rice.core.api.mo.common.Versioned,org.kuali.rice.core.api.mo.common.GloballyUnique,org.kuali.rice.core.api.mo.common.active.Inactivatable,org.kuali.rice.core.api.mo.common.Identifiable {
    java.lang.String getNamespaceCode();
    java.lang.String getName();
    java.lang.String getDescription();
    org.kuali.rice.kim.api.common.template.TemplateContract getTemplate();
    java.util.Map<java.lang.String, java.lang.String> getAttributes();
}
