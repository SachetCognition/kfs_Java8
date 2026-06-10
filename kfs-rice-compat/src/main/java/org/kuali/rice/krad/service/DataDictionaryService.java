package org.kuali.rice.krad.service;

import org.kuali.rice.krad.datadictionary.DataDictionary;
import org.kuali.rice.krad.datadictionary.AttributeSecurity;

public interface DataDictionaryService {
    DataDictionary getDataDictionary();
    AttributeSecurity getAttributeSecurity(String entryName, String attributeName);
    boolean isHide(String entryName, String attributeName);
    Integer getAttributeMaxLength(Class<?> boClass, String attributeName);
    Integer getAttributeSize(Class<?> boClass, String attributeName);
    String getAttributeLabel(Class<?> boClass, String attributeName);
    Boolean getAttributeForceUppercase(Class<?> boClass, String attributeName);
    String getDocumentTypeNameByClass(Class<?> documentClass);

    Class getAttributeFormatter(Class<? extends Object> boClass, String attributeName);
    Class findFormatter(Class<? extends Object> boClass);
    public java.util.Set getAllInactivationBlockingDefinitions(Class arg0);
    Integer getAttributeMaxLength(String entryName, String attributeName);
}
