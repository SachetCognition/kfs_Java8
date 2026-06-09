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

public class GeneralLedgerEntryAssetId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long generalLedgerAccountIdentifier;
    private Integer capitalAssetBuilderLineNumber;

    public GeneralLedgerEntryAssetId() {
    }

    public GeneralLedgerEntryAssetId(Long generalLedgerAccountIdentifier, Integer capitalAssetBuilderLineNumber) {
        this.generalLedgerAccountIdentifier = generalLedgerAccountIdentifier;
        this.capitalAssetBuilderLineNumber = capitalAssetBuilderLineNumber;
    }

    public Long getGeneralLedgerAccountIdentifier() {
        return generalLedgerAccountIdentifier;
    }

    public void setGeneralLedgerAccountIdentifier(Long generalLedgerAccountIdentifier) {
        this.generalLedgerAccountIdentifier = generalLedgerAccountIdentifier;
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
        GeneralLedgerEntryAssetId that = (GeneralLedgerEntryAssetId) o;
        return java.util.Objects.equals(generalLedgerAccountIdentifier, that.generalLedgerAccountIdentifier) && java.util.Objects.equals(capitalAssetBuilderLineNumber, that.capitalAssetBuilderLineNumber);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(generalLedgerAccountIdentifier, capitalAssetBuilderLineNumber);
    }
}
