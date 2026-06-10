package org.kuali.rice.kns.service;

import java.util.List;

public interface DataDictionaryService extends org.kuali.rice.krad.service.DataDictionaryService {
    org.kuali.rice.krad.datadictionary.DataDictionary getDataDictionary();
    Integer getAttributeMaxLength(Class<?> boClass, String attributeName);
    Integer getAttributeMaxLength(String className, String attributeName);
    Integer getAttributeSize(Class<?> boClass, String attributeName);
    Integer getAttributeSize(String className, String attributeName);
    String getAttributeLabel(Class<?> boClass, String attributeName);
    String getAttributeLabel(String className, String attributeName);
    Class<?> getAttributeFormatter(Class<?> boClass, String attributeName);
    boolean isAttributeRequired(Class<?> boClass, String attributeName);
    boolean isAttributeDefined(Class<?> boClass, String attributeName);
    String getDocumentTypeNameByClass(Class<?> documentClass);
    String getValidDocumentTypeNameByClass(Class<?> documentClass);
    Boolean getAttributeForceUppercase(Class<?> boClass, String attributeName);
    org.kuali.rice.krad.datadictionary.AttributeSecurity getAttributeSecurity(String entryName, String attributeName);
    String getAttributeShortLabel(Class clazz, String attributeName);
    String getCollectionLabel(Class clazz, String collectionName);
}