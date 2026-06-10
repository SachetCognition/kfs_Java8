package org.kuali.rice.kns.inquiry;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class KualiInquirableImpl extends org.kuali.rice.krad.inquiry.InquirableImpl implements Inquirable {
    public static final String INQUIRY_TITLE_PREFIX = "";
    
    public KualiInquirableImpl() {}
    
    public Object retrieveDataObject(Map fieldValues) { return null; }
    public org.kuali.rice.krad.bo.BusinessObject getBusinessObject(Map fieldValues) { return null; }
    public List<org.kuali.rice.kns.web.ui.Section> getSections(org.kuali.rice.krad.bo.BusinessObject bo) { return new ArrayList(); }
    public org.kuali.rice.kns.lookup.HtmlData getInquiryUrl(org.kuali.rice.krad.bo.BusinessObject bo, String propertyName) { return null; }
    public void setBusinessObjectClass(Class clazz) {}
    public Class getBusinessObjectClass() { return null; }
    public org.kuali.rice.kns.lookup.HtmlData getInquiryUrl(org.kuali.rice.krad.bo.BusinessObject businessObject, String attributeName, boolean forceInquiry) { return null; }
    public org.kuali.rice.kns.lookup.HtmlData getHyperLink(Class clazz, java.util.Map<String, String> fieldValues, String linkText) { return null; }
}
