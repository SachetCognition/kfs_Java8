package org.kuali.rice.kns.kim.role;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class DerivedRoleTypeServiceBase {
    public DerivedRoleTypeServiceBase() {}
    
    public List<org.kuali.rice.kim.api.role.RoleMembership> getRoleMembersFromDerivedRole(String namespaceCode, String roleName, Map<String, String> qualification) {
        return new ArrayList<org.kuali.rice.kim.api.role.RoleMembership>();
    }
    
    public boolean hasDerivedRole(String principalId, List<String> groupIds, String namespaceCode, String roleName, Map<String, String> qualification) {
        return false;
    }
    
    public boolean dynamicRoleMembership(String namespaceCode, String roleName) {
        return true;
    }
}
