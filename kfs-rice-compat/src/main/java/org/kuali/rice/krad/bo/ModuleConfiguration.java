package org.kuali.rice.krad.bo;

public class ModuleConfiguration implements org.springframework.beans.factory.InitializingBean,org.springframework.context.ApplicationContextAware {
    public ModuleConfiguration() {}


    public java.util.List<java.lang.String> getDatabaseRepositoryFilePaths() { return new java.util.ArrayList(); }
    public void setDatabaseRepositoryFilePaths(java.util.List<java.lang.String> p0) {  }
    public java.util.List<java.lang.String> getDataDictionaryPackages() { return new java.util.ArrayList(); }
    public void setDataDictionaryPackages(java.util.List<java.lang.String> p0) {  }
    public java.util.Map<java.lang.Class, java.lang.Class> getExternalizableBusinessObjectImplementations() { return new java.util.HashMap(); }
    public void setExternalizableBusinessObjectImplementations(java.util.Map<java.lang.Class, java.lang.Class> p0) {  }
    public java.util.List<java.lang.String> getPackagePrefixes() { return new java.util.ArrayList(); }
    public void setPackagePrefixes(java.util.List<java.lang.String> p0) {  }
    public void setInitializeDataDictionary(boolean p0) {  }
    public java.util.List<java.lang.String> getScriptConfigurationFilePaths() { return new java.util.ArrayList(); }
    public java.util.List<java.lang.String> getJobNames() { return new java.util.ArrayList(); }
    public void setJobNames(java.util.List<java.lang.String> p0) {  }
    public java.util.List<java.lang.String> getTriggerNames() { return new java.util.ArrayList(); }
    public void setTriggerNames(java.util.List<java.lang.String> p0) {  }
    public boolean isInitializeDataDictionary() { return false; }
    public void setScriptConfigurationFilePaths(java.util.List<java.lang.String> p0) {  }
    public void afterPropertiesSet() throws java.lang.Exception {  }
    public java.lang.String getNamespaceCode() { return null; }
    public void setNamespaceCode(java.lang.String p0) {  }
    public void setApplicationContext(org.springframework.context.ApplicationContext p0) {  }
    public org.kuali.rice.krad.service.DataDictionaryService getDataDictionaryService() { return null; }
    public void setDataDictionaryService(org.kuali.rice.krad.service.DataDictionaryService p0) {  }
    public org.kuali.rice.krad.service.PersistenceService getPersistenceService() { return null; }
    public void setPersistenceService(org.kuali.rice.krad.service.PersistenceService p0) {  }
    public java.lang.String getDataSourceName() { return null; }
    public void setDataSourceName(java.lang.String p0) {  }
    public javax.persistence.EntityManager getEntityManager() { return null; }
    public void setEntityManager(javax.persistence.EntityManager p0) {  }
}
