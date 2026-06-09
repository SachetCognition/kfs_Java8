package org.kuali.kfs.module.purap.util;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.purap.document.PurchasingAccountsPayableDocument;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class VendorGroupingHelperTest extends KfsUnitTestBase {

    private PurchasingAccountsPayableDocument mockDoc() {
        PurchasingAccountsPayableDocument doc = Mockito.mock(PurchasingAccountsPayableDocument.class);
        when(doc.getVendorHeaderGeneratedIdentifier()).thenReturn(1000);
        when(doc.getVendorDetailAssignedIdentifier()).thenReturn(0);
        when(doc.getVendorCountryCode()).thenReturn("US");
        when(doc.getVendorPostalCode()).thenReturn("47405");
        return doc;
    }

    @Test
    void constructorExtractsFieldsFromDocument() {
        VendorGroupingHelper helper = new VendorGroupingHelper(mockDoc());
        assertThat(helper.getVendorHeaderGeneratedIdentifier()).isEqualTo(1000);
        assertThat(helper.getVendorDetailAssignedIdentifier()).isEqualTo(0);
        assertThat(helper.getVendorCountry()).isEqualTo("US");
        assertThat(helper.getVendorPostalCode()).isEqualTo("47405");
    }

    @Test
    void postalCodeTruncatedToFiveCharacters() {
        PurchasingAccountsPayableDocument doc = mockDoc();
        when(doc.getVendorPostalCode()).thenReturn("47405-1234");
        VendorGroupingHelper helper = new VendorGroupingHelper(doc);
        assertThat(helper.getVendorPostalCode()).isEqualTo("47405");
    }

    @Test
    void postalCodeNullPreserved() {
        PurchasingAccountsPayableDocument doc = mockDoc();
        when(doc.getVendorPostalCode()).thenReturn(null);
        VendorGroupingHelper helper = new VendorGroupingHelper(doc);
        assertThat(helper.getVendorPostalCode()).isNull();
    }

    @Test
    void toStringFormat() {
        VendorGroupingHelper helper = new VendorGroupingHelper(mockDoc());
        assertThat(helper.toString()).isEqualTo("1000-0-US-47405");
    }

    @Test
    void equalsAndHashCodeContract() {
        VendorGroupingHelper h1 = new VendorGroupingHelper(mockDoc());
        VendorGroupingHelper h2 = new VendorGroupingHelper(mockDoc());
        assertThat(h1).isEqualTo(h2);
        assertThat(h1.hashCode()).isEqualTo(h2.hashCode());
    }

    @Test
    void equalsReturnsFalseForDifferentVendor() {
        PurchasingAccountsPayableDocument doc2 = mockDoc();
        when(doc2.getVendorHeaderGeneratedIdentifier()).thenReturn(2000);
        VendorGroupingHelper h1 = new VendorGroupingHelper(mockDoc());
        VendorGroupingHelper h2 = new VendorGroupingHelper(doc2);
        assertThat(h1).isNotEqualTo(h2);
    }

    @Test
    void equalsReturnsFalseForNonVendorGrouping() {
        VendorGroupingHelper h1 = new VendorGroupingHelper(mockDoc());
        assertThat(h1.equals("not a VendorGroupingHelper")).isFalse();
    }

    @Test
    void compareToEqualObjects() {
        VendorGroupingHelper h1 = new VendorGroupingHelper(mockDoc());
        VendorGroupingHelper h2 = new VendorGroupingHelper(mockDoc());
        assertThat(h1.compareTo(h2)).isZero();
    }

    @Test
    void compareToDifferentPostalCode() {
        PurchasingAccountsPayableDocument doc2 = mockDoc();
        when(doc2.getVendorPostalCode()).thenReturn("90210");
        VendorGroupingHelper h1 = new VendorGroupingHelper(mockDoc());
        VendorGroupingHelper h2 = new VendorGroupingHelper(doc2);
        assertThat(h1.compareTo(h2)).isNotZero();
    }
}
