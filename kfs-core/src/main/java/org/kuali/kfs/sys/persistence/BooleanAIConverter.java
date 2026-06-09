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
package org.kuali.kfs.sys.persistence;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * JPA {@link AttributeConverter} that maps boolean values to 'A' (active/true)
 * and 'I' (inactive/false) database column values.
 *
 * <p>This mirrors the legacy OJB {@code OjbCharBooleanFieldAIConversion}.</p>
 */
@Converter
public class BooleanAIConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute ? "A" : "I";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return "A".equals(dbData);
    }
}
