package org.kuali.rice.kim.api.role;
import java.util.List;
import java.util.Map;
public interface RoleService {
    boolean principalHasRole(String principalId, List<String> roleIds, Map<String, String> qualification);
    List<String> getMemberParentRoleIds(String memberType, String memberId);
    Role getRole(String roleId);
    Role getRoleByNamespaceCodeAndName(String namespaceCode, String roleName);
    RoleMember assignPrincipalToRole(String principalId, String namespaceCode, String roleName, Map<String, String> qualifier);
    void removePrincipalFromRole(String principalId, String namespaceCode, String roleName, Map<String, String> qualifier);
    RoleMember updateRoleMember(RoleMember roleMember);
    List<RoleMembership> getRoleMembers(List<String> roleIds, Map<String, String> qualification);
}
