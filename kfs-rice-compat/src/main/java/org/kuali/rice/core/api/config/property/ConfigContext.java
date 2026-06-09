package org.kuali.rice.core.api.config.property;

public class ConfigContext {
    private static Config config;
    
    public static Config getCurrentContextConfig() {
        return config;
    }
    
    public static void init(Config config) {
        ConfigContext.config = config;
    }
    
    private ConfigContext() {}
}
