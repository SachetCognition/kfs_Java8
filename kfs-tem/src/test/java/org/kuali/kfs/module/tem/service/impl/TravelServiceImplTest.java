package org.kuali.kfs.module.tem.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.TemConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.kfs.integration.ar.AccountsReceivableModuleService;
import org.kuali.kfs.module.tem.dataaccess.TravelDocumentDao;
import org.kuali.kfs.module.tem.service.TemRoleService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TravelServiceImpl")
class TravelServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private ParameterService parameterService;

    @Mock
    private AccountsReceivableModuleService accountsReceivableModuleService;

    @Mock
    private TemRoleService temRoleService;

    @Mock
    private TravelDocumentDao travelDocumentDao;

    @InjectMocks
    private TravelServiceImpl travelService;

    @Test
    @DisplayName("should validate US phone number successfully")
    void testValidateUSPhoneNumber() {
        String result = travelService.validatePhoneNumber("US", "812-555-1234", "Phone error");
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("should return error for invalid US phone number")
    void testValidateInvalidUSPhoneNumber() {
        String result = travelService.validatePhoneNumber("US", "abc-invalid", "Phone error");
        assertThat(result).isEqualTo("Phone error");
    }

    @Test
    @DisplayName("should validate international phone number")
    void testValidateInternationalPhoneNumber() {
        String result = travelService.validatePhoneNumber("UK", "+44-20-7946-0958", "Phone error");
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("should use international format when country code is blank")
    void testValidatePhoneNumberBlankCountry() {
        String result = travelService.validatePhoneNumber("", "+1-555-123-4567", "Phone error");
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("should validate phone number without country code parameter")
    void testValidatePhoneNumberWithoutCountry() {
        String result = travelService.validatePhoneNumber("+1-555-123-4567", "Phone error");
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("should return error for empty phone number with non-US country")
    void testValidateEmptyPhoneNumberNonUS() {
        String result = travelService.validatePhoneNumber("UK", "", "Phone error");
        assertThat(result).isEqualTo("Phone error");
    }

    @Test
    @DisplayName("should return error for null phone number with blank country")
    void testValidateNullPhoneNumberBlankCountry() {
        String result = travelService.validatePhoneNumber("", null, "Phone error");
        assertThat(result).isEqualTo("Phone error");
    }
}
