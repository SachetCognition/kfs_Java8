package org.kuali.rice.krad.service.impl;

public class KualiModuleServiceImpl implements org.kuali.rice.krad.service.KualiModuleService,org.springframework.beans.factory.InitializingBean,org.springframework.context.ApplicationContextAware {
    public KualiModuleServiceImpl() {}


    public void setApplicationContext(org.springframework.context.ApplicationContext p0) {  }
    public java.util.List<org.kuali.rice.krad.service.ModuleService> getInstalledModuleServices() { return new java.util.ArrayList(); }
    public org.kuali.rice.krad.service.ModuleService getModuleService(java.lang.String p0) { return null; }
    public org.kuali.rice.krad.service.ModuleService getModuleServiceByNamespaceCode(java.lang.String p0) { return null; }
    public boolean isModuleServiceInstalled(java.lang.String p0) { return false; }
    public org.kuali.rice.krad.service.ModuleService getResponsibleModuleService(java.lang.Class p0) { return null; }
    public org.kuali.rice.krad.service.ModuleService getResponsibleModuleServiceForJob(java.lang.String p0) { return null; }
    public void setInstalledModuleServices(java.util.List<org.kuali.rice.krad.service.ModuleService> p0) {  }
    public java.util.List<java.lang.String> getDataDictionaryPackages() { return new java.util.ArrayList(); }
    public java.lang.String getNamespaceName(java.lang.String p0) { return null; }
    public void setLoadRiceInstalledModuleServices(boolean p0) {  }
    public void afterPropertiesSet() throws java.lang.Exception {  }
    public java.lang.String getNamespaceCode(java.lang.Class<?> p0) { return null; }
    public java.lang.String getComponentCode(java.lang.Class<?> p0) { return null; }
}
