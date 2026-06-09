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
package org.kuali.kfs.module.cab.businessobject;

import java.io.Serializable;

public class PurchasingAccountsPayableItemAssetId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String documentNumber;
    private Integer accountsPayableLineItemIdentifier;
    private Integer capitalAssetBuilderLineNumber;

    public PurchasingAccountsPayableItemAssetId() {
    }

    public PurchasingAccountsPayableItemAssetId(String documentNumber, Integer accountsPayableLineItemIdentifier, Integer capitalAssetBuilderLineNumber) {
        this.documentNumber = documentNumber;
        this.accountsPayableLineItemIdentifier = accountsPayableLineItemIdentifier;
        this.capitalAssetBuilderLineNumber = capitalAssetBuilderLineNumber;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Integer getAccountsPayableLineItemIdentifier() {
        return accountsPayableLineItemIdentifier;
    }

    public void setAccountsPayableLineItemIdentifier(Integer accountsPayableLineItemIdentifier) {
        this.accountsPayableLineItemIdentifier = accountsPayableLineItemIdentifier;
    }

    public Integer getCapitalAssetBuilderLineNumber() {
        return capitalAssetBuilderLineNumber;
    }

    public void setCapitalAssetBuilderLineNumber(Integer capitalAssetBuilderLineNumber) {
        this.capitalAssetBuilderLineNumber = capitalAssetBuilderLineNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchasingAccountsPayableItemAssetId that = (PurchasingAccountsPayableItemAssetId) o;
        return java.util.Objects.equals(documentNumber, that.documentNumber) && java.util.Objects.equals(accountsPayableLineItemIdentifier, that.accountsPayableLineItemIdentifier) && java.util.Objects.equals(capitalAssetBuilderLineNumber, that.capitalAssetBuilderLineNumber);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(documentNumber, accountsPayableLineItemIdentifier, capitalAssetBuilderLineNumber);
    }
}
