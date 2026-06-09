package org.kuali.rice.krad.rules.rule.event;
public class BlanketApproveDocumentEvent extends ApproveDocumentEvent {
    public BlanketApproveDocumentEvent(org.kuali.rice.krad.document.Document document) { super(document); }
    public BlanketApproveDocumentEvent(String errorPathPrefix, org.kuali.rice.krad.document.Document document) { super(errorPathPrefix, document); }
}
