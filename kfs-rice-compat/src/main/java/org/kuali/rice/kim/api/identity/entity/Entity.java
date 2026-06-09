package org.kuali.rice.kim.api.identity.entity;

import java.util.List;
import java.util.ArrayList;

public class Entity extends org.kuali.rice.core.api.mo.AbstractDataTransferObject {
    private String id;
    private List<org.kuali.rice.kim.api.identity.external.EntityExternalIdentifier> externalIdentifiers = new ArrayList<org.kuali.rice.kim.api.identity.external.EntityExternalIdentifier>();
    private List<org.kuali.rice.kim.api.identity.type.EntityTypeContactInfo> entityTypeContactInfos = new ArrayList<org.kuali.rice.kim.api.identity.type.EntityTypeContactInfo>();
    
    public static final class EntityTypes {
        public static final String PERSON = "PERSON";
        public static final String SYSTEM = "SYSTEM";
        private EntityTypes() {}
    }
    
    public Entity() {}
    public String getId() { return id; }
    public List<org.kuali.rice.kim.api.identity.external.EntityExternalIdentifier> getExternalIdentifiers() { return externalIdentifiers; }
    public List<org.kuali.rice.kim.api.identity.type.EntityTypeContactInfo> getEntityTypeContactInfos() { return entityTypeContactInfos; }
}
