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
package org.kuali.kfs.fp.document.web.struts;

import static org.kuali.kfs.sys.KFSConstants.AuxiliaryVoucher.ACCRUAL_DOC_TYPE;
import static org.kuali.kfs.sys.KFSConstants.AuxiliaryVoucher.ADJUSTMENT_DOC_TYPE;
import static org.kuali.kfs.sys.KFSConstants.AuxiliaryVoucher.RECODE_DOC_TYPE;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.kuali.kfs.coa.businessobject.AccountingPeriod;
import org.kuali.kfs.coa.service.AccountingPeriodService;
import org.kuali.kfs.fp.document.AuxiliaryVoucherDocument;
import org.kuali.kfs.fp.document.validation.impl.AuxiliaryVoucherDocumentRuleConstants;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.parameter.ParameterEvaluatorService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * Struts form so <code>{@link AuxiliaryVoucherDocument}</code> can be accessed and modified through UI.
 */
public class AuxiliaryVoucherForm extends VoucherForm {
    protected String originalVoucherType = KFSConstants.AuxiliaryVoucher.ADJUSTMENT_DOC_TYPE; // keep this in sync with the default

    // value set in the document business
    // object

    public AuxiliaryVoucherForm() { return null; }

    @Override
    protected String getDefaultDocumentTypeName() { return null; }

    /**
     * Overrides the parent to call super.populate and then to call the two methods that are specific to loading the two select
     * lists on the page. In addition, this also makes sure that the credit and debit amounts are filled in for situations where
     * validation errors occur and the page reposts.
     *
     * @see org.kuali.rice.kns.web.struts.pojo.PojoForm#populate(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void populate(HttpServletRequest request) {  }

    /**
     * @return Returns the serviceBillingDocument.
     */
    public AuxiliaryVoucherDocument getAuxiliaryVoucherDocument() { return null; }

    /**
     * @param serviceBillingDocument The serviceBillingDocument to set.
     */
    public void setAuxiliaryVoucherDocument(AuxiliaryVoucherDocument auxiliaryVoucherDocument) {  }

    /**
     * Gets today's date and then sets the day of the month as 15th, irrespective of the current day of the month
     * @return the modified reversal date
     */
    protected Date getAvReversalDate() { return null; }

    /**
     * Handles special case display rules for displaying Reversal Date at UI layer
     */
    public void populateReversalDateForRendering() {  }

    /**
     * This method returns the reversal date in the format MMM d, yyyy.
     *
     * @return String
     */
    @Override
    public String getFormattedReversalDate() { return null; }

    /**
     * @return String
     */
    public String getOriginalVoucherType() { return null; }

    /**
     * @param originalVoucherType
     */
    public void setOriginalVoucherType(String originalVoucherType) {  }

    /**
     * Returns a formatted auxiliary voucher type: <Voucher Type Name> (<Voucher Type Code>)
     *
     * @return
     */
    public String getFormattedAuxiliaryVoucherType() { return null; }

    /**
     * This method generates a proper list of valid accounting periods that the user can select from.
     *
     * @see org.kuali.kfs.fp.document.web.struts.VoucherForm#populateAccountingPeriodListForRendering()
     */
    @Override
    public void populateAccountingPeriodListForRendering() {  }

    public boolean getAccountingPeriodReadOnly() { return false; }

    protected class OpenAuxiliaryVoucherPredicate implements Predicate {
        protected ParameterService parameterService;
        protected UniversityDateService dateService;
        protected AccountingPeriodService acctPeriodService;
        protected Document auxiliaryVoucherDocument;
        protected AccountingPeriod currPeriod;
        protected java.sql.Date currentDate;
        protected Integer currentFiscalYear;

        public OpenAuxiliaryVoucherPredicate(Document doc) { return null; }

        @Override
        public boolean evaluate(Object o) { return false; }
    }

    public List<String> getAccountingPeriodCompositeValueList() { return new java.util.ArrayList<>(); }

    public List<String> getAccountingPeriodLabelList() { return new java.util.ArrayList<>(); }

    public static final String REVERSAL_DATE_DEFAULT_DAY_OF_THE_MONTH_PARM_NAME = "REVERSAL_DATE_DEFAULT_DAY_OF_THE_MONTH";

    /**
     * get the reversal date default day of month defined as an application parameter
     */
    protected int getReversalDateDefaultDayOfMonth() { return 0; }
}
