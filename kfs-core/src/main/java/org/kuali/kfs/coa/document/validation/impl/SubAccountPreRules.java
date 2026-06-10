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
package org.kuali.kfs.coa.document.validation.impl;

import org.apache.commons.lang.StringUtils;
import org.kuali.kfs.coa.businessobject.A21IndirectCostRecoveryAccount;
import org.kuali.kfs.coa.businessobject.A21SubAccount;
import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.coa.businessobject.IndirectCostRecoveryAccount;
import org.kuali.kfs.coa.businessobject.SubAccount;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.document.authorization.MaintenanceDocumentRestrictions;
import org.kuali.rice.kns.service.BusinessObjectAuthorizationService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * PreRules checks for the {@link SubAccount} that needs to occur while still in the Struts processing. This includes defaults, confirmations,
 * etc.
 */
public class SubAccountPreRules extends MaintenancePreRulesBase {

    protected SubAccount newSubAccount;

    // protected SubAccount copyAccount;

    public SubAccountPreRules() { return null; }

    /**
     * This checks to see if a continuation account is necessary and then copies the ICR data from the Account 
     * associated with this SubAccount (if necessary)
     * @see org.kuali.kfs.coa.document.validation.impl.MaintenancePreRulesBase#doCustomPreRules(org.kuali.rice.kns.document.MaintenanceDocument)
     */
    protected boolean doCustomPreRules(MaintenanceDocument document) { return false; }

    /**
     * 
     * This looks for the SubAccount's account number and then sets the values to the continuation account value if it exists
     * @param maintenanceAction
     */
    protected void checkForContinuationAccounts(String maintenanceAction) {  }

    /**
     * This method sets the convenience objects like newSubAccount, so you have short and easy handles to the new and
     * old objects contained in the maintenance document. It also calls the BusinessObjectBase.refresh(), which will attempt to load
     * all sub-objects from the DB by their primary keys, if available.
     * @param document
     */
    protected void setupConvenienceObjects(MaintenanceDocument document) {  }

    /**
     * 
     * This copies the Indirect Cost Rate (ICR) from the account if the SubAccount is a specific type - determined 
     * as "EX" from {@link SubAccountRule#CG_A21_TYPE_ICR}
     * <p>
     * If it is "EX" it will then copy over the ICR information from the Account specified for this SubAccount
     * @param document
     */
    protected void copyICRFromAccount(MaintenanceDocument document) {  }
}
