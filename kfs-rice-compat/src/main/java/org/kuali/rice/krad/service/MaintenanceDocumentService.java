package org.kuali.rice.krad.service;

public interface MaintenanceDocumentService {
    void setupMaintenanceObject(org.kuali.rice.kns.document.MaintenanceDocument document, String maintenanceAction, java.util.Map<String, String[]> requestParameters);
    org.kuali.rice.kns.maintenance.Maintainable setupNewMaintainability(String objectClassName);
    void deleteLocks(String documentNumber);
    void storeLocks(java.util.List maintenanceLocks);
    String getLockingDocumentId(org.kuali.rice.kns.document.MaintenanceDocument document);
    String getLockingDocumentId(org.kuali.rice.kns.maintenance.Maintainable maintainable, String documentNumber);
}
