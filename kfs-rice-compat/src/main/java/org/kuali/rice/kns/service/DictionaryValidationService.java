package org.kuali.rice.kns.service;

public interface DictionaryValidationService extends org.kuali.rice.krad.service.DictionaryValidationService {
    void validateDocumentRecursively(org.kuali.rice.krad.document.Document p0, int p1);
    void validateBusinessObjectOnMaintenanceDocument(org.kuali.rice.krad.bo.BusinessObject p0, java.lang.String p1);
    void validateBusinessObjectsRecursively(org.kuali.rice.krad.bo.BusinessObject p0, int p1);
    void validateAttributeFormat(java.lang.String p0, java.lang.String p1, java.lang.String p2, java.lang.String p3);
    void validateAttributeFormat(java.lang.String p0, java.lang.String p1, java.lang.String p2, java.lang.String p3, java.lang.String p4);
    void validateAttributeRequired(java.lang.String p0, java.lang.String p1, java.lang.Object p2, java.lang.Boolean p3, java.lang.String p4);
}
