package org.kuali.kfs.module.ld.batch;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ld.businessobject.LaborOriginEntry;
import org.kuali.kfs.module.ld.businessobject.LaborOriginEntryFieldUtil;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.datadictionary.AttributeDefinition;
import org.kuali.rice.krad.datadictionary.BusinessObjectEntry;
import org.kuali.rice.krad.datadictionary.DataDictionary;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class LaborDemergerSortComparatorTest extends KfsUnitTestBase {

    private Map<String, Integer> setupMockSpringContextAndGetPositionMap() {
        LaborOriginEntryFieldUtil fieldUtil = new LaborOriginEntryFieldUtil();
        String[] orderedProps = fieldUtil.getOrderedProperties();

        List<AttributeDefinition> attrDefs = new ArrayList<>();
        for (String prop : orderedProps) {
            AttributeDefinition ad = new AttributeDefinition();
            ad.setName(prop);
            ad.setMaxLength(10);
            attrDefs.add(ad);
        }

        DataDictionaryService ddService = mock(DataDictionaryService.class);
        DataDictionary dd = mock(DataDictionary.class);
        BusinessObjectEntry boEntry = mock(BusinessObjectEntry.class);

        when(ddService.getDataDictionary()).thenReturn(dd);
        when(dd.getBusinessObjectEntry(LaborOriginEntry.class.getName())).thenReturn(boEntry);
        when(boEntry.getAttributes()).thenReturn(attrDefs);
        for (String prop : orderedProps) {
            when(ddService.getAttributeMaxLength(LaborOriginEntry.class, prop)).thenReturn(10);
        }

        return Map.of("ddService", 0, "mock", 1);
    }

    @Test
    void testComparatorCanBeInstantiatedWithMockedSpring() {
        LaborOriginEntryFieldUtil fieldUtil = new LaborOriginEntryFieldUtil();
        String[] orderedProps = fieldUtil.getOrderedProperties();

        List<AttributeDefinition> attrDefs = new ArrayList<>();
        for (String prop : orderedProps) {
            AttributeDefinition ad = new AttributeDefinition();
            ad.setName(prop);
            ad.setMaxLength(10);
            attrDefs.add(ad);
        }

        DataDictionaryService ddService = mock(DataDictionaryService.class);
        DataDictionary dd = mock(DataDictionary.class);
        BusinessObjectEntry boEntry = mock(BusinessObjectEntry.class);

        when(ddService.getDataDictionary()).thenReturn(dd);
        when(dd.getBusinessObjectEntry(LaborOriginEntry.class.getName())).thenReturn(boEntry);
        when(boEntry.getAttributes()).thenReturn(attrDefs);
        for (String prop : orderedProps) {
            when(ddService.getAttributeMaxLength(LaborOriginEntry.class, prop)).thenReturn(10);
        }

        try (MockedStatic<SpringContext> springMock = mockStatic(SpringContext.class)) {
            springMock.when(() -> SpringContext.getBean(DataDictionaryService.class)).thenReturn(ddService);

            LaborDemergerSortComparator comparator = new LaborDemergerSortComparator();
            assertThat(comparator).isNotNull();
        }
    }

    @Test
    void testEqualStringsReturnZero() {
        LaborOriginEntryFieldUtil fieldUtil = new LaborOriginEntryFieldUtil();
        String[] orderedProps = fieldUtil.getOrderedProperties();

        List<AttributeDefinition> attrDefs = new ArrayList<>();
        for (String prop : orderedProps) {
            AttributeDefinition ad = new AttributeDefinition();
            ad.setName(prop);
            ad.setMaxLength(10);
            attrDefs.add(ad);
        }

        DataDictionaryService ddService = mock(DataDictionaryService.class);
        DataDictionary dd = mock(DataDictionary.class);
        BusinessObjectEntry boEntry = mock(BusinessObjectEntry.class);

        when(ddService.getDataDictionary()).thenReturn(dd);
        when(dd.getBusinessObjectEntry(LaborOriginEntry.class.getName())).thenReturn(boEntry);
        when(boEntry.getAttributes()).thenReturn(attrDefs);
        for (String prop : orderedProps) {
            when(ddService.getAttributeMaxLength(LaborOriginEntry.class, prop)).thenReturn(10);
        }

        try (MockedStatic<SpringContext> springMock = mockStatic(SpringContext.class)) {
            springMock.when(() -> SpringContext.getBean(DataDictionaryService.class)).thenReturn(ddService);

            LaborDemergerSortComparator comparator = new LaborDemergerSortComparator();
            int totalLength = orderedProps.length * 10;
            String entry = " ".repeat(totalLength);
            assertThat(comparator.compare(entry, entry)).isZero();
        }
    }

    @Test
    void testDifferentStringsAreAntiSymmetric() {
        LaborOriginEntryFieldUtil fieldUtil = new LaborOriginEntryFieldUtil();
        String[] orderedProps = fieldUtil.getOrderedProperties();

        List<AttributeDefinition> attrDefs = new ArrayList<>();
        for (String prop : orderedProps) {
            AttributeDefinition ad = new AttributeDefinition();
            ad.setName(prop);
            ad.setMaxLength(10);
            attrDefs.add(ad);
        }

        DataDictionaryService ddService = mock(DataDictionaryService.class);
        DataDictionary dd = mock(DataDictionary.class);
        BusinessObjectEntry boEntry = mock(BusinessObjectEntry.class);

        when(ddService.getDataDictionary()).thenReturn(dd);
        when(dd.getBusinessObjectEntry(LaborOriginEntry.class.getName())).thenReturn(boEntry);
        when(boEntry.getAttributes()).thenReturn(attrDefs);
        for (String prop : orderedProps) {
            when(ddService.getAttributeMaxLength(LaborOriginEntry.class, prop)).thenReturn(10);
        }

        try (MockedStatic<SpringContext> springMock = mockStatic(SpringContext.class)) {
            springMock.when(() -> SpringContext.getBean(DataDictionaryService.class)).thenReturn(ddService);

            LaborDemergerSortComparator comparator = new LaborDemergerSortComparator();
            int totalLength = orderedProps.length * 10;
            StringBuilder sb1 = new StringBuilder(" ".repeat(totalLength));
            StringBuilder sb2 = new StringBuilder(" ".repeat(totalLength));
            sb1.setCharAt(0, 'A');
            sb2.setCharAt(0, 'B');

            String entry1 = sb1.toString();
            String entry2 = sb2.toString();

            assertThat(Integer.signum(comparator.compare(entry1, entry2)))
                    .isEqualTo(-Integer.signum(comparator.compare(entry2, entry1)));
        }
    }
}
