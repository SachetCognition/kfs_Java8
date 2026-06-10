package org.kuali.rice.kew.api.document.attribute;
public class DocumentAttributeDateTime extends DocumentAttribute {
    public Object getValue() { return null; }
    public String getName() { return null; }
    public static class Builder {
        private String name;
        private org.joda.time.DateTime value;
        public static Builder create(String name) { Builder b = new Builder(); b.name = name; return b; }
        public Builder() {}
        public void setValue(org.joda.time.DateTime value) { this.value = value; }
        public DocumentAttributeDateTime build() { return new DocumentAttributeDateTime(); }
    }
}
