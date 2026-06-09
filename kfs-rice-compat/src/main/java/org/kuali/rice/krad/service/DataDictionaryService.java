package org.kuali.rice.krad.service;

import org.kuali.rice.krad.datadictionary.DataDictionary;
import org.kuali.rice.krad.datadictionary.AttributeSecurity;

public interface DataDictionaryService {
    DataDictionary getDataDictionary();
    AttributeSecurity getAttributeSecurity(String entryName, String attributeName);
    boolean isHide(String entryName, String attributeName);
}
