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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kfs.coa.businessobject.A21IndirectCostRecoveryAccount;
import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.coa.businessobject.Chart;
import org.kuali.kfs.coa.businessobject.IndirectCostRecoveryAccount;
import org.kuali.kfs.sys.KFSKeyConstants;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.kfs.sys.document.validation.impl.KfsMaintenanceDocumentRuleBase;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * Business rule(s) applicable to AccountMaintenance documents.
 */
abstract public class IndirectCostRecoveryAccountsRule extends KfsMaintenanceDocumentRuleBase {

    protected static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(IndirectCostRecoveryAccountsRule.class);

    protected static final BigDecimal BD100 = new BigDecimal(100);
    private List<? extends IndirectCostRecoveryAccount> activeIndirectCostRecoveryAccountList;
    protected String boFieldPath;
    
    /**
     * Custom processing for adding collection lines
     * 
     * @see org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase#processCustomAddCollectionLineBusinessRules(org.kuali.rice.kns.document.MaintenanceDocument, java.lang.String, org.kuali.rice.krad.bo.PersistableBusinessObject)
     */
    public boolean processCustomAddCollectionLineBusinessRules(MaintenanceDocument document, String collectionName, PersistableBusinessObject bo) { return false; }

    /**
     * This method calls the rule: KFSPropertyConstants.INDIRECT_COST_RECOVERY_ACCOUNT
     * 
     * @see org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.rice.kns.document.MaintenanceDocument)
     */
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) { return false; }
    
    /**
     * This method checks if the ICR collection should or should not be filled
     * error message is not handled in the function 
     * 
     * @param expectFilled
     * @return
     */
    protected boolean checkICRCollectionExist(boolean expectFilled) { return false; }
    

    /**
     * This method checks if the ICR collection should or should not be filled
     * add error message if validation is not successful
     * 
     * @param expectFilled
     * @param errorMessage
     * @param args
     * @return
     */
    protected boolean checkICRCollectionExistWithErrorMessage(boolean expectFilled, String errorMessage, String args) { return false; }
    
    /**
     * Check valid IndirectCostRecovery Account
     * 
     * @return
     */
    protected boolean checkIndirectCostRecoveryAccount(IndirectCostRecoveryAccount icrAccount) { return false; }
        
    protected boolean checkIndirectCostRecoveryAccount(String chartOfAccountsCode, String accountNumber, BigDecimal icraAccountLinePercentage) { return false; }

    /**
     * Check the collection list of indirect cost recovery account
     * 
     * 1. Check each account with rule: checkIndirectCostRecoveryAccount
     * 2. Total distributions from all the account should be 100
     * 
     * @param document
     * @return
     */
    protected boolean checkIndirectCostRecoveryAccountDistributions() { return false; }
    
    /**
     * Get the attribute label from DataDictionary
     * @param attribute
     * @return
     */
    protected String getDDAttributeLabel(String attribute) { return null; }
    
    public List<? extends IndirectCostRecoveryAccount> getActiveIndirectCostRecoveryAccountList() { return new java.util.ArrayList<>(); }

    public void setActiveIndirectCostRecoveryAccountList(List<? extends IndirectCostRecoveryAccount> indirectCostRecoveryAccountList) {  }
    
    public String getBoFieldPath() { return null; }

    public void setBoFieldPath(String boFieldPath) {  }

}

