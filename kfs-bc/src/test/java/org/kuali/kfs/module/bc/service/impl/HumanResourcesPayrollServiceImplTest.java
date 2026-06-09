package org.kuali.kfs.module.bc.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.BCConstants.SynchronizationCheckType;
import org.kuali.kfs.module.bc.businessobject.Position;
import org.kuali.kfs.module.bc.dataaccess.HumanResourcesPayrollDao;
import org.kuali.kfs.module.bc.exception.PositionNotFoundException;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class HumanResourcesPayrollServiceImplTest extends KfsUnitTestBase {

    @Mock
    private HumanResourcesPayrollDao humanResourcesPayrollDao;

    @InjectMocks
    private HumanResourcesPayrollServiceImpl service;

    @Test
    public void testValidatePositionUnionCode_alwaysTrue() {
        assertTrue(service.validatePositionUnionCode("ABC"));
    }

    @Test
    public void testValidatePositionUnionCode_empty() {
        assertTrue(service.validatePositionUnionCode(""));
    }

    @Test
    public void testValidatePositionUnionCode_null() {
        assertTrue(service.validatePositionUnionCode(null));
    }

    @Test
    public void testGetPosition_found() throws PositionNotFoundException {
        Position expected = mock(Position.class);
        when(humanResourcesPayrollDao.getPosition(2024, "POS001")).thenReturn(expected);

        Position result = service.getPosition(2024, "POS001");
        assertSame(expected, result);
    }

    @Test
    public void testGetPosition_notFound() {
        when(humanResourcesPayrollDao.getPosition(2024, "INVALID")).thenReturn(null);

        try {
            service.getPosition(2024, "INVALID");
            fail("Expected PositionNotFoundException");
        } catch (PositionNotFoundException e) {
            // expected
        }
    }

    @Test
    public void testIsActiveJob_alwaysTrue() {
        assertTrue(service.isActiveJob("EMP001", "POS001", 2024, SynchronizationCheckType.NONE));
    }

    @Test
    public void testIsActiveJob_nullParams() {
        assertTrue(service.isActiveJob(null, null, null, SynchronizationCheckType.NONE));
    }

    @Test
    public void testSetHumanResourcesPayrollDao() {
        service.setHumanResourcesPayrollDao(humanResourcesPayrollDao);
        assertNotNull(service);
    }
}
