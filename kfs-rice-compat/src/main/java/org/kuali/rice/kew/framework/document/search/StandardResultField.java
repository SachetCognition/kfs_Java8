package org.kuali.rice.kew.framework.document.search;

public class StandardResultField {
    private String key;
    private String label;
    
    public StandardResultField() {}
    public StandardResultField(String key, String label) { this.key = key; this.label = label; }
    
    public String getKey() { return key; }
    public String getLabel() { return label; }
}
