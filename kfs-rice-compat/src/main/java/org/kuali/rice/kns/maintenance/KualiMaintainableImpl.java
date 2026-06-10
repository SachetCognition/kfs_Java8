package org.kuali.rice.kns.maintenance;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
public class KualiMaintainableImpl implements Maintainable {
    protected Object businessObject;
    private Object dataObject;
    private String maintenanceAction;
    private String documentNumber;
    private Class businessObjectClass;
    private Class dataObjectClass;
    protected Map<String, PersistableBusinessObject> newCollectionLines = new HashMap<>();

    public KualiMaintainableImpl() {}
    public KualiMaintainableImpl(Object businessObject) { this.businessObject = businessObject; }
    
    public boolean isNotesEnabled() { return false; }
    public List<String> getWorkflowEngineDocumentIdsToLock() { return new ArrayList<String>(); }
    
    public PersistableBusinessObject getBusinessObject() { return (PersistableBusinessObject) businessObject; }
    public void setBusinessObject(Object bo) { this.businessObject = bo; }
    public Object getDataObject() { return dataObject; }
    public void setDataObject(Object obj) { this.dataObject = obj; }
    
    public String getMaintenanceAction() { return maintenanceAction; }
    public void setMaintenanceAction(String action) { this.maintenanceAction = action; }
    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public Class getBusinessObjectClass() { return businessObjectClass; }
    public void setBusinessObjectClass(Class clazz) { this.businessObjectClass = clazz; }
    public Class getBoClass() { return businessObjectClass; }
    public Class getDataObjectClass() { return dataObjectClass; }
    public void setDataObjectClass(Class clazz) { this.dataObjectClass = clazz; }

    public void setGenerateDefaultValues(String docTypeName) {}
    public void processAfterCopy(org.kuali.rice.krad.document.Document document, Map<String, String[]> requestParameters) {}
    public void processAfterEdit(org.kuali.rice.krad.document.Document document, Map<String, String[]> requestParameters) {}
    public void processAfterNew(org.kuali.rice.krad.document.Document document, Map<String, String[]> requestParameters) {}
    public void processAfterPost(org.kuali.rice.krad.document.Document document, Map<String, String[]> requestParameters) {}
    public String getDocumentTitle(org.kuali.rice.krad.document.Document document) { return null; }
    public void doRouteStatusChange(org.kuali.rice.krad.bo.DocumentHeader documentHeader) {}
    public List getSections(org.kuali.rice.krad.document.Document document, Maintainable oldMaintainable) { return new ArrayList(); }
    public void prepareForSave() {}
    public void saveBusinessObject() {}
    public void saveDataObject() {}
    public void deleteDataObject() {}
    public boolean isOldDataObjectInDocument() { return false; }
    public void prepareBusinessObject(org.kuali.rice.krad.bo.BusinessObject bo) {}
    public boolean isLockable() { return false; }
    public String getLockingDocumentId() { return null; }
    
    public Object retrieveObjectForEditOrCopy(org.kuali.rice.krad.document.Document document, Map fieldValues) { return null; }
    public boolean isExternalBusinessObject() { return false; }
    public void prepareExternalBusinessObject(org.kuali.rice.krad.bo.BusinessObject bo) {}
    public void setupNewFromExisting(org.kuali.rice.krad.document.Document document, Map<String, String[]> requestParameters) {}
    public boolean hasCustomHtmlErrorPage() { return false; }
    public String getCustomHtmlErrorPage() { return null; }
    public void processBeforeAddLine(String colName, Class colClass, org.kuali.rice.krad.bo.BusinessObject bo) {}
    public void refresh(String refreshCaller, Map fieldValues, org.kuali.rice.kns.document.MaintenanceDocument document) {}
    
    protected void refreshReferences(String referencesToRefresh) {}
    public PersistableBusinessObject getNewCollectionLine(String collectionName) { return null; }

    public Object getNewBo() { return businessObject; }
    public void setNewBo(Object bo) { this.businessObject = bo; }
    public void performForceUppercase(Object bo) {}
    protected void prepareGlobalsForSave() {}
    public List<org.kuali.rice.krad.maintenance.MaintenanceLock> generateMaintenanceLocks() { return new ArrayList<>(); }
    public Class<? extends PersistableBusinessObject> getPrimaryEditedBusinessObjectClass() { return null; }
    public org.kuali.rice.kns.service.BusinessObjectAuthorizationService getBusinessObjectAuthorizationService() { return null; }
    public boolean isHiddenSectionId(String sectionId) { return false; }
    public void setNewCollectionRecord(boolean b) {}
    public void setBoClass(Class clazz) {}
    public boolean attributeValueNeedsToBeEncryptedOnFormsAndLinks(Class clazz, String attr) { return false; }
    public java.util.List getCoreSections(org.kuali.rice.kns.document.MaintenanceDocument document, org.kuali.rice.kns.maintenance.Maintainable maintainable) { return new java.util.ArrayList(); }
    public java.util.List<String> getDuplicateIdentifierFieldsFromDataDictionary(String docTypeName, String collectionName) { return new java.util.ArrayList<>(); }
    public java.util.List<String> getMultiValueIdentifierList(java.util.Collection collection, java.util.List<String> identifierFields) { return new java.util.ArrayList<>(); }
    public Object getMaintenanceDocumentDictionaryService() { return null; }
    public Object createHybridBusinessObject(Class clazz, org.kuali.rice.krad.bo.PersistableBusinessObject existingBo, java.util.Map fieldValues) { return null; }
    public void prepareBusinessObjectForAdditionFromMultipleValueLookup(String collectionName, org.kuali.rice.krad.bo.BusinessObject bo) {}
    public boolean hasBusinessObjectExisted(org.kuali.rice.krad.bo.BusinessObject bo, java.util.List<String> existingBos) { return false; }
    public org.kuali.rice.krad.bo.PersistableBusinessObject newInstance(String className) { return null; }
    public Class getCollectionBusinessObjectClass(String collectionName) { return null; }
    public java.util.List getMaintainableSections() { return new java.util.ArrayList(); }
    public void setLookupObjectId(String id) {}
    public void processAfterPost(org.kuali.rice.kns.document.MaintenanceDocument document, java.util.Map<String, String[]> parameters) {}
    public boolean hasBusinessObjectExisted(org.kuali.rice.krad.bo.PersistableBusinessObject bo, java.util.List<String> existingBos) { return false; }
    public java.util.List<org.kuali.rice.kns.web.ui.Section> getSections(org.kuali.rice.kns.document.MaintenanceDocument document, Maintainable oldMaintainable) { return new java.util.ArrayList<>(); }
}
