package org.kuali.kfs.module.purap.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.purap.PurapConstants.ItemTypeCodes;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class ItemTypeTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor() {
        ItemType itemType = new ItemType();
        assertThat(itemType.getItemTypeCode()).isNull();
        assertThat(itemType.getItemTypeDescription()).isNull();
        assertThat(itemType.isActive()).isFalse();
    }

    @Test
    void settersAndGetters() {
        ItemType itemType = new ItemType();
        itemType.setItemTypeCode("ITEM");
        itemType.setItemTypeDescription("Item");
        itemType.setActive(true);
        itemType.setQuantityBasedGeneralLedgerIndicator(true);
        itemType.setAdditionalChargeIndicator(false);
        itemType.setTaxableIndicator(true);

        assertThat(itemType.getItemTypeCode()).isEqualTo("ITEM");
        assertThat(itemType.getItemTypeDescription()).isEqualTo("Item");
        assertThat(itemType.isActive()).isTrue();
        assertThat(itemType.isQuantityBasedGeneralLedgerIndicator()).isTrue();
        assertThat(itemType.isAdditionalChargeIndicator()).isFalse();
        assertThat(itemType.isTaxableIndicator()).isTrue();
    }

    @Test
    void isAmountBasedIsOppositeOfQuantityBased() {
        ItemType itemType = new ItemType();
        itemType.setQuantityBasedGeneralLedgerIndicator(true);
        assertThat(itemType.isAmountBasedGeneralLedgerIndicator()).isFalse();

        itemType.setQuantityBasedGeneralLedgerIndicator(false);
        assertThat(itemType.isAmountBasedGeneralLedgerIndicator()).isTrue();
    }

    @Test
    void isLineItemIndicatorIsOppositeOfAdditionalCharge() {
        ItemType itemType = new ItemType();
        itemType.setAdditionalChargeIndicator(false);
        assertThat(itemType.isLineItemIndicator()).isTrue();

        itemType.setAdditionalChargeIndicator(true);
        assertThat(itemType.isLineItemIndicator()).isFalse();
    }

    @Test
    void isTaxChargeForFederalTax() {
        ItemType itemType = new ItemType();
        itemType.setItemTypeCode(ItemTypeCodes.ITEM_TYPE_FEDERAL_TAX_CODE);
        assertThat(itemType.getIsTaxCharge()).isTrue();
    }

    @Test
    void isTaxChargeForFederalGross() {
        ItemType itemType = new ItemType();
        itemType.setItemTypeCode(ItemTypeCodes.ITEM_TYPE_FEDERAL_GROSS_CODE);
        assertThat(itemType.getIsTaxCharge()).isTrue();
    }

    @Test
    void isTaxChargeForStateTax() {
        ItemType itemType = new ItemType();
        itemType.setItemTypeCode(ItemTypeCodes.ITEM_TYPE_STATE_TAX_CODE);
        assertThat(itemType.getIsTaxCharge()).isTrue();
    }

    @Test
    void isTaxChargeForStateGross() {
        ItemType itemType = new ItemType();
        itemType.setItemTypeCode(ItemTypeCodes.ITEM_TYPE_STATE_GROSS_CODE);
        assertThat(itemType.getIsTaxCharge()).isTrue();
    }

    @Test
    void isTaxChargeFalseForRegularItem() {
        ItemType itemType = new ItemType();
        itemType.setItemTypeCode("ITEM");
        assertThat(itemType.getIsTaxCharge()).isFalse();
    }

    @Test
    void staticIsTaxChargeMethod() {
        assertThat(ItemType.getIsTaxCharge(ItemTypeCodes.ITEM_TYPE_FEDERAL_TAX_CODE)).isTrue();
        assertThat(ItemType.getIsTaxCharge(ItemTypeCodes.ITEM_TYPE_STATE_TAX_CODE)).isTrue();
        assertThat(ItemType.getIsTaxCharge("ITEM")).isFalse();
    }
}
