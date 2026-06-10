package org.kuali.rice.kew.api;
public final class KewApiConstants {
    public static final String ACTION_TAKEN_APPROVED_CD = "A";
    public static final String ACTION_TAKEN_COMPLETED_CD = "C";
    public static final String ACTION_TAKEN_DENIED_CD = "D";
    public static final String ACTION_TAKEN_CANCELED_CD = "X";
    public static final String ACTION_TAKEN_ROUTED_CD = "R";
    public static final String ACTION_TAKEN_ACKNOWLEDGED_CD = "K";
    public static final String ACTION_TAKEN_FYI_CD = "F";
    public static final String ACTION_TAKEN_SAVED_CD = "S";
    public static final String ACTION_REQUEST_FYI_REQ = "F";
    public static final String DOCUMENT_STATUS_INITIATED = "I";
    public static final String DOCUMENT_STATUS_SAVED = "S";
    public static final String DOCUMENT_STATUS_ENROUTE = "R";
    public static final String DOCUMENT_STATUS_PROCESSED = "P";
    public static final String DOCUMENT_STATUS_FINAL = "F";
    public static final String DOCUMENT_STATUS_CANCELED = "X";
    public static final String DOCUMENT_STATUS_DISAPPROVED = "D";
    public static final String KEW_URL = "kew.url";
    public static final String PROD_ENVIRONMENT_CODE = "PRD";
    public static final String MAINTAINABLE_ERROR_PREFIX = "document.newMaintainableObject.";
    
    public static final class SearchableAttributeConstants {
        public static final String DATA_TYPE_STRING = "string";
        public static final String DATA_TYPE_DATE = "date";
        public static final String DATA_TYPE_LONG = "long";
        public static final String DATA_TYPE_FLOAT = "float";
        private SearchableAttributeConstants() {}
    }
    
    private KewApiConstants() {}
    public static final int DOCUMENT_LOOKUP_DEFAULT_RESULT_CAP = 20;
    public static final String KEW_NAMESPACE = "KEW_NAMESPACE";
    public static final String DOC_SEARCH_RESULT_CAP = "DOC_SEARCH_RESULT_CAP";
    public static final String DOC_SEARCH_FETCH_MORE_ITERATION_LIMIT = "DOC_SEARCH_FETCH_MORE_ITERATION_LIMIT";
    public static final String ACTIONLIST_COMMAND = "displayActionListView";
    public static final String ROUTE_HEADER_SAVED_CD = "S";
    public static final String ROUTE_HEADER_ENROUTE_CD = "R";
}