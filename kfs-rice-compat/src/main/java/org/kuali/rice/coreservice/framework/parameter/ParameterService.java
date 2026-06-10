package org.kuali.rice.coreservice.framework.parameter;

import org.kuali.rice.coreservice.api.parameter.Parameter;

public interface ParameterService {
    String getParameterValueAsString(String namespaceCode, String componentCode, String parameterName);
    String getParameterValueAsString(Class<?> componentClass, String parameterName);
    Boolean getParameterValueAsBoolean(String namespaceCode, String componentCode, String parameterName);
    Boolean getParameterValueAsBoolean(Class<?> componentClass, String parameterName);
    Boolean getParameterValueAsBoolean(Class<?> componentClass, String parameterName, Boolean defaultValue);
    java.util.Collection<String> getParameterValuesAsString(String namespaceCode, String componentCode, String parameterName);
    java.util.Collection<String> getParameterValuesAsString(Class<?> componentClass, String parameterName);
    boolean parameterExists(String namespaceCode, String componentCode, String parameterName);
    boolean parameterExists(Class<?> componentClass, String parameterName);
    boolean getIndicatorParameter(Class<?> componentClass, String parameterName);
    Boolean getParameterValueAsBoolean(String namespaceCode, String componentCode, String parameterName, Boolean defaultValue);
    Parameter getParameter(String namespaceCode, String componentCode, String parameterName);
    Parameter getParameter(Class<?> componentClass, String parameterName);
    String getParameterValueAsString(Class<?> componentClass, String parameterName, String defaultValue);
    java.util.Collection<String> getSubParameterValuesAsString(Class clazz, String parameterName, String constraintValue);
    String getSubParameterValueAsString(Class<?> componentClass, String parameterName, String subParameterName);
    String getParameterValueAsString(String namespaceCode, String componentCode, int parameterIndex);
}
