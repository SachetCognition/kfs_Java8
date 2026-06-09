package org.kuali.kfs.sys.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.dataaccess.BusinessObjectMetaDataDao;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class KfsBusinessObjectMetaDataServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectMetaDataDao businessObjectMetaDataDao;
    @Mock
    private ParameterService parameterService;
    @Mock
    private DataDictionaryService dataDictionaryService;
    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private KfsBusinessObjectMetaDataServiceImpl service;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(service).isNotNull();
    }
}
