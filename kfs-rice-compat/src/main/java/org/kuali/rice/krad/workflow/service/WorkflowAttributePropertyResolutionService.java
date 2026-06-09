package org.kuali.rice.krad.workflow.service;

public interface WorkflowAttributePropertyResolutionService {
    java.util.List<java.util.Map<java.lang.String, java.lang.String>> resolveRoutingTypeQualifiers(org.kuali.rice.krad.document.Document p0, org.kuali.rice.krad.datadictionary.RoutingTypeDefinition p1);
    java.util.List<org.kuali.rice.kew.api.document.attribute.DocumentAttribute> resolveSearchableAttributeValues(org.kuali.rice.krad.document.Document p0, org.kuali.rice.krad.datadictionary.WorkflowAttributes p1);
    java.lang.Object getPropertyByPath(java.lang.Object p0, java.lang.String p1);
    java.lang.String determineFieldDataType(java.lang.Class<? extends org.kuali.rice.krad.bo.BusinessObject> p0, java.lang.String p1);
    org.kuali.rice.kew.api.document.attribute.DocumentAttribute buildSearchableAttribute(java.lang.Class<? extends org.kuali.rice.krad.bo.BusinessObject> p0, java.lang.String p1, java.lang.Object p2);
}
