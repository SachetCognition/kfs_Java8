package org.kuali.rice.core.api.uif;
public class RemotableSelect extends RemotableAbstractControl {
    public static class Builder extends RemotableAbstractControl.Builder {
        private java.util.Map<String, String> options = new java.util.LinkedHashMap<>();
        private boolean multiselect;
        private Builder(java.util.Map<String, String> options) { if(options != null) this.options = options; }
        public void setMultiple(boolean b) {}
        public void setSize(Integer size) {}
        public static Builder create(java.util.Map<String, String> options) { return new Builder(options); }
        public boolean isMultiselect() { return multiselect; }
        public void setMultiselect(boolean ms) { this.multiselect = ms; }
    }
}
