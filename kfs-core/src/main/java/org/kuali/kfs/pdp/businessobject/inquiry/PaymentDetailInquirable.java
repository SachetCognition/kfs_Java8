/*
 * The Kuali Financial System, a comprehensive financial management system for higher education.
 * 
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kfs.pdp.businessobject.inquiry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.kuali.kfs.pdp.PdpConstants;
import org.kuali.kfs.pdp.PdpPropertyConstants;
import org.kuali.kfs.pdp.businessobject.CustomerProfile;
import org.kuali.kfs.pdp.businessobject.PaymentDetail;
import org.kuali.kfs.pdp.service.ResearchParticipantPaymentValidationService;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.businessobject.inquiry.KfsInquirableImpl;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.datadictionary.InquirySectionDefinition;
import org.kuali.rice.kns.inquiry.InquiryRestrictions;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.service.BusinessObjectAuthorizationService;
import org.kuali.rice.kns.web.ui.Section;
import org.kuali.rice.kns.web.ui.SectionBridge;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.datadictionary.AttributeDefinition;
import org.kuali.rice.krad.datadictionary.AttributeSecurity;
import org.kuali.rice.krad.datadictionary.BusinessObjectEntry;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;
import org.kuali.rice.krad.util.UrlFactory;

public class PaymentDetailInquirable extends KfsInquirableImpl {

    /**
     * @see org.kuali.kfs.sys.businessobject.inquiry.KfsInquirableImpl#getInquiryUrl(org.kuali.rice.krad.bo.BusinessObject,
     *      java.lang.String, boolean)
     */
    @Override
    public HtmlData getInquiryUrl(BusinessObject businessObject, String attributeName, boolean forceInquiry) { return null; }


    /**
     * Overrides superclass method so that we could mask the payee name field whenever the customer profile is
     * the one for Research Participant Upload and don't need to mask the payee name field for any other cases.
     *
     * @see org.kuali.rice.kns.inquiry.KualiInquirableImpl#getSections(org.kuali.rice.kns.bo.BusinessObject)
     */
    @Override
    public List<Section> getSections(BusinessObject bo) { return new java.util.ArrayList<>(); }

}
