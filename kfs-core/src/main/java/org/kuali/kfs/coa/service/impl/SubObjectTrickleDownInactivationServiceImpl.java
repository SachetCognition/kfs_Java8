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
package org.kuali.kfs.coa.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.coa.businessobject.ObjectCode;
import org.kuali.kfs.coa.businessobject.SubObjectCode;
import org.kuali.kfs.coa.service.SubObjectTrickleDownInactivationService;
import org.kuali.kfs.sys.KFSKeyConstants;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.kns.service.MaintenanceDocumentDictionaryService;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.bo.Note;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.dao.MaintenanceDocumentDao;
import org.kuali.rice.krad.maintenance.MaintenanceLock;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentHeaderService;
import org.kuali.rice.krad.service.NoteService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class SubObjectTrickleDownInactivationServiceImpl implements SubObjectTrickleDownInactivationService {

    private static final int NO_OF_SUB_OBJECTS_PER_NOTE = 15;

    private static final Logger LOG = Logger.getLogger(SubObjectTrickleDownInactivationServiceImpl.class);
    
    protected BusinessObjectService businessObjectService;
    protected MaintenanceDocumentDictionaryService maintenanceDocumentDictionaryService;
    protected MaintenanceDocumentDao maintenanceDocumentDao;
    protected NoteService noteService;
    protected ConfigurationService kualiConfigurationService;
    protected UniversityDateService universityDateService;
    protected DocumentHeaderService documentHeaderService;
    
    public List<MaintenanceLock> generateTrickleDownMaintenanceLocks(Account inactivatedAccount, String documentNumber) { return new java.util.ArrayList<>(); }

    public List<MaintenanceLock> generateTrickleDownMaintenanceLocks(ObjectCode inactivatedObjectCode, String documentNumber) { return new java.util.ArrayList<>(); }

    public List<MaintenanceLock> generateTrickleDownMaintenanceLocks(Collection<SubObjectCode> subObjects, String documentNumber) { return new java.util.ArrayList<>(); }
    
    protected class TrickleDownInactivationStatus {
        public List<SubObjectCode> inactivatedSubObjCds;
        public Map<SubObjectCode, String> alreadyLockedSubObjCds;
        public List<SubObjectCode> errorPersistingSubObjCds;
        
        public TrickleDownInactivationStatus() { return null; }
    }
    
    public void trickleDownInactivateSubObjects(Account inactivatedAccount, String documentNumber) {  }

    public void trickleDownInactivateSubObjects(ObjectCode inactivatedObject, String documentNumber) {  }

    protected TrickleDownInactivationStatus trickleDownInactivate(Collection<SubObjectCode> subObjects, String documentNumber) { return null; }
    
    protected void addNotesToDocument(TrickleDownInactivationStatus trickleDownInactivationStatus, String documentNumber) {  }

    protected MaintenanceLock verifyAllLocksFromThisDocument(List<MaintenanceLock> maintenanceLocks, String documentNumber) { return null; }
    
    protected Maintainable getSubObjectMaintainable(String documentNumber) { return null; }
    
    protected Collection<SubObjectCode> getAssociatedSubObjects(Account account) { return new java.util.ArrayList<>(); }
    
    protected Collection<SubObjectCode> getAssociatedSubObjects(ObjectCode objectCode) { return new java.util.ArrayList<>(); }

    protected void addNotes(String documentNumber, List<SubObjectCode> listOfSubObjects, String messageKey, PersistableBusinessObject noteParent, Note noteTemplate) {  }
    
    protected void addMaintenanceLockedNotes(String documentNumber, Map<SubObjectCode, String> lockedSubObjects, String messageKey, PersistableBusinessObject noteParent, Note noteTemplate) {  }
    
    protected String createSubObjectChunk(List<SubObjectCode> listOfSubObjects, int startIndex, int endIndex) { return null; }
    
    protected int getNumSubObjectsPerNote() { return 0; }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {  }

    public void setMaintenanceDocumentDictionaryService(MaintenanceDocumentDictionaryService maintenanceDocumentDictionaryService) {  }

    public void setMaintenanceDocumentDao(MaintenanceDocumentDao maintenanceDocumentDao) {  }

    public void setNoteService(NoteService noteService) {  }

    public void setConfigurationService(ConfigurationService kualiConfigurationService) {  }

    public void setUniversityDateService(UniversityDateService universityDateService) {  }

    public void setDocumentHeaderService(DocumentHeaderService documentHeaderService) {  }
}
