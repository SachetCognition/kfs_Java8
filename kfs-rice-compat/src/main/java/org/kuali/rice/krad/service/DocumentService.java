package org.kuali.rice.krad.service;

public interface DocumentService {
    org.kuali.rice.krad.document.Document getByDocumentHeaderId(String documentId) throws org.kuali.rice.kew.api.exception.WorkflowException;
    org.kuali.rice.krad.document.Document getNewDocument(String documentTypeName) throws org.kuali.rice.kew.api.exception.WorkflowException;
    org.kuali.rice.krad.document.Document getNewDocument(Class<? extends org.kuali.rice.krad.document.Document> documentClass) throws org.kuali.rice.kew.api.exception.WorkflowException;
    org.kuali.rice.krad.document.Document saveDocument(org.kuali.rice.krad.document.Document document) throws org.kuali.rice.kew.api.exception.WorkflowException;
    org.kuali.rice.krad.document.Document routeDocument(org.kuali.rice.krad.document.Document document, String annotation, java.util.List adHocRecipients) throws org.kuali.rice.kew.api.exception.WorkflowException;
    org.kuali.rice.krad.document.Document approveDocument(org.kuali.rice.krad.document.Document document, String annotation, java.util.List adHocRecipients) throws org.kuali.rice.kew.api.exception.WorkflowException;
    org.kuali.rice.krad.document.Document blanketApproveDocument(org.kuali.rice.krad.document.Document document, String annotation, java.util.List adHocRecipients) throws org.kuali.rice.kew.api.exception.WorkflowException;
    boolean documentExists(String documentHeaderId);
    org.kuali.rice.krad.bo.Note createNoteFromDocument(org.kuali.rice.krad.document.Document document, String text);
    org.kuali.rice.krad.document.Document saveDocument(org.kuali.rice.krad.document.Document document, String annotation) throws org.kuali.rice.kew.api.exception.WorkflowException;
    org.kuali.rice.krad.document.Document saveDocument(org.kuali.rice.krad.document.Document document, Class<? extends org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent> kualiDocumentEventClass) throws org.kuali.rice.kew.api.exception.WorkflowException;
    org.kuali.rice.krad.document.Document saveDocument(org.kuali.rice.krad.document.Document document, String annotation, java.util.List adHocRecipients) throws org.kuali.rice.kew.api.exception.WorkflowException;
    org.kuali.rice.krad.document.Document recallDocument(org.kuali.rice.krad.document.Document document) throws org.kuali.rice.kew.api.exception.WorkflowException;
    org.kuali.rice.krad.document.Document cancelDocument(org.kuali.rice.krad.document.Document document, String annotation) throws org.kuali.rice.kew.api.exception.WorkflowException;
    org.kuali.rice.krad.document.Document disapproveDocument(org.kuali.rice.krad.document.Document document, String annotation) throws org.kuali.rice.kew.api.exception.WorkflowException;
    org.kuali.rice.krad.document.Document route(org.kuali.rice.krad.document.Document document, String annotation, Object adHocRecipients) throws Exception;
    void prepareWorkflowDocument(org.kuali.rice.krad.document.Document document) throws Exception;
    void superUserApproveDocument(org.kuali.rice.krad.document.Document document, String annotation) throws Exception;
}