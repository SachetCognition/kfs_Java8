package org.kuali.rice.krad.datadictionary;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public abstract class DataDictionaryEntryBase {
    protected List<AttributeDefinition> attributes = new ArrayList<AttributeDefinition>();
    protected Map<String, AttributeDefinition> attributeMap = new HashMap<String, AttributeDefinition>();
    
    public DataDictionaryEntryBase() {}
    
    public List<AttributeDefinition> getAttributes() { return attributes; }
    public void setAttributes(List<AttributeDefinition> attributes) { this.attributes = attributes; }
    
    public AttributeDefinition getAttributeDefinition(String attributeName) { return attributeMap.get(attributeName); }
}
