package org.kuali.rice.krad.datadictionary;
import java.util.List;
import java.util.Map;
import java.util.Set;
public interface DataDictionaryMapper {
    BusinessObjectEntry getBusinessObjectEntry(DataDictionaryIndex index, String className);
    DataObjectEntry getDataObjectEntry(DataDictionaryIndex index, String className);
    BusinessObjectEntry getBusinessObjectEntryForConcreteClass(DataDictionaryIndex index, String className);
    List<String> getBusinessObjectClassNames(DataDictionaryIndex index);
    Map<String, BusinessObjectEntry> getBusinessObjectEntries(DataDictionaryIndex index);
    DataDictionaryEntry getDictionaryObjectEntry(DataDictionaryIndex index, String className);
    Map<String, DocumentEntry> getDocumentEntries(DataDictionaryIndex index);
    DocumentEntry getDocumentEntry(DataDictionaryIndex index, String documentTypeName);
    MaintenanceDocumentEntry getMaintenanceDocumentEntryForBusinessObjectClass(DataDictionaryIndex index, Class<?> boClass);
    Set getAllInactivationBlockingMetadatas(DataDictionaryIndex index, Class<?> boClass);
    org.kuali.rice.krad.uif.view.View getViewById(UifDictionaryIndex uifIndex, String viewId);
    org.kuali.rice.krad.uif.view.View getViewByTypeIndex(UifDictionaryIndex uifIndex, org.kuali.rice.krad.uif.UifConstants.ViewType viewType, Map<String, String> indexKey);
    org.springframework.beans.PropertyValues getViewPropertiesById(UifDictionaryIndex uifIndex, String viewId);
    org.springframework.beans.PropertyValues getViewPropertiesByType(UifDictionaryIndex uifIndex, org.kuali.rice.krad.uif.UifConstants.ViewType viewType, Map<String, String> indexKey);
    java.util.List getViewsForType(UifDictionaryIndex uifIndex, org.kuali.rice.krad.uif.UifConstants.ViewType viewType);
    boolean viewByTypeExist(UifDictionaryIndex uifIndex, org.kuali.rice.krad.uif.UifConstants.ViewType viewType, Map<String, String> indexKey);
}
