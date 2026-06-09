package org.kuali.rice.krad.keyvalues;

import java.util.List;
import java.util.ArrayList;
import org.kuali.rice.core.api.util.KeyValue;

public abstract class KeyValuesBase implements KeyValuesFinder, java.io.Serializable {
    public KeyValuesBase() {}
    
    public String getKeyLabel(String key) {
        List<KeyValue> kvs = getKeyValues();
        for (KeyValue kv : kvs) {
            if (kv.getKey().equals(key)) return kv.getValue();
        }
        return key;
    }
    
    public String getCodeAndDescription() { return ""; }
}
