package org.kuali.rice.kim.api;

public final class KimConstants {
    public static final class Namespaces {
        public static final String KIM_NAMESPACE_PREFIX = "http://rice.kuali.org/kim/v2_0";
        public static final String KIM_NAMESPACE_2_0 = "http://rice.kuali.org/kim/v2_0";
        private Namespaces() {}
    }
    public static final class KimGroupMemberTypes {
        public static final String PRINCIPAL_MEMBER_TYPE = "P";
        public static final String GROUP_MEMBER_TYPE = "G";
        public static final String ROLE_MEMBER_TYPE = "R";
        private KimGroupMemberTypes() {}
    }
    public static final class PermissionTemplateNames {
        public static final String DEFAULT = "Default";
        public static final String DOCUMENT_DESCRIPTION = "Edit Document Description";
        public static final String USE_TRANSACTIONAL_DOCUMENT = "Use Transactional Document";
        private PermissionTemplateNames() {}
    }
    public static final class PersonExternalIdentifierTypes {
        public static final String TAX = "TAX";
        private PersonExternalIdentifierTypes() {}
    }
    public static final class EntityTypes {
        public static final String PERSON = "PERSON";
        public static final String SYSTEM = "SYSTEM";
        private EntityTypes() {}
    }
    public static final String KIM_TYPE_DEFAULT_NAMESPACE = "KUALI";
    public static final String KIM_TYPE_DEFAULT_NAME = "Default";
    public static final String DEFAULT_NAMESPACE = "KUALI";
    
    public static final class PrimaryKeyConstants {
        public static final String PRINCIPAL_ID = "principalId";
        public static final String GROUP_ID = "id";
        public static final String ROLE_ID = "id";
        public static final String DELEGATION_ID = "delegationId";
        public static final String KIM_TYPE_ID = "id";
        public static final String PERMISSION_ID = "id";
        public static final String RESPONSIBILITY_ID = "id";
        private PrimaryKeyConstants() {}
    }
    
    private KimConstants() {}
}
