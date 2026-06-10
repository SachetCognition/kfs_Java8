package org.kuali.rice.kns.rules;
public class DocumentRuleBase {
    public DocumentRuleBase() {}
    protected boolean processCustomAddAdHocRoutePersonBusinessRules(org.kuali.rice.krad.document.Document document, org.kuali.rice.krad.bo.AdHocRoutePerson person) { return true; }
    protected boolean processCustomAddAdHocRouteWorkgroupBusinessRules(org.kuali.rice.krad.document.Document document, org.kuali.rice.krad.bo.AdHocRouteWorkgroup workgroup) { return true; }
    protected boolean processCustomAddNoteBusinessRules(org.kuali.rice.krad.document.Document document, org.kuali.rice.krad.bo.Note note) { return true; }
    protected boolean processCustomApproveDocumentBusinessRules(org.kuali.rice.krad.document.Document document) { return true; }
    protected boolean processCustomRouteDocumentBusinessRules(org.kuali.rice.krad.document.Document document) { return true; }
    protected boolean processCustomSaveDocumentBusinessRules(org.kuali.rice.krad.document.Document document) { return true; }
    protected boolean processCustomCompleteDocumentBusinessRules(org.kuali.rice.krad.document.Document document) { return true; }
    protected boolean isDocumentAttributesValid(org.kuali.rice.krad.document.Document document, boolean validateRequired) { return true; }
    protected org.kuali.rice.kns.service.DocumentHelperService getDocumentHelperService() { return null; }
    protected boolean processCustomApproveDocumentBusinessRules(org.kuali.rice.krad.rules.rule.event.ApproveDocumentEvent event) { return true; }
}
