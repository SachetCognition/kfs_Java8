package org.kuali.rice.krad.rules.rule.event;
public class ApproveDocumentEvent extends KualiDocumentEventBase {
    public ApproveDocumentEvent(org.kuali.rice.krad.document.Document document) { super("", "", document); }
    public ApproveDocumentEvent(String errorPathPrefix, org.kuali.rice.krad.document.Document document) { super("", errorPathPrefix, document); }
}
