package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.document.service.CustomerAddressService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class InvoiceRecurrenceDocumentServiceImplTest extends KfsUnitTestBase {

    @Mock private ParameterService parameterService;
    @Mock private BusinessObjectService businessObjectService;
    @Mock private CustomerAddressService customerAddressService;
    @Mock private DocumentService documentService;
    @Mock private DateTimeService dateTimeService;

    @InjectMocks
    private InvoiceRecurrenceDocumentServiceImpl service;

    @Test
    void isValidRecurrenceBeginDate_shouldReturnTrueForFutureDate() {
        Date futureDate = Date.valueOf("2099-01-01");
        when(dateTimeService.getCurrentDate()).thenReturn(new java.util.Date());

        assertThat(service.isValidRecurrenceBeginDate(futureDate)).isTrue();
    }

    @Test
    void isValidRecurrenceBeginDate_shouldReturnTrueForNullDate() {
        assertThat(service.isValidRecurrenceBeginDate(null)).isTrue();
    }

    @Test
    void isValidRecurrenceEndDate_shouldReturnTrueWhenEndDateAfterBeginDate() {
        Date beginDate = Date.valueOf("2024-01-01");
        Date endDate = Date.valueOf("2024-12-31");

        assertThat(service.isValidRecurrenceEndDate(beginDate, endDate)).isTrue();
    }

    @Test
    void isValidRecurrenceEndDate_shouldReturnFalseWhenEndDateBeforeBeginDate() {
        Date beginDate = Date.valueOf("2024-12-31");
        Date endDate = Date.valueOf("2024-01-01");

        assertThat(service.isValidRecurrenceEndDate(beginDate, endDate)).isFalse();
    }

    @Test
    void isValidEndDateOrTotalRecurrenceNumber_shouldReturnTrueWhenEndDateProvided() {
        Date endDate = Date.valueOf("2024-12-31");
        assertThat(service.isValidEndDateOrTotalRecurrenceNumber(endDate, null)).isTrue();
    }

    @Test
    void isValidEndDateOrTotalRecurrenceNumber_shouldReturnTrueWhenRecurrenceNumberProvided() {
        assertThat(service.isValidEndDateOrTotalRecurrenceNumber(null, 12)).isTrue();
    }

    @Test
    void isValidEndDateOrTotalRecurrenceNumber_shouldReturnFalseWhenBothNull() {
        assertThat(service.isValidEndDateOrTotalRecurrenceNumber(null, null)).isFalse();
    }

    @Test
    void isValidInitiator_shouldAlwaysReturnTrue() {
        assertThat(service.isValidInitiator("admin")).isTrue();
        assertThat(service.isValidInitiator("")).isTrue();
        assertThat(service.isValidInitiator(null)).isTrue();
    }
}
