package org.kuali.kfs.coa.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.ObjectCode;
import org.kuali.kfs.coa.dataaccess.ObjectCodeDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class ObjectCodeServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ObjectCodeDao objectCodeDao;
    @Mock
    private UniversityDateService universityDateService;
    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private ObjectCodeServiceImpl objectCodeService;

    @Test
    void getYearList_delegatesToDao() {
        List years = Collections.singletonList(Integer.valueOf(2024));
        when(objectCodeDao.getYearList("BL", "5000")).thenReturn(years);

        List result = objectCodeService.getYearList("BL", "5000");
        assertThat(result).hasSize(1);
    }

    @Test
    void getObjectCodesByLevelIds_delegatesToBusinessObjectService() {
        List<String> levelCodes = Arrays.asList("TRIN", "LEVL");
        when(businessObjectService.findMatching(ObjectCode.class, Collections.singletonMap("financialObjectLevelCode", (Object) levelCodes)))
                .thenReturn(Collections.<ObjectCode>emptyList());

        List<ObjectCode> result = objectCodeService.getObjectCodesByLevelIds(levelCodes);
        assertThat(result).isEmpty();
    }
}
