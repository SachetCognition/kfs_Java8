package org.kuali.rice.coreservice.api.parameter;

public class Parameter extends org.kuali.rice.core.api.mo.AbstractDataTransferObject {
    private String applicationId;
    private String namespaceCode;
    private String componentCode;
    private String name;
    private String value;
    private String description;
    private String parameterTypeCode;
    private EvaluationOperator evaluationOperator;
    
    public Parameter() {}
    
    public String getApplicationId() { return applicationId; }
    public String getNamespaceCode() { return namespaceCode; }
    public String getComponentCode() { return componentCode; }
    public String getName() { return name; }
    public String getValue() { return value; }
    public String getDescription() { return description; }
    public String getParameterTypeCode() { return parameterTypeCode; }
    public EvaluationOperator getEvaluationOperator() { return evaluationOperator; }
}
