package org.kuali.rice.kns.web.ui;

import java.util.List;
import java.util.Set;

public class FieldBridge {
    public FieldBridge() {}

    public static void setupField(Field field, org.kuali.rice.kns.datadictionary.FieldDefinitionI definition, Set<String> displayEditMode) {}
    public static void populateFieldFromBusinessObject(Field field, org.kuali.rice.krad.bo.BusinessObject bo) {}
    public static Field toField(org.kuali.rice.kns.datadictionary.MaintainableItemDefinition item, org.kuali.rice.kns.datadictionary.MaintainableSectionDefinition section, org.kuali.rice.krad.bo.BusinessObject bo, org.kuali.rice.kns.maintenance.Maintainable maintainable, Section uiSection, List<String> displayedFieldNames, Set<String> conditionallyRequired) throws InstantiationException, IllegalAccessException { return null; }
    public static List<Field> getNewFormFields(org.kuali.rice.kns.datadictionary.CollectionDefinitionI collection, org.kuali.rice.krad.bo.BusinessObject bo, org.kuali.rice.kns.maintenance.Maintainable maintainable, List<String> displayedFieldNames, Set<String> conditionallyRequired, StringBuffer containerRowErrorKey, String parents, boolean hideAdd, int numberOfColumns) { return new java.util.ArrayList<Field>(); }
    public static List<Field> constructContainerField(org.kuali.rice.kns.datadictionary.CollectionDefinitionI collection, String label, org.kuali.rice.krad.bo.BusinessObject bo, boolean isAdd, int index, String prefix, List<Field> fields) { return new java.util.ArrayList<Field>(); }
    public static List<Field> getNewFormFields(org.kuali.rice.kns.datadictionary.MaintainableCollectionDefinition collection, org.kuali.rice.krad.bo.BusinessObject bo, org.kuali.rice.kns.maintenance.Maintainable maintainable, List<String> displayedFieldNames, Set<String> conditionallyRequired, StringBuffer containerRowErrorKey, int numberOfColumns) { return new java.util.ArrayList<Field>(); }
    public static Field toField(org.kuali.rice.kns.datadictionary.FieldDefinition definition, org.kuali.rice.krad.bo.BusinessObject bo, Section uiSection) { return null; }
    public static org.kuali.rice.krad.service.DataDictionaryService getDataDictionaryService() { return null; }
    public static org.kuali.rice.krad.service.PersistenceStructureService getPersistenceStructureService() { return null; }
    public static org.kuali.rice.kns.service.BusinessObjectDictionaryService getBusinessObjectDictionaryService() { return null; }
    public static org.kuali.rice.kns.service.MaintenanceDocumentDictionaryService getMaintenanceDocumentDictionaryService() { return null; }
}
