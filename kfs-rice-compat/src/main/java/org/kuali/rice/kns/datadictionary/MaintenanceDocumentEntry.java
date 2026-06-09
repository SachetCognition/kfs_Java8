package org.kuali.rice.kns.datadictionary;
public class MaintenanceDocumentEntry extends org.kuali.rice.krad.datadictionary.MaintenanceDocumentEntry
        implements KNSDocumentEntry {
    private Class derivedValuesSetterClass;
    public MaintenanceDocumentEntry() {}
    public Class getDerivedValuesSetterClass() { return derivedValuesSetterClass; }
    public void setDerivedValuesSetterClass(Class clazz) { this.derivedValuesSetterClass = clazz; }
}
