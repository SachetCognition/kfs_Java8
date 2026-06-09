package org.kuali.kfs.module.ld.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ld.businessobject.LaborObject;
import org.kuali.kfs.module.ld.dataaccess.LaborObjectDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class LaborObjectServiceImplTest extends KfsUnitTestBase {

    @Mock
    private LaborObjectDao laborObjectDao;

    @InjectMocks
    private LaborObjectServiceImpl laborObjectService;

    @Test
    void testFindAllLaborObjectInPositionGroupsDelegatesToDao() {
        Map<String, Object> fieldValues = new HashMap<>();
        fieldValues.put("universityFiscalYear", 2024);

        List<String> positionGroupCodes = List.of("AC", "PR");

        Collection<LaborObject> expected = List.of(new LaborObject());
        when(laborObjectDao.findAllLaborObjectInPositionGroups(fieldValues, positionGroupCodes))
                .thenReturn(expected);

        Collection<LaborObject> result = laborObjectService.findAllLaborObjectInPositionGroups(fieldValues, positionGroupCodes);

        assertThat(result).isSameAs(expected);
        verify(laborObjectDao).findAllLaborObjectInPositionGroups(fieldValues, positionGroupCodes);
    }

    @Test
    void testFindAllLaborObjectInPositionGroupsWithEmptyResult() {
        Map<String, Object> fieldValues = new HashMap<>();
        List<String> positionGroupCodes = List.of("XX");

        when(laborObjectDao.findAllLaborObjectInPositionGroups(fieldValues, positionGroupCodes))
                .thenReturn(Collections.emptyList());

        Collection<LaborObject> result = laborObjectService.findAllLaborObjectInPositionGroups(fieldValues, positionGroupCodes);

        assertThat(result).isEmpty();
    }
}
