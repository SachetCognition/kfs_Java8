package org.kuali.rice.kns.lookup;
import java.util.Collection;
import java.util.Map;
public interface Lookupable {
    void setBusinessObjectDictionaryService(org.kuali.rice.kns.service.BusinessObjectDictionaryService svc);
    void setBusinessObjectClass(Class boClass);
    void setFieldConversions(Map<String, String> fieldConversions);
    void validateSearchParameters(Map<String, String> fieldValues);
    boolean isSearchUsingOnlyPrimaryKeyValues();
    String getPrimaryKeyFieldLabels();
    LookupableHelperService getLookupableHelperService();
    Collection performLookup(org.kuali.rice.kns.web.struts.form.LookupForm lookupForm, Collection resultTable, boolean bounded);
    java.util.List getDefaultSortColumns();
    void setLookupParameters(Map<String, String> lookupParameters);
    String getLookupableImplServiceName();
    Map<String, String> getFieldConversions();
    Map<String, String> getLookupParameters();
    java.util.List<org.kuali.rice.kns.web.ui.Row> getRows();
    boolean checkForAdditionalFields(java.util.Map fieldValues);
    String getReturnUrl(org.kuali.rice.krad.bo.BusinessObject bo, java.util.Map fieldConversions, String lookupImpl, java.util.List pkNames);
    String getReturnUrl(org.kuali.rice.krad.bo.BusinessObject bo, org.kuali.rice.kns.web.struts.form.LookupForm form, java.util.List pkNames);
    String getBackLocation();
    void setBackLocation(String backLocation);
    String getDocFormKey();
    void setDocFormKey(String docFormKey);
    String getDocNum();
    void setDocNum(String docNum);
}
