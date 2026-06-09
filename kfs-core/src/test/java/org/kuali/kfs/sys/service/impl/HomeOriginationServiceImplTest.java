package org.kuali.kfs.sys.service.impl;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.kfs.sys.businessobject.HomeOrigination;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

class HomeOriginationServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private HomeOriginationServiceImpl homeOriginationService;

    @Test
    void getHomeOrigination_returnsFirst() {
        HomeOrigination expected = new HomeOrigination();
        when(businessObjectService.findAll(HomeOrigination.class))
                .thenReturn(Arrays.asList(expected));

        HomeOrigination result = homeOriginationService.getHomeOrigination();
        assertThat(result).isSameAs(expected);
    }

    @Test
    void getHomeOrigination_emptyCollection_throws() {
        when(businessObjectService.findAll(HomeOrigination.class))
                .thenReturn(Collections.emptyList());

        assertThatThrownBy(() -> homeOriginationService.getHomeOrigination())
                .isInstanceOf(java.util.NoSuchElementException.class);
    }
}
