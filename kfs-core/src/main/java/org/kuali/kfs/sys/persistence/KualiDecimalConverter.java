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

import java.math.BigDecimal;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import org.kuali.rice.core.api.util.type.KualiDecimal;

/**
 * JPA {@link AttributeConverter} that maps {@link KualiDecimal} properties to
 * {@code DECIMAL} database columns.
 */
@Converter(autoApply = true)
public class KualiDecimalConverter implements AttributeConverter<KualiDecimal, BigDecimal> {

    @Override
    public BigDecimal convertToDatabaseColumn(KualiDecimal attribute) {
        return attribute == null ? null : attribute.bigDecimalValue();
    }

    @Override
    public KualiDecimal convertToEntityAttribute(BigDecimal dbData) {
        return dbData == null ? null : new KualiDecimal(dbData);
    }
}
