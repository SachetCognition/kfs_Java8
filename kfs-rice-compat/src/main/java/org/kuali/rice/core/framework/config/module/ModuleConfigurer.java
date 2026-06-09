package org.kuali.rice.core.framework.config.module;

public class ModuleConfigurer extends org.kuali.rice.core.api.lifecycle.BaseCompositeLifecycle implements org.kuali.rice.core.api.config.module.Configurer,org.springframework.beans.factory.InitializingBean,org.springframework.beans.factory.DisposableBean,org.springframework.web.context.ServletContextAware {
    public ModuleConfigurer() {}


    public static java.util.Collection<org.kuali.rice.core.framework.config.module.ModuleConfigurer> getCurrentContextConfigurers() { return new java.util.ArrayList(); }
    public void start() throws java.lang.Exception {  }
    public void afterPropertiesSet() throws java.lang.Exception {  }
    public void stop() throws java.lang.Exception {  }
    public void destroy() throws java.lang.Exception {  }
    public java.util.List<org.kuali.rice.core.api.lifecycle.Lifecycle> loadLifecycles() throws java.lang.Exception { return new java.util.ArrayList(); }
    public org.kuali.rice.core.api.config.module.RunMode getRunMode() { return null; }
    public boolean hasWebInterface() { return false; }
    public boolean shouldRenderWebInterface() { return false; }
    public boolean isExposeServicesOnBus() { return false; }
    public java.util.List<java.lang.String> getPrimarySpringFiles() { return new java.util.ArrayList(); }
    public org.kuali.rice.core.framework.config.module.WebModuleConfiguration getWebModuleConfiguration() { return null; }
    public java.util.Properties getProperties() { return null; }
    public void setProperties(java.util.Properties p0) {  }
    public java.util.List<java.lang.String> getAdditionalSpringFiles() { return new java.util.ArrayList(); }
    public java.lang.String getModuleName() { return null; }
    public java.util.List<org.kuali.rice.core.api.config.module.RunMode> getValidRunModes() { return new java.util.ArrayList(); }
    public void validateConfigurerState() {  }
    public void addToConfig() {  }
    public void initializeResourceLoaders() throws java.lang.Exception {  }
    public void setServletContext(javax.servlet.ServletContext p0) {  }
}
