package org.kuali.rice.krad.keyvalues;

import java.util.List;
import org.kuali.rice.core.api.util.KeyValue;

public interface KeyValuesFinder {
    List<KeyValue> getKeyValues();
    String getKeyLabel(String key);
}
