package org.kuali.rice.core.api.uif;

public class RemotableAttributeField {
    private String name;
    
    public RemotableAttributeField() {}
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public static class Builder {
        private String name;
        public static Builder create(String name) { Builder b = new Builder(); b.name = name; return b; }
        public static Builder create() { return new Builder(); }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public RemotableAttributeField build() { RemotableAttributeField f = new RemotableAttributeField(); f.name = this.name; return f; }
    }
}
