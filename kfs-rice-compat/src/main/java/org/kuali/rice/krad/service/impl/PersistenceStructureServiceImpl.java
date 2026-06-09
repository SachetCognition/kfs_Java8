package org.kuali.rice.krad.service.impl;

import java.util.Map;
import java.util.HashMap;

public class PersistenceStructureServiceImpl implements org.kuali.rice.krad.service.PersistenceStructureService {
    public Map<String, String> referenceConversionMap = new HashMap<String, String>();
    
    public PersistenceStructureServiceImpl() {}
    
    public boolean hasReference(Class clazz, String referenceName) { return false; }
    public boolean hasCollection(Class clazz, String collectionName) { return false; }
    public Map<String, String> getRelationshipMetadata(Class clazz, String attributeName) { return new HashMap<String, String>(); }
    public Map<String, String> getReferenceConversionMap(Class clazz) { return referenceConversionMap; }
    public java.util.List<String> listPrimaryKeyFieldNames(Class clazz) { return new java.util.ArrayList<String>(); }
    public java.util.List<String> listFieldNames(Class clazz) { return new java.util.ArrayList<String>(); }
    public Map<String, Class> listReferenceObjectFields(Class clazz) { return new HashMap<String, Class>(); }
    public Map<String, Class> listCollectionObjectTypes(Class clazz) { return new HashMap<String, Class>(); }
    public boolean isPersistable(Class clazz) { return false; }
    public boolean isReferenceUpdatable(Class clazz, String referenceName) { return false; }
    public boolean isCollectionUpdatable(Class clazz, String collectionName) { return false; }
    public Map<String, String> getInverseForeignKeysForCollection(Class clazz, String collectionName) { return new HashMap<String, String>(); }
    public Map<String, String> getForeignKeysForReference(Class clazz, String referenceName) { return new HashMap<String, String>(); }
    public String getTableName(Class clazz) { return ""; }
}
