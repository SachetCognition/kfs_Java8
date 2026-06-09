package org.kuali.kfs.module.ec.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ec.businessobject.EffortCertificationDocumentBuild;
import org.kuali.kfs.module.ec.service.EffortCertificationDetailBuildService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EffortCertificationDocumentBuildServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private EffortCertificationDetailBuildService detailBuildService;

    @InjectMocks
    private EffortCertificationDocumentBuildServiceImpl service;

    @Nested
    @DisplayName("removeExistingDocumentBuild")
    class RemoveExistingDocumentBuild {

        @Test
        @SuppressWarnings("unchecked")
        void shouldFindAndDeleteMatchingDocuments() {
            Map<String, String> fieldValues = new HashMap<>();
            fieldValues.put("universityFiscalYear", "2024");

            Collection<EffortCertificationDocumentBuild> docs = new ArrayList<>();
            when(businessObjectService.findMatching(eq(EffortCertificationDocumentBuild.class), any(Map.class)))
                    .thenReturn(docs);

            service.removeExistingDocumentBuild(fieldValues);

            verify(businessObjectService).findMatching(EffortCertificationDocumentBuild.class, fieldValues);
            verify(businessObjectService).delete(any(ArrayList.class));
        }
    }
}
