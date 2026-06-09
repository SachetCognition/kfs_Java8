package org.kuali.kfs.sys.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.businessobject.HomeOrigination;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class HomeOriginationServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private HomeOriginationServiceImpl homeOriginationService;

    @Test
    void getHomeOrigination_returnsFirstResult() {
        HomeOrigination ho = new HomeOrigination();
        when(businessObjectService.findAll(HomeOrigination.class))
                .thenReturn(Collections.singletonList(ho));

        HomeOrigination result = homeOriginationService.getHomeOrigination();
        assertThat(result).isSameAs(ho);
    }
}
