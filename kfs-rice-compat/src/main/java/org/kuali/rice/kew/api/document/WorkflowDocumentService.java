package org.kuali.rice.kew.api.document;
public interface WorkflowDocumentService {
    Document getDocument(String documentId);
    String getDocumentHandlerUrl(String documentId);
}
