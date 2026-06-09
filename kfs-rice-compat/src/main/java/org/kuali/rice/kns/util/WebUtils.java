package org.kuali.rice.kns.util;

public class WebUtils {
    public WebUtils() {}

    public static final java.lang.String FILE_UPLOAD_LIMIT_EXCEEDED_EXCEPTION_ALREADY_THROWN = "";

    public static java.lang.String parseMethodToCall(org.apache.struts.action.ActionForm p0, javax.servlet.http.HttpServletRequest p1) { return null; }
    public static void logRequestContents(org.apache.log4j.Logger p0, org.apache.log4j.Level p1, javax.servlet.http.HttpServletRequest p2) {  }
    public static void saveMimeOutputStreamAsFile(javax.servlet.http.HttpServletResponse p0, java.lang.String p1, java.io.ByteArrayOutputStream p2, java.lang.String p3) throws java.io.IOException {  }
    public static void saveMimeInputStreamAsFile(javax.servlet.http.HttpServletResponse p0, java.lang.String p1, java.io.InputStream p2, java.lang.String p3, int p4) throws java.io.IOException {  }
    public static java.lang.String getTabState(org.kuali.rice.kns.web.struts.form.KualiForm p0, java.lang.String p1) { return null; }
    public static void incrementTabIndex(org.kuali.rice.kns.web.struts.form.KualiForm p0, java.lang.String p1) {  }
    public static void reopenInactiveRecords(java.util.List<org.kuali.rice.kns.web.ui.Section> p0, java.util.Map<java.lang.String, java.lang.String> p1, java.lang.String p2) {  }
    public static java.lang.String generateTabKey(java.lang.String p0) { return null; }
    public static void getMultipartParameters(javax.servlet.http.HttpServletRequest p0, org.apache.struts.action.ActionServletWrapper p1, org.apache.struts.action.ActionForm p2, org.apache.struts.action.ActionMapping p3) {  }
    public static long getMaxUploadSize(org.apache.struts.action.ActionForm p0) { return 0; }
    public static void registerEditableProperty(org.kuali.rice.kns.web.struts.form.pojo.PojoFormBase p0, java.lang.String p1) {  }
    public static boolean isDocumentSession(org.kuali.rice.krad.document.Document p0, org.kuali.rice.kns.web.struts.form.pojo.PojoFormBase p1) { return false; }
    public static boolean isFormSessionDocument(org.kuali.rice.kns.web.struts.form.pojo.PojoFormBase p0) { return false; }
    public static org.apache.struts.action.ActionForm getKualiForm(javax.servlet.jsp.PageContext p0) { return null; }
    public static org.apache.struts.action.ActionForm getKualiForm(javax.servlet.http.HttpServletRequest p0) { return null; }
    public static boolean isPropertyEditable(java.util.Set<java.lang.String> p0, java.lang.String p1) { return false; }
    public static boolean endsWithCoordinates(java.lang.String p0) { return false; }
    public static int getIndexOfCoordinateExtension(java.lang.String p0) { return 0; }
    public static boolean isInquiryHiddenField(java.lang.String p0, java.lang.String p1, java.lang.Object p2, java.lang.String p3) { return false; }
    public static boolean isHiddenKimObjectType(java.lang.String p0, java.lang.String p1) { return false; }
    public static java.lang.String getFullyMaskedValue(java.lang.String p0, java.lang.String p1, java.lang.Object p2, java.lang.String p3) { return null; }
    public static java.lang.String getPartiallyMaskedValue(java.lang.String p0, java.lang.String p1, java.lang.Object p2, java.lang.String p3) { return null; }
    public static boolean canFullyUnmaskField(java.lang.String p0, java.lang.String p1, org.kuali.rice.kns.web.struts.form.KualiForm p2) { return false; }
    public static boolean canPartiallyUnmaskField(java.lang.String p0, java.lang.String p1, org.kuali.rice.kns.web.struts.form.KualiForm p2) { return false; }
    public static boolean canAddNoteAttachment(org.kuali.rice.krad.document.Document p0) { return false; }
    public static boolean canViewNoteAttachment(org.kuali.rice.krad.document.Document p0, java.lang.String p1) { return false; }
    public static boolean canDeleteNoteAttachment(org.kuali.rice.krad.document.Document p0, java.lang.String p1, java.lang.String p2) { return false; }
    public static void reuseErrorMapFromPreviousRequest(org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase p0) {  }
    public static java.lang.String filterHtmlAndReplaceRiceMarkup(java.lang.String p0) { return null; }
    public static java.lang.String getButtonImageUrl(java.lang.String p0) { return null; }
    public static java.lang.String getAttachmentImageForUrl(java.lang.String p0) { return null; }
    public static java.lang.String getDefaultButtonImageUrl(java.lang.String p0) { return null; }
    public static org.kuali.rice.core.api.config.property.ConfigurationService getKualiConfigurationService() { return null; }
    public static java.lang.String preserveWhitespace(java.lang.String p0) { return null; }
    public static java.lang.String getKimGroupDisplayName(java.lang.String p0) { return null; }
    public static java.lang.String getPrincipalDisplayName(java.lang.String p0) { return null; }
    public static java.lang.String getRoleDisplayName(org.kuali.rice.kew.api.action.ActionRequest p0) { return null; }
    public static java.lang.String toAbsoluteURL(java.lang.String p0, java.lang.String p1) { return null; }
}
