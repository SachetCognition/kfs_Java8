package org.kuali.kfs.coa.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.ResponsibilityCenter;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class ResponsibilityCenterServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private ResponsibilityCenterServiceImpl responsibilityCenterService;

    @Test
    void getByPrimaryId_returnsCenter() {
        ResponsibilityCenter expected = new ResponsibilityCenter();
        when(businessObjectService.findBySinglePrimaryKey(ResponsibilityCenter.class, "RC01")).thenReturn(expected);

        ResponsibilityCenter result = responsibilityCenterService.getByPrimaryId("RC01");
        assertThat(result).isSameAs(expected);
    }

    @Test
    void getByPrimaryId_notFound_returnsNull() {
        when(businessObjectService.findBySinglePrimaryKey(ResponsibilityCenter.class, "XX")).thenReturn(null);

        ResponsibilityCenter result = responsibilityCenterService.getByPrimaryId("XX");
        assertThat(result).isNull();
    }
}
