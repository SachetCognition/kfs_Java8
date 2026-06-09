package org.kuali.rice.krad.document;
public interface MaintenanceDocument extends Document {
    String getDocumentNumber();
    boolean isNew();
    boolean isEdit();
    boolean isCopy();
    boolean isFieldsClearedOnCopy();
    org.kuali.rice.krad.maintenance.Maintainable getOldMaintainableObject();
    org.kuali.rice.krad.maintenance.Maintainable getNewMaintainableObject();
    void setOldMaintainableObject(org.kuali.rice.krad.maintenance.Maintainable oldMaintainableObject);
    void setNewMaintainableObject(org.kuali.rice.krad.maintenance.Maintainable newMaintainableObject);
}
