package org.kuali.rice.core.api.mo;

public abstract class AbstractDataTransferObject implements org.kuali.rice.core.api.mo.ModelObjectComplete {
    public AbstractDataTransferObject() {}

    public int hashCode() { return super.hashCode(); }
    public boolean equals(java.lang.Object other) { return super.equals(other); }
    public java.lang.String toString() { return getClass().getSimpleName() + "@" + Integer.toHexString(System.identityHashCode(this)); }
}
