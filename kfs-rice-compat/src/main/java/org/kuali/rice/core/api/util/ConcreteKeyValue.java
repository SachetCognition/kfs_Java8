package org.kuali.rice.core.api.util;
public class ConcreteKeyValue implements KeyValue {
    private String key;
    private String value;
    public ConcreteKeyValue() {}
    public ConcreteKeyValue(String key, String value) { this.key = key; this.value = value; }
    public String getKey() { return key; }
    public String getValue() { return value; }
    public void setKey(String key) { this.key = key; }
    public void setValue(String value) { this.value = value; }
}
