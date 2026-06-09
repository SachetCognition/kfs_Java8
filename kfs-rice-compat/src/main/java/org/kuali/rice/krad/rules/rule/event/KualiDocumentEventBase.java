package org.kuali.rice.krad.rules.rule.event;
public abstract class KualiDocumentEventBase implements KualiDocumentEvent {
    private org.kuali.rice.krad.document.Document document;
    private String errorPathPrefix;
    private String description;
    
    public KualiDocumentEventBase() {}
    public KualiDocumentEventBase(String errorPathPrefix) { this.errorPathPrefix = errorPathPrefix; }
    public KualiDocumentEventBase(String description, String errorPathPrefix) { this.description = description; this.errorPathPrefix = errorPathPrefix; }
    public KualiDocumentEventBase(String description, String errorPathPrefix, org.kuali.rice.krad.document.Document document) {
        this.description = description;
        this.errorPathPrefix = errorPathPrefix;
        this.document = document;
    }
    public org.kuali.rice.krad.document.Document getDocument() { return document; }
    public void setDocument(org.kuali.rice.krad.document.Document document) { this.document = document; }
    public String getErrorPathPrefix() { return errorPathPrefix; }
    public java.util.List getRules() { return new java.util.ArrayList(); }
}
