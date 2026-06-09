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
        private DetailTypes() {}
    }
    
    private KRADConstants() {}
}
