package org.apache.ojb.broker.util;
public class ObjectModificationDefaultImpl implements ObjectModification {
    private boolean insert;
    private boolean update;
    public ObjectModificationDefaultImpl() {}
    public ObjectModificationDefaultImpl(boolean insert, boolean update) { this.insert = insert; this.update = update; }
    public boolean needsInsert() { return insert; }
    public boolean needsUpdate() { return update; }
    public boolean needsDelete() { return false; }
}
