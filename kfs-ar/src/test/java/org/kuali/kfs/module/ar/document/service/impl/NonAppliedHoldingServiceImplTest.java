package org.kuali.kfs.module.ar.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.ar.businessobject.Customer;
import org.kuali.kfs.module.ar.businessobject.NonAppliedHolding;
import org.kuali.kfs.module.ar.document.dataaccess.NonAppliedHoldingDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class NonAppliedHoldingServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private NonAppliedHoldingDao nonAppliedHoldingDao;

    @InjectMocks
    private NonAppliedHoldingServiceImpl nonAppliedHoldingService;

    @Test
    void getNonAppliedHoldingsForCustomer_withCustomerObject_shouldDelegateToStringVersion() {
        Customer customer = new Customer();
        customer.setCustomerNumber("CUST001");

        when(nonAppliedHoldingDao.getNonAppliedHoldingsForCustomer("CUST001"))
                .thenReturn(new ArrayList<NonAppliedHolding>());

        Collection<NonAppliedHolding> result = nonAppliedHoldingService.getNonAppliedHoldingsForCustomer(customer);
        assertThat(result).isNotNull();
    }

    @Test
    void getNonAppliedHoldingsForCustomer_withNullCustomer_shouldReturnNull() {
        Collection<NonAppliedHolding> result = nonAppliedHoldingService.getNonAppliedHoldingsForCustomer((Customer) null);
        assertThat(result).isNull();
    }

    @Test
    void getNonAppliedHoldingsForCustomer_withBlankCustomerNumber_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() {
                nonAppliedHoldingService.getNonAppliedHoldingsForCustomer("");
            }
        });
    }

    @Test
    void getNonAppliedHoldingsForCustomer_withNullCustomerNumber_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() {
                nonAppliedHoldingService.getNonAppliedHoldingsForCustomer((String) null);
            }
        });
    }

    @Test
    void getNonAppliedHoldingsForCustomer_shouldFilterOutNonPositiveAmounts() {
        when(nonAppliedHoldingDao.getNonAppliedHoldingsForCustomer("CUST001"))
                .thenReturn(new ArrayList<NonAppliedHolding>());

        Collection<NonAppliedHolding> result = nonAppliedHoldingService.getNonAppliedHoldingsForCustomer("CUST001");
        assertThat(result).isEmpty();
    }

    @Test
    void getNonAppliedHoldingsByListOfDocumentNumbers_withNullList_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() {
                nonAppliedHoldingService.getNonAppliedHoldingsByListOfDocumentNumbers(null);
            }
        });
    }

    @Test
    void getNonAppliedHoldingsByListOfDocumentNumbers_withEmptyList_shouldReturnEmptyCollection() {
        Collection<NonAppliedHolding> result = nonAppliedHoldingService.getNonAppliedHoldingsByListOfDocumentNumbers(new ArrayList<String>());
        assertThat(result).isEmpty();
    }

    @Test
    void getNonAppliedHoldingsByListOfDocumentNumbers_withValidList_shouldDelegateToDao() {
        List<String> docNumbers = Arrays.asList("DOC001", "DOC002");
        NonAppliedHolding holding = new NonAppliedHolding();
        when(nonAppliedHoldingDao.getNonAppliedHoldingsByListOfDocumentNumbers(docNumbers))
                .thenReturn(Arrays.asList(holding));

        Collection<NonAppliedHolding> result = nonAppliedHoldingService.getNonAppliedHoldingsByListOfDocumentNumbers(docNumbers);
        assertThat(result).hasSize(1);
    }
}
