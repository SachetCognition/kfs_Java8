package org.kuali.rice.kns.lookup;
public interface LookupService {
    java.util.Collection findCollectionBySearchHelper(Class clazz, java.util.Map<String, String> formProps, boolean unbounded);
    java.util.Collection findCollectionBySearch(Class clazz, java.util.Map<String, String> formProps);
    public java.util.List findCollectionBySearchUnbounded(Class arg0, java.util.Map arg1);
}
