package org.kuali.kfs.coa.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.kns.service.MaintenanceDocumentDictionaryService;
import org.kuali.rice.krad.dao.MaintenanceDocumentDao;
import org.kuali.rice.krad.service.DocumentHeaderService;
import org.kuali.rice.krad.service.NoteService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class SubObjectTrickleDownInactivationServiceImplTest extends KfsUnitTestBase {

    @Mock
    private MaintenanceDocumentDictionaryService maintenanceDocumentDictionaryService;
    @Mock
    private MaintenanceDocumentDao maintenanceDocumentDao;
    @Mock
    private NoteService noteService;
    @Mock
    private ConfigurationService kualiConfigurationService;
    @Mock
    private DocumentHeaderService documentHeaderService;

    @InjectMocks
    private SubObjectTrickleDownInactivationServiceImpl service;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(service).isNotNull();
    }
}
