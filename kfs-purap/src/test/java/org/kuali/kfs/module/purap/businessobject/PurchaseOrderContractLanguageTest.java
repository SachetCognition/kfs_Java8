package org.kuali.kfs.module.purap.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.mockito.MockedStatic;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class PurchaseOrderContractLanguageTest extends KfsUnitTestBase {

    @Test
    void defaultConstructorSetsCreateDate() {
        DateTimeService dateTimeService = mock(DateTimeService.class);
        Date today = new Date(System.currentTimeMillis());
        when(dateTimeService.getCurrentSqlDate()).thenReturn(today);

        try (MockedStatic<SpringContext> springCtx = mockStatic(SpringContext.class)) {
            springCtx.when(() -> SpringContext.getBean(DateTimeService.class)).thenReturn(dateTimeService);

            PurchaseOrderContractLanguage lang = new PurchaseOrderContractLanguage();
            assertThat(lang.getContractLanguageCreateDate()).isEqualTo(today);
            assertThat(lang.getPurchaseOrderContractLanguageIdentifier()).isNull();
            assertThat(lang.getCampusCode()).isNull();
        }
    }

    @Test
    void settersAndGetters() {
        DateTimeService dateTimeService = mock(DateTimeService.class);
        when(dateTimeService.getCurrentSqlDate()).thenReturn(new Date(System.currentTimeMillis()));

        try (MockedStatic<SpringContext> springCtx = mockStatic(SpringContext.class)) {
            springCtx.when(() -> SpringContext.getBean(DateTimeService.class)).thenReturn(dateTimeService);

            PurchaseOrderContractLanguage lang = new PurchaseOrderContractLanguage();
            lang.setPurchaseOrderContractLanguageIdentifier(1);
            lang.setCampusCode("BL");
            lang.setPurchaseOrderContractLanguageDescription("Standard terms");
            lang.setActive(true);

            assertThat(lang.getPurchaseOrderContractLanguageIdentifier()).isEqualTo(1);
            assertThat(lang.getCampusCode()).isEqualTo("BL");
            assertThat(lang.getPurchaseOrderContractLanguageDescription()).isEqualTo("Standard terms");
            assertThat(lang.isActive()).isTrue();
        }
    }
}
