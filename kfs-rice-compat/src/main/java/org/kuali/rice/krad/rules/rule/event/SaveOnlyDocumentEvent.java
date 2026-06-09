package org.kuali.rice.krad.rules.rule.event;
public class SaveOnlyDocumentEvent extends SaveDocumentEvent {
    public SaveOnlyDocumentEvent(org.kuali.rice.krad.document.Document document) { super(document); }
    public SaveOnlyDocumentEvent(String errorPathPrefix, org.kuali.rice.krad.document.Document document) { super(errorPathPrefix, document); }
}
