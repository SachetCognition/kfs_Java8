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
package org.kuali.kfs.module.ld.persistence.converter;

import java.math.BigDecimal;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.kuali.rice.core.api.util.type.KualiPercent;

/**
 * JPA AttributeConverter that replicates the OJB OjbKualiPercentFieldConversion behavior.
 * Converts between KualiPercent and database DECIMAL/BigDecimal values.
 */
@Converter
public class OjbKualiPercentFieldConverter implements AttributeConverter<KualiPercent, BigDecimal> {

    @Override
    public BigDecimal convertToDatabaseColumn(KualiPercent attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.bigDecimalValue();
    }

    @Override
    public KualiPercent convertToEntityAttribute(BigDecimal dbData) {
        if (dbData == null) {
            return null;
        }
        return new KualiPercent(dbData);
    }
}
