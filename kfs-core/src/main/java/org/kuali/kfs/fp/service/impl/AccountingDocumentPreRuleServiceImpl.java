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
package org.kuali.kfs.fp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.KFSKeyConstants;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.businessobject.AccountingLine;
import org.kuali.kfs.sys.businessobject.AccountingLineOverride;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.kfs.sys.document.AccountingDocumentBase;
import org.kuali.kfs.sys.document.authorization.AccountingLineAuthorizer;
import org.kuali.kfs.sys.document.authorization.AccountingLineAuthorizerBase;
import org.kuali.kfs.sys.document.datadictionary.AccountingLineGroupDefinition;
import org.kuali.kfs.sys.document.datadictionary.FinancialSystemTransactionalDocumentEntry;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kim.api.identity.Person;


import org.kuali.rice.krad.document.Document;
import org.kuali.rice.kns.rule.event.PromptBeforeValidationEvent;
import org.kuali.rice.kns.rules.PromptBeforeValidationBase;
import org.kuali.rice.kns.service.DataDictionaryService;

import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;



import org.kuali.kfs.fp.service.AccountingDocumentPreRuleService;



/**
 * This service interface defines methods that a AccountingDocumentPreRuleService implementation must provide.
 */

public class AccountingDocumentPreRuleServiceImpl implements AccountingDocumentPreRuleService {
    protected static Logger LOG = Logger.getLogger(AccountingDocumentPreRuleServiceImpl.class);

    /**
     * Access the account override question for all accounting document
     * 
     * @param document
     * @param preRule
     * @return
     */
    public boolean expiredAccountOverrideQuestion(AccountingDocumentBase document, PromptBeforeValidationBase preRule, PromptBeforeValidationEvent event) { return false; }

    /**
     * Set up override for all accounting line with the same account number
     * 
     * @param document
     * @param accountLine
     * @param code
     */
    protected void setAccountOverride(AccountingDocumentBase document, Account overrideAccount, String code) {  }

    /**
     * DTT-3163: Walk through all source and target accounting lines to identify if there is account which is expired and requires
     * approver to override but approver does not have the edit permission
     * 
     * @param document
     * @return
     */
    protected List<AccountingLine> getOverrideQuestionAccount(Document document) { return new java.util.ArrayList<>(); }

    /**
     * Determines the property of the accounting line collection from the error prefixes
     * 
     * @return the accounting line collection property
     */
    protected String getAccountingLineCollectionProperty(AccountingLine account) { return null; }

    /**
     * @param accountingLines
     * @return Map containing accountingLines from the given List, indexed by their sequenceNumber
     */
    protected Map buildAccountingLineMap(List accountingLines) { return new java.util.HashMap<>(); }

    /**
     * @return hopefully, the best accounting line authorizer implementation to do the KIM check for to see if lines are accessible
     */
    protected AccountingLineAuthorizer lookupAccountingLineAuthorizer(AccountingLine account, Document document, String groupName) { return null; }

    /**
     * Returns the name of the accounting line group which holds the proper authorizer to do the KIM check
     * 
     * @return the name of the accouting line group to get the authorizer from
     */
    protected String getGroupName(AccountingLine line) { return null; }
}
