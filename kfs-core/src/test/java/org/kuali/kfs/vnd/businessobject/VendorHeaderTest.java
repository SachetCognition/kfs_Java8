package org.kuali.kfs.vnd.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.rice.krad.service.DataDictionaryService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.krad.datadictionary.AttributeSecurity;
import org.mockito.MockedStatic;

import java.sql.Date;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class VendorHeaderTest extends KfsUnitTestBase {

    private VendorHeader vendorHeader;

    @BeforeEach
    void setUp() {
        vendorHeader = new VendorHeader();
    }

    @Test
    void defaultConstructorInitializesSupplierDiversities() {
        assertThat(vendorHeader.getVendorSupplierDiversities()).isNotNull().isEmpty();
    }

    @Test
    void vendorHeaderGeneratedIdentifierGetterSetter() {
        vendorHeader.setVendorHeaderGeneratedIdentifier(42);
        assertThat(vendorHeader.getVendorHeaderGeneratedIdentifier()).isEqualTo(42);
    }

    @Test
    void vendorTypeCodeGetterSetter() {
        vendorHeader.setVendorTypeCode("PO");
        assertThat(vendorHeader.getVendorTypeCode()).isEqualTo("PO");
    }

    @Test
    void vendorTaxNumberGetterSetter() {
        vendorHeader.setVendorTaxNumber("123456789");
        assertThat(vendorHeader.getVendorTaxNumber()).isEqualTo("123456789");
    }

    @Test
    void vendorTaxTypeCodeGetterSetter() {
        vendorHeader.setVendorTaxTypeCode("FEIN");
        assertThat(vendorHeader.getVendorTaxTypeCode()).isEqualTo("FEIN");
    }

    @Test
    void vendorOwnershipCodeGetterSetter() {
        vendorHeader.setVendorOwnershipCode("NR");
        assertThat(vendorHeader.getVendorOwnershipCode()).isEqualTo("NR");
    }

    @Test
    void vendorOwnershipCategoryCodeGetterSetter() {
        vendorHeader.setVendorOwnershipCategoryCode("CAT1");
        assertThat(vendorHeader.getVendorOwnershipCategoryCode()).isEqualTo("CAT1");
    }

    @Test
    void vendorDebarredIndicatorGetterSetter() {
        vendorHeader.setVendorDebarredIndicator(true);
        assertThat(vendorHeader.getVendorDebarredIndicator()).isTrue();
    }

    @Test
    void vendorForeignIndicatorGetterSetter() {
        vendorHeader.setVendorForeignIndicator(true);
        assertThat(vendorHeader.getVendorForeignIndicator()).isTrue();
    }

    @Test
    void vendorW9ReceivedIndicatorGetterSetter() {
        vendorHeader.setVendorW9ReceivedIndicator(true);
        assertThat(vendorHeader.getVendorW9ReceivedIndicator()).isTrue();
    }

    @Test
    void vendorW8BenReceivedIndicatorGetterSetter() {
        vendorHeader.setVendorW8BenReceivedIndicator(false);
        assertThat(vendorHeader.getVendorW8BenReceivedIndicator()).isFalse();
    }

    @Test
    void vendorFederalWithholdingDatesGetterSetter() {
        Date begin = Date.valueOf("2024-01-01");
        Date end = Date.valueOf("2024-12-31");
        vendorHeader.setVendorFederalWithholdingTaxBeginningDate(begin);
        vendorHeader.setVendorFederalWithholdingTaxEndDate(end);

        assertThat(vendorHeader.getVendorFederalWithholdingTaxBeginningDate()).isEqualTo(begin);
        assertThat(vendorHeader.getVendorFederalWithholdingTaxEndDate()).isEqualTo(end);
    }

    @Test
    void vendorW8TypeCodeGetterSetter() {
        vendorHeader.setVendorW8TypeCode("W8-BEN");
        assertThat(vendorHeader.getVendorW8TypeCode()).isEqualTo("W8-BEN");
    }

    @Test
    void vendorW8SignedDateGetterSetter() {
        Date signed = Date.valueOf("2024-06-15");
        vendorHeader.setVendorW8SignedDate(signed);
        assertThat(vendorHeader.getVendorW8SignedDate()).isEqualTo(signed);
    }

    @Test
    void vendorW9SignedDateGetterSetter() {
        Date signed = Date.valueOf("2024-07-01");
        vendorHeader.setVendorW9SignedDate(signed);
        assertThat(vendorHeader.getVendorW9SignedDate()).isEqualTo(signed);
    }

    @Test
    void vendorCorpCitizenCodeGetterSetter() {
        vendorHeader.setVendorCorpCitizenCode("US");
        assertThat(vendorHeader.getVendorCorpCitizenCode()).isEqualTo("US");
    }

    @Test
    void vendorForeignTaxIdGetterSetter() {
        vendorHeader.setVendorForeignTaxId("FT123");
        assertThat(vendorHeader.getVendorForeignTaxId()).isEqualTo("FT123");
    }

    @Test
    void vendorGIINGetterSetter() {
        vendorHeader.setVendorGIIN("GIIN123");
        assertThat(vendorHeader.getVendorGIIN()).isEqualTo("GIIN123");
    }

    @Test
    void vendorDOBGetterSetter() {
        Date dob = Date.valueOf("1990-05-20");
        vendorHeader.setVendorDOB(dob);
        assertThat(vendorHeader.getVendorDOB()).isEqualTo(dob);
    }

    @Test
    void vendorChapter3StatusCodeGetterSetter() {
        vendorHeader.setVendorChapter3StatusCode("C3");
        assertThat(vendorHeader.getVendorChapter3StatusCode()).isEqualTo("C3");
    }

    @Test
    void vendorChapter4StatusCodeGetterSetter() {
        vendorHeader.setVendorChapter4StatusCode("C4");
        assertThat(vendorHeader.getVendorChapter4StatusCode()).isEqualTo("C4");
    }

    @Test
    void vendorSupplierDiversitiesGetterSetter() {
        ArrayList<VendorSupplierDiversity> diversities = new ArrayList<>();
        vendorHeader.setVendorSupplierDiversities(diversities);
        assertThat(vendorHeader.getVendorSupplierDiversities()).isSameAs(diversities);
    }

    @Test
    void toStringExcludesMaskedSensitiveFields() {
        vendorHeader.setVendorHeaderGeneratedIdentifier(1010101010);
        vendorHeader.setVendorTaxNumber("999999999");
        vendorHeader.setVendorTaxTypeCode("SSN");
        vendorHeader.setVendorTypeCode("PO");

        DataDictionaryService ddService = mock(DataDictionaryService.class);
        AttributeSecurity maskedSecurity = new AttributeSecurity();
        maskedSecurity.setMask(true);

        when(ddService.getAttributeSecurity(eq(VendorHeader.class.getName()), anyString()))
                .thenReturn(null);
        when(ddService.getAttributeSecurity(eq(VendorHeader.class.getName()), eq("vendorTaxNumber")))
                .thenReturn(maskedSecurity);
        when(ddService.getAttributeSecurity(eq(VendorHeader.class.getName()), eq("vendorTaxTypeCode")))
                .thenReturn(maskedSecurity);

        try (MockedStatic<SpringContext> springCtx = mockStatic(SpringContext.class)) {
            springCtx.when(() -> SpringContext.getBean(DataDictionaryService.class))
                    .thenReturn(ddService);

            String result = vendorHeader.toString();

            assertThat(result).doesNotContain("vendorTaxNumber");
            assertThat(result).doesNotContain("vendorTaxTypeCode");
            assertThat(result).contains("vendorHeaderGeneratedIdentifier");
        }
    }
}
