package org.kuali.kfs.module.ld.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LaborTransactionDescriptionServiceImplTest extends KfsUnitTestBase {

    private LaborTransactionDescriptionServiceImpl service;
    private Map<String, String> descriptionMap;

    @BeforeEach
    void setUp() {
        service = new LaborTransactionDescriptionServiceImpl();
        descriptionMap = new HashMap<>();
        descriptionMap.put("LLJV", "Labor Ledger Journal Voucher");
        descriptionMap.put("ST", "Salary Transfer");
        service.setTransactionDescriptionMap(descriptionMap);
    }

    @Test
    void testGetTransactionDescriptionReturnsDescriptionForKnownKey() {
        String description = service.getTransactionDescription("LLJV");
        assertThat(description).isEqualTo("Labor Ledger Journal Voucher");
    }

    @Test
    void testGetTransactionDescriptionReturnsEmptyStringForUnknownKey() {
        String description = service.getTransactionDescription("UNKNOWN");
        assertThat(description).isEqualTo(KFSConstants.EMPTY_STRING);
    }

    @Test
    void testGetTransactionDescriptionReturnsCorrectForSTKey() {
        String description = service.getTransactionDescription("ST");
        assertThat(description).isEqualTo("Salary Transfer");
    }

    @Test
    void testSetTransactionDescriptionMap() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("NEW", "New Description");
        service.setTransactionDescriptionMap(newMap);
        assertThat(service.getTransactionDescription("NEW")).isEqualTo("New Description");
        assertThat(service.getTransactionDescription("LLJV")).isEqualTo(KFSConstants.EMPTY_STRING);
    }
}
