package org.kuali.rice.krad.util;

public class KRADConstants {
    public static final String KNS_NAMESPACE = "KR-NS";
    public static final String KRAD_NAMESPACE = "KR-KRAD";
    public static final String KUALI_RICE_SYSTEM_NAMESPACE = "KR-SYS";
    public static final String KUALI_RICE_WORKFLOW_NAMESPACE = "KR-WKFLW";
    public static final String DEFAULT_NAMESPACE = "KUALI";
    
    public static final String MAINTENANCE_NEW_ACTION = "New";
    public static final String MAINTENANCE_COPY_ACTION = "Copy";
    public static final String MAINTENANCE_EDIT_ACTION = "Edit";
    public static final String MAINTENANCE_NEWWITHEXISTING_ACTION = "newWithExisting";
    public static final String MAINTENANCE_DELETE_ACTION = "Delete";
    
    public static final String EMPTY_STRING = "";
    public static final String QUESTION_CLICKED_BUTTON = "buttonClicked";
    public static final String DOCUMENT_HEADER_PROPERTY_NAME = "documentHeader";
    public static final String DISPATCH_REQUEST_PARAMETER = "methodToCall";
    public static final String DOC_FORM_KEY = "docFormKey";
    public static final String RETURN_LOCATION_PARAMETER = "returnLocation";
    public static final String LOOKUP_RESULTS_SEQUENCE_NUMBER = "lookupResultsSequenceNumber";
    public static final String LOOKUP_RESULTS_SEQUENCE = "LOOKUP_RESULTS_SEQUENCE";
    public static final String LOOKUP_RESULTS_BO_CLASS_NAME = "lookupResultsBOClassName";
    public static final String REFERENCES_TO_REFRESH = "referencesToRefresh";
    public static final String MULTIPLE_VALUE = "multipleValues";
    public static final String BACK_LOCATION = "backLocation";
    public static final String APPLICATION_URL = "application.url";
    public static final String DOCUMENT_TYPE_CODE = "DTYPE";
    
    public static final String EXTERNALIZABLE_HELP_URL_KEY = "externalizable.help.url";
    public static final String EXTERNALIZABLE_IMAGES_URL_KEY = "kr.externalizable.images.url";
    public static final String PROD_ENVIRONMENT_CODE = "PRD";
    public static final String USE_TRANSACTIONAL_DOCUMENT = "useTransactionalDocument";
    public static final String DOCUMENT = "document";
    public static final String DOCUMENT_DESCRIPTION = "documentDescription";
    
    public static final String KUALI_ACTION_CAN_EDIT = "canEdit";
    
    public static final String GLOBAL_ERRORS = "org.kuali.rice.krad.GLOBAL_ERRORS";
    public static final String GLOBAL_MESSAGES = "org.kuali.rice.krad.GLOBAL_MESSAGES";
    
    public static final class DetailTypes {
        public static final String DOCUMENT_DETAIL_TYPE = "Document";
        public static final String ALL_DETAIL_TYPE = "All";
        public static final String LOOKUP_DETAIL_TYPE = "Lookup";
        public static final String LOOKUP_PARM_DETAIL_TYPE = "Lookup";
        public static final String DOCUMENT_SEARCH_DETAIL_TYPE = "DocumentSearch";
        private DetailTypes() {}
    }
    
    public static final String KUALI_ACTION_CAN_COPY = "canCopy";
    
    public static final String QUESTION_INST_ATTRIBUTE_NAME = "questionIndex";
    public static final String QUESTION_REASON_ATTRIBUTE_NAME = "reason";
    public static final String QUESTION_CONTEXT = "context";
    public static final String CONFIRMATION_QUESTION = "ConfirmationQuestion";
    public static final String MAINTENANCE_EDIT_METHOD_TO_CALL = "edit";
    public static final String MAINTENANCE_COPY_METHOD_TO_CALL = "copy";

    public static final class Maintenance {
        public static final String REQUEST_MAPPING_MAINTENANCE = "/maintenance";
        public static final String LOCK_AFTER_VALUE_DELIM = "::";
        private Maintenance() {}
    }

    public static final String ADD_PREFIX = "add";
    public static final String BUSINESS_OBJECT_CLASS_ATTRIBUTE = "businessObjectClassName";
    public static final String DISPLAY_ALL_INACTIVATION_BLOCKERS_ACTION = "displayAllInactivationBlockers";
    public static final String METHOD_DISPLAY_ALL_INACTIVATION_BLOCKERS = "displayAllInactivationBlockers";

    private KRADConstants() {}
    public static final String KRAD_LOOKUP_URL_KEY = "kradLookupUrl";
    public static final String DOCUMENT_PROPERTY_NAME = "DOCUMENT_PROPERTY_NAME";
    public static final String KUALI_ACTION_CAN_BLANKET_APPROVE = "KUALI_ACTION_CAN_BLANKET_APPROVE";
    public static final String INQUIRY_ACTION = "INQUIRY_ACTION";
    public static final int DEFAULT_NUM_OF_COLUMNS = 1;
    public static final int RESULTS_DEFAULT_MAX_COLUMN_LENGTH = 50;
    public static final String PARAM_MAINTENANCE_VIEW_MODE = "maintenanceViewMode";
    public static final String APPLICATION_URL_KEY = "application.url";
    public static final String LOOKUP_ACTION = "lookup";
    public static final String PARAM_MAINTENANCE_VIEW_MODE_LOOKUP = "lookup";
    public static final String LOOKUP_RANGE_LOWER_BOUND_PROPERTY_PREFIX = "rangeLowerBoundKeyPrefix_";
    public static final String LOOKUP_DEFAULT_RANGE_SEARCH_UPPER_BOUND_LABEL = "to";
}