package org.kuali.rice.krad.datadictionary;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class BusinessObjectEntry {
    private List<AttributeDefinition> attributes = new ArrayList<>();
    public BusinessObjectEntry() {}
    public String getName() { return null; }
    public Class getBusinessObjectClass() { return null; }
    public List<String> getPrimaryKeys() { return new ArrayList<>(); }
    public List<AttributeDefinition> getAttributes() { return attributes; }
    public void setAttributes(List<AttributeDefinition> attributes) { this.attributes = attributes; }
    public AttributeDefinition getAttributeDefinition(String attributeName) { return null; }

    public String getObjectLabel() { return null; }
    public Class<?> getDataObjectClass() { return null; }
    public String getTitleAttribute() { return null; }
}
