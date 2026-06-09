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

import org.kuali.rice.kew.api.KewApiConstants;

/**
 * KFS-owned adapter for Rice KEW (Kuali Enterprise Workflow) constants.
 * Delegates to the Rice KewApiConstants class to minimize direct Rice coupling.
 */
public final class KfsKewConstants {

    private KfsKewConstants() {
        // static constants only
    }

    public static final String ROUTE_HEADER_INITIATED_CD = KewApiConstants.ROUTE_HEADER_INITIATED_CD;
    public static final String ROUTE_HEADER_SAVED_CD = KewApiConstants.ROUTE_HEADER_SAVED_CD;
    public static final String ROUTE_HEADER_ENROUTE_CD = KewApiConstants.ROUTE_HEADER_ENROUTE_CD;
    public static final String ROUTE_HEADER_PROCESSED_CD = KewApiConstants.ROUTE_HEADER_PROCESSED_CD;
    public static final String ROUTE_HEADER_FINAL_CD = KewApiConstants.ROUTE_HEADER_FINAL_CD;
    public static final String ROUTE_HEADER_CANCEL_CD = KewApiConstants.ROUTE_HEADER_CANCEL_CD;
    public static final String ROUTE_HEADER_DISAPPROVED_CD = KewApiConstants.ROUTE_HEADER_DISAPPROVED_CD;
    public static final String ROUTE_HEADER_EXCEPTION_CD = KewApiConstants.ROUTE_HEADER_EXCEPTION_CD;

    public static final String ROUTE_HEADER_INITIATED_LABEL = KewApiConstants.ROUTE_HEADER_INITIATED_LABEL;
    public static final String ROUTE_HEADER_SAVED_LABEL = KewApiConstants.ROUTE_HEADER_SAVED_LABEL;
    public static final String ROUTE_HEADER_ENROUTE_LABEL = KewApiConstants.ROUTE_HEADER_ENROUTE_LABEL;
    public static final String ROUTE_HEADER_PROCESSED_LABEL = KewApiConstants.ROUTE_HEADER_PROCESSED_LABEL;
    public static final String ROUTE_HEADER_FINAL_LABEL = KewApiConstants.ROUTE_HEADER_FINAL_LABEL;
    public static final String ROUTE_HEADER_CANCEL_LABEL = KewApiConstants.ROUTE_HEADER_CANCEL_LABEL;
    public static final String ROUTE_HEADER_DISAPPROVED_LABEL = KewApiConstants.ROUTE_HEADER_DISAPPROVED_LABEL;
    public static final String ROUTE_HEADER_EXCEPTION_LABEL = KewApiConstants.ROUTE_HEADER_EXCEPTION_LABEL;

    public static final String ACTION_REQUEST_APPROVE_REQ = KewApiConstants.ACTION_REQUEST_APPROVE_REQ;
    public static final String ACTION_REQUEST_ACKNOWLEDGE_REQ = KewApiConstants.ACTION_REQUEST_ACKNOWLEDGE_REQ;
    public static final String ACTION_REQUEST_FYI_REQ = KewApiConstants.ACTION_REQUEST_FYI_REQ;
    public static final String ACTION_REQUEST_COMPLETE_REQ = KewApiConstants.ACTION_REQUEST_COMPLETE_REQ;
}
