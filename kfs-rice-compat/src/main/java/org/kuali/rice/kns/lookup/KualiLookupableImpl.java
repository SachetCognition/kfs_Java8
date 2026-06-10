package org.kuali.rice.kns.lookup;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
public class KualiLookupableImpl implements Lookupable {
    public KualiLookupableImpl() {}
    public void setBusinessObjectDictionaryService(org.kuali.rice.kns.service.BusinessObjectDictionaryService svc) {}
    public void setBusinessObjectClass(Class boClass) {}
    public void setFieldConversions(Map<String, String> fieldConversions) {}
    public void validateSearchParameters(Map<String, String> fieldValues) {}
    public boolean isSearchUsingOnlyPrimaryKeyValues() { return false; }
    public String getPrimaryKeyFieldLabels() { return ""; }
    public LookupableHelperService getLookupableHelperService() { return null; }
    public Collection performLookup(org.kuali.rice.kns.web.struts.form.LookupForm lookupForm, Collection resultTable, boolean bounded) { return new ArrayList(); }
    public List getDefaultSortColumns() { return new ArrayList(); }
    public void setLookupParameters(Map<String, String> lookupParameters) {}
    public String getLookupableImplServiceName() { return null; }
    public Map<String, String> getFieldConversions() { return new HashMap<>(); }
    public Map<String, String> getLookupParameters() { return new HashMap<>(); }
    public boolean checkForAdditionalFields(java.util.Map fieldValues) { return false; }
    public java.util.List<org.kuali.rice.kns.web.ui.Row> getRows() { return new java.util.ArrayList<>(); }
    public String getReturnUrl(org.kuali.rice.krad.bo.BusinessObject bo, java.util.Map fieldConversions, String lookupImpl, java.util.List pkNames) { return null; }
    public String getReturnUrl(org.kuali.rice.krad.bo.BusinessObject bo, org.kuali.rice.kns.web.struts.form.LookupForm form, java.util.List pkNames) { return null; }
    public String getBackLocation() { return null; }
    public void setBackLocation(String s) {}
    public String getDocFormKey() { return null; }
    public void setDocFormKey(String s) {}
    public String getDocNum() { return null; }
    public void setDocNum(String s) {}
}
