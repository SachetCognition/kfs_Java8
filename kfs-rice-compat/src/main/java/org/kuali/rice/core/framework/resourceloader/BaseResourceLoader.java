package org.kuali.rice.core.framework.resourceloader;
import javax.xml.namespace.QName;
public class BaseResourceLoader implements org.kuali.rice.core.api.resourceloader.ResourceLoader {
    public BaseResourceLoader() {}
    public BaseResourceLoader(QName name) {}
    public Object getService(QName serviceName) { return null; }
    public Object getObject(org.kuali.rice.core.api.reflect.ObjectDefinition definition) { return null; }
    public String getName() { return null; }
    public void start() throws Exception {}
    public void stop() throws Exception {}
    public boolean isStarted() { return false; }
}
