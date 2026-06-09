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
 * Composite primary key class for {@link Room}.
 */
public class RoomId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String campusCode;
    private String buildingCode;
    private String buildingRoomNumber;

    public RoomId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomId that = (RoomId) o;
        return Objects.equals(campusCode, that.campusCode) && Objects.equals(buildingCode, that.buildingCode) && Objects.equals(buildingRoomNumber, that.buildingRoomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(campusCode, buildingCode, buildingRoomNumber);
    }
}
