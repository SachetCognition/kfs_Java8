package org.kuali.rice.kns.web.struts.form;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import org.kuali.rice.kns.web.ui.ExtraButton;
public class KualiForm extends org.kuali.rice.kns.web.struts.form.pojo.PojoFormBase {
    private String businessObjectClassName;
    private String methodToCall;
    private String backLocation;
    private Map<String, String> editingMode = new HashMap<>();
    private Map<String, String> documentActions = new HashMap<>();
    private Map<String, String> tabStates = new HashMap<>();
    protected Map forcedReadOnlyFields = new HashMap();
    private int tabIndex = 0;
    public KualiForm() {}
    public java.util.Map getUnconvertedValues() { return new java.util.HashMap(); }
    public void addToErrorPath(String path) {}
    public void removeFromErrorPath(String path) {}
    public boolean isFieldLevelHelpEnabled() { return false; }
    public boolean isDocumentationFieldDisplayed() { return false; }
    public String getBusinessObjectClassName() { return businessObjectClassName; }
    public void setBusinessObjectClassName(String name) { this.businessObjectClassName = name; }
    public String getMethodToCall() { return methodToCall; }
    public void setMethodToCall(String methodToCall) { this.methodToCall = methodToCall; }
    public List<ExtraButton> getExtraButtons() { return new ArrayList<>(); }
    public void setExtraButtons(ArrayList<ExtraButton> buttons) {}
    public Map<String, String> getEditingMode() { return editingMode; }
    public void setEditingMode(Map<String, String> editingMode) { this.editingMode = editingMode; }
    public Map<String, String> getDocumentActions() { return documentActions; }
    public void setDocumentActions(Map<String, String> documentActions) { this.documentActions = documentActions; }
    public String getTabState(String tabKey) { return tabStates.get(tabKey); }
    public Map<String, String> getTabStates() { return tabStates; }
    public void setTabStates(Map<String, String> tabStates) { this.tabStates = tabStates; }
    public String getBackLocation() { return backLocation; }
    public void setBackLocation(String backLocation) { this.backLocation = backLocation; }
    public Map getForcedReadOnlyFields() { return forcedReadOnlyFields; }
    public void setForcedReadOnlyFields(Map forcedReadOnlyFields) { this.forcedReadOnlyFields = forcedReadOnlyFields; }
    public void incrementTabIndex() { tabIndex++; }
    public void setFormatterType(String fieldName, Class formatterClass) {}
    public void addMaxUploadSize(String path) {}
    public void registerEditableProperty(String propertyName) {}
    public String getRefreshCaller() { return null; }
    public void setRefreshCaller(String refreshCaller) {}
    public void registerRequiredNonEditableProperty(String propertyName) {}
    public void addRequiredNonEditableProperties() {}

    public java.util.Map getDisplayedErrors() { return new java.util.HashMap(); }
    public java.util.Map getDisplayedWarnings() { return new java.util.HashMap(); }
    public java.util.Map getDisplayedInfo() { return new java.util.HashMap(); }

    public String getFormKey() { return null; }
    public void setFormKey(String formKey) {}
    public void populate(jakarta.servlet.http.HttpServletRequest request) {}
}
