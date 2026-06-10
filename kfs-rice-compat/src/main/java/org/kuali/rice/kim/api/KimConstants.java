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
        public static final String MODIFY_FIELD = "Modify Field";
        public static final String LOOK_UP_RECORDS = "Look Up Records";
        public static final String MAINTAIN_SYSTEM_PARAMETER = "Maintain System Parameter";
        public static final String VIEW_FIELD = "View Field";
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
    
    public static final class KimUIConstants {
        public static final String KIM_ROLE_DOCUMENT_SHORT_KEY = "ROLD";
        public static final String OR_OPERATOR = "|";
        public static final String KIM_URL_KEY = "kim.url";
        public static final String URL_SEPARATOR = "/";
        public static final String PARAMETERIZED_URL_SEPARATOR = "/";
        public static final String KIM_APPLICATION = "kim";
        public static final java.util.Map<String, String> KIM_MEMBER_TYPES_MAP = new java.util.HashMap<>();
        static {
            KIM_MEMBER_TYPES_MAP.put("P", "Principal");
            KIM_MEMBER_TYPES_MAP.put("G", "Group");
            KIM_MEMBER_TYPES_MAP.put("R", "Role");
        }
        private KimUIConstants() {}
    }

    private KimConstants() {}
    

    public static final class AttributeConstants {
        public static final String NAMESPACE_CODE = "namespaceCode";
        public static final String COMPONENT_NAME = "componentName";
        public static final String PERMISSION_NAME = "permissionName";
        public static final String ACTION_DETAILS_AT_ROLE_MEMBER_LEVEL = "actionDetailsAtRoleMemberLevel";
        public static final String PROPERTY_NAME = "propertyName";
        public static final String DOCUMENT_TYPE_NAME = "documentTypeName";
        public static final String MODIFY_FIELD = "modifyField";
        public static final String VIEW_FIELD = "viewField";
        public static final String ROUTE_NODE_NAME = "routeNodeName";
        public static final String ROLE_NAME = "roleName";
        public static final String REQUIRED = "required";
        public static final String ACTION_REQUEST_CD = "actionRequestCd";
        public static final String BEAN_NAME = "beanName";
        public static final String GROUP_NAME = "groupName";
        public static final String DOCUMENT_NUMBER = "documentNumber";
        public static final String SECTION_ID = "sectionId";
        public static final String EDIT_MODE = "editMode";
        public static final String CAMPUS_CODE = "campusCode";
        public static final String CHART_OF_ACCOUNTS_CODE = "chartOfAccountsCode";
        public static final String ORGANIZATION_CODE = "organizationCode";
        public static final String ACCOUNT_NUMBER = "accountNumber";
        public static final String SUB_ACCOUNT_NUMBER = "subAccountNumber";
        public static final String FINANCIAL_DOCUMENT_TOTAL_AMOUNT = "financialDocumentTotalAmount";
        private AttributeConstants() {}
    }

}
