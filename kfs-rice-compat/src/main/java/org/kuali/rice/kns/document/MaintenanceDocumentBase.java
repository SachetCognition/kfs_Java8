package org.kuali.rice.kns.document;
public class MaintenanceDocumentBase extends org.kuali.rice.krad.document.DocumentBase implements MaintenanceDocument {
    private org.kuali.rice.kns.maintenance.Maintainable newMaintainableObject;
    private org.kuali.rice.kns.maintenance.Maintainable oldMaintainableObject;
    
    public MaintenanceDocumentBase() {}
    public MaintenanceDocumentBase(String documentTypeName) {}
    public org.kuali.rice.kns.maintenance.Maintainable getNewMaintainableObject() { return newMaintainableObject; }
    public void setNewMaintainableObject(org.kuali.rice.kns.maintenance.Maintainable m) { this.newMaintainableObject = m; }
    public org.kuali.rice.kns.maintenance.Maintainable getOldMaintainableObject() { return oldMaintainableObject; }
    public void setOldMaintainableObject(org.kuali.rice.kns.maintenance.Maintainable m) { this.oldMaintainableObject = m; }
    public void refreshNonUpdateableReferences() {}
    public boolean isEdit() { return false; }
    public java.util.List getAdHocRoutePersons() { return new java.util.ArrayList(); }
    public java.util.List getAdHocRouteWorkgroups() { return new java.util.ArrayList(); }
}
