package org.kuali.rice.kim.api.type;
public class KimAttributeField {
    private String id;
    private org.kuali.rice.core.api.uif.RemotableAttributeField attributeField;
    private boolean unique;
    
    public String getId() { return id; }
    public org.kuali.rice.core.api.uif.RemotableAttributeField getAttributeField() { return attributeField; }
    public boolean isUnique() { return unique; }
    
    public static class Builder {
        private String id;
        public void setUnique(boolean unique) {}
        public static Builder create(org.kuali.rice.core.api.uif.RemotableAttributeField.Builder raf) { return new Builder(); }
        public static Builder create() { return new Builder(); }
        public KimAttributeField build() { return new KimAttributeField(); }
    }
}
