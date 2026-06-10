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
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class LaborDemergerSortComparatorTest extends KfsUnitTestBase {

    private static final int FIELD_LENGTH = 10;

    private DataDictionaryService setupMockDDService() {
        LaborOriginEntryFieldUtil fieldUtil = new LaborOriginEntryFieldUtil();
        String[] orderedProps = fieldUtil.getOrderedProperties();

        List<AttributeDefinition> attrDefs = new ArrayList<>();
        for (String prop : orderedProps) {
            AttributeDefinition ad = new AttributeDefinition();
            ad.setName(prop);
            ad.setMaxLength(FIELD_LENGTH);
            attrDefs.add(ad);
        }

        DataDictionaryService ddService = mock(DataDictionaryService.class);
        DataDictionary dd = mock(DataDictionary.class);
        BusinessObjectEntry boEntry = mock(BusinessObjectEntry.class);

        when(ddService.getDataDictionary()).thenReturn(dd);
        when(dd.getBusinessObjectEntry(LaborOriginEntry.class.getName())).thenReturn(boEntry);
        when(boEntry.getAttributes()).thenReturn(attrDefs);
        for (String prop : orderedProps) {
            when(ddService.getAttributeMaxLength(LaborOriginEntry.class, prop)).thenReturn(FIELD_LENGTH);
        }
        return ddService;
    }

    private int positionOf(String propertyName) {
        String[] props = new LaborOriginEntryFieldUtil().getOrderedProperties();
        return Arrays.asList(props).indexOf(propertyName) * FIELD_LENGTH;
    }

    @Test
    void testComparatorCanBeInstantiatedWithMockedSpring() {
        DataDictionaryService ddService = setupMockDDService();
        try (MockedStatic<SpringContext> springMock = mockStatic(SpringContext.class)) {
            springMock.when(() -> SpringContext.getBean(DataDictionaryService.class)).thenReturn(ddService);

            LaborDemergerSortComparator comparator = new LaborDemergerSortComparator();
            assertThat(comparator).isNotNull();
        }
    }

    @Test
    void testEqualStringsReturnZero() {
        DataDictionaryService ddService = setupMockDDService();
        try (MockedStatic<SpringContext> springMock = mockStatic(SpringContext.class)) {
            springMock.when(() -> SpringContext.getBean(DataDictionaryService.class)).thenReturn(ddService);

            LaborDemergerSortComparator comparator = new LaborDemergerSortComparator();
            int totalLength = new LaborOriginEntryFieldUtil().getOrderedProperties().length * FIELD_LENGTH;
            String entry = " ".repeat(totalLength);
            assertThat(comparator.compare(entry, entry)).isZero();
        }
    }

    @Test
    void testDifferentStringsAreAntiSymmetric() {
        DataDictionaryService ddService = setupMockDDService();
        try (MockedStatic<SpringContext> springMock = mockStatic(SpringContext.class)) {
            springMock.when(() -> SpringContext.getBean(DataDictionaryService.class)).thenReturn(ddService);

            LaborDemergerSortComparator comparator = new LaborDemergerSortComparator();
            int totalLength = new LaborOriginEntryFieldUtil().getOrderedProperties().length * FIELD_LENGTH;
            int docTypePos = positionOf(KFSPropertyConstants.FINANCIAL_DOCUMENT_TYPE_CODE);

            StringBuilder sb1 = new StringBuilder(" ".repeat(totalLength));
            StringBuilder sb2 = new StringBuilder(" ".repeat(totalLength));
            sb1.setCharAt(docTypePos, 'A');
            sb2.setCharAt(docTypePos, 'B');

            assertThat(Integer.signum(comparator.compare(sb1.toString(), sb2.toString())))
                    .isEqualTo(-Integer.signum(comparator.compare(sb2.toString(), sb1.toString())));
        }
    }
}
