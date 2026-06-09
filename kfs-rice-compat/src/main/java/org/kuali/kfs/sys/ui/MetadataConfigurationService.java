package org.kuali.kfs.sys.ui;

import java.util.HashMap;
import java.util.Map;

public class MetadataConfigurationService {

    public static class FieldMetadata {
        private String label;
        private String shortLabel;
        private String description;
        private boolean required;
        private int maxLength;

        public String getLabel() { return label; }
        public void setLabel(String label) { this.label = label; }
        public String getShortLabel() { return shortLabel; }
        public void setShortLabel(String shortLabel) { this.shortLabel = shortLabel; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public boolean isRequired() { return required; }
        public void setRequired(boolean required) { this.required = required; }
        public int getMaxLength() { return maxLength; }
        public void setMaxLength(int maxLength) { this.maxLength = maxLength; }
    }

    private final Map<String, Map<String, FieldMetadata>> classMetadata = new HashMap<>();

    public void registerField(String className, String fieldName, FieldMetadata metadata) {
        Map<String, FieldMetadata> fields = classMetadata.get(className);
        if (fields == null) {
            fields = new HashMap<>();
            classMetadata.put(className, fields);
        }
        fields.put(fieldName, metadata);
    }

    public FieldMetadata getFieldMetadata(String className, String fieldName) {
        Map<String, FieldMetadata> fields = classMetadata.get(className);
        if (fields != null) {
            return fields.get(fieldName);
        }
        return null;
    }
}
