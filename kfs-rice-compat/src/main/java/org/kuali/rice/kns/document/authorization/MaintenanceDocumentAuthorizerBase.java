package org.kuali.rice.kns.document.authorization;

public class MaintenanceDocumentAuthorizerBase extends org.kuali.rice.kns.document.authorization.DocumentAuthorizerBase implements org.kuali.rice.kns.document.authorization.MaintenanceDocumentAuthorizer {
    public MaintenanceDocumentAuthorizerBase() {}


    public boolean canCreate(java.lang.Class p0, org.kuali.rice.kim.api.identity.Person p1) { return false; }
    public boolean canMaintain(java.lang.Object p0, org.kuali.rice.kim.api.identity.Person p1) { return false; }
    public boolean canCreateOrMaintain(org.kuali.rice.krad.maintenance.MaintenanceDocument p0, org.kuali.rice.kim.api.identity.Person p1) { return false; }
    public java.util.Set<java.lang.String> getSecurePotentiallyHiddenSectionIds() { return new java.util.HashSet(); }
    public java.util.Set<java.lang.String> getSecurePotentiallyReadOnlySectionIds() { return new java.util.HashSet(); }
}
