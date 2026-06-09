package org.kuali.rice.kns.document;
public interface MaintenanceDocument extends org.kuali.rice.krad.document.Document {
    org.kuali.rice.kns.maintenance.Maintainable getNewMaintainableObject();
    void setNewMaintainableObject(org.kuali.rice.kns.maintenance.Maintainable m);
    org.kuali.rice.kns.maintenance.Maintainable getOldMaintainableObject();
    void setOldMaintainableObject(org.kuali.rice.kns.maintenance.Maintainable m);
}
