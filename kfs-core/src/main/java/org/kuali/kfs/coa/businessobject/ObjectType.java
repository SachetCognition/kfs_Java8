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
package org.kuali.kfs.coa.businessobject;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import org.hibernate.type.YesNoConverter;

import org.kuali.kfs.sys.KFSConstants;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.bo.KualiCodeBase;

/**
 *
 */
@Entity
@Table(name = "CA_OBJ_TYPE_T")

public class ObjectType extends KualiCodeBase implements MutableInactivatable {

    public static final String CACHE_NAME = KFSConstants.APPLICATION_NAMESPACE_CODE + "/" + "ObjectType";

    @Column(name = "FIN_OBJTYP_DBCR_CD")
    protected String finObjectTypeDebitcreditCd;
    @Column(name = "FIN_OBJ_TYP_ICR_CD")
    @Convert(converter = YesNoConverter.class)
    protected boolean finObjectTypeIcrSelectionIndicator;
    @Column(name = "FUND_BALANCE_CD")
    @Convert(converter = YesNoConverter.class)
    protected boolean fundBalanceIndicator;
    @Column(name = "FIN_REPORT_SORT_CD")
    protected String financialReportingSortCode;
    @Column(name = "ACCTG_CTGRY_CD")
    protected String basicAccountingCategoryCode;

    @Transient
    protected BasicAccountingCategory basicAccountingCategory;

    /**
     * Gets the finObjectTypeDebitcreditCd attribute.
     *
     * @return Returns the finObjectTypeDebitcreditCd
     */
    public String getFinObjectTypeDebitcreditCd() {
        return finObjectTypeDebitcreditCd;
    }

    /**
     * Sets the finObjectTypeDebitcreditCd attribute.
     *
     * @param finObjectTypeDebitcreditCd The finObjectTypeDebitcreditCd to set.
     */
    public void setFinObjectTypeDebitcreditCd(String finObjectTypeDebitcreditCd) {
        this.finObjectTypeDebitcreditCd = finObjectTypeDebitcreditCd;
    }

    /**
     * Gets the finObjectTypeIcrSelectionIndicator attribute.
     *
     * @return Returns the finObjectTypeIcrSelectionIndicator
     */
    public boolean isFinObjectTypeIcrSelectionIndicator() {
        return finObjectTypeIcrSelectionIndicator;
    }

    /**
     * Sets the finObjectTypeIcrSelectionIndicator attribute.
     *
     * @param finObjectTypeIcrSelectionIndicator The finObjectTypeIcrSelectionIndicator to set.
     */
    public void setFinObjectTypeIcrSelectionIndicator(boolean finObjectTypeIcrSelectionIndicator) {
        this.finObjectTypeIcrSelectionIndicator = finObjectTypeIcrSelectionIndicator;
    }

    /**
     * Gets the fundBalanceIndicator attribute.
     *
     * @return Returns the fundBalanceIndicator
     */
    public boolean isFundBalanceIndicator() {
        return fundBalanceIndicator;
    }

    /**
     * Sets the fundBalanceIndicator attribute.
     *
     * @param fundBalanceIndicator The fundBalanceIndicator to set.
     */
    public void setFundBalanceIndicator(boolean fundBalanceIndicator) {
        this.fundBalanceIndicator = fundBalanceIndicator;
    }

    /**
     * Gets the financialReportingSortCode attribute.
     *
     * @return Returns the financialReportingSortCode
     */
    public String getFinancialReportingSortCode() {
        return financialReportingSortCode;
    }

    /**
     * Sets the financialReportingSortCode attribute.
     *
     * @param financialReportingSortCode The financialReportingSortCode to set.
     */
    public void setFinancialReportingSortCode(String financialReportingSortCode) {
        this.financialReportingSortCode = financialReportingSortCode;
    }

    /**
     * Gets the accountCategoryCode attribute.
     *
     * @return Returns the accountCategoryCode.
     */
    public String getBasicAccountingCategoryCode() {
        return basicAccountingCategoryCode;
    }

    /**
     * Sets the accountCategoryCode attribute value.
     *
     * @param accountCategoryCode The accountCategoryCode to set.
     */
    public void setBasicAccountingCategoryCode(String accountCategoryCode) {
        this.basicAccountingCategoryCode = accountCategoryCode;
    }

    /**
     * Gets the basicAccountingCategory attribute.
     *
     * @return Returns the basicAccountingCategory.
     */
    public BasicAccountingCategory getBasicAccountingCategory() {
        return basicAccountingCategory;
    }

    /**
     * Sets the basicAccountingCategory attribute value.
     *
     * @param basicAccountingCategory The basicAccountingCategory to set.
     * @deprecated
     */
    @Deprecated
    public void setBasicAccountingCategory(BasicAccountingCategory basicAccountingCategory) {
        this.basicAccountingCategory = basicAccountingCategory;
    }

}
