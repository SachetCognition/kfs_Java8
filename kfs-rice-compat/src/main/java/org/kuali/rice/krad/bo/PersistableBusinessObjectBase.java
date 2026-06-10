package org.kuali.rice.krad.bo;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
public abstract class PersistableBusinessObjectBase implements PersistableBusinessObject {
    protected Long versionNumber;
    protected String objectId;
    public PersistableBusinessObjectBase() {}
    public void refresh() {}
    public void refreshNonUpdateableReferences() {}
    public void refreshReferenceObject(String referenceObjectName) {}
    public void refreshReferences(String referenceName) {}
    public Long getVersionNumber() { return null; }
    public void setVersionNumber(Long versionNumber) {}
    public String getObjectId() { return null; }
    public void setObjectId(String objectId) {}
    public List<Collection<PersistableBusinessObject>> buildListOfDeletionAwareLists() { return new ArrayList<>(); }
    protected void preUpdate() {}
    protected void prePersist() {}
    protected void preRemove() {}
    protected void postLoad() {}
    protected void postUpdate() {}
    protected void postPersist() {}
    protected void postRemove() {}
    public void linkEditableUserFields() {}
}
