package org.kuali.rice.krad.service;

public interface DocumentDictionaryService {
    String getDocumentTypeByClass(Class documentClass);
    Class getDocumentClassByTypeName(String documentTypeName);
    Class getMaintenanceDocumentClass(String docTypeName);
    boolean getAllowsCopy(org.kuali.rice.krad.document.Document document);
    org.kuali.rice.krad.datadictionary.DocumentEntry getDocumentEntry(String documentTypeName);
    org.kuali.rice.krad.datadictionary.DocumentEntry getDocumentEntryByClass(Class documentClass);
    Class getBusinessRulesClass(org.kuali.rice.krad.document.Document document);
}
