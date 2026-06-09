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

package org.kuali.kfs.module.purap.businessobject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;



/**
 * Accounting line Business Object for a credit memo item line.
 */
@Entity
@Table(name = "AP_CRDT_MEMO_ACCT_T")
public class CreditMemoAccount extends PurApAccountingLineBase {

    /**
     * Default constructor.
     */
    public CreditMemoAccount() {

    }

    /**
     * Constructs a Credit Memo Account object from an existing PurAp Accounting Line object.
     * 
     * @param accountingLine the accounting line to copy from.
     */
    public CreditMemoAccount(PurApAccountingLineBase accountingLine) {
        super();

        setChartOfAccountsCode(accountingLine.getChartOfAccountsCode());
        setAccountNumber(accountingLine.getAccountNumber());
        setSubAccountNumber(accountingLine.getSubAccountNumber());
        setFinancialObjectCode(accountingLine.getFinancialObjectCode());
        setFinancialSubObjectCode(accountingLine.getFinancialSubObjectCode());
        setProjectCode(accountingLine.getProjectCode());
        setOrganizationReferenceId(accountingLine.getOrganizationReferenceId());
        setAmount(accountingLine.getAmount());
        setAccountLinePercent(accountingLine.getAccountLinePercent());
        this.setSequenceNumber(accountingLine.getSequenceNumber());
    }

    public CreditMemoItem getCreditMemoItem() {
        return super.getPurapItem();
    }

    /**
     * @deprecated
     */
    public void setCreditMemoItem(CreditMemoItem creditMemoItem) {
        super.setPurapItem(creditMemoItem);
    }
}
