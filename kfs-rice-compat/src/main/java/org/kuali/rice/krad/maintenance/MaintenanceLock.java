package org.kuali.rice.krad.maintenance;
public class MaintenanceLock extends org.kuali.rice.krad.bo.PersistableBusinessObjectBase {
    private Long lockId;
    private String lockingRepresentation;
    private String documentNumber;
    public MaintenanceLock() {}
    public Long getLockId() { return lockId; }
    public void setLockId(Long lockId) { this.lockId = lockId; }
    public String getLockingRepresentation() { return lockingRepresentation; }
    public void setLockingRepresentation(String lockingRepresentation) { this.lockingRepresentation = lockingRepresentation; }
    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }
}
