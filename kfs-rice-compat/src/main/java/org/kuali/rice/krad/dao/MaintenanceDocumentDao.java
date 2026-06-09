package org.kuali.rice.krad.dao;

public interface MaintenanceDocumentDao {
    java.lang.String getLockingDocumentNumber(java.lang.String p0, java.lang.String p1);
    void deleteLocks(java.lang.String p0);
    void storeLocks(java.util.List<org.kuali.rice.krad.maintenance.MaintenanceLock> p0);
}
