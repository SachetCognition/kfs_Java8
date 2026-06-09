package org.kuali.rice.krad.bo;
public class AdHocRouteRecipient extends PersistableBusinessObjectBase {
    public static final Integer PERSON = 0;
    public static final Integer WORKGROUP = 1;
    private Integer type;
    private String id;
    private String name;
    private String actionRequested;
    public AdHocRouteRecipient() {}
    public Integer getType() { return type; }
    public void setType(Integer type) { this.type = type; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getActionRequested() { return actionRequested; }
    public void setActionRequested(String actionRequested) { this.actionRequested = actionRequested; }
}
