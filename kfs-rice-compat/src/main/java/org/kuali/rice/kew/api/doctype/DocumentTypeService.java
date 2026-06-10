package org.kuali.rice.kew.api.doctype;

public interface DocumentTypeService {
    org.kuali.rice.kew.doctype.bo.DocumentType findByName(String name);
    org.kuali.rice.kew.doctype.bo.DocumentType findById(String id);
    String getIdByName(String name);
    DocumentType getDocumentTypeByName(String name);
    String getDocumentHandlerUrl(String documentId);
    DocumentType getDocumentTypeById(String id);
}
