package org.kuali.rice.core.api.uif;
public class RemotableAttributeLookupDefinition {
    public static class Builder {
        public static Builder create() { return new Builder(); }
        public void setLongLabel(String label) {}
        public void setShortLabel(String label) {}
        public void setMaxLength(Integer max) {}
        public void setRequired(boolean b) {}
        public void setForceUpperCase(boolean b) {}
        public void setDataType(DataType dataType) {}
        public void setControl(RemotableAbstractControl.Builder control) {}
        public void setWidgets(java.util.List<RemotableAbstractWidget.Builder> widgets) {}
        public void setName(String name) {}
        public RemotableAttributeLookupDefinition build() { return new RemotableAttributeLookupDefinition(); }
    }
}
