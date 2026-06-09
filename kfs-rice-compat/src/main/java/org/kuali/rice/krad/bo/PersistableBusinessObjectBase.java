package org.kuali.rice.krad.bo;
public abstract class PersistableBusinessObjectBase implements PersistableBusinessObject {
    public PersistableBusinessObjectBase() {}
    public void refresh() {}
    public void refreshNonUpdateableReferences() {}
    public void refreshReferenceObject(String referenceObjectName) {}
    public void refreshReferences(String referenceName) {}
    public Long getVersionNumber() { return null; }
    public void setVersionNumber(Long versionNumber) {}
    public String getObjectId() { return null; }
    public void setObjectId(String objectId) {}
    protected void preUpdate() {}
    protected void prePersist() {}
    protected void preRemove() {}
    protected void postLoad() {}
    protected void postUpdate() {}
    protected void postPersist() {}
    protected void postRemove() {}
}
