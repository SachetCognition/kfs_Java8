package org.kuali.rice.kim.api.identity;

public class CodedAttribute extends org.kuali.rice.core.api.mo.AbstractDataTransferObject {
    private String code;
    private String name;
    private boolean active;
    
    public CodedAttribute() {}
    
    public String getCode() { return code; }
    public String getName() { return name; }
    public boolean isActive() { return active; }
    
    public static class Builder implements org.kuali.rice.core.api.mo.ModelBuilder, java.io.Serializable {
        private String code;
        private String name;
        private boolean active;
        
        private Builder() {}
        public static Builder create(String code) { Builder b = new Builder(); b.code = code; return b; }
        public CodedAttribute build() { CodedAttribute ca = new CodedAttribute(); ca.code = this.code; ca.name = this.name; ca.active = this.active; return ca; }
    }
}
