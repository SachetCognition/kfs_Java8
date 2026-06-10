package org.kuali.rice.krad.datadictionary;

public class AttributeDefinition {
    private String name;
    private String label;
    private String shortLabel;
    private Integer maxLength;
    private Class<?> formatterClass;
    private boolean required;
    private boolean forceUppercase;
    private AttributeSecurity attributeSecurity;
    
    public AttributeDefinition() {}
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public String getShortLabel() { return shortLabel; }
    public Integer getMaxLength() { return maxLength; }
    public Class<?> getFormatterClass() { return formatterClass; }
    public boolean isRequired() { return required; }
    public boolean isForceUppercase() { return forceUppercase; }
    public AttributeSecurity getAttributeSecurity() { return attributeSecurity; }
    public void setAttributeSecurity(AttributeSecurity attributeSecurity) { this.attributeSecurity = attributeSecurity; }

    public org.kuali.rice.krad.datadictionary.validation.ValidationPattern getValidationPattern() { return null; }
    public Object getDisplayMask() { return null; }
    public boolean isSecure() { return false; }
    public String getObjectLabel() { return null; }
    protected String defaultValue;
    public String getDefaultValue() { return defaultValue; }
    public void setDefaultValue(String defaultValue) { this.defaultValue = defaultValue; }
    protected Class defaultValueFinderClass;
    public Class getDefaultValueFinderClass() { return defaultValueFinderClass; }
    public void setDefaultValueFinderClass(Class clazz) { this.defaultValueFinderClass = clazz; }

    public Boolean getForceUppercase() { return null; }
    public org.kuali.rice.krad.datadictionary.control.ControlDefinition getControl() { return null; }
    public Integer getSize() { return null; }
    public String getRegexPattern() { return null; }
    public org.kuali.rice.krad.keyvalues.KeyValuesFinder getOptionsFinder() { return null; }
    public java.util.Map getKeyLabelMap() { return new java.util.HashMap(); }
}
