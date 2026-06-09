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
}
