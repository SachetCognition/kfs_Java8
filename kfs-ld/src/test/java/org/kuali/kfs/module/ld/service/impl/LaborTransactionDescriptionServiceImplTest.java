package org.kuali.kfs.module.ld.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LaborTransactionDescriptionServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private LaborTransactionDescriptionServiceImpl service;

    private Map<String, String> transactionDescriptionMap;

    @BeforeEach
    void setUpDescriptionMap() {
        transactionDescriptionMap = new HashMap<>();
        transactionDescriptionMap.put("ST", "Salary Transfer");
        transactionDescriptionMap.put("BT", "Benefit Transfer");
        service.setTransactionDescriptionMap(transactionDescriptionMap);
    }

    @Test
    void testGetTransactionDescription_keyExists() {
        String result = service.getTransactionDescription("ST");
        assertEquals("Salary Transfer", result);
    }

    @Test
    void testGetTransactionDescription_keyNotFound() {
        String result = service.getTransactionDescription("XX");
        assertEquals(KFSConstants.EMPTY_STRING, result);
    }

    @Test
    void testGetTransactionDescription_anotherKey() {
        String result = service.getTransactionDescription("BT");
        assertEquals("Benefit Transfer", result);
    }

    @Test
    void testGetTransactionDescription_emptyKey() {
        String result = service.getTransactionDescription("");
        assertEquals(KFSConstants.EMPTY_STRING, result);
    }

    @Test
    void testGetTransactionDescription_nullKey() {
        String result = service.getTransactionDescription((String) null);
        assertEquals(KFSConstants.EMPTY_STRING, result);
    }
}
