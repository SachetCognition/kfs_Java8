package org.kuali.rice.krad.dao;

import java.util.Collection;
import java.util.Map;

public interface LookupDao {
    Collection findCollectionBySearchHelper(Class clazz, Map<String, String> formProps, boolean unbounded, boolean usePrimaryKeyValuesOnly);
    Long findCountByMap(Object example, Map<String, String> formProps);
    Object findObjectByMap(Object example, Map<String, String> formProps);
}
