package org.kuali.rice.kns.web.struts.action;

public class KualiMultipleValueLookupAction extends org.kuali.rice.kns.web.struts.action.KualiLookupAction implements org.kuali.rice.kns.web.struts.action.KualiTableRenderAction {
    public KualiMultipleValueLookupAction() {}

    public static final int DEFAULT_MAX_ROWS_PER_PAGE = 0;

    public org.apache.struts.action.ActionForward search(org.apache.struts.action.ActionMapping p0, org.apache.struts.action.ActionForm p1, javax.servlet.http.HttpServletRequest p2, javax.servlet.http.HttpServletResponse p3) throws java.lang.Exception { return null; }
    public org.apache.struts.action.ActionForward switchToPage(org.apache.struts.action.ActionMapping p0, org.apache.struts.action.ActionForm p1, javax.servlet.http.HttpServletRequest p2, javax.servlet.http.HttpServletResponse p3) throws java.lang.Exception { return null; }
    public org.apache.struts.action.ActionForward sort(org.apache.struts.action.ActionMapping p0, org.apache.struts.action.ActionForm p1, javax.servlet.http.HttpServletRequest p2, javax.servlet.http.HttpServletResponse p3) throws java.lang.Exception { return null; }
    public org.apache.struts.action.ActionForward prepareToReturnSelectedResults(org.apache.struts.action.ActionMapping p0, org.apache.struts.action.ActionForm p1, javax.servlet.http.HttpServletRequest p2, javax.servlet.http.HttpServletResponse p3) throws java.lang.Exception { return null; }
    public org.apache.struts.action.ActionForward selectAll(org.apache.struts.action.ActionMapping p0, org.apache.struts.action.ActionForm p1, javax.servlet.http.HttpServletRequest p2, javax.servlet.http.HttpServletResponse p3) throws java.lang.Exception { return null; }
    public org.apache.struts.action.ActionForward unselectAll(org.apache.struts.action.ActionMapping p0, org.apache.struts.action.ActionForm p1, javax.servlet.http.HttpServletRequest p2, javax.servlet.http.HttpServletResponse p3) throws java.lang.Exception { return null; }
    public org.apache.struts.action.ActionForward cancel(org.apache.struts.action.ActionMapping p0, org.apache.struts.action.ActionForm p1, javax.servlet.http.HttpServletRequest p2, javax.servlet.http.HttpServletResponse p3) throws java.lang.Exception { return null; }
    public org.apache.struts.action.ActionForward prepareToReturnNone(org.apache.struts.action.ActionMapping p0, org.apache.struts.action.ActionForm p1, javax.servlet.http.HttpServletRequest p2, javax.servlet.http.HttpServletResponse p3) throws java.lang.Exception { return null; }
    public org.apache.struts.action.ActionForward export(org.apache.struts.action.ActionMapping p0, org.apache.struts.action.ActionForm p1, javax.servlet.http.HttpServletRequest p2, javax.servlet.http.HttpServletResponse p3) throws java.lang.Exception { return null; }
    public org.apache.struts.action.ActionForward clearValues(org.apache.struts.action.ActionMapping p0, org.apache.struts.action.ActionForm p1, javax.servlet.http.HttpServletRequest p2, javax.servlet.http.HttpServletResponse p3) throws java.io.IOException, javax.servlet.ServletException { return null; }
    protected int getMaxRowsPerPage(org.kuali.rice.kns.web.struts.form.LookupForm form) { return 100; }
    protected java.util.Collection performMultipleValueLookup(org.kuali.rice.kns.web.struts.form.MultipleValueLookupForm form, java.util.List<org.kuali.rice.kns.web.ui.ResultRow> resultTable, int maxRowsPerPage, boolean bounded) { return new java.util.ArrayList(); }
    protected void prepareToReturnSelectedResultBOs(org.kuali.rice.kns.web.struts.form.MultipleValueLookupForm form) {}
    protected void prepareToReturnNone(org.kuali.rice.kns.web.struts.form.MultipleValueLookupForm form) {}
    protected java.util.List<org.kuali.rice.kns.web.ui.ResultRow> selectAll(org.kuali.rice.kns.web.struts.form.MultipleValueLookupForm form, int maxRowsPerPage) { return new java.util.ArrayList<>(); }
}
