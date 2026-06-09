package org.apache.ojb.broker.util;
public class GUID implements java.io.Serializable {
    public GUID() {}
    public String toString() { return java.util.UUID.randomUUID().toString(); }
}
