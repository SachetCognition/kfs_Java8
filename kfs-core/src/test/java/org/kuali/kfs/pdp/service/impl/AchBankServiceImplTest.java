package org.kuali.kfs.pdp.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.PdpPropertyConstants;
import org.kuali.kfs.pdp.businessobject.ACHBank;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class AchBankServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private AchBankServiceImpl achBankService;

    @Test
    void getByPrimaryId_found_returnsBank() {
        ACHBank expected = new ACHBank();
        Map<String, String> keys = new HashMap<String, String>();
        keys.put(PdpPropertyConstants.BANK_ROUTING_NUMBER, "123456789");
        when(businessObjectService.findByPrimaryKey(ACHBank.class, keys)).thenReturn(expected);

        ACHBank result = achBankService.getByPrimaryId("123456789");
        assertThat(result).isSameAs(expected);
    }

    @Test
    void getByPrimaryId_notFound_returnsNull() {
        Map<String, String> keys = new HashMap<String, String>();
        keys.put(PdpPropertyConstants.BANK_ROUTING_NUMBER, "000000000");
        when(businessObjectService.findByPrimaryKey(ACHBank.class, keys)).thenReturn(null);

        ACHBank result = achBankService.getByPrimaryId("000000000");
        assertThat(result).isNull();
    }
}
