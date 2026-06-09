package org.kuali.rice.core.api.uif;
public class RemotableAttributeField extends org.kuali.rice.core.api.mo.AbstractDataTransferObject {
    public RemotableAttributeField() {}
    public String getName() { return null; }
    public String getLongLabel() { return null; }
    public String getShortLabel() { return null; }
    
    public static class Builder {
        private String name;
        public Builder(String name) { this.name = name; }
        public static Builder create(String name) { return new Builder(name); }
        public RemotableAttributeField build() { return new RemotableAttributeField(); }
    }
}
