package org.kuali.rice.kim.api.role;

public interface RoleContract extends org.kuali.rice.core.api.mo.common.Versioned,org.kuali.rice.core.api.mo.common.Identifiable,org.kuali.rice.core.api.mo.common.active.Inactivatable,org.kuali.rice.core.api.mo.common.GloballyUnique {
    java.lang.String getNamespaceCode();
    java.lang.String getName();
    java.lang.String getDescription();
    java.lang.String getKimTypeId();
}
