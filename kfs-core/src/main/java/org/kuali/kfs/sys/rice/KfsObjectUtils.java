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
package org.kuali.kfs.sys.rice;

import java.io.Serializable;

import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * KFS-owned adapter for Rice ObjectUtils. Delegates to the Rice ObjectUtils
 * static methods to minimize direct Rice coupling across KFS source files.
 */
public final class KfsObjectUtils {

    private KfsObjectUtils() {
        // static utility class
    }

    public static boolean isNull(Object object) {
        return ObjectUtils.isNull(object);
    }

    public static boolean isNotNull(Object object) {
        return ObjectUtils.isNotNull(object);
    }

    public static Object getPropertyValue(Object businessObject, String propertyName) {
        return ObjectUtils.getPropertyValue(businessObject, propertyName);
    }

    @SuppressWarnings("rawtypes")
    public static void setObjectProperty(Object bo, String propertyName, Object propertyValue) throws Exception {
        ObjectUtils.setObjectProperty(bo, propertyName, propertyValue);
    }

    @SuppressWarnings("rawtypes")
    public static void setObjectProperty(Object bo, String propertyName, Class propertyType, Object propertyValue) throws Exception {
        ObjectUtils.setObjectProperty(bo, propertyName, propertyType, propertyValue);
    }

    public static boolean equalByKeys(PersistableBusinessObject bo1, PersistableBusinessObject bo2) {
        return ObjectUtils.equalByKeys(bo1, bo2);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T deepCopy(T object) {
        return (T) ObjectUtils.deepCopy(object);
    }
}
