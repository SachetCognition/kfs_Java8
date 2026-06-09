package org.kuali.kfs.fp.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.fp.document.dataaccess.CashManagementDao;
import org.kuali.kfs.fp.service.CashDrawerService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CashReceiptServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private CashManagementDao cashManagementDao;
    @Mock
    private CashDrawerService cashDrawerService;

    @InjectMocks
    private CashReceiptServiceImpl cashReceiptService;

    @Test
    void getCashReceiptVerificationUnitForUser_nullUser_throwsException() {
        try {
            cashReceiptService.getCashReceiptVerificationUnitForUser(null);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).contains("null");
        }
    }

    @Test
    void getCashReceiptVerificationUnitForUser_validUser_returnsCampusCode() {
        Person person = mock(Person.class);
        when(person.getCampusCode()).thenReturn("BL");

        String result = cashReceiptService.getCashReceiptVerificationUnitForUser(person);
        assertThat(result).isEqualTo("BL");
    }
}
