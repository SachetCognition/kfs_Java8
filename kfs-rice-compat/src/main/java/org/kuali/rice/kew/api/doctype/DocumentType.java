package org.kuali.rice.kew.api.doctype;

public class DocumentType extends org.kuali.rice.core.api.mo.AbstractDataTransferObject {
    public static final String DOCUMENT_TYPE_CODE = "DTYPE";
    
    private String id;
    private String name;
    private String label;
    private String description;
    private String parentId;
    private boolean active;
    private String documentHandlerUrl;
    private String unresolvedDocHandlerUrl;
    
    public DocumentType() {}
    
    public String getId() { return id; }
    public String getName() { return name; }
    public String getLabel() { return label; }
    public String getDescription() { return description; }
    public String getParentId() { return parentId; }
    public boolean isActive() { return active; }
    public String getDocumentHandlerUrl() { return documentHandlerUrl; }
    public String getUnresolvedDocHandlerUrl() { return unresolvedDocHandlerUrl; }
    
    public static DocumentType from(org.kuali.rice.kew.doctype.bo.DocumentType dtBo) { return new DocumentType(); }
    public static DocumentType from(org.kuali.rice.kew.doctype.bo.DocumentTypeEBO dtBo) { return new DocumentType(); }
    public static DocumentType from(DocumentType dt) { return dt != null ? dt : new DocumentType(); }
}
