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
package org.kuali.kfs.sys.businessobject;

import java.io.Serializable;
import java.util.Objects;

/**
 * Composite primary key class for {@link FunctionalFieldDescription}.
 */
public class FunctionalFieldDescriptionId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String namespaceCode;
    private String componentClass;
    private String propertyName;

    public FunctionalFieldDescriptionId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FunctionalFieldDescriptionId that = (FunctionalFieldDescriptionId) o;
        return Objects.equals(namespaceCode, that.namespaceCode) && Objects.equals(componentClass, that.componentClass) && Objects.equals(propertyName, that.propertyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namespaceCode, componentClass, propertyName);
    }
}
