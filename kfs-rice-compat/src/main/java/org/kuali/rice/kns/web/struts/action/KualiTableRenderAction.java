package org.kuali.rice.kns.web.struts.action;

public interface KualiTableRenderAction {
    org.apache.struts.action.ActionForward switchToPage(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception;
    org.apache.struts.action.ActionForward sort(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception;
}
