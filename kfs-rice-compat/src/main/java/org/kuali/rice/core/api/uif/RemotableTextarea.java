package org.kuali.rice.core.api.uif;
public class RemotableTextarea extends RemotableAbstractControl {
    public static class Builder extends RemotableAbstractControl.Builder {
        public static Builder create() { return new Builder(); }
        public void setRows(Integer rows) {}
        public void setCols(Integer cols) {}
    }
}
