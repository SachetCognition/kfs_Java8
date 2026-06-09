package org.kuali.rice.kns.web.struts.form;
import java.util.Map;
import java.util.HashMap;
public class LookupForm extends KualiForm {
    private String businessObjectClassName;
    private Class businessObjectClass;
    private Map<String, String> fieldConversions = new HashMap<String, String>();
    private Map<String, String> fieldsForLookup = new HashMap<String, String>();

    public LookupForm() {}
    public String getBusinessObjectClassName() { return businessObjectClassName; }
    public void setBusinessObjectClassName(String name) { this.businessObjectClassName = name; }
    public Class getBusinessObjectClass() { return businessObjectClass; }
    public void setBusinessObjectClass(Class clazz) { this.businessObjectClass = clazz; }
    public Map<String, String> getFieldConversions() { return fieldConversions; }
    public void setFieldConversions(Map<String, String> fieldConversions) { this.fieldConversions = fieldConversions; }
    public Map<String, String> getFieldsForLookup() { return fieldsForLookup; }
    public void setFieldsForLookup(Map<String, String> fieldsForLookup) { this.fieldsForLookup = fieldsForLookup; }
}
