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
    public Map<String, String> getFields() { return fieldsForLookup; }
    public void setFields(Map<String, String> fields) { this.fieldsForLookup = fields; }
    public org.kuali.rice.kns.lookup.Lookupable getLookupable() { return null; }
    public void setLookupable(org.kuali.rice.kns.lookup.Lookupable lookupable) {}
    public boolean isSearchUsingOnlyPrimaryKeyValues() { return false; }
    public void setSearchUsingOnlyPrimaryKeyValues(boolean val) {}
    public String getPrimaryKeyFieldLabels() { return ""; }
    public void setPrimaryKeyFieldLabels(String labels) {}
    public String getLookupAnchor() { return null; }
    public void setLookupAnchor(String anchor) {}
    public void setPrependDisplayText(String text) {}

    public String getFormKey() { return null; }
    public void setFormKey(String formKey) {}
    protected String backLocation;
    public String getBackLocation() { return backLocation; }
    public void setBackLocation(String backLocation) { this.backLocation = backLocation; }
    public String getLookupResultsSequenceNumber() { return null; }
    public String getLookupableImplServiceName() { return null; }
    public java.util.List getRows() { return new java.util.ArrayList(); }
    public void setNumColumns(int numColumns) {}
    public void setLookupObjectId(String id) {}
    public String getLookupObjectId() { return null; }
}
