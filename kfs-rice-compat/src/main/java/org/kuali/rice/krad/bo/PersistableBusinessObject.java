package org.kuali.rice.krad.bo;
import java.util.List;
import java.util.Collection;
public interface PersistableBusinessObject extends BusinessObject, org.kuali.rice.core.api.mo.common.GloballyUnique {
    Long getVersionNumber();
    void setVersionNumber(Long versionNumber);
    String getObjectId();
    void setObjectId(String objectId);
    default void refreshNonUpdateableReferences() {}
    default void refreshReferenceObject(String referenceObjectName) {}
    default void refresh() {}
    default List<Collection<PersistableBusinessObject>> buildListOfDeletionAwareLists() { return new java.util.ArrayList<>(); }
    default PersistableBusinessObject getExtension() { return null; }
    default void setExtension(Object extension) {}
    default boolean isNewCollectionRecord() { return false; }
    default void setNewCollectionRecord(boolean flag) {}
}