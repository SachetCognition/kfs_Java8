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
package org.kuali.kfs.sys.document.workflow;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.core.api.uif.DataType;
import org.kuali.rice.core.api.uif.RemotableAbstractControl;
import org.kuali.rice.core.api.uif.RemotableAbstractWidget;
import org.kuali.rice.core.api.uif.RemotableAttributeField;
import org.kuali.rice.core.api.uif.RemotableCheckbox;
import org.kuali.rice.core.api.uif.RemotableHiddenInput;
import org.kuali.rice.core.api.uif.RemotableQuickFinder;
import org.kuali.rice.core.api.uif.RemotableRadioButtonGroup;
import org.kuali.rice.core.api.uif.RemotableSelect;
import org.kuali.rice.core.api.uif.RemotableTextInput;
import org.kuali.rice.core.api.uif.RemotableTextarea;
import org.kuali.rice.kns.datadictionary.control.CheckboxControlDefinition;
import org.kuali.rice.kns.datadictionary.control.HiddenControlDefinition;
import org.kuali.rice.kns.datadictionary.control.MultivalueControlDefinitionBase;
import org.kuali.rice.kns.datadictionary.control.RadioControlDefinition;
import org.kuali.rice.kns.datadictionary.control.SelectControlDefinition;
import org.kuali.rice.kns.datadictionary.control.TextControlDefinition;
import org.kuali.rice.kns.datadictionary.control.TextareaControlDefinition;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.bo.DataObjectRelationship;
import org.kuali.rice.krad.datadictionary.AttributeDefinition;
import org.kuali.rice.krad.datadictionary.control.ControlDefinition;
import org.kuali.rice.krad.keyvalues.KeyValuesFinder;
import org.kuali.rice.krad.service.DataDictionaryService;
import org.kuali.rice.krad.service.DataObjectMetaDataService;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.service.KRADServiceLocatorInternal;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.workflow.service.WorkflowAttributePropertyResolutionService;

//RICE20 This class is a temporary fix to support KNS attribute definitions. Should be deleted when rice2.0 adds support.
public class DataDictionaryRemoteFieldBuilder {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(DataDictionaryRemoteFieldBuilder.class);

    /**
     * @see org.kuali.rice.krad.service.DataDictionaryRemoteFieldService#buildRemotableFieldFromAttributeDefinition(java.lang.String,
     *      java.lang.String)
     */
    public RemotableAttributeField buildRemotableFieldFromAttributeDefinition(String componentClassName, String attributeName) { return null; }

    /**
     * Creates a {@link RemotableAbstractControl} instance based on the control definition within the given attribute definition
     *
     * @param attr - attribute definition instance to pull control from
     * @return RemotableAbstractControl instance or null if one could not be built
     */
    protected RemotableAbstractControl.Builder createControl(AttributeDefinition attr) { return null; }

    /**
     * Will first try to retrieve options configured on the control. If that doesn't return any values then will try to use the
     * optionfinder on the AttributeDefinition.
     *
     * @param attr - AttributeDefinition
     * @return Map of key value pairs
     */
    protected Map<String, String> getValues(AttributeDefinition attr) { return new java.util.HashMap<>(); }

    /**
     * Builds a {@link RemotableQuickFinder} instance for the given attribute based on determined relationships
     * <p>
     * Uses the {@link DataObjectMetaDataService} to find relationships the given attribute participates in within the given class.
     * If a relationship is not found, the title attribute is also checked to determine if a lookup should be rendered back to the
     * component class itself. If a relationship suitable for lookup is found, the associated field conversions and lookup
     * parameters are built
     * </p>
     *
     * @param componentClass - class that attribute belongs to and should be checked for relationships
     * @param attributeName - name of the attribute to determine quickfinder for
     * @return RemotableQuickFinder.Builder instance for the configured lookup, or null if one could not be found
     */
    protected RemotableQuickFinder.Builder createQuickFinder(Class<?> componentClass, String attributeName) { return null; }

    protected DataDictionaryService getDataDictionaryService() { return null; }

    protected DataObjectMetaDataService getDataObjectMetaDataService() { return null; }

    protected ConfigurationService getKualiConfigurationService() { return null; }


    public void setForceUpperCase(java.lang.Boolean arg0) {  }
    public java.util.Map getKeyLabelMap() { return new java.util.HashMap<>(); }
    public void setMultiple(boolean arg0) {  }
    public void setSize(java.lang.Integer arg0) {  }
    public void setCols(java.lang.Integer arg0) {  }
    public void setRows(java.lang.Integer arg0) {  }
}
