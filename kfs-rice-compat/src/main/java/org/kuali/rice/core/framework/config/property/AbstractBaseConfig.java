package org.kuali.rice.core.framework.config.property;
public class AbstractBaseConfig implements org.kuali.rice.core.api.config.property.Config {
    protected java.util.Properties properties = new java.util.Properties();
    public AbstractBaseConfig() {}
    public AbstractBaseConfig(java.util.Properties props) { if (props != null) this.properties.putAll(props); }
    public String getDailyEmailFirstDeliveryDate() { return null; }
    public String getWeeklyEmailFirstDeliveryDate() { return null; }
    public String getBaseWebServiceURL() { return null; }
    public String getBaseWebServiceWsdlPath() { return null; }
    public String getClientWSDLFullPathAndFileName() { return null; }
    public String getWebServicesConnectRetry() { return null; }
    public String getKEWBaseURL() { return null; }
    public String getKIMBaseURL() { return null; }
    public String getKRBaseURL() { return null; }
    public String getKENBaseURL() { return null; }
    public String getLog4jFileLocation() { return null; }
    public String getLog4jReloadInterval() { return null; }
    public String getTransactionTimeout() { return null; }
    public String getEmailConfigurationPath() { return null; }
    public String getRiceVersion() { return null; }
    public String getApplicationName() { return null; }
    public String getApplicationVersion() { return null; }
    public String getEnvironment() { return null; }
    public String getProductionEnvironmentCode() { return null; }
    public String getEDLConfigLocation() { return null; }
    public String getDefaultKewNoteClass() { return null; }
    public String getEmbeddedPluginLocation() { return null; }
    public Integer getRefreshRate() { return null; }
    public String getEndPointUrl() { return null; }
    public String getAlternateSpringFile() { return null; }
    public String getAlternateOJBFile() { return null; }
    public String getKeystoreAlias() { return null; }
    public String getKeystorePassword() { return null; }
    public String getKeystoreFile() { return null; }
    public String getDocumentLockTimeout() { return null; }
    public Boolean getEmailReminderLifecycleEnabled() { return null; }
    public Boolean getXmlPipelineLifeCycleEnabled() { return null; }
    public Boolean getDevMode() { return null; }
    public Boolean getBatchMode() { return null; }
    public Boolean getOutBoxOn() { return null; }
    public boolean isProductionEnvironment() { return false; }
    public java.util.Properties getProperties() { return properties; }
    public String getProperty(String key) { return properties.getProperty(key); }
    public boolean getBooleanProperty(String key, boolean defaultValue) { return defaultValue; }
    public Boolean getBooleanProperty(String key) { return null; }
    public long getNumericProperty(String key, long defaultValue) { return defaultValue; }
    public Long getNumericProperty(String key) { return null; }
    public java.util.Map<String, String> getPropertiesWithPrefix(String prefix, boolean stripPrefix) { return new java.util.HashMap<>(); }
    public java.util.Map<String, Object> getObjects() { return new java.util.HashMap<>(); }
    public Object getObject(String key) { return null; }
    public void putProperties(java.util.Properties p) { if (p != null) properties.putAll(p); }
    public void putProperty(String key, String value) { properties.setProperty(key, value); }
    public void removeProperty(String key) { properties.remove(key); }
    public void putObjects(java.util.Map<String, Object> objects) {}
    public void putObject(String key, Object value) {}
    public void removeObject(String key) {}
}
