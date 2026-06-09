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
}
