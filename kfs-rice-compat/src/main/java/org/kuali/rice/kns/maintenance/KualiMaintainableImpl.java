package org.kuali.rice.kns.maintenance;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
public class KualiMaintainableImpl implements Maintainable {
    private Object businessObject;
    private Object dataObject;
    private String maintenanceAction;
    private String documentNumber;
    private Class businessObjectClass;
    private Class dataObjectClass;

    public KualiMaintainableImpl() {}
    public KualiMaintainableImpl(Object businessObject) { this.businessObject = businessObject; }
    
    public boolean isNotesEnabled() { return false; }
    public List<String> getWorkflowEngineDocumentIdsToLock() { return new ArrayList<String>(); }
    
    public Object getBusinessObject() { return businessObject; }
    public void setBusinessObject(Object bo) { this.businessObject = bo; }
    public Object getDataObject() { return dataObject; }
    public void setDataObject(Object obj) { this.dataObject = obj; }
    
    public String getMaintenanceAction() { return maintenanceAction; }
    public void setMaintenanceAction(String action) { this.maintenanceAction = action; }
    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public Class getBusinessObjectClass() { return businessObjectClass; }
    public void setBusinessObjectClass(Class clazz) { this.businessObjectClass = clazz; }
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
    
    public Object getNewBo() { return businessObject; }
    public void setNewBo(Object bo) { this.businessObject = bo; }
    public void performForceUppercase(Object bo) {}
}
