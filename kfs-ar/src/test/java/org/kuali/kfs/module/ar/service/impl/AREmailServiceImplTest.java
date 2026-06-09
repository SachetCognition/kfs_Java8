package org.kuali.kfs.module.ar.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.service.ContractsGrantsBillingUtilityService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.AttachmentMailService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KualiModuleService;
import org.kuali.rice.krad.service.NoteService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class AREmailServiceImplTest extends KfsUnitTestBase {

    @Mock private AttachmentMailService mailService;
    @Mock private ParameterService parameterService;
    @Mock private DataDictionaryService dataDictionaryService;
    @Mock private ConfigurationService kualiConfigurationService;
    @Mock private BusinessObjectService businessObjectService;
    @Mock private DocumentService documentService;
    @Mock private NoteService noteService;
    @Mock private KualiModuleService kualiModuleService;
    @Mock private ContractsGrantsBillingUtilityService contractsGrantsBillingUtilityService;

    @InjectMocks
    private AREmailServiceImpl service;

    @Test
    void serviceInitialization_shouldCreateInstance() {
        assertThat(service).isNotNull();
    }
}
