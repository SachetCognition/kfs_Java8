package org.kuali.rice.kns.service.impl;
public class DataDictionaryServiceImpl implements org.kuali.rice.kns.service.DataDictionaryService {
    public DataDictionaryServiceImpl() {}
    public org.kuali.rice.krad.datadictionary.DataDictionary getDataDictionary() { return null; }
    public Class findFormatter(Class<? extends Object> boClass) { return null; }
    public Class getAttributeFormatter(Class<? extends Object> boClass, String attributeName) { return null; }
    public Integer getAttributeMaxLength(Class<?> boClass, String attributeName) { return null; }
    public Integer getAttributeSize(Class<?> boClass, String attributeName) { return null; }
    public String getAttributeLabel(Class<?> boClass, String attributeName) { return null; }
    public boolean isAttributeRequired(Class<?> boClass, String attributeName) { return false; }
    public boolean isAttributeDefined(Class<?> boClass, String attributeName) { return false; }
    public String getDocumentTypeNameByClass(Class<?> documentClass) { return null; }
    public String getValidDocumentTypeNameByClass(Class<?> documentClass) { return null; }
    public Boolean getAttributeForceUppercase(Class<?> boClass, String attributeName) { return null; }
    public org.kuali.rice.krad.datadictionary.AttributeSecurity getAttributeSecurity(String entryName, String attributeName) { return null; }
    public boolean isHide(String entryName, String attributeName) { return false; }
    public void addDataDictionaryLocation(String location) throws java.io.IOException {}
    public void addDataDictionaryLocation(String location, org.springframework.context.ApplicationContext ctx) throws java.io.IOException {}
    public java.util.Set getAllInactivationBlockingDefinitions(Class clazz) { return new java.util.HashSet(); }
    public Integer getAttributeMaxLength(String className, String attributeName) { return null; }
    public Integer getAttributeSize(String className, String attributeName) { return null; }
    public String getAttributeLabel(String className, String attributeName) { return null; }

    public String getCollectionLabel(Class clazz, String collectionName) { return null; }
    public String getAttributeShortLabel(Class clazz, String attributeName) { return null; }
}