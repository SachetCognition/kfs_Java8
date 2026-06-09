package org.kuali.rice.core.api.util.type;

import java.math.BigDecimal;

public abstract class AbstractKualiDecimal<T> implements java.io.Serializable {
    protected BigDecimal value;
    
    protected AbstractKualiDecimal() { this.value = BigDecimal.ZERO; }
    protected AbstractKualiDecimal(BigDecimal value) { this.value = value != null ? value : BigDecimal.ZERO; }
    
    public BigDecimal bigDecimalValue() { return value; }
    
    @Override
    public String toString() { return value.toString(); }
}
