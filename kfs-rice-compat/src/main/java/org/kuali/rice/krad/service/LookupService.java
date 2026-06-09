package org.kuali.rice.krad.service;

import java.util.Collection;
import java.util.Map;

public interface LookupService {
    <T extends org.kuali.rice.krad.bo.BusinessObject> Collection<T> findCollectionBySearch(Class<T> clazz, Map<String, String> formProps);
    <T extends org.kuali.rice.krad.bo.BusinessObject> Collection<T> findCollectionBySearchUnbounded(Class<T> clazz, Map<String, String> formProps);
    <T extends org.kuali.rice.krad.bo.BusinessObject> T findObjectBySearch(Class<T> clazz, Map<String, String> formProps);
}
