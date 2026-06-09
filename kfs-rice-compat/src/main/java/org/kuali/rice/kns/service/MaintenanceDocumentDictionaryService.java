package org.kuali.rice.kns.service;

import java.util.List;

public interface MaintenanceDocumentDictionaryService {
    String getDocumentTypeName(Class businessObjectClass);
    Class getBusinessObjectClass(String docTypeName);
    String getMaintainableClass(String docTypeName);
    List getDefaultExistenceChecks(Class businessObjectClass);
    List getDefaultExistenceChecks(String docTypeName);
    boolean getPreserveLockingKeysOnCopy(Class businessObjectClass);
    boolean getAllowsCopy(Class businessObjectClass);
    boolean getAllowsNewOrCopy(String docTypeName);
    boolean getAllowsRecordDeletion(Class businessObjectClass);
    boolean getAllowsRecordDeletion(Class maintenanceDocumentClass, String collectionName);
}
