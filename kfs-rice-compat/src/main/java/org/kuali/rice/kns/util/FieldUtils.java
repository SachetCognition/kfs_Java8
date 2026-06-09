package org.kuali.rice.kns.util;

import java.util.List;
import java.util.ArrayList;

public class FieldUtils {
    private FieldUtils() {}
    
    public static List<org.kuali.rice.kns.web.ui.Field> createAndPopulateFieldsForLookup(List<String> lookupFieldAttributeList, List<String> readOnlyFieldsList, Class businessObjectClass) {
        return new ArrayList<org.kuali.rice.kns.web.ui.Field>();
    }
    
    public static void populateFieldsFromBusinessObject(List<org.kuali.rice.kns.web.ui.Field> fields, Object bo) {}
}
