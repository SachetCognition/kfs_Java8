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
package org.kuali.kfs.gl.web.struts;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.kuali.kfs.gl.GeneralLedgerConstants;
import org.kuali.kfs.gl.businessobject.Entry;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.businessobject.GeneralLedgerPendingEntry;
import org.kuali.kfs.sys.businessobject.lookup.LookupableSpringContext;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.kns.lookup.Lookupable;
import org.kuali.rice.kns.service.BusinessObjectDictionaryService;
import org.kuali.rice.kns.web.struts.form.LookupForm;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.bo.BusinessObject;

/**
 * This class is the action form for balance inquiries.
 */
public class BalanceInquiryForm extends LookupForm {
    private static final long serialVersionUID = 1L;

    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(BalanceInquiryForm.class);

    private String formKey;
    private String backLocation;
    private Map fields;
    private String lookupableImplServiceName;
    private String conversionFields;
    private Map fieldConversions;
    private String businessObjectClassName;
    private Lookupable lookupable;
    private Lookupable pendingEntryLookupable;
    private boolean hideReturnLink = false;


    /**
     * Picks out business object name from the request to get retrieve a lookupable and set properties.
     * 
     * @see org.kuali.rice.kns.web.struts.form.LookupForm#populate(javax.servlet.http.HttpServletRequest)
     *      
     * KRAD Conversion: Lookupable performs customization of the fields and check for additional fields.
     *  
     * Fields are in data dictionary for bo Balance.
     */
    public void populate(HttpServletRequest request) {  }

    /**
     * @return Returns the lookupableImplServiceName.
     */
    public String getLookupableImplServiceName() { return null; }

    /**
     * @param lookupableImplServiceName The lookupableImplServiceName to set.
     */
    public void setLookupableImplServiceName(String lookupableImplServiceName) {  }


    /**
     * @return Returns the backLocation.
     */
    public String getBackLocation() { return null; }

    /**
     * @param backLocation The backLocation to set.
     */
    public void setBackLocation(String backLocation) {  }

    /**
     * @return Returns the formKey.
     */
    public String getFormKey() { return null; }

    /**
     * @param formKey The formKey to set.
     */
    public void setFormKey(String formKey) {  }

    /**
     * @return Returns the fields.
     */
    public Map getFields() { return new java.util.HashMap<>(); }

    /**
     * @param fields The fields to set.
     */
    public void setFields(Map fields) {  }


    /**
     * @return Returns the conversionFields.
     */
    public String getConversionFields() { return null; }

    /**
     * @param conversionFields The conversionFields to set.
     */
    public void setConversionFields(String conversionFields) {  }

    /**
     * @return Returns the fieldConversions.
     */
    public Map getFieldConversions() { return new java.util.HashMap<>(); }

    /**
     * @param fieldConversions The fieldConversions to set.
     */
    public void setFieldConversions(Map fieldConversions) {  }

    /**
     * @return Returns the businessObjectClassName.
     */
    public String getBusinessObjectClassName() { return null; }

    /**
     * @param businessObjectClassName The businessObjectClassName to set.
     */
    public void setBusinessObjectClassName(String businessObjectClassName) {  }


    /**
     * @return Returns the lookupable.
     */
    public Lookupable getLookupable() { return null; }


    /**
     * @param lookupable The lookupable to set.
     */
    public void setLookupable(Lookupable lookupable) {  }


    /**
     * @return Returns the hideReturnLink.
     */
    public boolean isHideReturnLink() { return false; }


    /**
     * @param hideReturnLink The hideReturnLink to set.
     */
    public void setHideReturnLink(boolean hideReturnLink) {  }


    /**
     * @param pendingEntryLookupable
     */
    public void setPendingEntryLookupable(Lookupable pendingEntryLookupable) {  }


    /**
     * @return Returns the pendingEntryLookupable.
     */
    public Lookupable getPendingEntryLookupable() { return null; }
}
