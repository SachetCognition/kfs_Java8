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
package org.kuali.kfs.module.external.kc.businessobject;

import java.io.Serializable;

/**
 * Composite primary key class for {@link BillingFrequencyMapping}.
 */
public class BillingFrequencyMappingId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String kcFrequencyCode;
    private String frequency;

    public BillingFrequencyMappingId() {
    }

    public BillingFrequencyMappingId(String kcFrequencyCode, String frequency) {
        this.kcFrequencyCode = kcFrequencyCode;
        this.frequency = frequency;
    }

    public String getKcFrequencyCode() {
        return kcFrequencyCode;
    }

    public void setKcFrequencyCode(String kcFrequencyCode) {
        this.kcFrequencyCode = kcFrequencyCode;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BillingFrequencyMappingId that = (BillingFrequencyMappingId) o;
        if (kcFrequencyCode != null ? !kcFrequencyCode.equals(that.kcFrequencyCode) : that.kcFrequencyCode != null)
            return false;
        return frequency != null ? frequency.equals(that.frequency) : that.frequency == null;
    }

    @Override
    public int hashCode() {
        int result = kcFrequencyCode != null ? kcFrequencyCode.hashCode() : 0;
        result = 31 * result + (frequency != null ? frequency.hashCode() : 0);
        return result;
    }
}
