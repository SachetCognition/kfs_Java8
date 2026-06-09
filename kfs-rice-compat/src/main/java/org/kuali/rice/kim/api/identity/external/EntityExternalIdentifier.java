package org.kuali.rice.kim.api.identity.external;

public class EntityExternalIdentifier extends org.kuali.rice.core.api.mo.AbstractDataTransferObject {
    private String id;
    private String entityId;
    private String externalIdentifierTypeCode;
    private String externalId;
    
    public EntityExternalIdentifier() {}
    
    public String getId() { return id; }
    public String getEntityId() { return entityId; }
    public String getExternalIdentifierTypeCode() { return externalIdentifierTypeCode; }
    public String getExternalId() { return externalId; }
}
