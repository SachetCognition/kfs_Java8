package org.kuali.rice.kns.web.struts.form;
public class KualiForm extends org.kuali.rice.kns.web.struts.form.pojo.PojoFormBase {
    private String businessObjectClassName;
    public KualiForm() {}
    public java.util.Map getUnconvertedValues() { return new java.util.HashMap(); }
    public void addToErrorPath(String path) {}
    public void removeFromErrorPath(String path) {}
    public boolean isFieldLevelHelpEnabled() { return false; }
    public boolean isDocumentationFieldDisplayed() { return false; }
    public String getBusinessObjectClassName() { return businessObjectClassName; }
    public void setBusinessObjectClassName(String name) { this.businessObjectClassName = name; }
}
