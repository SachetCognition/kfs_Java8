package org.kuali.rice.krad.rules.rule.event;
public class SaveDocumentEvent extends KualiDocumentEventBase {
    public SaveDocumentEvent(org.kuali.rice.krad.document.Document document) { super("", "", document); }
    public SaveDocumentEvent(String errorPathPrefix, org.kuali.rice.krad.document.Document document) { super("", errorPathPrefix, document); }
}
