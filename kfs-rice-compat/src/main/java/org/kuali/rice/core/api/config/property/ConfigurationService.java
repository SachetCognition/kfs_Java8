package org.kuali.rice.core.api.config.property;

public interface ConfigurationService {
    String getPropertyValueAsString(String key);
    boolean getPropertyValueAsBoolean(String key);
    boolean getPropertyValueAsBoolean(String key, boolean defaultValue);
}
