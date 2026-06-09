package org.kuali.rice.krad.service;

import java.util.Collection;
import java.util.Map;

public interface BusinessObjectService {
    <T extends org.kuali.rice.krad.bo.PersistableBusinessObject> T save(T bo);
    <T extends org.kuali.rice.krad.bo.PersistableBusinessObject> T retrieve(T bo);
    void delete(org.kuali.rice.krad.bo.PersistableBusinessObject bo);
    void delete(java.util.List<? extends org.kuali.rice.krad.bo.PersistableBusinessObject> boList);
    <T extends org.kuali.rice.krad.bo.BusinessObject> T findBySinglePrimaryKey(Class<T> clazz, Object primaryKey);
    <T extends org.kuali.rice.krad.bo.BusinessObject> T findByPrimaryKey(Class<T> clazz, Map<String, ?> primaryKeys);
    <T extends org.kuali.rice.krad.bo.BusinessObject> Collection<T> findAll(Class<T> clazz);
    <T extends org.kuali.rice.krad.bo.BusinessObject> Collection<T> findMatching(Class<T> clazz, Map<String, ?> fieldValues);
    <T extends org.kuali.rice.krad.bo.BusinessObject> Collection<T> findMatchingOrderBy(Class<T> clazz, Map<String, ?> fieldValues, String sortField, boolean sortAscending);
    int countMatching(Class clazz, Map<String, ?> fieldValues);
    int countMatching(Class clazz, Map<String, ?> positiveFieldValues, Map<String, ?> negativeFieldValues);
    <T extends org.kuali.rice.krad.bo.BusinessObject> T getReferenceIfExists(org.kuali.rice.krad.bo.BusinessObject bo, String referenceName);
    void linkAndSave(org.kuali.rice.krad.bo.PersistableBusinessObject bo);
}
