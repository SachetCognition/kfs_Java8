package org.kuali.rice.kim.api.common.template;
public class Template {
    private String namespaceCode;
    private String name;
    private String id;
    public String getNamespaceCode() { return namespaceCode; }
    public String getName() { return name; }
    public String getId() { return id; }
    public void setNamespaceCode(String ns) { this.namespaceCode = ns; }
    public void setName(String name) { this.name = name; }
    public void setId(String id) { this.id = id; }

    public static class Builder {
        public static Builder create() { return new Builder(); }
        public void setId(String id) {}
        public void setNamespaceCode(String ns) {}
        public void setName(String name) {}
        public Template build() { return new Template(); }
    }
}
