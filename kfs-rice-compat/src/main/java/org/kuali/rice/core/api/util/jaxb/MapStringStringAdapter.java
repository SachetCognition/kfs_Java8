package org.kuali.rice.core.api.util.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.HashMap;
import java.util.Map;

public class MapStringStringAdapter extends XmlAdapter<String, Map<String, String>> {
    public MapStringStringAdapter() {}
    
    @Override
    public Map<String, String> unmarshal(String v) throws Exception {
        return new HashMap<String, String>();
    }
    
    @Override
    public String marshal(Map<String, String> v) throws Exception {
        return v != null ? v.toString() : null;
    }
    
    public static class StringMapEntry implements java.io.Serializable {
        private String key;
        private String value;
        
        public StringMapEntry() {}
        public StringMapEntry(Map.Entry<String, String> e) { this.key = e.getKey(); this.value = e.getValue(); }
        public StringMapEntry(String key, String value) { this.key = key; this.value = value; }
        
        public String getKey() { return key; }
        public void setKey(String key) { this.key = key; }
        public String getValue() { return value; }
        public void setValue(String value) { this.value = value; }
    }
}
