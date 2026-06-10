package org.kuali.rice.kns.kim.role;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class RoleTypeServiceBase extends org.kuali.rice.kns.kim.type.DataDictionaryTypeServiceBase implements org.kuali.rice.kim.framework.role.RoleTypeService, org.kuali.rice.kim.framework.common.delegate.DelegationTypeService {
    public RoleTypeServiceBase() {}
    
    public boolean doesRoleQualifierMatchQualification(Map<String, String> qualification, Map<String, String> roleQualifier) { return false; }
    public List<org.kuali.rice.kim.api.role.RoleMembership> getMatchingRoleMemberships(Map<String, String> qualification, List<org.kuali.rice.kim.api.role.RoleMembership> roleMemberList) { return new ArrayList<org.kuali.rice.kim.api.role.RoleMembership>(); }
    public List<org.kuali.rice.kim.api.role.RoleMembership> getRoleMembersFromDerivedRole(String namespaceCode, String roleName, Map<String, String> qualification) { return new ArrayList<org.kuali.rice.kim.api.role.RoleMembership>(); }
    public boolean hasDerivedRole(String principalId, List<String> groupIds, String namespaceCode, String roleName, Map<String, String> qualification) { return false; }
    public boolean isDerivedRoleType() { return false; }
    public Map<String, String> convertQualificationForMemberRoles(String namespaceCode, String roleName, String memberRoleNamespaceCode, String memberRoleName, Map<String, String> qualification) { return new HashMap<String, String>(); }
    public boolean doesDelegationQualifierMatchQualification(Map<String, String> qualification, Map<String, String> delegationQualifier) { return false; }
    public boolean dynamicRoleMembership(String namespaceCode, String roleName) { return false; }
    public List<String> getQualifiersForExactMatch() { return new ArrayList<String>(); }
    public java.util.List<String> getUniqueAttributes(String kimTypeId) { return new java.util.ArrayList<>(); }
    public java.util.List<String> getWorkflowRoutingAttributes(String routeLevel) { return new java.util.ArrayList<>(); }
    public String getWorkflowDocumentTypeName() { return null; }
    protected org.kuali.rice.kns.service.DataDictionaryService getDataDictionaryService() { return null; }
    protected java.util.List<String> extractErrorsFromGlobalVariablesErrorMap(String attributeName) { return new java.util.ArrayList<>(); }
}