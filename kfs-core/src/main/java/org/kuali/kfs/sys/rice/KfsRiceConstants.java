/*
 * The Kuali Financial System, a comprehensive financial management system for higher education.
 *
 * Copyright 2005-2014 The Kuali Foundation
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kfs.sys.rice;

import org.kuali.rice.krad.util.KRADConstants;

/**
 * KFS-owned adapter for Rice KRAD constants. Delegates to the Rice KRADConstants
 * class to minimize direct Rice coupling across KFS source files.
 *
 * <p>Usage: import org.kuali.kfs.sys.rice.KfsRiceConstants instead of
 * org.kuali.rice.krad.util.KRADConstants in KFS code.</p>
 */
public final class KfsRiceConstants {

    private KfsRiceConstants() {
        // static constants only
    }

    // --- KRAD Constants delegation ---

    public static final String MODULE_NAME = KRADConstants.MODULE_NAME;
    public static final String KNS_NAMESPACE = KRADConstants.KNS_NAMESPACE;
    public static final String KRAD_NAMESPACE = KRADConstants.KRAD_NAMESPACE;
    public static final String KUALI_RICE_SYSTEM_NAMESPACE = KRADConstants.KUALI_RICE_SYSTEM_NAMESPACE;
    public static final String KUALI_RICE_WORKFLOW_NAMESPACE = KRADConstants.KUALI_RICE_WORKFLOW_NAMESPACE;

    public static final String YES_INDICATOR_VALUE = KRADConstants.YES_INDICATOR_VALUE;
    public static final String NO_INDICATOR_VALUE = KRADConstants.NO_INDICATOR_VALUE;
    public static final String BLANK_SPACE = KRADConstants.BLANK_SPACE;

    public static final String KUALI_ACTION_CAN_EDIT = KRADConstants.KUALI_ACTION_CAN_EDIT;
    public static final String KUALI_ACTION_CAN_SAVE = KRADConstants.KUALI_ACTION_CAN_SAVE;
    public static final String KUALI_ACTION_CAN_ROUTE = KRADConstants.KUALI_ACTION_CAN_ROUTE;
    public static final String KUALI_ACTION_CAN_CANCEL = KRADConstants.KUALI_ACTION_CAN_CANCEL;
    public static final String KUALI_ACTION_CAN_CLOSE = KRADConstants.KUALI_ACTION_CAN_CLOSE;
    public static final String KUALI_ACTION_CAN_COPY = KRADConstants.KUALI_ACTION_CAN_COPY;
    public static final String KUALI_ACTION_CAN_RELOAD = KRADConstants.KUALI_ACTION_CAN_RELOAD;
    public static final String KUALI_ACTION_CAN_BLANKET_APPROVE = KRADConstants.KUALI_ACTION_CAN_BLANKET_APPROVE;
    public static final String KUALI_ACTION_CAN_APPROVE = KRADConstants.KUALI_ACTION_CAN_APPROVE;
    public static final String KUALI_ACTION_CAN_DISAPPROVE = KRADConstants.KUALI_ACTION_CAN_DISAPPROVE;
    public static final String KUALI_ACTION_CAN_ANNOTATE = KRADConstants.KUALI_ACTION_CAN_ANNOTATE;
    public static final String KUALI_ACTION_CAN_AD_HOC_ROUTE = KRADConstants.KUALI_ACTION_CAN_AD_HOC_ROUTE;
    public static final String KUALI_ACTION_CAN_ACKNOWLEDGE = KRADConstants.KUALI_ACTION_CAN_ACKNOWLEDGE;
    public static final String KUALI_ACTION_CAN_FYI = KRADConstants.KUALI_ACTION_CAN_FYI;
    public static final String KUALI_ACTION_CAN_RECALL = KRADConstants.KUALI_ACTION_CAN_RECALL;
    public static final String KUALI_ACTION_CAN_COMPLETE = KRADConstants.KUALI_ACTION_CAN_COMPLETE;
    public static final String KUALI_ACTION_PERFORM_ROUTE_REPORT = KRADConstants.KUALI_ACTION_PERFORM_ROUTE_REPORT;
    public static final String KUALI_ACTION_CAN_SEND_ADHOC_REQUESTS = KRADConstants.KUALI_ACTION_CAN_SEND_ADHOC_REQUESTS;
    public static final String KUALI_ACTION_CAN_SEND_NOTE_FYI = KRADConstants.KUALI_ACTION_CAN_SEND_NOTE_FYI;

    public static final String ENVIRONMENT_KEY = KRADConstants.ENVIRONMENT_KEY;
    public static final String APPLICATION_URL_KEY = KRADConstants.APPLICATION_URL_KEY;
    public static final String EXTERNALIZABLE_HELP_URL_KEY = KRADConstants.EXTERNALIZABLE_HELP_URL_KEY;
    public static final String DOC_HANDLER_ACTION = KRADConstants.DOC_HANDLER_ACTION;
    public static final String WORKFLOW_URL_KEY = KRADConstants.WORKFLOW_URL_KEY;
    public static final String PROD_ENVIRONMENT_CODE_KEY = KRADConstants.PROD_ENVIRONMENT_CODE_KEY;

    public static final String MAINTENANCE_ACTION = KRADConstants.MAINTENANCE_ACTION;
    public static final String MAINTENANCE_NEW_ACTION = KRADConstants.MAINTENANCE_NEW_ACTION;
    public static final String MAINTENANCE_EDIT_ACTION = KRADConstants.MAINTENANCE_EDIT_ACTION;
    public static final String MAINTENANCE_COPY_ACTION = KRADConstants.MAINTENANCE_COPY_ACTION;
    public static final String MAINTENANCE_DELETE_ACTION = KRADConstants.MAINTENANCE_DELETE_ACTION;
    public static final String MAINTENANCE_NEWWITHEXISTING_ACTION = KRADConstants.MAINTENANCE_NEWWITHEXISTING_ACTION;

    public static final String DISPATCH_REQUEST_PARAMETER = KRADConstants.DISPATCH_REQUEST_PARAMETER;
    public static final String DOC_FORM_KEY = KRADConstants.DOC_FORM_KEY;
    public static final String PARAMETER_DOC_ID = KRADConstants.PARAMETER_DOC_ID;
    public static final String PARAMETER_COMMAND = KRADConstants.PARAMETER_COMMAND;
    public static final String METHOD_DISPLAY_DOC_SEARCH_VIEW = KRADConstants.METHOD_DISPLAY_DOC_SEARCH_VIEW;

    public static final String RETURN_LOCATION_PARAMETER = KRADConstants.RETURN_LOCATION_PARAMETER;
    public static final String BUSINESS_OBJECT_CLASS_ATTRIBUTE = KRADConstants.BUSINESS_OBJECT_CLASS_ATTRIBUTE;
    public static final String LOOKUP_RESULTS_SEQUENCE_NUMBER = KRADConstants.LOOKUP_RESULTS_SEQUENCE_NUMBER;
    public static final String LOOKED_UP_COLLECTION_NAME = KRADConstants.LOOKED_UP_COLLECTION_NAME;
    public static final String MULTIPLE_VALUE_LOOKUP_PREVIOUSLY_SELECTED_OBJ_IDS_PARAM = KRADConstants.MULTIPLE_VALUE_LOOKUP_PREVIOUSLY_SELECTED_OBJ_IDS_PARAM;

    public static final String GLOBAL_ERRORS = KRADConstants.GLOBAL_ERRORS;
    public static final String GLOBAL_MESSAGES = KRADConstants.GLOBAL_MESSAGES;

    public static final String EMPTY_STRING = KRADConstants.EMPTY_STRING;
}
