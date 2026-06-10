package org.kuali.rice.kns.web.struts.action;

public abstract class KualiAction extends org.apache.struts.actions.DispatchAction {
    public KualiAction() {}

    public static final java.lang.String TEXT_AREA_FIELD_NAME = "";
    public static final java.lang.String TEXT_AREA_FIELD_LABEL = "";
    public static final java.lang.String TEXT_AREA_READ_ONLY = "";
    public static final java.lang.String TEXT_AREA_FIELD_ANCHOR = "";
    public static final java.lang.String TEXT_AREA_MAX_LENGTH = "";
    public static final java.lang.String FORM_ACTION = "";
    public static final java.lang.String METHOD_TO_CALL = "";
    public static final java.lang.String FORWARD_TEXT_AREA_UPDATE = "";
    public static final java.lang.String POST_TEXT_AREA_TO_PARENT = "";
    public static final java.lang.String FORWARD_NEXT = "";

    public org.apache.struts.action.ActionForward execute(org.apache.struts.action.ActionMapping p0, org.apache.struts.action.ActionForm p1, javax.servlet.http.HttpServletRequest p2, javax.servlet.http.HttpServletResponse p3) throws java.lang.Exception { return null; }
    public org.apache.struts.action.ActionForward toggleTab(org.apache.struts.action.ActionMapping p0, org.apache.struts.action.ActionForm p1, javax.servlet.http.HttpServletRequest p2, javax.servlet.http.HttpServletResponse p3) throws java.lang.Exception { return null; }
    public org.apache.struts.action.ActionForward showAllTabs(org.apache.struts.action.ActionMapping p0, org.apache.struts.action.ActionForm p1, javax.servlet.http.HttpServletRequest p2, javax.servlet.http.HttpServletResponse p3) throws java.lang.Exception { return null; }
    public org.apache.struts.action.ActionForward hideAllTabs(org.apache.struts.action.ActionMapping p0, org.apache.struts.action.ActionForm p1, javax.servlet.http.HttpServletRequest p2, javax.servlet.http.HttpServletResponse p3) throws java.lang.Exception { return null; }
    public org.apache.struts.action.ActionForward refresh(org.apache.struts.action.ActionMapping p0, org.apache.struts.action.ActionForm p1, javax.servlet.http.HttpServletRequest p2, javax.servlet.http.HttpServletResponse p3) throws java.lang.Exception { return null; }
    public org.apache.struts.action.ActionForward performLookup(org.apache.struts.action.ActionMapping p0, org.apache.struts.action.ActionForm p1, javax.servlet.http.HttpServletRequest p2, javax.servlet.http.HttpServletResponse p3) throws java.lang.Exception { return null; }
    public org.apache.struts.action.ActionForward performInquiry(org.apache.struts.action.ActionMapping p0, org.apache.struts.action.ActionForm p1, javax.servlet.http.HttpServletRequest p2, javax.servlet.http.HttpServletResponse p3) throws java.lang.Exception { return null; }
    public org.apache.struts.action.ActionForward performWorkgroupLookup(org.apache.struts.action.ActionMapping p0, org.apache.struts.action.ActionForm p1, javax.servlet.http.HttpServletRequest p2, javax.servlet.http.HttpServletResponse p3) throws java.lang.Exception { return null; }
    public org.apache.struts.action.ActionForward headerTab(org.apache.struts.action.ActionMapping p0, org.apache.struts.action.ActionForm p1, javax.servlet.http.HttpServletRequest p2, javax.servlet.http.HttpServletResponse p3) throws java.lang.Exception { return null; }
    public org.apache.struts.action.ActionForward updateTextArea(org.apache.struts.action.ActionMapping p0, org.apache.struts.action.ActionForm p1, javax.servlet.http.HttpServletRequest p2, javax.servlet.http.HttpServletResponse p3) { return null; }
    public org.apache.struts.action.ActionForward postTextAreaToParent(org.apache.struts.action.ActionMapping p0, org.apache.struts.action.ActionForm p1, javax.servlet.http.HttpServletRequest p2, javax.servlet.http.HttpServletResponse p3) { return null; }
    public static java.lang.String getApplicationBaseUrl() { return null; }
    protected int getLineToDelete(javax.servlet.http.HttpServletRequest request) { return 0; }
    protected int getSelectedLine(javax.servlet.http.HttpServletRequest request) { return 0; }
    protected org.kuali.rice.coreservice.framework.parameter.ParameterService getParameterService() { return null; }
    protected void checkAuthorization(org.apache.struts.action.ActionForm form, String methodToCall) throws org.kuali.rice.krad.exception.AuthorizationException {}
    protected org.apache.struts.action.ActionForward performQuestionWithInput(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, String questionId, String questionText, String questionType, String caller, String context) throws Exception { return null; }
    protected org.apache.struts.action.ActionForward performQuestionWithInputAgainBecauseOfErrors(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, String questionId, String questionText, String questionType, String caller, String context, String reason, String errorKey, String errorPropertyName, String errorParameter) throws Exception { return null; }
    protected org.apache.struts.action.ActionForward performQuestion(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, String questionId, String questionText, String questionType, String caller, String context) throws Exception { return null; }
    protected org.kuali.rice.krad.service.KualiConfigurationService getKualiConfigurationService() { return null; }
}
