package org.kuali.rice.krad.service;

import java.util.Collection;
import java.util.Map;

public interface KeyValuesService {
    <T extends org.kuali.rice.krad.bo.BusinessObject> Collection<T> findAll(Class<T> clazz);
    <T extends org.kuali.rice.krad.bo.BusinessObject> Collection<T> findMatching(Class<T> clazz, Map<String, ?> fieldValues);
    void performForceUppercase(Object bo);
    java.util.List findAllOrderBy(Class clazz, String sortField, boolean ascending);
}
