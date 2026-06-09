package org.kuali.kfs.module.ec.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.integration.cg.ContractsAndGrantsModuleService;
import org.kuali.kfs.integration.ld.LaborModuleService;
import org.kuali.kfs.module.ec.businessobject.EffortCertificationDetail;
import org.kuali.kfs.module.ec.document.EffortCertificationDocument;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KualiModuleService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class EffortCertificationDocumentServiceImplTest extends KfsUnitTestBase {

    @Mock
    private LaborModuleService laborModuleService;

    @Mock
    private KualiModuleService kualiModuleService;

    @Mock
    private ContractsAndGrantsModuleService contractsAndGrantsModuleService;

    @Mock
    private DocumentService documentService;

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private EffortCertificationDocumentServiceImpl service;

    @Nested
    @DisplayName("removeEffortCertificationDetailLines")
    class RemoveDetailLines {

        @Test
        @SuppressWarnings("unchecked")
        void shouldCallDeleteMatchingOnBusinessObjectService() {
            EffortCertificationDocument doc =
                    mock(EffortCertificationDocument.class, CALLS_REAL_METHODS);
            doc.setDocumentNumber("DOC001");

            service.removeEffortCertificationDetailLines(doc);

            verify(businessObjectService).deleteMatching(
                    eq(EffortCertificationDetail.class), any(Map.class));
        }
    }
}
