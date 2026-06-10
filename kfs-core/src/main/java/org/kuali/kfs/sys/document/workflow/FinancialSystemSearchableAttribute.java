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
package org.kuali.kfs.sys.document.workflow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.coa.businessobject.Organization;
import org.kuali.kfs.integration.ld.LaborLedgerPendingEntryForSearching;
import org.kuali.kfs.integration.ld.LaborLedgerPostingDocumentForSearching;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.businessobject.AccountingLine;
import org.kuali.kfs.sys.businessobject.FinancialSystemDocumentHeader;
import org.kuali.kfs.sys.businessobject.GeneralLedgerPendingEntry;
import org.kuali.kfs.sys.businessobject.SourceAccountingLine;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.kfs.sys.document.AccountingDocument;
import org.kuali.kfs.sys.document.AmountTotaling;
import org.kuali.kfs.sys.document.GeneralLedgerPostingDocument;
import org.kuali.kfs.sys.document.datadictionary.AccountingLineGroupDefinition;
import org.kuali.kfs.sys.document.datadictionary.FinancialSystemTransactionalDocumentEntry;
import org.kuali.rice.core.api.uif.RemotableAttributeError;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kew.api.KewApiConstants.SearchableAttributeConstants;
import org.kuali.rice.kew.api.document.DocumentWithContent;
import org.kuali.rice.kew.api.document.attribute.DocumentAttribute;
import org.kuali.rice.kew.api.document.attribute.DocumentAttributeDecimal;
import org.kuali.rice.kew.api.document.attribute.DocumentAttributeString;
import org.kuali.rice.kew.api.document.search.DocumentSearchCriteria;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.api.extension.ExtensionDefinition;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.util.FieldUtils;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.datadictionary.DocumentEntry;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.ObjectUtils;
import org.kuali.rice.krad.workflow.attribute.DataDictionarySearchableAttribute;

//RICE20 This class needs to be fixed to support pre-rice2.0 features
public class FinancialSystemSearchableAttribute extends DataDictionarySearchableAttribute {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(FinancialSystemSearchableAttribute.class);

    protected static final String DISPLAY_TYPE_SEARCH_ATTRIBUTE_LABEL = "Search Result Type";
    protected static final String WORKFLOW_DISPLAY_TYPE_LABEL = "Workflow Data";
    protected static final String DOCUMENT_DISPLAY_TYPE_LABEL = "Document Specific Data";
    protected static final String WORKFLOW_DISPLAY_TYPE_VALUE = "workflow";
    protected static final String DOCUMENT_DISPLAY_TYPE_VALUE = "document";
    protected static final String DISPLAY_TYPE_SEARCH_ATTRIBUTE_NAME = "displayType";

    protected static final List<KeyValue> SEARCH_RESULT_TYPE_OPTION_LIST = new ArrayList<KeyValue>(2);
    static {
        SEARCH_RESULT_TYPE_OPTION_LIST.add(new ConcreteKeyValue(DOCUMENT_DISPLAY_TYPE_VALUE, DOCUMENT_DISPLAY_TYPE_LABEL));
        SEARCH_RESULT_TYPE_OPTION_LIST.add(new ConcreteKeyValue(WORKFLOW_DISPLAY_TYPE_VALUE, WORKFLOW_DISPLAY_TYPE_LABEL));
    }

    // used to map the special fields to the DD Entry that validate it.
    private static final Map<String, String> magicFields = new HashMap<String, String>();

    static {
        magicFields.put(KFSPropertyConstants.CHART_OF_ACCOUNTS_CODE, SourceAccountingLine.class.getSimpleName());
        magicFields.put(KFSPropertyConstants.ORGANIZATION_CODE, Organization.class.getSimpleName());
        magicFields.put(KFSPropertyConstants.ACCOUNT_NUMBER, SourceAccountingLine.class.getSimpleName());
        magicFields.put(KFSPropertyConstants.FINANCIAL_DOCUMENT_TYPE_CODE, GeneralLedgerPendingEntry.class.getSimpleName());
        magicFields.put(KFSPropertyConstants.FINANCIAL_DOCUMENT_TOTAL_AMOUNT, FinancialSystemDocumentHeader.class.getSimpleName() );
    }

    @Override
    protected List<Row> getSearchingRows(String documentTypeName) { return new java.util.ArrayList<>(); }

    @Override
    public List<DocumentAttribute> extractDocumentAttributes(ExtensionDefinition extensionDefinition, DocumentWithContent documentWithContent) { return new java.util.ArrayList<>(); }

    @Override
    public List<RemotableAttributeError> validateDocumentAttributeCriteria(ExtensionDefinition extensionDefinition, DocumentSearchCriteria documentSearchCriteria) { return new java.util.ArrayList<>(); }

    /**
     * Harvest chart of accounts code, account number, and organization code as searchable attributes from an accounting document
     * @param accountingDoc the accounting document to pull values from
     * @return a List of searchable values
     */
    protected List<DocumentAttribute> harvestAccountingDocumentSearchableAttributes(AccountingDocument accountingDoc) { return new java.util.ArrayList<>(); }

    /**
     * Harvest GLPE document type as searchable attributes from a GL posting document
     * @param GLPDoc the GLP document to pull values from
     * @return a List of searchable values
     */
    protected List<DocumentAttribute> harvestGLPDocumentSearchableAttributes(GeneralLedgerPostingDocument doc) { return new java.util.ArrayList<>(); }

    /**
     * Harvest LLPE document type as searchable attributes from a LL posting document
     * @param LLPDoc the LLP document to pull values from
     * @return a List of searchable values
     */
    protected List<DocumentAttribute> harvestLLPDocumentSearchableAttributes(LaborLedgerPostingDocumentForSearching LLPDoc) { return new java.util.ArrayList<>(); }


    /**
     * Pulls the default searchable attributes - chart code, account number, and account organization code - from a given accounting line and populates
     * the searchable attribute values in the given list
     * @param searchAttrValues a List of SearchableAttributeValue objects to populate
     * @param accountingLine an AccountingLine to get values from
     */
    protected void addSearchableAttributesForAccountingLine(List<DocumentAttribute> searchAttrValues, AccountingLine accountingLine) {  }

    /**
     * Pulls the default searchable attribute - financialSystemTypeCode - from a given accounting line and populates
     * the searchable attribute values in the given list
     * @param searchAttrValues a List of SearchableAttributeValue objects to populate
     * @param glpe a GeneralLedgerPendingEntry to get values from
     */
    protected void addSearchableAttributesForGLPE(List<DocumentAttribute> searchAttrValues, GeneralLedgerPendingEntry glpe) {  }

    /**
     * Pulls the default searchable attribute - financialSystemTypeCode from a given accounting line and populates
     * the searchable attribute values in the given list
     * @param searchAttrValues a List of SearchableAttributeValue objects to populate
     * @param llpe a LaborLedgerPendingEntry to get values from
     */
    protected void addSearchableAttributesForLLPE(List<DocumentAttribute> searchAttrValues, LaborLedgerPendingEntryForSearching llpe) {  }

    protected Row createSearchResultDisplayTypeRow() { return null; }


    // RICE20: fixes to allow document search to function until Rice 2.0.1
//    @Override
//    public List<RemotableAttributeField> getSearchFields(ExtensionDefinition extensionDefinition, String documentTypeName) {
//        if (LOG.isDebugEnabled()) {
//            LOG.debug("getSearchFields( " + extensionDefinition + ", " + documentTypeName + " )");
//        }
//        List<Row> searchRows = getSearchingRows(documentTypeName);
//        for ( Row row : searchRows ) {
//            for ( Field field : row.getFields() ) {
//                if ( field.getFieldType().equals(Field.CURRENCY) ) {
//                    field.setFieldType(Field.TEXT);
//                }
//                if ( field.getMaxLength() < 1 ) {
//                    field.setMaxLength(100);
//                }
//            }
//        }
//        return FieldUtils.convertRowsToAttributeFields(searchRows);
//    }
}
