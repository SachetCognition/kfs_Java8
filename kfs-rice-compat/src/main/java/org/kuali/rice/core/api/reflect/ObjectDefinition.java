package org.kuali.rice.core.api.reflect;
public class ObjectDefinition {
    private String className;
    public ObjectDefinition(String className) { this.className = className; }
    public ObjectDefinition(Class clazz) { this.className = clazz.getName(); }
    public String getClassName() { return className; }
}
