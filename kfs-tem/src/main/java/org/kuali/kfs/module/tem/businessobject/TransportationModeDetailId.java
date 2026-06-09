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
package org.kuali.kfs.module.tem.businessobject;

import java.io.Serializable;

public class TransportationModeDetailId implements Serializable {
    private String documentNumber;
    private String transportationModeCode;

    public TransportationModeDetailId() {}

    public TransportationModeDetailId(String documentNumber, String transportationModeCode) {
        this.documentNumber = documentNumber;
        this.transportationModeCode = transportationModeCode;
    }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }
    public String getTransportationModeCode() { return transportationModeCode; }
    public void setTransportationModeCode(String transportationModeCode) { this.transportationModeCode = transportationModeCode; }

    @Override
    public int hashCode() {
        int result = documentNumber != null ? documentNumber.hashCode() : 0;
        result = 31 * result + (transportationModeCode != null ? transportationModeCode.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransportationModeDetailId that = (TransportationModeDetailId) o;
        if (documentNumber != null ? !documentNumber.equals(that.documentNumber) : that.documentNumber != null) return false;
        return transportationModeCode != null ? transportationModeCode.equals(that.transportationModeCode) : that.transportationModeCode == null;
    }
}
