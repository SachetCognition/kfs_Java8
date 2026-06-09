package org.kuali.kfs.module.purap.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class ElectronicInvoiceItemMappingTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor() {
        ElectronicInvoiceItemMapping mapping = new ElectronicInvoiceItemMapping();
        assertThat(mapping.getInvoiceMapIdentifier()).isNull();
        assertThat(mapping.getInvoiceItemTypeCode()).isNull();
        assertThat(mapping.getItemTypeCode()).isNull();
        assertThat(mapping.isActive()).isFalse();
        assertThat(mapping.getVendorHeaderGeneratedIdentifier()).isNull();
        assertThat(mapping.getVendorDetailAssignedIdentifier()).isNull();
    }

    @Test
    void settersAndGetters() {
        ElectronicInvoiceItemMapping mapping = new ElectronicInvoiceItemMapping();
        mapping.setInvoiceMapIdentifier(10);
        mapping.setInvoiceItemTypeCode("EI_SHIP");
        mapping.setItemTypeCode("FRHT");
        mapping.setActive(true);
        mapping.setVendorHeaderGeneratedIdentifier(1000);
        mapping.setVendorDetailAssignedIdentifier(0);

        assertThat(mapping.getInvoiceMapIdentifier()).isEqualTo(10);
        assertThat(mapping.getInvoiceItemTypeCode()).isEqualTo("EI_SHIP");
        assertThat(mapping.getItemTypeCode()).isEqualTo("FRHT");
        assertThat(mapping.isActive()).isTrue();
        assertThat(mapping.getVendorHeaderGeneratedIdentifier()).isEqualTo(1000);
        assertThat(mapping.getVendorDetailAssignedIdentifier()).isEqualTo(0);
    }

    @Test
    void setItemTypeSetsCodeAutomatically() {
        ElectronicInvoiceItemMapping mapping = new ElectronicInvoiceItemMapping();
        ItemType itemType = new ItemType();
        itemType.setItemTypeCode("ITEM");
        mapping.setItemType(itemType);
        assertThat(mapping.getItemType()).isNotNull();
        assertThat(mapping.getItemTypeCode()).isEqualTo("ITEM");
    }

    @Test
    void invoiceItemTypeReference() {
        ElectronicInvoiceItemMapping mapping = new ElectronicInvoiceItemMapping();
        ItemType invoiceType = new ItemType();
        invoiceType.setItemTypeCode("SRTX");
        mapping.setInvoiceItemType(invoiceType);
        assertThat(mapping.getInvoiceItemType()).isNotNull();
        assertThat(mapping.getInvoiceItemType().getItemTypeCode()).isEqualTo("SRTX");
    }
}
