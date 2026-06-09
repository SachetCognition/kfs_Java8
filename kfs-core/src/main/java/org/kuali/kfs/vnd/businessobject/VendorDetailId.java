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
package org.kuali.kfs.vnd.businessobject;

import java.io.Serializable;
import java.util.Objects;

/**
 * Composite primary key class for {@link VendorDetail}.
 */
public class VendorDetailId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer vendorHeaderGeneratedIdentifier;
    private Integer vendorDetailAssignedIdentifier;


    public VendorDetailId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VendorDetailId)) return false;
        VendorDetailId that = (VendorDetailId) o;
        return Objects.equals(vendorHeaderGeneratedIdentifier, that.vendorHeaderGeneratedIdentifier) && Objects.equals(vendorDetailAssignedIdentifier, that.vendorDetailAssignedIdentifier);
    }
    @Override
    public int hashCode() {
        return Objects.hash(vendorHeaderGeneratedIdentifier, vendorDetailAssignedIdentifier);
    }
}
