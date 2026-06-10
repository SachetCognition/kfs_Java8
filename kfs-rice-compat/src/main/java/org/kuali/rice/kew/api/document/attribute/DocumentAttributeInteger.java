package org.kuali.rice.kew.api.document.attribute;
public class DocumentAttributeInteger extends DocumentAttribute {
    public Object getValue() { return null; }
    public String getName() { return null; }
    public static class Builder {
        private String name;
        private java.math.BigInteger value;
        public static Builder create(String name) { Builder b = new Builder(); b.name = name; return b; }
        public Builder() {}
        public void setValue(java.math.BigInteger value) { this.value = value; }
        public DocumentAttributeInteger build() { return new DocumentAttributeInteger(); }
    }
}
