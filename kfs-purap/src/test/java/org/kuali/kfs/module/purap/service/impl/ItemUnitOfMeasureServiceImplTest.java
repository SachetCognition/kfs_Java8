package org.kuali.kfs.module.purap.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.Mock;
import org.kuali.kfs.sys.businessobject.UnitOfMeasure;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;

@MockitoSettings(strictness = Strictness.LENIENT)
public class ItemUnitOfMeasureServiceImplTest extends KfsUnitTestBase {

    @Mock private BusinessObjectService businessObjectService;

    @InjectMocks
    private ItemUnitOfMeasureServiceImpl itemUnitOfMeasureService;

    @Test
    public void testGetByPrimaryId_returnsUnitOfMeasure() {
        UnitOfMeasure expected = new UnitOfMeasure();
        expected.setItemUnitOfMeasureCode("EA");
        when(businessObjectService.retrieve(any(UnitOfMeasure.class))).thenReturn(expected);

        UnitOfMeasure result = itemUnitOfMeasureService.getByPrimaryId("EA");

        assertThat(result).isNotNull();
        assertThat(result.getItemUnitOfMeasureCode()).isEqualTo("EA");
    }

    @Test
    public void testGetByPrimaryId_notFound_returnsNull() {
        when(businessObjectService.retrieve(any(UnitOfMeasure.class))).thenReturn(null);

        UnitOfMeasure result = itemUnitOfMeasureService.getByPrimaryId("NOTEXIST");

        assertThat(result).isNull();
    }

    @Test
    public void testGetByPrimaryId_uppercasesInput() {
        UnitOfMeasure expected = new UnitOfMeasure();
        expected.setItemUnitOfMeasureCode("EA");
        when(businessObjectService.retrieve(any(UnitOfMeasure.class))).thenReturn(expected);

        UnitOfMeasure result = itemUnitOfMeasureService.getByPrimaryId("ea");

        assertThat(result).isEqualTo(expected);
    }
}
