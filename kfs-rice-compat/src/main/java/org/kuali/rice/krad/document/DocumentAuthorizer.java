package org.kuali.rice.krad.document;

public interface DocumentAuthorizer extends org.kuali.rice.krad.bo.DataObjectAuthorizer {
    boolean canInitiate(java.lang.String p0, org.kuali.rice.kim.api.identity.Person p1);
    boolean canOpen(org.kuali.rice.krad.document.Document p0, org.kuali.rice.kim.api.identity.Person p1);
    boolean canEdit(org.kuali.rice.krad.document.Document p0, org.kuali.rice.kim.api.identity.Person p1);
    boolean canAnnotate(org.kuali.rice.krad.document.Document p0, org.kuali.rice.kim.api.identity.Person p1);
    boolean canReload(org.kuali.rice.krad.document.Document p0, org.kuali.rice.kim.api.identity.Person p1);
    boolean canClose(org.kuali.rice.krad.document.Document p0, org.kuali.rice.kim.api.identity.Person p1);
    boolean canSave(org.kuali.rice.krad.document.Document p0, org.kuali.rice.kim.api.identity.Person p1);
    boolean canRoute(org.kuali.rice.krad.document.Document p0, org.kuali.rice.kim.api.identity.Person p1);
    boolean canCancel(org.kuali.rice.krad.document.Document p0, org.kuali.rice.kim.api.identity.Person p1);
    boolean canCopy(org.kuali.rice.krad.document.Document p0, org.kuali.rice.kim.api.identity.Person p1);
    boolean canPerformRouteReport(org.kuali.rice.krad.document.Document p0, org.kuali.rice.kim.api.identity.Person p1);
    boolean canBlanketApprove(org.kuali.rice.krad.document.Document p0, org.kuali.rice.kim.api.identity.Person p1);
    boolean canApprove(org.kuali.rice.krad.document.Document p0, org.kuali.rice.kim.api.identity.Person p1);
    boolean canDisapprove(org.kuali.rice.krad.document.Document p0, org.kuali.rice.kim.api.identity.Person p1);
    boolean canSendNoteFyi(org.kuali.rice.krad.document.Document p0, org.kuali.rice.kim.api.identity.Person p1);
    boolean canEditDocumentOverview(org.kuali.rice.krad.document.Document p0, org.kuali.rice.kim.api.identity.Person p1);
    boolean canFyi(org.kuali.rice.krad.document.Document p0, org.kuali.rice.kim.api.identity.Person p1);
    boolean canAcknowledge(org.kuali.rice.krad.document.Document p0, org.kuali.rice.kim.api.identity.Person p1);
    boolean canReceiveAdHoc(org.kuali.rice.krad.document.Document p0, org.kuali.rice.kim.api.identity.Person p1, java.lang.String p2);
    boolean canAddNoteAttachment(org.kuali.rice.krad.document.Document p0, java.lang.String p1, org.kuali.rice.kim.api.identity.Person p2);
    boolean canDeleteNoteAttachment(org.kuali.rice.krad.document.Document p0, java.lang.String p1, java.lang.String p2, org.kuali.rice.kim.api.identity.Person p3);
    boolean canViewNoteAttachment(org.kuali.rice.krad.document.Document p0, java.lang.String p1, java.lang.String p2, org.kuali.rice.kim.api.identity.Person p3);
    boolean canSendAdHocRequests(org.kuali.rice.krad.document.Document p0, java.lang.String p1, org.kuali.rice.kim.api.identity.Person p2);
    boolean canSendAnyTypeAdHocRequests(org.kuali.rice.krad.document.Document p0, org.kuali.rice.kim.api.identity.Person p1);
    boolean canTakeRequestedAction(org.kuali.rice.krad.document.Document p0, java.lang.String p1, org.kuali.rice.kim.api.identity.Person p2);
    boolean canRecall(org.kuali.rice.krad.document.Document p0, org.kuali.rice.kim.api.identity.Person p1);
}
