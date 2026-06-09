package org.kuali.kfs.module.bc.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionIntendedIncumbent;
import org.kuali.kfs.module.bc.businessobject.Incumbent;
import org.kuali.kfs.module.bc.exception.BudgetIncumbentAlreadyExistsException;
import org.kuali.kfs.module.bc.service.HumanResourcesPayrollService;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BudgetConstructionIntendedIncumbentServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private HumanResourcesPayrollService humanResourcesPayrollService;

    @InjectMocks
    private BudgetConstructionIntendedIncumbentServiceImpl service;

    @Test
    void getByPrimaryId_returnsIncumbent() {
        String emplid = "EMP001";
        BudgetConstructionIntendedIncumbent expected = new BudgetConstructionIntendedIncumbent();

        Map<String, Object> primaryKeys = new HashMap<>();
        primaryKeys.put(KFSPropertyConstants.EMPLID, emplid);

        when(businessObjectService.findByPrimaryKey(BudgetConstructionIntendedIncumbent.class, primaryKeys))
                .thenReturn(expected);

        BudgetConstructionIntendedIncumbent result = service.getByPrimaryId(emplid);
        assertThat(result).isSameAs(expected);
    }

    @Test
    void getByPrimaryId_returnsNullWhenNotFound() {
        String emplid = "NOBODY";
        Map<String, Object> primaryKeys = new HashMap<>();
        primaryKeys.put(KFSPropertyConstants.EMPLID, emplid);

        when(businessObjectService.findByPrimaryKey(BudgetConstructionIntendedIncumbent.class, primaryKeys))
                .thenReturn(null);

        BudgetConstructionIntendedIncumbent result = service.getByPrimaryId(emplid);
        assertThat(result).isNull();
    }

    @Test
    void pullNewIncumbentFromExternal_throwsWhenAlreadyExists() {
        String emplid = "EMP001";
        BudgetConstructionIntendedIncumbent existingIncumbent = new BudgetConstructionIntendedIncumbent();

        Incumbent mockIncumbent = new BudgetConstructionIntendedIncumbent();
        mockIncumbent.setEmplid(emplid);
        mockIncumbent.setName("Test User");
        when(humanResourcesPayrollService.getIncumbent(emplid)).thenReturn(mockIncumbent);

        Map<String, Object> primaryKeys = new HashMap<>();
        primaryKeys.put(KFSPropertyConstants.EMPLID, emplid);
        when(businessObjectService.findByPrimaryKey(BudgetConstructionIntendedIncumbent.class, primaryKeys))
                .thenReturn(existingIncumbent);

        assertThatThrownBy(() -> service.pullNewIncumbentFromExternal(emplid))
                .isInstanceOf(BudgetIncumbentAlreadyExistsException.class);
    }

    @Test
    void pullNewIncumbentFromExternal_savesWhenNotExists() {
        String emplid = "NEWEMP";

        Incumbent mockIncumbent = new BudgetConstructionIntendedIncumbent();
        mockIncumbent.setEmplid(emplid);
        mockIncumbent.setName("New User");
        when(humanResourcesPayrollService.getIncumbent(emplid)).thenReturn(mockIncumbent);

        Map<String, Object> primaryKeys = new HashMap<>();
        primaryKeys.put(KFSPropertyConstants.EMPLID, emplid);
        when(businessObjectService.findByPrimaryKey(BudgetConstructionIntendedIncumbent.class, primaryKeys))
                .thenReturn(null);

        service.pullNewIncumbentFromExternal(emplid);

        verify(businessObjectService).save(any(BudgetConstructionIntendedIncumbent.class));
    }

    @Test
    void refreshIncumbentFromExternal_updatesExistingRecord() {
        String emplid = "EMP001";

        Incumbent mockIncumbent = new BudgetConstructionIntendedIncumbent();
        mockIncumbent.setEmplid(emplid);
        mockIncumbent.setName("Updated Name");
        when(humanResourcesPayrollService.getIncumbent(emplid)).thenReturn(mockIncumbent);

        BudgetConstructionIntendedIncumbent existing = new BudgetConstructionIntendedIncumbent();
        existing.setEmplid(emplid);
        existing.setVersionNumber(1L);
        existing.setActive(true);

        Map<String, Object> primaryKeys = new HashMap<>();
        primaryKeys.put(KFSPropertyConstants.EMPLID, emplid);
        when(businessObjectService.findByPrimaryKey(BudgetConstructionIntendedIncumbent.class, primaryKeys))
                .thenReturn(existing);

        service.refreshIncumbentFromExternal(emplid);

        verify(businessObjectService).save(any(BudgetConstructionIntendedIncumbent.class));
    }
}
