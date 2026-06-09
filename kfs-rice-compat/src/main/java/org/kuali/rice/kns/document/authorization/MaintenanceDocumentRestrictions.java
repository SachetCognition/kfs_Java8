package org.kuali.rice.kns.document.authorization;

public interface MaintenanceDocumentRestrictions extends org.kuali.rice.kns.document.authorization.InquiryOrMaintenanceDocumentRestrictions {
    void addReadOnlyField(java.lang.String p0);
    void addReadOnlySectionId(java.lang.String p0);
    java.util.Set<java.lang.String> getReadOnlySectionIds();
    boolean isReadOnlySectionId(java.lang.String p0);
}
