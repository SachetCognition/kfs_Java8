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
package org.kuali.kfs.sys.rice.impl;

import java.util.Collection;

import org.kuali.kfs.sys.rice.KfsParameterService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

/**
 * Delegates to Rice ParameterService.
 */
public class KfsParameterServiceImpl implements KfsParameterService {

    private ParameterService parameterService;

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @Override
    public Boolean parameterExists(Class<?> componentClass, String parameterName) {
        return parameterService.parameterExists(componentClass, parameterName);
    }

    @Override
    public Boolean parameterExists(String namespaceCode, String componentCode, String parameterName) {
        return parameterService.parameterExists(namespaceCode, componentCode, parameterName);
    }

    @Override
    public String getParameterValueAsString(Class<?> componentClass, String parameterName) {
        return parameterService.getParameterValueAsString(componentClass, parameterName);
    }

    @Override
    public String getParameterValueAsString(Class<?> componentClass, String parameterName, String defaultValue) {
        return parameterService.getParameterValueAsString(componentClass, parameterName, defaultValue);
    }

    @Override
    public String getParameterValueAsString(String namespaceCode, String componentCode, String parameterName) {
        return parameterService.getParameterValueAsString(namespaceCode, componentCode, parameterName);
    }

    @Override
    public String getParameterValueAsString(String namespaceCode, String componentCode, String parameterName, String defaultValue) {
        return parameterService.getParameterValueAsString(namespaceCode, componentCode, parameterName, defaultValue);
    }

    @Override
    public Boolean getParameterValueAsBoolean(Class<?> componentClass, String parameterName) {
        return parameterService.getParameterValueAsBoolean(componentClass, parameterName);
    }

    @Override
    public Boolean getParameterValueAsBoolean(Class<?> componentClass, String parameterName, Boolean defaultValue) {
        return parameterService.getParameterValueAsBoolean(componentClass, parameterName, defaultValue);
    }

    @Override
    public Boolean getParameterValueAsBoolean(String namespaceCode, String componentCode, String parameterName) {
        return parameterService.getParameterValueAsBoolean(namespaceCode, componentCode, parameterName);
    }

    @Override
    public Boolean getParameterValueAsBoolean(String namespaceCode, String componentCode, String parameterName, Boolean defaultValue) {
        return parameterService.getParameterValueAsBoolean(namespaceCode, componentCode, parameterName, defaultValue);
    }

    @Override
    public Collection<String> getParameterValuesAsString(Class<?> componentClass, String parameterName) {
        return parameterService.getParameterValuesAsString(componentClass, parameterName);
    }

    @Override
    public Collection<String> getParameterValuesAsString(String namespaceCode, String componentCode, String parameterName) {
        return parameterService.getParameterValuesAsString(namespaceCode, componentCode, parameterName);
    }

    @Override
    public String getSubParameterValueAsString(Class<?> componentClass, String parameterName, String subParameterName) {
        return parameterService.getSubParameterValueAsString(componentClass, parameterName, subParameterName);
    }

    @Override
    public String getSubParameterValueAsString(String namespaceCode, String componentCode, String parameterName, String subParameterName) {
        return parameterService.getSubParameterValueAsString(namespaceCode, componentCode, parameterName, subParameterName);
    }

    @Override
    public Collection<String> getSubParameterValuesAsString(Class<?> componentClass, String parameterName, String subParameterName) {
        return parameterService.getSubParameterValuesAsString(componentClass, parameterName, subParameterName);
    }

    @Override
    public Collection<String> getSubParameterValuesAsString(String namespaceCode, String componentCode, String parameterName, String subParameterName) {
        return parameterService.getSubParameterValuesAsString(namespaceCode, componentCode, parameterName, subParameterName);
    }
}
