package org.kuali.rice.kim.api.identity.principal;

public interface PrincipalContract extends org.kuali.rice.core.api.mo.common.active.Inactivatable,org.kuali.rice.core.api.mo.common.Versioned,org.kuali.rice.core.api.mo.common.GloballyUnique {
    java.lang.String getPrincipalId();
    java.lang.String getPrincipalName();
    java.lang.String getEntityId();
}
