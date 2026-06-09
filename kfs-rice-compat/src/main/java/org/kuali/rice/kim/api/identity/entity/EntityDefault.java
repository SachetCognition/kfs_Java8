package org.kuali.rice.kim.api.identity.entity;

public class EntityDefault extends org.kuali.rice.core.api.mo.AbstractDataTransferObject {
    public EntityDefault() {}


    public java.lang.String getEntityId() { return null; }
    public org.kuali.rice.kim.api.identity.name.EntityName getName() { return null; }
    public java.util.List<org.kuali.rice.kim.api.identity.principal.Principal> getPrincipals() { return new java.util.ArrayList(); }
    public java.util.List<org.kuali.rice.kim.api.identity.type.EntityTypeContactInfoDefault> getEntityTypeContactInfos() { return new java.util.ArrayList(); }
    public java.util.List<org.kuali.rice.kim.api.identity.affiliation.EntityAffiliation> getAffiliations() { return new java.util.ArrayList(); }
    public org.kuali.rice.kim.api.identity.affiliation.EntityAffiliation getDefaultAffiliation() { return null; }
    public org.kuali.rice.kim.api.identity.employment.EntityEmployment getEmployment() { return null; }
    public java.util.List<org.kuali.rice.kim.api.identity.external.EntityExternalIdentifier> getExternalIdentifiers() { return new java.util.ArrayList(); }
    public org.kuali.rice.kim.api.identity.privacy.EntityPrivacyPreferences getPrivacyPreferences() { return null; }
    public boolean isActive() { return false; }
    public org.kuali.rice.kim.api.identity.type.EntityTypeContactInfoDefault getEntityType(java.lang.String p0) { return null; }
}
