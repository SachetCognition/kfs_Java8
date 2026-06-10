package org.apache.ojb.broker.metadata;
public class ClassDescriptor implements java.io.Serializable {
    public ClassDescriptor() {}
    public Class getClassOfObject() { return null; }
    public FieldDescriptor[] getFieldDescriptions() { return new FieldDescriptor[0]; }
    public FieldDescriptor getFieldDescriptorByName(String name) { return null; }
    public CollectionDescriptor getCollectionDescriptorByName(String name) { return null; }
    public String getTableName() { return null; }
    public FieldDescriptor getAutoIncrementField() { return null; }
    public FieldDescriptor[] getPkFields() { return new FieldDescriptor[0]; }
    public String getFullTableName() { return null; }
    public ObjectReferenceDescriptor getObjectReferenceDescriptorByName(String name) { return null; }
}
