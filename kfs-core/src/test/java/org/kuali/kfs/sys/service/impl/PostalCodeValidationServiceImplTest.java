package org.kuali.kfs.sys.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;

import static org.assertj.core.api.Assertions.assertThat;

class PostalCodeValidationServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private PostalCodeValidationServiceImpl postalCodeValidationService;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(postalCodeValidationService).isNotNull();
    }
}
