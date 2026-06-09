package org.kuali.kfs.module.bc.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionAdministrativePost;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BudgetConstructionAdministrativePostServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private BudgetConstructionAdministrativePostServiceImpl service;

    @Test
    void getByPrimaryId_returnsExpectedPost() {
        String emplid = "EMP001";
        String positionNumber = "POS001";
        BudgetConstructionAdministrativePost expected = new BudgetConstructionAdministrativePost();

        Map<String, Object> primaryKeys = new HashMap<>();
        primaryKeys.put(KFSPropertyConstants.EMPLID, emplid);
        primaryKeys.put(KFSPropertyConstants.POSITION_NUMBER, positionNumber);

        when(businessObjectService.findByPrimaryKey(BudgetConstructionAdministrativePost.class, primaryKeys))
                .thenReturn(expected);

        BudgetConstructionAdministrativePost result = service.getByPrimaryId(emplid, positionNumber);
        assertThat(result).isSameAs(expected);
        verify(businessObjectService).findByPrimaryKey(BudgetConstructionAdministrativePost.class, primaryKeys);
    }

    @Test
    void getByPrimaryId_returnsNullWhenNotFound() {
        String emplid = "INVALID";
        String positionNumber = "NOPOS";

        Map<String, Object> primaryKeys = new HashMap<>();
        primaryKeys.put(KFSPropertyConstants.EMPLID, emplid);
        primaryKeys.put(KFSPropertyConstants.POSITION_NUMBER, positionNumber);

        when(businessObjectService.findByPrimaryKey(BudgetConstructionAdministrativePost.class, primaryKeys))
                .thenReturn(null);

        BudgetConstructionAdministrativePost result = service.getByPrimaryId(emplid, positionNumber);
        assertThat(result).isNull();
    }
}
