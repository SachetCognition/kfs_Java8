package org.kuali.rice.krad.bo;

import java.util.List;

public interface GlobalBusinessObject {
    String getDocumentNumber();
    void setDocumentNumber(String documentNumber);
    List<? extends GlobalBusinessObjectDetail> getAllDetailObjects();
    boolean isPersistable();
    List<PersistableBusinessObject> generateDeactivationsToPersist();
    List<PersistableBusinessObject> generateGlobalChangesToPersist();
}
