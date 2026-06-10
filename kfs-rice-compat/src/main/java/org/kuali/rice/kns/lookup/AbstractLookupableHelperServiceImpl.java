package org.kuali.rice.kns.lookup;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.kns.web.struts.form.LookupForm;
import org.kuali.rice.kns.web.ui.Row;
public abstract class AbstractLookupableHelperServiceImpl implements LookupableHelperService {
    public AbstractLookupableHelperServiceImpl() {}
    public List getSearchResults(Map<String, String> fieldValues) { return new ArrayList(); }
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) { return new ArrayList<>(); }
    protected List<HtmlData> getEmptyActionUrls() { return new ArrayList<>(); }
    protected org.kuali.rice.krad.service.BusinessObjectService getBusinessObjectService() { return null; }
    public List<String> getDefaultSortColumns() { return new ArrayList<>(); }
    public Collection performLookup(LookupForm lookupForm, Collection resultTable, boolean bounded) { return new ArrayList(); }
    public List<Row> getRows() { return new ArrayList<>(); }
    public List getSearchResultsUnbounded(Map<String, String> fieldValues) { return new ArrayList(); }
    public void validateSearchParameters(Map<String, String> fieldValues) {}
    public boolean isSearchUsingOnlyPrimaryKeyValues() { return false; }
    public String getPrimaryKeyFieldLabels() { return ""; }
    public void setPrimaryKeyFieldLabels(String labels) {}
    public void setSearchUsingOnlyPrimaryKeyValues(boolean val) {}
    protected org.kuali.rice.krad.service.PersistenceStructureService getPersistenceStructureService() { return null; }
    public HtmlData getInquiryUrl(org.kuali.rice.krad.bo.BusinessObject bo, String propertyName) { return null; }
    protected String backLocation;
    public void setBackLocation(String backLocation) {}
    public void setDocFormKey(String docFormKey) {}
    public void setReferencesToRefresh(String refs) {}
    public String getBackLocation() { return null; }
    public String getDocFormKey() { return null; }
    public String getReferencesToRefresh() { return null; }
    public Class getBusinessObjectClass() { return null; }
    public void setBusinessObjectClass(Class clazz) {}
    public void prepareToReturnSelectedResultBOs(org.kuali.rice.kns.web.struts.form.MultipleValueLookupForm form) {}
    public int getMaxRowsPerPage(org.kuali.rice.kns.web.struts.form.LookupForm form) { return 100; }
    public String getLookupAnchor() { return null; }
    public void setLookupAnchor(String anchor) {}
    public void setPrependDisplayText(String text) {}
    public org.kuali.rice.kns.lookup.LookupableHelperService getLookupableHelperService() { return null; }
    public org.kuali.rice.kns.lookup.Lookupable getLookupable() { return null; }
    public Class getBusinessObjectAttributeClass(Class clazz, String propertyName) { return String.class; }
    public Class getAttributeFormatter(Class<? extends Object> clazz, String propertyName) { return null; }
    protected HtmlData.AnchorHtmlData getUrlData(org.kuali.rice.krad.bo.BusinessObject bo, String methodToCall, java.util.List pkNames) { return new HtmlData.AnchorHtmlData(); }
    protected HtmlData getReturnAnchorHtmlData(org.kuali.rice.krad.bo.BusinessObject bo, java.util.Properties params, org.kuali.rice.kns.web.struts.form.LookupForm form, java.util.List returnKeys, org.kuali.rice.kns.document.authorization.BusinessObjectRestrictions restrictions) { return new HtmlData(); }
    protected void persistResultsTable(String lookupResultsSequenceNumber, java.util.List resultTable, String personId) {}
    protected java.util.List<org.kuali.rice.kns.web.ui.ResultRow> retrieveResultsTable(String lookupResultsSequenceNumber, String personId) { return new java.util.ArrayList<>(); }

    public org.kuali.rice.kns.web.ui.Field getPropertyField(Class clazz, String propertyName, boolean forLookup) { return new org.kuali.rice.kns.web.ui.Field(); }
    public void setInquiryURL(org.kuali.rice.kns.web.ui.Field field, Object bo, String propertyName) {}
    protected org.kuali.rice.kns.lookup.LookupService getLookupService() { return null; }
    protected Object getDictionaryValidationService() { return null; }
    protected Object getModuleConfiguration() { return null; }
    protected Class findFormatter(Class<? extends Object> boClass) { return null; }
    protected Object getFormatter(Class<? extends Object> boClass) { return null; }
    protected Object ddService;
    protected org.kuali.rice.krad.service.BusinessObjectService businessObjectService;

    public java.util.Collection findCollectionBySearchHelper(Class clazz, java.util.Map<String, String> formProps, boolean unbounded) { return new java.util.ArrayList(); }
    protected org.kuali.rice.krad.service.KualiConfigurationService getKualiConfigurationService() { return null; }

    public org.kuali.rice.kns.lookup.HtmlData getReturnUrl(org.kuali.rice.krad.bo.BusinessObject bo, org.kuali.rice.kns.web.struts.form.LookupForm lookupForm, java.util.List returnKeys, org.kuali.rice.kns.document.authorization.BusinessObjectRestrictions restrictions) { return new org.kuali.rice.kns.lookup.HtmlData(); }
    protected java.util.Properties getParameters(org.kuali.rice.krad.bo.BusinessObject bo, java.util.Map fieldConversions, String lookupImpl, java.util.List returnKeys) { return new java.util.Properties(); }
    public org.kuali.rice.kns.lookup.HtmlData getUrlData(org.kuali.rice.krad.bo.BusinessObject bo, String methodToCall, String lookupAction, java.util.List pkNames) { return new org.kuali.rice.kns.lookup.HtmlData(); }
    public java.util.List getColumns() { return new java.util.ArrayList(); }
    public org.kuali.rice.kns.web.ui.Field getExtraField() { return null; }
    public boolean allowsMaintenanceNewOrCopyAction() { return true; }
    public boolean allowsNewOrCopyAction(String documentTypeName) { return true; }
    public org.kuali.rice.kns.service.BusinessObjectDictionaryService getBusinessObjectDictionaryService() { return null; }
    public org.kuali.rice.kns.service.DataDictionaryService getDataDictionaryService() { return null; }
    public java.util.List<String> getReadOnlyFieldsList() { return new java.util.ArrayList<>(); }
    public java.util.List<org.kuali.rice.kns.web.ui.Field> wrapFields(java.util.List<org.kuali.rice.kns.web.ui.Field> fields, int numColumns) { return fields; }
    public boolean isResultReturnable(org.kuali.rice.krad.bo.BusinessObject object) { return true; }
    public org.kuali.rice.kns.lookup.HtmlData getHyperLink(Class clazz, java.util.Map<String, String> fieldConversions, String lookupParameters) { return null; }
    public java.util.Map<String, String[]> getParameters() { return new java.util.HashMap<>(); }
    public void setParameters(java.util.Map<String, String[]> params) {}
    public org.kuali.rice.coreservice.framework.parameter.ParameterService getParameterService() { return null; }
}
