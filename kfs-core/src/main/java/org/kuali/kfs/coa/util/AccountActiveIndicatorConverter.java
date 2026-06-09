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
package org.kuali.kfs.coa.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * JPA equivalent of {@link OjbAccountActiveIndicatorConversion}.
 * <p>
 * The database column {@code ACCT_CLOSED_IND} stores 'Y' when the account is
 * <em>closed</em> (inactive) and 'N' when it is open (active).  The Java field
 * {@code active} uses normal boolean semantics ({@code true} = active).  This
 * converter inverts the mapping so the two representations stay consistent:
 * <ul>
 *   <li>{@code active = true}  &rarr; column value {@code 'N'} (not closed)</li>
 *   <li>{@code active = false} &rarr; column value {@code 'Y'} (closed)</li>
 * </ul>
 */
@Converter
public class AccountActiveIndicatorConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean active) {
        if (active == null) {
            return null;
        }
        return active ? "N" : "Y";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbValue) {
        if (dbValue == null) {
            return null;
        }
        return "N".equals(dbValue);
    }
}
