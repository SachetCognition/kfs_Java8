package org.kuali.rice.core.api.uif;
public class RemotableTextInput extends RemotableAbstractControl {
    public static class Builder extends RemotableAbstractControl.Builder {
        private int size;
        private Builder() {}
        public static Builder create() { return new Builder(); }
        public void setSize(int size) { this.size = size; }
        public int getSize() { return size; }
    }
}
