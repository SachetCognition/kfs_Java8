package org.kuali.rice.kns.maintenance.rules;

import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

public class MaintenanceDocumentRuleBase {
    protected static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(MaintenanceDocumentRuleBase.class);
    protected static final String MAINTAINABLE_ERROR_PREFIX = "document.newMaintainableObject.";
    protected static final String MAINTAINABLE_ERROR_PATH = "document.newMaintainableObject";
    protected org.kuali.rice.krad.bo.PersistableBusinessObject oldBo;
    protected org.kuali.rice.krad.bo.PersistableBusinessObject newBo;
    
    public MaintenanceDocumentRuleBase() {}
    
    public void setupConvenienceObjects() {}
    
    protected PersistableBusinessObject getOldBo() { return oldBo; }
    protected PersistableBusinessObject getNewBo() { return newBo; }
    
    protected boolean processCustomSaveDocumentBusinessRules(MaintenanceDocument document) { return true; }
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) { return true; }
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) { return true; }
    public boolean processCustomAddCollectionLineBusinessRules(MaintenanceDocument document, String collectionName, PersistableBusinessObject line) { return true; }
    
    protected boolean isDocumentValidForSave(MaintenanceDocument document) { return true; }
    
    protected boolean dataDictionaryValidate(MaintenanceDocument document) { return true; }
    protected boolean primaryKeyCheck(MaintenanceDocument document) { return true; }
    
    protected void putFieldError(String propertyName, String errorKey) {
        org.kuali.rice.krad.util.GlobalVariables.getMessageMap().putError(propertyName, errorKey);
    }
    protected void putFieldError(String propertyName, String errorKey, String errorParam) {
        org.kuali.rice.krad.util.GlobalVariables.getMessageMap().putError(propertyName, errorKey, errorParam);
    }
    protected void putFieldError(String propertyName, String errorKey, String[] errorParams) {
        org.kuali.rice.krad.util.GlobalVariables.getMessageMap().putError(propertyName, errorKey, errorParams);
    }
    protected void putGlobalError(String errorKey) {
        org.kuali.rice.krad.util.GlobalVariables.getMessageMap().putError(org.kuali.rice.krad.util.KRADConstants.GLOBAL_ERRORS, errorKey);
    }
    protected void putGlobalError(String errorKey, String errorParam) {
        org.kuali.rice.krad.util.GlobalVariables.getMessageMap().putError(org.kuali.rice.krad.util.KRADConstants.GLOBAL_ERRORS, errorKey, errorParam);
    }
    protected void putDocumentError(String propertyName, String errorKey, String errorParam) {
        putFieldError(propertyName, errorKey, errorParam);
    }
    protected void clearErrorPath() {}
    protected void resumeErrorPath() {}
    
    protected boolean checkEmptyDocumentField(String fieldName, Object value, String errorKey) { return value != null; }
    protected boolean checkEmptyValue(String value) { return value != null && !value.trim().isEmpty(); }
    
    protected org.kuali.rice.kns.service.BusinessObjectDictionaryService getBoService() { return null; }
    protected org.kuali.rice.krad.service.BusinessObjectService getBusinessObjectService() { return null; }
    protected org.kuali.rice.kns.service.DocumentHelperService getDocumentHelperService() { return null; }
    protected boolean isCorrectMaintenanceClass(org.kuali.rice.kns.document.MaintenanceDocument doc, Class clazz) { return true; }
    protected Object getDictionaryValidationService() { return null; }
}
