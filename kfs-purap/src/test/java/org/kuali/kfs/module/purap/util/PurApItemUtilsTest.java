package org.kuali.kfs.module.purap.util;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.purap.businessobject.ItemType;
import org.kuali.kfs.module.purap.businessobject.PurApItem;
import org.kuali.kfs.module.purap.businessobject.PurchaseOrderItem;
import org.kuali.kfs.module.purap.businessobject.RequisitionItem;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PurApItemUtilsTest extends KfsUnitTestBase {

    @Test
    void checkItemActiveReturnsTrueForNonPOItem() {
        RequisitionItem item = new RequisitionItem();
        assertThat(PurApItemUtils.checkItemActive(item)).isTrue();
    }

    @Test
    void checkItemActiveReturnsTrueForActivePOItem() {
        PurchaseOrderItem poItem = new PurchaseOrderItem();
        poItem.setItemActiveIndicator(true);
        assertThat(PurApItemUtils.checkItemActive(poItem)).isTrue();
    }

    @Test
    void checkItemActiveReturnsFalseForInactivePOItem() {
        PurchaseOrderItem poItem = new PurchaseOrderItem();
        poItem.setItemActiveIndicator(false);
        assertThat(PurApItemUtils.checkItemActive(poItem)).isFalse();
    }

    @Test
    void isNonZeroExtendedReturnsFalseForNullItem() {
        assertThat(PurApItemUtils.isNonZeroExtended(null)).isFalse();
    }

    @Test
    void isNonZeroExtendedReturnsFalseForNullExtendedPrice() {
        PurApItem item = mock(PurApItem.class);
        when(item.getExtendedPrice()).thenReturn(null);
        assertThat(PurApItemUtils.isNonZeroExtended(item)).isFalse();
    }

    @Test
    void isNonZeroExtendedReturnsFalseForZeroPrice() {
        PurApItem item = mock(PurApItem.class);
        when(item.getExtendedPrice()).thenReturn(KualiDecimal.ZERO);
        assertThat(PurApItemUtils.isNonZeroExtended(item)).isFalse();
    }

    @Test
    void isNonZeroExtendedReturnsTrueForPositivePrice() {
        PurApItem item = mock(PurApItem.class);
        when(item.getExtendedPrice()).thenReturn(new KualiDecimal(100));
        assertThat(PurApItemUtils.isNonZeroExtended(item)).isTrue();
    }

    @Test
    void isNonZeroExtendedReturnsTrueForNegativePrice() {
        PurApItem item = mock(PurApItem.class);
        when(item.getExtendedPrice()).thenReturn(new KualiDecimal(-50));
        assertThat(PurApItemUtils.isNonZeroExtended(item)).isTrue();
    }

    @Test
    void countBelowTheLineItemsEmptyList() {
        assertThat(PurApItemUtils.countBelowTheLineItems(new ArrayList<>())).isZero();
    }

    @Test
    void countBelowTheLineItemsWithBelowLineItems() {
        ItemType lineItemType = new ItemType();
        lineItemType.setItemTypeCode("ITEM");
        lineItemType.setAdditionalChargeIndicator(false);

        ItemType belowLineType = new ItemType();
        belowLineType.setItemTypeCode("FRHT");
        belowLineType.setAdditionalChargeIndicator(true);

        PurApItem lineItem = mock(PurApItem.class);
        lenient().when(lineItem.getItemType()).thenReturn(lineItemType);

        PurApItem belowItem1 = mock(PurApItem.class);
        lenient().when(belowItem1.getItemType()).thenReturn(belowLineType);

        PurApItem belowItem2 = mock(PurApItem.class);
        lenient().when(belowItem2.getItemType()).thenReturn(belowLineType);

        List items = new ArrayList();
        items.add(lineItem);
        items.add(belowItem1);
        items.add(belowItem2);

        assertThat(PurApItemUtils.countBelowTheLineItems(items)).isEqualTo(2);
    }

    @Test
    void countBelowTheLineItemsAllLineItems() {
        ItemType lineItemType = new ItemType();
        lineItemType.setItemTypeCode("ITEM");
        lineItemType.setAdditionalChargeIndicator(false);

        PurApItem item1 = mock(PurApItem.class);
        lenient().when(item1.getItemType()).thenReturn(lineItemType);
        PurApItem item2 = mock(PurApItem.class);
        lenient().when(item2.getItemType()).thenReturn(lineItemType);

        List items = new ArrayList();
        items.add(item1);
        items.add(item2);

        assertThat(PurApItemUtils.countBelowTheLineItems(items)).isZero();
    }
}
