package org.kuali.rice.krad.datadictionary;
import java.util.List;
import java.util.ArrayList;
public class DocumentEntry {
    private WorkflowAttributes workflowAttributes;
    private String documentTypeName;
    private Class documentClass;
    
    public DocumentEntry() {}
    public String getDocumentTypeName() { return documentTypeName; }
    public void setDocumentTypeName(String name) { this.documentTypeName = name; }
    public Class getDocumentClass() { return documentClass; }
    public void setDocumentClass(Class clazz) { this.documentClass = clazz; }
    public String getLabel() { return null; }
    public String getDescription() { return null; }
    public WorkflowAttributes getWorkflowAttributes() { return workflowAttributes; }
    public void setWorkflowAttributes(WorkflowAttributes wa) { this.workflowAttributes = wa; }
    public List<String> getRoutingTypeDefinitions() { return new ArrayList<String>(); }
}
