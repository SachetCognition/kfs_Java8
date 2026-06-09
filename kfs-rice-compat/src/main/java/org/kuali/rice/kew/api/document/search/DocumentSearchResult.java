package org.kuali.rice.kew.api.document.search;
public class DocumentSearchResult extends org.kuali.rice.core.api.mo.AbstractDataTransferObject {
    public DocumentSearchResult() {}
    public org.kuali.rice.kew.api.document.Document getDocument() { return null; }
    
    public static class Builder {
        public static Builder create() { return new Builder(); }
        public DocumentSearchResult build() { return new DocumentSearchResult(); }
    }
}
