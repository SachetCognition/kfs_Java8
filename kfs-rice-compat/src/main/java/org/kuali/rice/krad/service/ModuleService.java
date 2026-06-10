package org.kuali.rice.krad.service;
import java.util.List;
import java.util.Map;
public interface ModuleService {
    <T> List<T> retrieveExternalizableBusinessObjectsList(org.kuali.rice.krad.bo.BusinessObject bo, String propertyName, Class<T> type);
    <T extends org.kuali.rice.krad.bo.ExternalizableBusinessObject> T getExternalizableBusinessObject(Class<T> clazz, Map<String, Object> fieldValues);
    <T extends org.kuali.rice.krad.bo.ExternalizableBusinessObject> List<T> getExternalizableBusinessObjectsList(Class<T> clazz, Map<String, Object> fieldValues);
    boolean isExternalizableBusinessObjectLookupable(Class clazz);
    boolean isExternalizableBusinessObjectInquirable(Class clazz);
    <T extends org.kuali.rice.krad.bo.ExternalizableBusinessObject> T retrieveExternalizableBusinessObjectIfNecessary(org.kuali.rice.krad.bo.BusinessObject bo, T ebo, String propertyName);
    boolean isResponsibleForPackage(String packageName);
    boolean isExternalizable(Class clazz);
    String getExternalizableDataObjectInquiryUrl(Class clazz, java.util.Properties parameters);
    String getExternalizableBusinessObjectLookupUrl(Class clazz, java.util.Properties parameters);
    org.kuali.rice.krad.bo.ModuleConfiguration getModuleConfiguration();
}
