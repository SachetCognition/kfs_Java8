package org.kuali.rice.krad.bo;
public class DocumentHeader extends PersistableBusinessObjectBase {
    private String documentNumber;
    private String documentDescription;
    private String documentTemplateNumber;
    private String explanation;
    private String organizationDocumentNumber;
    private transient org.kuali.rice.kew.api.WorkflowDocument workflowDocument;
    
    public DocumentHeader() {}
    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }
    public String getDocumentDescription() { return documentDescription; }
    public void setDocumentDescription(String documentDescription) { this.documentDescription = documentDescription; }
    public String getDocumentTemplateNumber() { return documentTemplateNumber; }
    public void setDocumentTemplateNumber(String documentTemplateNumber) { this.documentTemplateNumber = documentTemplateNumber; }
    public String getExplanation() { return explanation; }
    public void setExplanation(String explanation) { this.explanation = explanation; }
    public String getOrganizationDocumentNumber() { return organizationDocumentNumber; }
    public void setOrganizationDocumentNumber(String organizationDocumentNumber) { this.organizationDocumentNumber = organizationDocumentNumber; }
    public org.kuali.rice.kew.api.WorkflowDocument getWorkflowDocument() { return workflowDocument; }
    public void setWorkflowDocument(org.kuali.rice.kew.api.WorkflowDocument workflowDocument) { this.workflowDocument = workflowDocument; }
    public boolean hasWorkflowDocument() { return workflowDocument != null; }
}
