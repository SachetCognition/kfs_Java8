package org.kuali.rice.core.framework.resourceloader;
import javax.xml.namespace.QName;
public class SpringResourceLoader extends BaseResourceLoader {
    public SpringResourceLoader() {}
    public SpringResourceLoader(QName name) { super(name); }
    public SpringResourceLoader(QName name, String fileLoc) { super(name); }
}
