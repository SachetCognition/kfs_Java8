package org.kuali.rice.kim.api.identity;

import java.util.List;

public interface IdentityService {
    org.kuali.rice.kim.api.identity.principal.Principal getPrincipal(String principalId);
    org.kuali.rice.kim.api.identity.principal.Principal getPrincipalByPrincipalName(String principalName);
    org.kuali.rice.kim.api.identity.entity.Entity getEntity(String entityId);
    org.kuali.rice.kim.api.identity.entity.Entity getEntityByPrincipalId(String principalId);
    org.kuali.rice.kim.api.identity.entity.Entity getEntityByPrincipalName(String principalName);
    org.kuali.rice.kim.api.identity.type.EntityTypeContactInfo getEntityTypeContactInfo(String entityId, String entityTypeCode);
    List<org.kuali.rice.kim.api.identity.address.EntityAddress> getEntityAddresses(String entityId);
    List<org.kuali.rice.kim.api.identity.principal.Principal> getPrincipalsByEmployeeId(String employeeId);
}
