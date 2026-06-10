package org.kuali.rice.krad.service.impl;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
public class ModuleServiceBase implements org.kuali.rice.krad.service.ModuleService {
    public ModuleServiceBase() {}
    public <T> List<T> retrieveExternalizableBusinessObjectsList(org.kuali.rice.krad.bo.BusinessObject bo, String propertyName, Class<T> type) { return new ArrayList<T>(); }
    public <T extends org.kuali.rice.krad.bo.ExternalizableBusinessObject> T getExternalizableBusinessObject(Class<T> clazz, Map<String, Object> fieldValues) { return null; }
    public <T extends org.kuali.rice.krad.bo.ExternalizableBusinessObject> List<T> getExternalizableBusinessObjectsList(Class<T> clazz, Map<String, Object> fieldValues) { return new ArrayList<T>(); }
    public boolean isExternalizableBusinessObjectLookupable(Class clazz) { return false; }
    public boolean isExternalizableBusinessObjectInquirable(Class clazz) { return false; }
    public <T extends org.kuali.rice.krad.bo.ExternalizableBusinessObject> T retrieveExternalizableBusinessObjectIfNecessary(org.kuali.rice.krad.bo.BusinessObject bo, T ebo, String propertyName) { return ebo; }
    public boolean isResponsibleForPackage(String packageName) { return false; }
    public boolean isExternalizable(Class clazz) { return false; }
    public String getExternalizableDataObjectInquiryUrl(Class clazz, java.util.Properties parameters) { return null; }
    public String getExternalizableBusinessObjectLookupUrl(Class clazz, java.util.Properties parameters) { return null; }
    public org.kuali.rice.krad.bo.ModuleConfiguration getModuleConfiguration() { return null; }
}
