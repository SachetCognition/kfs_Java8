package org.kuali.rice.kew.api.document.search;
public class DocumentSearchCriteria extends org.kuali.rice.core.api.mo.AbstractDataTransferObject {
    public DocumentSearchCriteria() {}
    public String getDocumentId() { return null; }
    public String getDocumentTypeName() { return null; }
    public String getInitiatorPrincipalName() { return null; }
    public String getTitle() { return null; }
    
    public static class Builder {
        public Builder() {}
        public static Builder create() { return new Builder(); }
        public Builder setDocumentTypeName(String name) { return this; }
        public Builder setDocumentId(String id) { return this; }
        public DocumentSearchCriteria build() { return new DocumentSearchCriteria(); }
    }
}
