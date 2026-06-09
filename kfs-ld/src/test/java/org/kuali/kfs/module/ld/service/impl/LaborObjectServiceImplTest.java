package org.kuali.kfs.module.ld.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ld.businessobject.LaborObject;
import org.kuali.kfs.module.ld.dataaccess.LaborObjectDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LaborObjectServiceImplTest extends KfsUnitTestBase {

    @Mock
    private LaborObjectDao laborObjectDao;

    @InjectMocks
    private LaborObjectServiceImpl service;

    @Test
    void testFindAllLaborObjectInPositionGroups() {
        Map<String, Object> fieldValues = new HashMap<>();
        fieldValues.put("universityFiscalYear", 2014);
        List<String> positionGroupCodes = Arrays.asList("PG1", "PG2");

        Collection<LaborObject> expected = new ArrayList<LaborObject>();
        expected.add(new LaborObject());

        when(laborObjectDao.findAllLaborObjectInPositionGroups(fieldValues, positionGroupCodes)).thenReturn(expected);

        Collection<LaborObject> result = service.findAllLaborObjectInPositionGroups(fieldValues, positionGroupCodes);
        assertEquals(expected, result);
        verify(laborObjectDao).findAllLaborObjectInPositionGroups(fieldValues, positionGroupCodes);
    }

    @Test
    void testFindAllLaborObjectInPositionGroups_emptyResult() {
        Map<String, Object> fieldValues = new HashMap<>();
        List<String> positionGroupCodes = Collections.emptyList();

        Collection<LaborObject> empty = Collections.emptyList();
        when(laborObjectDao.findAllLaborObjectInPositionGroups(fieldValues, positionGroupCodes)).thenReturn(empty);

        Collection<LaborObject> result = service.findAllLaborObjectInPositionGroups(fieldValues, positionGroupCodes);
        assertTrue(result.isEmpty());
    }
}
