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
package org.kuali.kfs.fp.batch.service.impl;

import static org.kuali.kfs.fp.document.validation.impl.ProcurementCardDocumentRuleConstants.AUTO_APPROVE_DOCUMENTS_IND;
import static org.kuali.kfs.fp.document.validation.impl.ProcurementCardDocumentRuleConstants.AUTO_APPROVE_NUMBER_OF_DAYS;
import static org.kuali.kfs.fp.document.validation.impl.ProcurementCardDocumentRuleConstants.DEFAULT_TRANS_ACCOUNT_PARM_NM;
import static org.kuali.kfs.fp.document.validation.impl.ProcurementCardDocumentRuleConstants.DEFAULT_TRANS_CHART_CODE_PARM_NM;
import static org.kuali.kfs.fp.document.validation.impl.ProcurementCardDocumentRuleConstants.DEFAULT_TRANS_OBJECT_CODE_PARM_NM;
import static org.kuali.kfs.fp.document.validation.impl.ProcurementCardDocumentRuleConstants.ERROR_TRANS_ACCOUNT_PARM_NM;
import static org.kuali.kfs.fp.document.validation.impl.ProcurementCardDocumentRuleConstants.SINGLE_TRANSACTION_IND_PARM_NM;
import static org.kuali.kfs.sys.KFSConstants.GL_CREDIT_CODE;
import static org.kuali.kfs.sys.KFSConstants.FinancialDocumentTypeCodes.PROCUREMENT_CARD;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kfs.coa.businessobject.ProjectCode;
import org.kuali.kfs.coa.businessobject.SubAccount;
import org.kuali.kfs.coa.businessobject.SubObjectCode;
import org.kuali.kfs.coa.service.AccountService;
import org.kuali.kfs.coa.service.ChartService;
import org.kuali.kfs.coa.service.ObjectCodeService;
import org.kuali.kfs.coa.service.ProjectCodeService;
import org.kuali.kfs.coa.service.SubAccountService;
import org.kuali.kfs.coa.service.SubObjectCodeService;
import org.kuali.kfs.fp.batch.ProcurementCardAutoApproveDocumentsStep;
import org.kuali.kfs.fp.batch.ProcurementCardCreateDocumentsStep;
import org.kuali.kfs.fp.batch.ProcurementCardLoadStep;
import org.kuali.kfs.fp.batch.ProcurementCardReportType;
import org.kuali.kfs.fp.batch.service.ProcurementCardCreateDocumentService;
import org.kuali.kfs.fp.businessobject.CapitalAssetInformation;
import org.kuali.kfs.fp.businessobject.ProcurementCardDefault;
import org.kuali.kfs.fp.businessobject.ProcurementCardHolder;
import org.kuali.kfs.fp.businessobject.ProcurementCardSourceAccountingLine;
import org.kuali.kfs.fp.businessobject.ProcurementCardTargetAccountingLine;
import org.kuali.kfs.fp.businessobject.ProcurementCardTransaction;
import org.kuali.kfs.fp.businessobject.ProcurementCardTransactionDetail;
import org.kuali.kfs.fp.businessobject.ProcurementCardVendor;
import org.kuali.kfs.fp.document.ProcurementCardDocument;
import org.kuali.kfs.fp.document.validation.impl.ProcurementCardDocumentRuleConstants;
import org.kuali.kfs.integration.cab.CapitalAssetBuilderModuleService;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.kfs.sys.document.service.AccountingLineRuleHelperService;
import org.kuali.kfs.sys.document.service.FinancialSystemDocumentService;
import org.kuali.kfs.sys.document.validation.event.DocumentSystemSaveEvent;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.kfs.sys.service.VelocityEmailService;
import org.kuali.kfs.sys.util.KfsDateUtils;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.core.web.format.CurrencyFormatter;
import org.kuali.rice.core.web.format.Formatter;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.document.DocumentStatus;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DataDictionaryService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;
import org.kuali.rice.krad.util.ObjectUtils;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;
import org.springframework.transaction.annotation.Transactional;



/**
 * This is the default implementation of the ProcurementCardCreateDocumentService interface.
 *
 * @see org.kuali.kfs.fp.batch.service.ProcurementCardCreateDocumentService
 */

public class ProcurementCardCreateDocumentServiceImpl implements ProcurementCardCreateDocumentService {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProcurementCardCreateDocumentServiceImpl.class);

    protected static final String WORKFLOW_SEARCH_RESULT_KEY = "routeHeaderId";

    protected ParameterService parameterService;
    protected BusinessObjectService businessObjectService;
    protected DocumentService documentService;
    protected WorkflowDocumentService workflowDocumentService;
    protected DataDictionaryService dataDictionaryService;
    protected DateTimeService dateTimeService;
    protected AccountingLineRuleHelperService accountingLineRuleUtil;
    protected CapitalAssetBuilderModuleService capitalAssetBuilderModuleService;
    protected FinancialSystemDocumentService financialSystemDocumentService;
    private VelocityEmailService procurementCardCreateEmailService;

    public static final String DOCUMENT_DESCRIPTION_PATTERN = "{0}-{1}-{2}-{3}";

    /**
     * This method retrieves a collection of credit card transactions and traverses through this list, creating
     * ProcurementCardDocuments for each card.
     *
     * @return True if the procurement card documents were created successfully.  If any problem occur while creating the
     * documents, a runtime exception will be thrown.
     *
     * @see org.kuali.kfs.fp.batch.service.ProcurementCardCreateDocumentService#createProcurementCardDocuments()
     */
    @Override
    @SuppressWarnings("rawtypes")
    @Transactional
    public boolean createProcurementCardDocuments() { return false; }

    /**
     * Sending email notification with initializing template variable details in report.
     *
     * @param documents
     */
    protected void sendEmailNotification(List<ProcurementCardDocument> documents) {  }

    /**
     * Retrieve date formatter used to format batch running timestamp in report. System parameter has precedence over system default String.
     *
     * @return
     */
    protected DateFormat getDateFormat(String namespaceCode, String componentCode, String parameterName, String defaultValue) { return null; }

    /**
     * Get the total number of transactions processed by this batch run
     *
     * @param summaryList
     * @return
     */
    protected int getBatchTotalTransactionCnt(List<ProcurementCardReportType> summaryList) { return 0; }

    /**
     * Build the sorted batch report summary list
     *
     * @param documents
     * @return
     */
    protected List<ProcurementCardReportType> getSortedReportSummaryList(List<ProcurementCardDocument> documents) { return new java.util.ArrayList<>(); }

    /**
     * Build Procurement Card report object.
     *
     * @param docMapByPstDt
     * @param transctionMapByPstDt
     * @param totalAmountMapByPstDt
     * @return
     */
    protected List<ProcurementCardReportType> buildBatchReportSummary(Map<Date, HashMap<String, String>> docMapByPstDt, Map<Date, Integer> transctionMapByPstDt, Map<Date, KualiDecimal> totalAmountMapByPstDt) { return new java.util.ArrayList<>(); }


    /**
     * Sorting the report summary list by transaction posting date
     *
     * @param summaryList
     */
    protected void sortingSummaryList(List<ProcurementCardReportType> summaryList) {  }

    /**
     * This method retrieves all the procurement card documents with a status of 'I' and routes them to the next step in the
     * routing path.
     *
     * @return True if the routing was performed successfully.  A runtime exception will be thrown if any errors occur while routing.
     *
     * @see org.kuali.kfs.fp.batch.service.ProcurementCardCreateDocumentService#routeProcurementCardDocuments(java.util.List)
     */
    @Override
    public boolean routeProcurementCardDocuments() { return false; }

    /**
     * Returns a list of all initiated but not yet routed procurement card documents, using the KualiWorkflowInfo service.
     * @return a list of procurement card documents to route
     */
    protected Collection<ProcurementCardDocument> retrieveProcurementCardDocumentsToRoute(String statusCode) { return new java.util.ArrayList<>(); }

    /**
     * This method determines if procurement card documents can be auto approved.  A document can be auto approved if
     * the grace period for allowing auto approval of a procurement card document has passed.  The grace period is defined
     * by a parameter in the parameters table.  The create date of the document is then compared against the current date and
     * if the difference is larger than the grace period defined, then the document is auto approved.
     *
     * @return This method always returns true.
     *
     * @see org.kuali.kfs.fp.batch.service.ProcurementCardCreateDocumentService#autoApproveProcurementCardDocuments()
     */
    @Override
    public boolean autoApproveProcurementCardDocuments() { return false; }


    /**
     * This method retrieves a list of transactions from a temporary table, and groups them into document lists, based on
     * single transaction indicator or a grouping by card.
     *
     * @return List containing transactions for document.
     */
    @SuppressWarnings("rawtypes")
    protected List retrieveTransactions() { return new java.util.ArrayList<>(); }


    /**
     * Creates a ProcurementCardDocument from the List of transactions given.
     *
     * @param transactions List of ProcurementCardTransaction objects to be used for creating the document.
     * @return A ProcurementCardDocument populated with the transactions provided.
     */
    protected ProcurementCardDocument createProcurementCardDocument(List transactions) { return null; }

    /**
     * Set up PCDO document description as "cardholder name-card number (which is 4 digits)-chartOfAccountsCode-default account"
     *
     * @param pcardDocument
     */
    protected void setupDocumentDescription(ProcurementCardDocument pcardDocument) {  }

    /**
     * Creates card holder record and sets that record to the document given.
     *
     * @param pcardDocument Procurement card document to place the record in.
     * @param transaction The transaction to set the card holder record fields from.
     */
    protected void createCardHolderRecord(ProcurementCardDocument pcardDocument, ProcurementCardTransaction transaction) {  }

    /**
     * Creates a transaction detail record and adds that record to the document provided.
     *
     * @param pcardDocument Document to place record in.
     * @param transaction Transaction to set fields from.
     * @param transactionLineNumber Line number of the new transaction detail record within the procurement card document.
     * @return The error text that was generated from the creation of the detail records.  If the text is empty, no errors were encountered.
     */
    protected String createTransactionDetailRecord(ProcurementCardDocument pcardDocument, ProcurementCardTransaction transaction, Integer transactionLineNumber) { return null; }


    /**
     * Creates a transaction vendor detail record and adds it to the transaction detail.
     *
     * @param pcardDocument The procurement card document to retrieve values from.
     * @param transaction Transaction to set fields from.
     * @param transactionDetail The transaction detail to set the vendor record on.
     */
    protected void createTransactionVendorRecord(ProcurementCardDocument pcardDocument, ProcurementCardTransaction transaction, ProcurementCardTransactionDetail transactionDetail) {  }

    /**
     * From the transaction accounting attributes, creates source and target accounting lines. Attributes are validated first, and
     * replaced with default and error values if needed. There will be 1 source and 1 target line generated.
     *
     * @param pcardDocument The procurement card document to add the new accounting lines to.
     * @param transaction The transaction to process into account lines.
     * @param docTransactionDetail The transaction detail to create source and target accounting lines from.
     * @return String containing any error messages.
     */
    protected String createAndValidateAccountingLines(ProcurementCardDocument pcardDocument, ProcurementCardTransaction transaction, ProcurementCardTransactionDetail docTransactionDetail) { return null; }

    /**
     * Creates the to record for the transaction. The chart of account attributes from the transaction are used to create
     * the accounting line.
     *
     * @param transaction The transaction to pull information from to create the accounting line.
     * @param docTransactionDetail The transaction detail to pull information from to populate the accounting line.
     * @return The target accounting line fully populated with values from the parameters passed in.
     */
    protected ProcurementCardTargetAccountingLine createTargetAccountingLine(ProcurementCardTransaction transaction, ProcurementCardTransactionDetail docTransactionDetail) { return null; }

    /**
     * Creates the from record for the transaction. The clearing chart, account, and object code is used for creating the line.
     *
     * @param transaction The transaction to pull information from to create the accounting line.
     * @param docTransactionDetail The transaction detail to pull information from to populate the accounting line.
     * @return The source accounting line fully populated with values from the parameters passed in.
     */
    protected ProcurementCardSourceAccountingLine createSourceAccountingLine(ProcurementCardTransaction transaction, ProcurementCardTransactionDetail docTransactionDetail) { return null; }

    /**
     * Validates the chart of account attributes for existence and active indicator. Will substitute for defined
     * default parameters or set fields to empty that if they have errors.
     *
     * @param targetLine The target accounting line to be validated.
     * @return String with error messages discovered during validation.  An empty string indicates no validation errors were found.
     */
    protected String validateTargetAccountingLine(ProcurementCardTargetAccountingLine targetLine) { return null; }

    /**
     * Validates the chart of account attributes for existence and active indicator. Will substitute for defined
     * default parameters or set fields to empty that if they have errors.
     *
     * @param transaction The transaction to be validated.
     * @return String with error messages discovered during validation.  An empty string indicates no validation errors were found.
     */
    protected String validateTransaction(ProcurementCardTransaction transaction) { return null; }

    /**
     * Retrieves the error chart code from the parameter table.
     * @return The error chart code defined in the parameter table.
     */
    protected String getErrorChartCode() { return null; }

    /**
     * Retrieves the error account number from the parameter table.
     * @return The error account number defined in the parameter table.
     */
    protected String getErrorAccountNumber() { return null; }

    /**
     * Gets the default Chart Code, Account from the custom Procurement Cardholder table.
     *
     */
    protected ProcurementCardDefault retrieveProcurementCardDefault(String creditCardNumber) { return null; }

    /**
     * Retrieves the default chard code from the parameter table.
     * @return The default chart code defined in the parameter table.
     */
    protected String getDefaultChartCode() { return null; }

    /**
     * Retrieves the default account number from the parameter table.
     * @return The default account number defined in the parameter table.
     */
    protected String getDefaultAccountNumber() { return null; }

    /**
     * Retrieves the default object code from the parameter table.
     * @return The default object code defined in the parameter table.
     */
    protected String getDefaultObjectCode() { return null; }

    /**
     * Calls businessObjectService to remove all the procurement card transaction rows from the transaction load table.
     */
    protected void cleanTransactionsTable() {  }

    /**
     * Loads all the parsed XML transactions into the temp transaction table.
     *
     * @param transactions List of ProcurementCardTransactions to load.
     */
    protected void loadTransactions(List transactions) {  }

    /**
     * @return retrieves the presumably injected implementation of ParameterService to use
     */
    public ParameterService getParameterService() { return null; }

    /**
     * Sets the parameterService attribute.
     * @param parameterService
     */
    public void setParameterService(ParameterService parameterService) {  }

    /**
     * Gets the businessObjectService attribute.
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() { return null; }

    /**
     * Sets the businessObjectService attribute.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {  }

    /**
     * Gets the documentService attribute.
     * @return Returns the documentService.
     */
    public DocumentService getDocumentService() { return null; }

    /**
     * Sets the documentService attribute.
     * @param documentService The documentService to set.
     */
    public void setDocumentService(DocumentService documentService) {  }

    public WorkflowDocumentService getWorkflowDocumentService() { return null; }

    public void setWorkflowDocumentService(WorkflowDocumentService workflowDocumentService) {  }

    /**
     * Gets the dataDictionaryService attribute.
     * @return Returns the dataDictionaryService.
     */
    public DataDictionaryService getDataDictionaryService() { return null; }

    /**
     * Sets the dataDictionaryService attribute.
     * @param dataDictionaryService dataDictionaryService to set.
     */
    public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {  }


    /**
     * Gets the dateTimeService attribute.
     * @return Returns the dateTimeService.
     */
    public DateTimeService getDateTimeService() { return null; }

    /**
     * Sets the dateTimeService attribute.
     * @param dateTimeService The dateTimeService to set.
     */
    public void setDateTimeService(DateTimeService dateTimeService) {  }

    /**
     * Sets the accountingLineRuleUtil attribute value.
     * @param accountingLineRuleUtil The accountingLineRuleUtil to set.
     */
    public void setAccountingLineRuleUtil(AccountingLineRuleHelperService accountingLineRuleUtil) {  }

    /**
     * Sets the capitalAssetBuilderModuleService attribute value.
     * @param capitalAssetBuilderModuleService The capitalAssetBuilderModuleService to set.
     */
    public void setCapitalAssetBuilderModuleService(CapitalAssetBuilderModuleService capitalAssetBuilderModuleService) {  }

    /**
     * Sets the procurementCardCreateEmailService attribute.
     *
     * @param procurementCardCreateEmailService The procurementCardCreateEmailService to set.
     */
    public void setProcurementCardCreateEmailService(VelocityEmailService procurementCardCreateEmailService) {  }

    /**
     * Gets the financialSystemDocumentHeaderSerivce attribute
     *
     * @return
     */
    public FinancialSystemDocumentService getFinancialSystemDocumentService() { return null; }

    /**
     * Spring hook to inject financialSystemDocumentHeaderService
     *
     * @param financialSystemDocumentService
     */
    public void setFinancialSystemDocumentService(FinancialSystemDocumentService financialSystemDocumentService) {  }


}
