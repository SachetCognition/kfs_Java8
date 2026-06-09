package org.kuali.rice.kns.lookup;

public class LookupUtils {
    public LookupUtils() {}


    public static void removeHiddenCriteriaFields(java.lang.Class p0, java.util.Map p1) {  }
    public static java.lang.Integer getSearchResultsLimit(java.lang.Class p0) { return null; }
    public static java.lang.Integer getApplicationSearchResultsLimit() { return null; }
    public static java.lang.Integer getBusinessObjectSearchResultsLimit(java.lang.Class p0) { return null; }
    public static void applySearchResultsLimit(java.lang.Class p0, org.apache.ojb.broker.query.Criteria p1, org.kuali.rice.core.framework.persistence.platform.DatabasePlatform p2) {  }
    public static void applySearchResultsLimit(java.lang.Class p0, org.kuali.rice.core.framework.persistence.jpa.criteria.Criteria p1) {  }
    public static java.lang.Integer getApplicationMaximumSearchResulsPerPageForMultipleValueLookups() { return null; }
    public static java.util.List<java.lang.String> translateReadOnlyFieldsToList(java.lang.String p0) { return new java.util.ArrayList(); }
    public static java.util.Map<java.lang.String, java.lang.String> translateFieldConversions(java.lang.String p0) { return new java.util.HashMap(); }
    public static org.kuali.rice.kns.web.ui.Field setFieldQuickfinder(org.kuali.rice.krad.bo.BusinessObject p0, java.lang.String p1, org.kuali.rice.kns.web.ui.Field p2, java.util.List p3) { return null; }
    public static org.kuali.rice.kns.web.ui.Field setFieldQuickfinder(org.kuali.rice.krad.bo.BusinessObject p0, java.lang.String p1, org.kuali.rice.kns.web.ui.Field p2, java.util.List p3, org.kuali.rice.krad.lookup.SelectiveReferenceRefresher p4) { return null; }
    public static org.kuali.rice.kns.web.ui.Field setFieldQuickfinder(org.kuali.rice.krad.bo.BusinessObject p0, java.lang.String p1, boolean p2, int p3, java.lang.String p4, org.kuali.rice.kns.web.ui.Field p5, java.util.List p6, org.kuali.rice.krad.lookup.SelectiveReferenceRefresher p7) { return null; }
    public static org.kuali.rice.kns.web.ui.Field setFieldQuickfinder(org.kuali.rice.krad.bo.BusinessObject p0, java.lang.String p1, boolean p2, int p3, java.lang.String p4, org.kuali.rice.kns.web.ui.Field p5, java.util.List p6) { return null; }
    public static org.kuali.rice.kns.web.ui.Field setFieldQuickfinder(org.kuali.rice.krad.bo.BusinessObject p0, java.lang.String p1, boolean p2, int p3, java.lang.String p4, org.kuali.rice.kns.web.ui.Field p5, java.util.List p6, boolean p7) { return null; }
    public static java.lang.String getBaseLookupUrl(boolean p0) { return null; }
    public static java.lang.String getBaseInquiryUrl() { return null; }
    public static java.lang.String transformLookupUrlToMultiple(java.lang.String p0) { return null; }
    public static org.kuali.rice.kns.web.ui.Field setFieldDirectInquiry(org.kuali.rice.krad.bo.BusinessObject p0, java.lang.String p1, org.kuali.rice.kns.web.ui.Field p2) { return null; }
    public static java.util.Map getPrimitiveReference(org.kuali.rice.krad.bo.BusinessObject p0, java.lang.String p1) { return new java.util.HashMap(); }
    public static org.kuali.rice.krad.bo.BusinessObject getNestedBusinessObject(org.kuali.rice.krad.bo.BusinessObject p0, java.lang.String p1) { return null; }
    public static java.lang.Class getNestedReferenceClass(org.kuali.rice.krad.bo.BusinessObject p0, java.lang.String p1) { return null; }
    public static java.lang.String convertReferencesToSelectCollectionToString(java.util.Collection<java.lang.String> p0) { return null; }
    public static java.lang.String convertSetOfObjectIdsToString(java.util.Set<java.lang.String> p0) { return null; }
    public static java.util.Set<java.lang.String> convertStringOfObjectIdsToSet(java.lang.String p0) { return new java.util.HashSet(); }
    public static java.util.Comparator findBestValueComparatorForColumn(java.util.List<org.kuali.rice.kns.web.ui.ResultRow> p0, int p1) { return null; }
    public static java.util.Map<java.lang.String, java.lang.String> preProcessRangeFields(java.util.Map<java.lang.String, java.lang.String> p0) { return new java.util.HashMap(); }
    public static java.util.Map<java.lang.String, java.lang.String> generateCompositeSelectedObjectIds(java.util.Set<java.lang.String> p0, java.util.Set<java.lang.String> p1, java.util.Set<java.lang.String> p2) { return new java.util.HashMap(); }
    public static org.kuali.rice.krad.service.DataDictionaryService getDataDictionaryService() { return null; }
    public static org.kuali.rice.krad.service.PersistenceStructureService getPersistenceStructureService() { return null; }
    public static org.kuali.rice.kns.service.BusinessObjectDictionaryService getBusinessObjectDictionaryService() { return null; }
    public static org.kuali.rice.kns.service.BusinessObjectMetaDataService getBusinessObjectMetaDataService() { return null; }
    public static org.kuali.rice.core.api.datetime.DateTimeService getDateTimeService() { return null; }
}
