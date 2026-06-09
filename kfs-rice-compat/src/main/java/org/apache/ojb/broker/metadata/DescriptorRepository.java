package org.apache.ojb.broker.metadata;
public class DescriptorRepository implements java.io.Serializable {
    public DescriptorRepository() {}
    public ClassDescriptor getDescriptorFor(Class clazz) { return null; }
    public ClassDescriptor getDescriptorFor(String className) { return null; }
    public boolean hasDescriptorFor(Class clazz) { return false; }
    public java.util.Iterator iterator() { return java.util.Collections.emptyIterator(); }
}
