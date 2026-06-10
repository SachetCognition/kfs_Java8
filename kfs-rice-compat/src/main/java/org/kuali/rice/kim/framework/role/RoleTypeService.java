package org.kuali.rice.kim.framework.role;
public interface RoleTypeService {    default java.util.Map<String, String> translateInputAttributes(java.util.Map<String, String> inputAttributes) { return inputAttributes; }
    default java.util.List<org.kuali.rice.kim.api.type.KimAttributeField> getAttributeDefinitions(String kimTypeId) { return new java.util.ArrayList<>(); }
    default java.util.List<String> getUniqueAttributes(String kimTypeId) { return new java.util.ArrayList<>(); }
    default java.util.List<String> getWorkflowRoutingAttributes(String routeLevel) { return new java.util.ArrayList<>(); }
    default String getWorkflowDocumentTypeName() { return null; }
}