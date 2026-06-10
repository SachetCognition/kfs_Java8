package org.kuali.rice.krad.service;

import java.util.List;
import java.util.Map;

public interface DataObjectMetaDataService {
    List<String> listPrimaryKeyFieldNames(Class<?> clazz);
    Map<String, ?> getPrimaryKeyFieldValues(Object dataObject);
    boolean equalsByPrimaryKeys(Object do1, Object do2);
    org.kuali.rice.krad.datadictionary.DataDictionaryEntry getDataDictionaryEntry(String className);
    boolean areNotesSupported(Class<?> dataObjectClass);
    org.kuali.rice.krad.bo.DataObjectRelationship getDataObjectRelationship(Object dataObject, Class<?> dataObjectClass, String attributeName, String persistableAttributeName, boolean keysOnly, boolean supportsLookup, boolean supportsInquiry);
    String getTitleAttribute(Class<?> dataObjectClass);
}
