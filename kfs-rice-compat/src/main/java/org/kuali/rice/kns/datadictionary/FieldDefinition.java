package org.kuali.rice.kns.datadictionary;
public class FieldDefinition implements FieldDefinitionI {
    private String attributeName;
    public FieldDefinition() {}
    public String getAttributeName() { return attributeName; }
    public void setAttributeName(String attributeName) { this.attributeName = attributeName; }
    public String getKeyLabel(String key) { return null; }
}
