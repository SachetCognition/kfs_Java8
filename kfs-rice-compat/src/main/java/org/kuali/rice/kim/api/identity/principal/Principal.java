package org.kuali.rice.kim.api.identity.principal;

public class Principal extends org.kuali.rice.core.api.mo.AbstractDataTransferObject implements PrincipalContract {
    private String principalId;
    private String principalName;
    private String entityId;
    private boolean active;
    
    public Principal() {}
    
    public String getPrincipalId() { return principalId; }
    public String getPrincipalName() { return principalName; }
    public String getEntityId() { return entityId; }
    public boolean isActive() { return active; }
    public Long getVersionNumber() { return null; }
    public String getObjectId() { return null; }
}
