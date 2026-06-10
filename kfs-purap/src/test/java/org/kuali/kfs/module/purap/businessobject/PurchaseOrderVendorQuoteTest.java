package org.kuali.kfs.module.purap.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

class PurchaseOrderVendorQuoteTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor() {
        PurchaseOrderVendorQuote quote = new PurchaseOrderVendorQuote();
        assertThat(quote.getDocumentNumber()).isNull();
        assertThat(quote.getPurchaseOrderVendorQuoteIdentifier()).isNull();
        assertThat(quote.getVendorHeaderGeneratedIdentifier()).isNull();
        assertThat(quote.getVendorName()).isNull();
    }

    @Test
    void settersAndGetters() {
        PurchaseOrderVendorQuote quote = new PurchaseOrderVendorQuote();
        quote.setDocumentNumber("DOC001");
        quote.setPurchaseOrderVendorQuoteIdentifier(1);
        quote.setVendorHeaderGeneratedIdentifier(1000);
        quote.setVendorDetailAssignedIdentifier(0);
        quote.setVendorName("ACME Corp");
        quote.setVendorLine1Address("123 Main St");
        quote.setVendorLine2Address("Suite 100");
        quote.setVendorCityName("Bloomington");
        quote.setVendorStateCode("IN");
        quote.setVendorPostalCode("47405");
        quote.setVendorPhoneNumber("812-555-1234");
        quote.setVendorFaxNumber("812-555-5678");
        quote.setVendorEmailAddress("acme@test.com");
        quote.setVendorAttentionName("John Doe");
        quote.setPurchaseOrderQuoteTransmitTypeCode("FAX");
        quote.setPurchaseOrderQuoteStatusCode("RCV");
        quote.setPurchaseOrderQuoteRankNumber("1");
        quote.setVendorCountryCode("US");
        quote.setVendorAddressInternationalProvinceName("Ontario");

        assertThat(quote.getDocumentNumber()).isEqualTo("DOC001");
        assertThat(quote.getPurchaseOrderVendorQuoteIdentifier()).isEqualTo(1);
        assertThat(quote.getVendorHeaderGeneratedIdentifier()).isEqualTo(1000);
        assertThat(quote.getVendorDetailAssignedIdentifier()).isEqualTo(0);
        assertThat(quote.getVendorName()).isEqualTo("ACME Corp");
        assertThat(quote.getVendorLine1Address()).isEqualTo("123 Main St");
        assertThat(quote.getVendorLine2Address()).isEqualTo("Suite 100");
        assertThat(quote.getVendorCityName()).isEqualTo("Bloomington");
        assertThat(quote.getVendorStateCode()).isEqualTo("IN");
        assertThat(quote.getVendorPostalCode()).isEqualTo("47405");
        assertThat(quote.getVendorPhoneNumber()).isEqualTo("812-555-1234");
        assertThat(quote.getVendorFaxNumber()).isEqualTo("812-555-5678");
        assertThat(quote.getVendorEmailAddress()).isEqualTo("acme@test.com");
        assertThat(quote.getVendorAttentionName()).isEqualTo("John Doe");
        assertThat(quote.getPurchaseOrderQuoteTransmitTypeCode()).isEqualTo("FAX");
        assertThat(quote.getPurchaseOrderQuoteStatusCode()).isEqualTo("RCV");
        assertThat(quote.getPurchaseOrderQuoteRankNumber()).isEqualTo("1");
        assertThat(quote.getVendorCountryCode()).isEqualTo("US");
        assertThat(quote.getVendorAddressInternationalProvinceName()).isEqualTo("Ontario");
    }

    @Test
    void transmitTimestampSetterGetter() {
        PurchaseOrderVendorQuote quote = new PurchaseOrderVendorQuote();
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        quote.setPurchaseOrderQuoteTransmitTimestamp(ts);
        assertThat(quote.getPurchaseOrderQuoteTransmitTimestamp()).isEqualTo(ts);
    }

    @Test
    void quoteAwardTimestamp() {
        PurchaseOrderVendorQuote quote = new PurchaseOrderVendorQuote();
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        quote.setPurchaseOrderQuoteAwardTimestamp(ts);
        assertThat(quote.getPurchaseOrderQuoteAwardTimestamp()).isEqualTo(ts);
    }

    @Test
    void transmitPrintDisplayed() {
        PurchaseOrderVendorQuote quote = new PurchaseOrderVendorQuote();
        assertThat(quote.isTransmitPrintDisplayed()).isFalse();
        quote.setTransmitPrintDisplayed(true);
        assertThat(quote.isTransmitPrintDisplayed()).isTrue();
    }

    @Test
    void pdfDisplayedToUserOnce() {
        PurchaseOrderVendorQuote quote = new PurchaseOrderVendorQuote();
        assertThat(quote.isPdfDisplayedToUserOnce()).isFalse();
        quote.setPdfDisplayedToUserOnce(true);
        assertThat(quote.isPdfDisplayedToUserOnce()).isTrue();
    }
}
