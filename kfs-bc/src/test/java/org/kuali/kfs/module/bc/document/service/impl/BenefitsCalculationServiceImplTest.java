package org.kuali.kfs.module.bc.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.document.dataaccess.BenefitsCalculationDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.OptionsService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BenefitsCalculationServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BenefitsCalculationDao benefitsCalculationDao;

    @Mock
    private OptionsService optionsService;

    @InjectMocks
    private BenefitsCalculationServiceImpl service;

    @Test
    public void testIsBenefitsCalculationDisabled() {
        assertFalse(service.isBenefitsCalculationDisabled());
    }

    @Test
    public void testSetters() {
        service.setBenefitsCalculationDao(benefitsCalculationDao);
        service.setOptionsService(optionsService);
        assertNotNull(service);
    }

    @Test
    public void testServiceInstantiation() {
        assertNotNull(service);
    }
}
