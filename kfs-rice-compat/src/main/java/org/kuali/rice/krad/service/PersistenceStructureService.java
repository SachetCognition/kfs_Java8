package org.kuali.rice.krad.service;

import java.util.List;
import java.util.Map;

public interface PersistenceStructureService {
    List<String> listPrimaryKeyFieldNames(Class clazz);
    List<String> listFieldNames(Class clazz);
    Map<String, Class> listReferenceObjectFields(Class clazz);
    Map<String, Class> listCollectionObjectTypes(Class clazz);
    boolean isPersistable(Class clazz);
    boolean hasReference(Class clazz, String referenceName);
    boolean hasCollection(Class clazz, String collectionName);
    boolean isReferenceUpdatable(Class clazz, String referenceName);
    boolean isCollectionUpdatable(Class clazz, String collectionName);
    Map<String, String> getInverseForeignKeysForCollection(Class clazz, String collectionName);
    Map<String, String> getForeignKeysForReference(Class clazz, String referenceName);
    String getTableName(Class clazz);
    List<String> getPrimaryKeys(Class clazz);
    boolean hasPrimaryKeyFieldValues(Object obj);
    Map<String, String> getRelationshipMetadata(Class clazz, String attributeName);
    Map getReferenceConversionMap(Class clazz);

    Class getBusinessObjectAttributeClass(Class clazz, String attributeName);
    String getForeignKeyFieldName(Class clazz, String attributeName, String pkFieldName);
}
