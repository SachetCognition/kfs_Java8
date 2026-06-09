package org.kuali.rice.kns.service;

import java.util.Collection;

public interface BusinessObjectMetaDataService {
    org.kuali.rice.krad.datadictionary.BusinessObjectEntry getBusinessObjectEntry(String className);
    Collection<String> getAllBusinessObjectEntryNames();
    org.kuali.rice.krad.datadictionary.BusinessObjectEntry getBusinessObjectEntryForConcreteClass(String className);
}
