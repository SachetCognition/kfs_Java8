package org.kuali.rice.krad.workflow.service;
public interface WorkflowDocumentService {
    org.kuali.rice.kew.api.WorkflowDocument createWorkflowDocument(String documentTypeName, org.kuali.rice.krad.UserSession user);
    org.kuali.rice.kew.api.WorkflowDocument loadWorkflowDocument(String documentId, org.kuali.rice.krad.UserSession user);
    org.kuali.rice.kew.api.WorkflowDocument loadWorkflowDocument(String documentId, org.kuali.rice.kim.api.identity.Person person);
    void saveRoutingData(org.kuali.rice.kew.api.WorkflowDocument workflowDocument);
    void save(org.kuali.rice.kew.api.WorkflowDocument workflowDocument, String annotation);
}
