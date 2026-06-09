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

import java.util.Collection;

/**
 * KFS-owned parameter service interface. Mirrors Rice ParameterService
 * to decouple KFS from direct Rice API dependency.
 */
public interface KfsParameterService {

    Boolean parameterExists(Class<?> componentClass, String parameterName);

    Boolean parameterExists(String namespaceCode, String componentCode, String parameterName);

    String getParameterValueAsString(Class<?> componentClass, String parameterName);

    String getParameterValueAsString(Class<?> componentClass, String parameterName, String defaultValue);

    String getParameterValueAsString(String namespaceCode, String componentCode, String parameterName);

    String getParameterValueAsString(String namespaceCode, String componentCode, String parameterName, String defaultValue);

    Boolean getParameterValueAsBoolean(Class<?> componentClass, String parameterName);

    Boolean getParameterValueAsBoolean(Class<?> componentClass, String parameterName, Boolean defaultValue);

    Boolean getParameterValueAsBoolean(String namespaceCode, String componentCode, String parameterName);

    Boolean getParameterValueAsBoolean(String namespaceCode, String componentCode, String parameterName, Boolean defaultValue);

    Collection<String> getParameterValuesAsString(Class<?> componentClass, String parameterName);

    Collection<String> getParameterValuesAsString(String namespaceCode, String componentCode, String parameterName);

    String getSubParameterValueAsString(Class<?> componentClass, String parameterName, String subParameterName);

    String getSubParameterValueAsString(String namespaceCode, String componentCode, String parameterName, String subParameterName);

    Collection<String> getSubParameterValuesAsString(Class<?> componentClass, String parameterName, String subParameterName);

    Collection<String> getSubParameterValuesAsString(String namespaceCode, String componentCode, String parameterName, String subParameterName);
}
