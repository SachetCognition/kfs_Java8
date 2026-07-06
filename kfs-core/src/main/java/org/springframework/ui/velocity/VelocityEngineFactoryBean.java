/*
 * Compatibility shim – VelocityEngineFactoryBean was removed in Spring 5.0.
 * Provides the FactoryBean used by KFS spring-sys.xml to create the shared
 * VelocityEngine for email/notification services.
 */
package org.springframework.ui.velocity;

import java.util.Map;
import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class VelocityEngineFactoryBean implements FactoryBean<VelocityEngine>, InitializingBean {

    private final Properties velocityProperties = new Properties();
    private VelocityEngine velocityEngine;

    public void setVelocityProperties(Map<String, String> properties) {
        if (properties != null) {
            velocityProperties.putAll(properties);
        }
    }

    @Override
    public void afterPropertiesSet() {
        try {
            velocityEngine = new VelocityEngine(velocityProperties);
            velocityEngine.init();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to initialize VelocityEngine", e);
        }
    }

    @Override
    public VelocityEngine getObject() {
        return velocityEngine;
    }

    @Override
    public Class<VelocityEngine> getObjectType() {
        return VelocityEngine.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
