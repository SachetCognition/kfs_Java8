package org.kuali.kfs.module.purap.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class PurchaseOrderQuoteLanguageTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor() {
        PurchaseOrderQuoteLanguage lang = new PurchaseOrderQuoteLanguage();
        assertThat(lang.getPurchaseOrderQuoteLanguageIdentifier()).isNull();
        assertThat(lang.getPurchaseOrderQuoteLanguageDescription()).isNull();
        assertThat(lang.getPurchaseOrderQuoteLanguageCreateDate()).isNull();
        assertThat(lang.isActive()).isFalse();
    }

    @Test
    void settersAndGetters() {
        PurchaseOrderQuoteLanguage lang = new PurchaseOrderQuoteLanguage();
        lang.setPurchaseOrderQuoteLanguageIdentifier(5);
        lang.setPurchaseOrderQuoteLanguageDescription("Quote terms and conditions");
        lang.setActive(true);

        assertThat(lang.getPurchaseOrderQuoteLanguageIdentifier()).isEqualTo(5);
        assertThat(lang.getPurchaseOrderQuoteLanguageDescription()).isEqualTo("Quote terms and conditions");
        assertThat(lang.isActive()).isTrue();
    }
}
