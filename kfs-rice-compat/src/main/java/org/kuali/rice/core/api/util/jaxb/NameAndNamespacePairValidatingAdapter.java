package org.kuali.rice.core.api.util.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class NameAndNamespacePairValidatingAdapter extends XmlAdapter<org.kuali.rice.core.util.jaxb.NameAndNamespacePair, org.kuali.rice.core.util.jaxb.NameAndNamespacePair> {
    public NameAndNamespacePairValidatingAdapter() {}
    
    @Override
    public org.kuali.rice.core.util.jaxb.NameAndNamespacePair unmarshal(org.kuali.rice.core.util.jaxb.NameAndNamespacePair v) throws Exception { return v; }
    
    @Override
    public org.kuali.rice.core.util.jaxb.NameAndNamespacePair marshal(org.kuali.rice.core.util.jaxb.NameAndNamespacePair v) throws Exception { return v; }
}
