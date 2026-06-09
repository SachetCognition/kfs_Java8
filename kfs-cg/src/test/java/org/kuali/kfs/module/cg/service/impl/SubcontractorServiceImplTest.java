package org.kuali.kfs.module.cg.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.cg.businessobject.SubContractor;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SubcontractorServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    private SubcontractorServiceImpl subcontractorService;

    @BeforeEach
    void setUp() {
        subcontractorService = new SubcontractorServiceImpl();
        subcontractorService.setBusinessObjectService(businessObjectService);
    }

    @Test
    void testGetByPrimaryIdReturnsSubcontractor() {
        String subcontractorNumber = "SC001";
        SubContractor expected = new SubContractor();
        expected.setSubcontractorNumber(subcontractorNumber);

        Map<String, Object> primaryKeys = new HashMap<>();
        primaryKeys.put(KFSPropertyConstants.SUBCONTRACTOR_NUMBER, subcontractorNumber);

        when(businessObjectService.findByPrimaryKey(eq(SubContractor.class), eq(primaryKeys)))
                .thenReturn(expected);

        SubContractor result = subcontractorService.getByPrimaryId(subcontractorNumber);

        assertThat(result).isSameAs(expected);
        verify(businessObjectService).findByPrimaryKey(eq(SubContractor.class), eq(primaryKeys));
    }

    @Test
    void testGetByPrimaryIdTrimsInput() {
        String subcontractorNumber = " SC001 ";
        SubContractor expected = new SubContractor();

        Map<String, Object> primaryKeys = new HashMap<>();
        primaryKeys.put(KFSPropertyConstants.SUBCONTRACTOR_NUMBER, "SC001");

        when(businessObjectService.findByPrimaryKey(eq(SubContractor.class), eq(primaryKeys)))
                .thenReturn(expected);

        SubContractor result = subcontractorService.getByPrimaryId(subcontractorNumber);

        assertThat(result).isSameAs(expected);
    }

    @Test
    void testGetByPrimaryIdReturnsNullWhenNotFound() {
        String subcontractorNumber = "NOTFOUND";

        Map<String, Object> primaryKeys = new HashMap<>();
        primaryKeys.put(KFSPropertyConstants.SUBCONTRACTOR_NUMBER, subcontractorNumber);

        when(businessObjectService.findByPrimaryKey(eq(SubContractor.class), eq(primaryKeys)))
                .thenReturn(null);

        SubContractor result = subcontractorService.getByPrimaryId(subcontractorNumber);

        assertThat(result).isNull();
    }
}
