package org.kuali.rice.krad.datadictionary;
public class BeanOverride {
    private String beanName;
    public BeanOverride() {}
    public String getBeanName() { return beanName; }
    public void setBeanName(String beanName) { this.beanName = beanName; }
    public void performOverride(Object bean) {}
}
