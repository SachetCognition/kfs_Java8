package org.kuali.rice.krad.keyvalues;

import java.util.List;
import org.kuali.rice.core.api.util.KeyValue;

public interface KeyValuesFinder {
    List<KeyValue> getKeyValues();
    default String getKeyLabel(String key) { return null; }
    default java.util.Map getKeyLabelMap() { return new java.util.HashMap(); }
}
