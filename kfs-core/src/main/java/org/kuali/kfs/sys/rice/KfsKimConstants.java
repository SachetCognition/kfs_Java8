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

import org.kuali.rice.kim.api.KimConstants;

/**
 * KFS-owned adapter for Rice KIM (Kuali Identity Management) constants.
 * Delegates to the Rice KimConstants class to minimize direct Rice coupling.
 */
public final class KfsKimConstants {

    private KfsKimConstants() {
        // static constants only
    }

    public static final String NAMESPACE_CODE = KimConstants.NAMESPACE_CODE;

    public static final class AttributeConstants {
        public static final String PRINCIPAL_ID = KimConstants.AttributeConstants.PRINCIPAL_ID;
        public static final String DOCUMENT_NUMBER = KimConstants.AttributeConstants.DOCUMENT_NUMBER;
        public static final String DOCUMENT_TYPE_NAME = KimConstants.AttributeConstants.DOCUMENT_TYPE_NAME;
        public static final String ROUTE_NODE_NAME = KimConstants.AttributeConstants.ROUTE_NODE_NAME;
        public static final String NAMESPACE_CODE = KimConstants.AttributeConstants.NAMESPACE_CODE;
        public static final String COMPONENT_NAME = KimConstants.AttributeConstants.COMPONENT_NAME;
        public static final String PROPERTY_NAME = KimConstants.AttributeConstants.PROPERTY_NAME;
        public static final String ROLE_NAME = KimConstants.AttributeConstants.ROLE_NAME;
        public static final String PERMISSION_NAME = KimConstants.AttributeConstants.PERMISSION_NAME;
        public static final String RESPONSIBILITY_NAME = KimConstants.AttributeConstants.RESPONSIBILITY_NAME;
        public static final String GROUP_NAME = KimConstants.AttributeConstants.GROUP_NAME;
        public static final String REQUIRED = KimConstants.AttributeConstants.REQUIRED;
        public static final String ACTION_REQUEST_CD = KimConstants.AttributeConstants.ACTION_REQUEST_CD;
    }

    public static final class PermissionNames {
        public static final String LOG_IN = KimConstants.PermissionNames.LOG_IN;
        public static final String ADMIN_PESSIMISTIC_LOCKING = KimConstants.PermissionNames.ADMIN_PESSIMISTIC_LOCKING;
        public static final String OVERRIDE_ENTITY_PRIVACY_PREFERENCES = KimConstants.PermissionNames.OVERRIDE_ENTITY_PRIVACY_PREFERENCES;
        public static final String MODIFY_ENTITY = KimConstants.PermissionNames.MODIFY_ENTITY;
        public static final String ACCESS_LOCKED_MODULE = KimConstants.PermissionNames.ACCESS_LOCKED_MODULE;
    }

    public static final class PermissionTemplateNames {
        public static final String INITIATE_DOCUMENT = KimConstants.PermissionTemplateNames.INITIATE_DOCUMENT;
        public static final String EDIT_DOCUMENT = KimConstants.PermissionTemplateNames.EDIT_DOCUMENT;
        public static final String BLANKET_APPROVE_DOCUMENT = KimConstants.PermissionTemplateNames.BLANKET_APPROVE_DOCUMENT;
        public static final String OPEN_DOCUMENT = KimConstants.PermissionTemplateNames.OPEN_DOCUMENT;
    }
}
