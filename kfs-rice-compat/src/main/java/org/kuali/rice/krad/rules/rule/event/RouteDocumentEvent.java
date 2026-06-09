package org.kuali.rice.krad.rules.rule.event;
public class RouteDocumentEvent extends KualiDocumentEventBase {
    public RouteDocumentEvent(org.kuali.rice.krad.document.Document document) { super("", "", document); }
    public RouteDocumentEvent(String errorPathPrefix, org.kuali.rice.krad.document.Document document) { super("", errorPathPrefix, document); }
}
