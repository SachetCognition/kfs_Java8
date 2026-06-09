package org.kuali.rice.krad.bo;
public interface PersistableBusinessObject extends BusinessObject, org.kuali.rice.core.api.mo.common.GloballyUnique {
    Long getVersionNumber();
    void setVersionNumber(Long versionNumber);
    String getObjectId();
    void setObjectId(String objectId);
}
