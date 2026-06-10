package org.kuali.rice.kns.maintenance;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
public interface Maintainable extends org.kuali.rice.krad.maintenance.Maintainable {
    boolean isNotesEnabled();
    PersistableBusinessObject getBusinessObject();
    void setBusinessObject(Object bo);
    Class getBoClass();
    java.util.List<org.kuali.rice.krad.maintenance.MaintenanceLock> generateMaintenanceLocks();
    void setBoClass(Class boClass);
    void setDocumentNumber(String documentNumber);
    Class getCollectionBusinessObjectClass(String collectionName);
    java.util.List getMaintainableSections();
    void setLookupObjectId(String lookupObjectId);
    void saveBusinessObject();
    org.kuali.rice.krad.bo.PersistableBusinessObject newInstance(String className);
}