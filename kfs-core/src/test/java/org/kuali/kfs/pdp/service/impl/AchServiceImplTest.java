package org.kuali.kfs.pdp.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.businessobject.PayeeACHAccount;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class AchServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private AchServiceImpl achService;

    @Test
    void getAchInformation_withSingleMatch_returnsAccount() {
        PayeeACHAccount expected = new PayeeACHAccount();
        when(businessObjectService.findMatching(eq(PayeeACHAccount.class), any(Map.class)))
                .thenReturn(Arrays.asList(expected));

        PayeeACHAccount result = achService.getAchInformation("E", "123456789", "22");
        assertThat(result).isSameAs(expected);
    }

    @Test
    void getAchInformation_withNoMatch_returnsNull() {
        when(businessObjectService.findMatching(eq(PayeeACHAccount.class), any(Map.class)))
                .thenReturn(Collections.<PayeeACHAccount>emptyList());

        PayeeACHAccount result = achService.getAchInformation("E", "999999999", "22");
        assertThat(result).isNull();
    }

    @Test
    void getAchInformation_withMultipleMatches_returnsNull() {
        PayeeACHAccount acct1 = new PayeeACHAccount();
        PayeeACHAccount acct2 = new PayeeACHAccount();
        when(businessObjectService.findMatching(eq(PayeeACHAccount.class), any(Map.class)))
                .thenReturn(Arrays.asList(acct1, acct2));

        PayeeACHAccount result = achService.getAchInformation("E", "123456789", "22");
        assertThat(result).isNull();
    }

    @Test
    void getActiveAchAccounts_returnsActiveAccounts() {
        PayeeACHAccount acct = new PayeeACHAccount();
        when(businessObjectService.findMatchingOrderBy(eq(PayeeACHAccount.class), any(Map.class), anyString(), eq(true)))
                .thenReturn(Arrays.asList(acct));

        List<PayeeACHAccount> result = achService.getActiveAchAccounts();
        assertThat(result).hasSize(1);
    }

    @Test
    void getActiveAchAccounts_noResults_returnsEmptyList() {
        when(businessObjectService.findMatchingOrderBy(eq(PayeeACHAccount.class), any(Map.class), anyString(), eq(true)))
                .thenReturn(Collections.<PayeeACHAccount>emptyList());

        List<PayeeACHAccount> result = achService.getActiveAchAccounts();
        assertThat(result).isEmpty();
    }
}
