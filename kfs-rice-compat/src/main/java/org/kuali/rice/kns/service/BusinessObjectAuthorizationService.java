package org.kuali.rice.kns.service;

public interface BusinessObjectAuthorizationService extends org.kuali.rice.krad.service.DataObjectAuthorizationService {
    org.kuali.rice.kns.document.authorization.BusinessObjectRestrictions getLookupResultRestrictions(java.lang.Object p0, org.kuali.rice.kim.api.identity.Person p1);
    org.kuali.rice.kns.inquiry.InquiryRestrictions getInquiryRestrictions(org.kuali.rice.krad.bo.BusinessObject p0, org.kuali.rice.kim.api.identity.Person p1);
    org.kuali.rice.kns.document.authorization.MaintenanceDocumentRestrictions getMaintenanceDocumentRestrictions(org.kuali.rice.kns.document.MaintenanceDocument p0, org.kuali.rice.kim.api.identity.Person p1);
    boolean canFullyUnmaskField(org.kuali.rice.kim.api.identity.Person p0, java.lang.Class<?> p1, java.lang.String p2, org.kuali.rice.krad.document.Document p3);
    boolean canPartiallyUnmaskField(org.kuali.rice.kim.api.identity.Person p0, java.lang.Class<?> p1, java.lang.String p2, org.kuali.rice.krad.document.Document p3);
    boolean attributeValueNeedsToBeEncryptedOnFormsAndLinks(Class<?> businessObjectClass, String fieldName);
}
