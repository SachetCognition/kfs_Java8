package org.kuali.rice.kns.util;
public class FieldUtils {
    public static org.kuali.rice.kns.web.ui.Field getPropertyField(Class clazz, String propertyName, boolean forLookup) { return new org.kuali.rice.kns.web.ui.Field(); }
    public static void setInquiryURL(org.kuali.rice.kns.web.ui.Field field, Object bo, String propertyName) {}
    public static void populateFieldsFromBusinessObject(java.util.List fields, Object bo) {}
    public static java.util.List<org.kuali.rice.kns.web.ui.Row> wrapFields(java.util.List<org.kuali.rice.kns.web.ui.Field> fields, int numColumns) { return new java.util.ArrayList<>(); }
    public static java.util.List<org.kuali.rice.kns.web.ui.Field> createAndPopulateFieldsForLookup(java.util.List<String> lookupFieldAttributeList, java.util.List<String> readOnlyFieldsList, Class businessObjectClass) { return new java.util.ArrayList<>(); }
}