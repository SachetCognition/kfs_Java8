package org.kuali.rice.kns.service;

public class KNSServiceLocator {
    public static final String KNS_NAMESPACE = "KR-NS";
    public static final String KIM_TYPE_DEFAULT_NAMESPACE = "KUALI";
    public static final String KIM_TYPE_DEFAULT_NAME = "Default";
    
    public static Object getService(String serviceName) { return null; }
    public static org.kuali.rice.kns.service.BusinessObjectDictionaryService getBusinessObjectDictionaryService() { return null; }
    public static org.kuali.rice.kns.service.DataDictionaryService getDataDictionaryService() { return null; }
    private KNSServiceLocator() {}
}
