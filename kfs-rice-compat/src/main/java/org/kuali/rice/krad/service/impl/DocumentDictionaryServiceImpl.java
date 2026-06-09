package org.kuali.rice.krad.service.impl;

import org.kuali.rice.krad.service.DocumentDictionaryService;

public class DocumentDictionaryServiceImpl implements DocumentDictionaryService {
    public DocumentDictionaryServiceImpl() {}
    
    public String getDocumentTypeByClass(Class documentClass) { return null; }
    public Class getDocumentClassByTypeName(String documentTypeName) { return null; }
    public Class getMaintenanceDocumentClass(String docTypeName) { return null; }
    public boolean getAllowsCopy(org.kuali.rice.krad.document.Document document) { return false; }
    public org.kuali.rice.krad.datadictionary.DocumentEntry getDocumentEntry(String documentTypeName) { return null; }
    public org.kuali.rice.krad.datadictionary.DocumentEntry getDocumentEntryByClass(Class documentClass) { return null; }
    public Class getBusinessRulesClass(org.kuali.rice.krad.document.Document document) { return null; }
}
