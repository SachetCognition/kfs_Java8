package org.kuali.kfs.coa.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.OrganizationReversion;
import org.kuali.kfs.coa.businessobject.OrganizationReversionCategory;
import org.kuali.kfs.gl.GeneralLedgerConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class OrganizationReversionServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private ParameterService parameterService;

    @InjectMocks
    private OrganizationReversionServiceImpl organizationReversionService;

    @Test
    void isCategoryActive_nullCategory_returnsFalse() {
        when(businessObjectService.findBySinglePrimaryKey(OrganizationReversionCategory.class, "XX"))
                .thenReturn(null);

        boolean result = organizationReversionService.isCategoryActive("XX");
        assertThat(result).isFalse();
    }

    @Test
    void isCategoryActive_activeCategory_returnsTrue() {
        OrganizationReversionCategory category = new OrganizationReversionCategory();
        category.setActive(true);
        when(businessObjectService.findBySinglePrimaryKey(OrganizationReversionCategory.class, "A1"))
                .thenReturn(category);

        boolean result = organizationReversionService.isCategoryActive("A1");
        assertThat(result).isTrue();
    }

    @Test
    void isCategoryActive_inactiveCategory_returnsFalse() {
        OrganizationReversionCategory category = new OrganizationReversionCategory();
        category.setActive(false);
        when(businessObjectService.findBySinglePrimaryKey(OrganizationReversionCategory.class, "A1"))
                .thenReturn(category);

        boolean result = organizationReversionService.isCategoryActive("A1");
        assertThat(result).isFalse();
    }

    @Test
    void isCategoryActiveByName_noCategories_returnsFalse() {
        when(businessObjectService.findMatching(OrganizationReversionCategory.class,
                Collections.singletonMap("organizationReversionCategoryName", "Unknown")))
                .thenReturn(Collections.<OrganizationReversionCategory>emptyList());

        boolean result = organizationReversionService.isCategoryActiveByName("Unknown");
        assertThat(result).isFalse();
    }

    @Test
    void getOrganizationReversionDetaiFromSystemParameters_delegatesToParameterService() {
        when(parameterService.getParameterValueAsString(OrganizationReversion.class,
                GeneralLedgerConstants.OrganizationReversionProcess.UNALLOC_OBJECT_CODE_PARM))
                .thenReturn("5000");

        String result = organizationReversionService.getOrganizationReversionDetaiFromSystemParameters();
        assertThat(result).isEqualTo("5000");
    }
}
