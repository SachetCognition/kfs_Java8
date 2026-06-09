package org.kuali.kfs.fp.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class TransferOfFundsServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ParameterService parameterService;

    @InjectMocks
    private TransferOfFundsServiceImpl transferOfFundsService;

    @Test
    void checkMandatoryTransfersSubType_nullSubType_throwsIllegalArgument() {
        boolean thrown = false;
        try {
            transferOfFundsService.isMandatoryTransfersSubType(null);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertThat(thrown).isTrue();
    }
}
