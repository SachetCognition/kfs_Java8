package org.kuali.rice.kew.doctype.bo;
public class DocumentType extends org.kuali.rice.krad.bo.PersistableBusinessObjectBase implements DocumentTypeEBO {
    private String name;
    private String label;
    private String description;
    private String docTypeParentId;
    private Long documentTypeId;
    private String applicationId;
    private String helpDefinitionUrl;

    public DocumentType() {}
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDocTypeParentId() { return docTypeParentId; }
    public Long getDocumentTypeIdLong() { return documentTypeId; }
    public String getDocumentTypeId() { return documentTypeId != null ? documentTypeId.toString() : null; }
    public String getApplicationId() { return applicationId; }
    public String getHelpDefinitionUrl() { return helpDefinitionUrl; }
    public boolean isActive() { return true; }
    public static DocumentType from(org.kuali.rice.kew.api.doctype.DocumentType apiDocType) { return new DocumentType(); }
}
