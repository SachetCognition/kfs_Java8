package org.kuali.rice.kew.framework.document.search;

public class DocumentSearchResultSetConfiguration extends org.kuali.rice.core.api.mo.AbstractDataTransferObject implements org.kuali.rice.kew.framework.document.search.DocumentSearchResultSetConfigurationContract {
    public DocumentSearchResultSetConfiguration() {}


    public boolean isOverrideSearchableAttributes() { return false; }
    public java.util.List<java.lang.String> getCustomFieldNamesToAdd() { return new java.util.ArrayList(); }
    public java.util.List<org.kuali.rice.kew.framework.document.search.StandardResultField> getStandardResultFieldsToRemove() { return new java.util.ArrayList(); }
    public java.util.List<org.kuali.rice.core.api.uif.RemotableAttributeField> getAdditionalAttributeFields() { return new java.util.ArrayList(); }
}
