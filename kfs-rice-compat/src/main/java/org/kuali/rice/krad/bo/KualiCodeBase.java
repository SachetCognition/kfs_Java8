package org.kuali.rice.krad.bo;
public class KualiCodeBase extends PersistableBusinessObjectBase implements KualiCode, org.kuali.rice.core.api.mo.common.active.MutableInactivatable {
    private String code;
    private String name;
    private boolean active = true;
    public KualiCodeBase() {}
    public KualiCodeBase(String code) { this.code = code; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public String getCodeAndDescription() { return code + " - " + name; }
}
