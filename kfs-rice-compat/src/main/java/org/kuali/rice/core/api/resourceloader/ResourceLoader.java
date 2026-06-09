package org.kuali.rice.core.api.resourceloader;
import javax.xml.namespace.QName;
public interface ResourceLoader {
    Object getService(QName serviceName);
    Object getObject(org.kuali.rice.core.api.reflect.ObjectDefinition definition);
    String getName();
    void start() throws Exception;
    void stop() throws Exception;
    boolean isStarted();
}
