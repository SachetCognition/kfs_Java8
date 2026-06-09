package org.kuali.rice.core.util.jaxb;

public class NameAndNamespacePair implements java.io.Serializable {
    private String name;
    private String namespaceCode;
    
    public NameAndNamespacePair() {}
    public NameAndNamespacePair(String namespaceCode, String name) { this.namespaceCode = namespaceCode; this.name = name; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getNamespaceCode() { return namespaceCode; }
    public void setNamespaceCode(String namespaceCode) { this.namespaceCode = namespaceCode; }
}
