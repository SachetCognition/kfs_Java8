package org.kuali.rice.kns.service;
public interface BusinessObjectDictionaryService {
    void performForceUppercase(Object bo);
    java.util.List getLookupFieldNames(Class clazz);
    boolean isLookupable(Class clazz);
    int countMatching(Class clazz, java.util.Map<String, String> fieldValues);
    java.util.List findMatching(Class clazz, java.util.Map fieldValues);
    java.util.List<String> getLookupResultFieldNames(Class clazz);
    Boolean getLookupResultFieldUseShortLabel(Class clazz, String fieldName);
    Integer getLookupResultFieldMaxLength(Class clazz, String fieldName);
    Boolean isInquirable(Class clazz);
    String getTitleAttribute(Class clazz);
    String getForeignKeyFieldName(Class clazz, String attributeName, Class targetClass);
    String getCollectionFieldDefaultValue(String boClassName, String collectionName, String fieldName);
}