package org.kuali.rice.kns.document.authorization;

import java.util.Set;
import java.util.HashSet;

public class MaintenanceDocumentPresentationControllerBase extends DocumentPresentationControllerBase implements MaintenanceDocumentPresentationController {
    public MaintenanceDocumentPresentationControllerBase() {}
    
    public Set<String> getConditionallyReadOnlyPropertyNames(org.kuali.rice.kns.document.MaintenanceDocument document) { return new HashSet<String>(); }
    public Set<String> getConditionallyReadOnlySectionIds(org.kuali.rice.kns.document.MaintenanceDocument document) { return new HashSet<String>(); }
    public Set<String> getConditionallyHiddenPropertyNames(org.kuali.rice.krad.bo.BusinessObject businessObject) { return new HashSet<String>(); }
    public Set<String> getConditionallyHiddenSectionIds(org.kuali.rice.krad.bo.BusinessObject businessObject) { return new HashSet<String>(); }
    public Set<String> getConditionallyRequiredPropertyNames(org.kuali.rice.kns.document.MaintenanceDocument document) { return new HashSet<String>(); }
}
