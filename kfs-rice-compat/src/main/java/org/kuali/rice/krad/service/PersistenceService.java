package org.kuali.rice.krad.service;

import java.util.List;
import java.util.Map;

public interface PersistenceService {
    void loadRepositoryDescriptor(String ojbRepositoryFilePath);
    void clearCache();
    Object resolveProxy(Object o);
    boolean isProxied(Object o);
    Object getEntityByPrimaryKeyFieldValues(Class clazz, Map<String, Object> primaryKeyValues);
    void retrieveNonKeyFields(Object persistableObject);
    void retrieveReferenceObject(Object persistableObject, String referenceObjectName);
    void retrieveReferenceObjects(Object persistableObject, List referenceObjectNames);
    void refreshAllNonUpdatingReferences(org.kuali.rice.krad.bo.PersistableBusinessObject bo);
    boolean isJpaEnabledForKradClass(Class clazz);
    List<String> listPrimaryKeyFieldNames(Class clazz);
    Object getPrimaryKeyFieldValues(Object persistableObject);
    boolean allPrimaryKeyValuesPresentAndNotWildcard(Class boClass, Map<String, String> formProps);
    boolean allForeignKeyValuesPopulatedForReference(org.kuali.rice.krad.bo.PersistableBusinessObject bo, String referenceName);
    Class getBusinessObjectAttributeClass(Class clazz, String attributeName);
    String getForeignKeyFieldName(Class clazz, String attributeName, String pkName);
}
