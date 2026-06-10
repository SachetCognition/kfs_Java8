package org.kuali.rice.kns.web.struts.form.pojo;
import java.util.Map;
import java.util.HashMap;
public class PojoFormBase extends org.apache.struts.action.ActionForm {
    public PojoFormBase() {}
    public void populate(javax.servlet.http.HttpServletRequest request) {}
    public Map<String, String> getFieldConversions() { return new HashMap<>(); }
    public void setFieldConversions(Map<String, String> fieldConversions) {}
    public void setDocTypeName(String docTypeName) {}
    public String discoverDocumentTypeName() { return null; }
}
