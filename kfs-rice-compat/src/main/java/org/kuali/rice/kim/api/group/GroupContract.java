package org.kuali.rice.kim.api.group;

public interface GroupContract extends org.kuali.rice.core.api.mo.common.Versioned,org.kuali.rice.core.api.mo.common.GloballyUnique,org.kuali.rice.core.api.mo.common.active.Inactivatable,org.kuali.rice.core.api.mo.common.Identifiable {
    java.lang.String getNamespaceCode();
    java.lang.String getName();
    java.lang.String getDescription();
    java.lang.String getKimTypeId();
    java.util.Map<java.lang.String, java.lang.String> getAttributes();
}
