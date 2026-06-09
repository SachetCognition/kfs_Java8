package org.apache.ojb.broker.util;
public interface ObjectModification {
    boolean needsInsert();
    boolean needsUpdate();
    boolean needsDelete();
    public static final ObjectModification INSERT = new ObjectModification() { public boolean needsInsert() { return true; } public boolean needsUpdate() { return false; } public boolean needsDelete() { return false; } };
    public static final ObjectModification UPDATE = new ObjectModification() { public boolean needsInsert() { return false; } public boolean needsUpdate() { return true; } public boolean needsDelete() { return false; } };
}
