package org.kuali.rice.core.api.uif;

public abstract class RemotableAbstractControl extends org.kuali.rice.core.api.mo.AbstractDataTransferObject {
    public RemotableAbstractControl() {}
    
    public abstract static class Builder implements org.kuali.rice.core.api.mo.ModelBuilder, java.io.Serializable {
        public abstract RemotableAbstractControl build();
    }
}
