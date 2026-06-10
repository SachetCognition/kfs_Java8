package org.kuali.rice.kim.api.role;
import java.util.List;
import java.util.Map;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
public interface RoleService {
    boolean principalHasRole(String principalId, List<String> roleIds, Map<String, String> qualification);
    List<String> getMemberParentRoleIds(String memberType, String memberId);
    Role getRole(String roleId);
    Role getRoleByNamespaceCodeAndName(String namespaceCode, String roleName);
    String getRoleIdByNamespaceCodeAndName(String namespaceCode, String roleName);
    RoleMember assignPrincipalToRole(String principalId, String namespaceCode, String roleName, Map<String, String> qualifier);
    RoleMember assignGroupToRole(String groupId, String namespaceCode, String roleName, Map<String, String> qualifier);
    RoleMember assignRoleToRole(String roleId, String namespaceCode, String roleName, Map<String, String> qualifier);
    void removePrincipalFromRole(String principalId, String namespaceCode, String roleName, Map<String, String> qualifier);
    RoleMember updateRoleMember(RoleMember roleMember);
    List<RoleMembership> getRoleMembers(List<String> roleIds, Map<String, String> qualification);
    RoleMemberQueryResults findRoleMembers(QueryByCriteria criteria);
    RoleMemberQueryResults findRoleMembers(QueryByCriteria.Builder criteria);
    void removeRoleFromRole(String roleId, String toRemoveRoleId, String memberTypeCode, java.util.Map<String, String> qualifier);
    java.util.List<String> getRoleMemberPrincipalIds(String namespaceCode, String roleName, Object qualification);
    org.kuali.rice.kim.api.role.Role updateRole(org.kuali.rice.kim.api.role.Role role);
    org.kuali.rice.kim.api.role.Role createRole(org.kuali.rice.kim.api.role.Role role);
    public java.util.List getRoleResponsibilities(java.lang.String arg0);
    org.kuali.rice.kim.api.type.KimType getKimType(String typeId);
}