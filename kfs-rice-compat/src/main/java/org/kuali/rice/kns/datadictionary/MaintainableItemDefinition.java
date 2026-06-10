package org.kuali.rice.kns.datadictionary;
public class MaintainableItemDefinition {
    protected String name;
    protected String defaultValue;
    protected Class defaultValueFinderClass;
    public MaintainableItemDefinition() {}
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
