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
package org.kuali.kfs.sec.document;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kfs.sec.businessobject.SecurityDefinition;
import org.kuali.kfs.sec.businessobject.SecurityDefinitionDocumentType;
import org.kuali.kfs.sec.service.AccessSecurityService;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kim.api.common.template.Template;
import org.kuali.rice.kim.api.permission.Permission;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.KRADConstants;


/**
 * Maintainable implementation for the Security Definition maintenance document. Hooks into Post processing to create the KIM permissions from the definition records
 */
public class SecurityDefinitionMaintainableImpl extends AbstractSecurityModuleMaintainable {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(SecurityDefinitionMaintainableImpl.class);

    /**
     * @see org.kuali.rice.kns.maintenance.KualiMaintainableImpl#doRouteStatusChange(org.kuali.rice.krad.bo.DocumentHeader)
     */
    @Override
    public void doRouteStatusChange(DocumentHeader documentHeader) {  }

    /**
     * Creates a new role for the definition (if the definition is new), then grants to the role any new permissions granted for the definition. Also update role active indicator
     * if indicator changed values on the definition
     *
     * @param oldSecurityDefinition SecurityDefinition record before updates
     * @param newSecurityDefinition SecurityDefinition after updates
     */
    protected void createOrUpdateDefinitionRole(SecurityDefinition oldSecurityDefinition, SecurityDefinition newSecurityDefinition ) {  }
}
