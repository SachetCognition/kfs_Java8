package org.kuali.rice.kim.api.group;

import java.util.List;
import java.util.Map;

public interface GroupService {
    Group getGroup(String groupId);
    Group getGroupByNamespaceCodeAndName(String namespaceCode, String groupName);
    List<String> getMemberPrincipalIds(String groupId);
    boolean isMemberOfGroup(String principalId, String groupId);
    List<Group> getGroups(java.util.Collection<String> groupIds);
    String getGroupIdByNamespaceCodeAndName(String namespaceCode, String groupName);
    boolean isGroupMemberOfGroup(String groupMemberId, String groupId);
    List<String> getDirectMemberPrincipalIds(String groupId);
}
