/*
 * The Kuali Financial System, a comprehensive financial management system for higher education.
 *
 * Copyright 2005-2014 The Kuali Foundation
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kfs.sys.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.KFSParameterKeyConstants;
import org.kuali.kfs.sys.businessobject.Bank;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class BankServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private DataDictionaryService dataDictionaryService;
    @Mock
    private ParameterService parameterService;

    @InjectMocks
    private BankServiceImpl bankService;

    @Test
    void getByPrimaryId_withValidCode_returnsBank() {
        Bank expected = new Bank();
        expected.setBankCode("TEST");
        when(businessObjectService.findBySinglePrimaryKey(Bank.class, "TEST")).thenReturn(expected);

        Bank result = bankService.getByPrimaryId("TEST");

        assertThat(result).isSameAs(expected);
        verify(businessObjectService).findBySinglePrimaryKey(Bank.class, "TEST");
    }

    @Test
    void getByPrimaryId_withBlankCode_returnsNull() {
        assertThat(bankService.getByPrimaryId("")).isNull();
        assertThat(bankService.getByPrimaryId(null)).isNull();
        assertThat(bankService.getByPrimaryId("  ")).isNull();
        verifyNoInteractions(businessObjectService);
    }

    @Test
    void getDefaultBankByDocType_whenParameterDoesNotExist_returnsNull() {
        when(parameterService.parameterExists(Bank.class, KFSParameterKeyConstants.DEFAULT_BANK_BY_DOCUMENT_TYPE))
                .thenReturn(false);

        assertThat(bankService.getDefaultBankByDocType("DV")).isNull();
    }

    @Test
    void getDefaultBankByDocType_whenNoParamValues_returnsNull() {
        when(parameterService.parameterExists(Bank.class, KFSParameterKeyConstants.DEFAULT_BANK_BY_DOCUMENT_TYPE))
                .thenReturn(true);
        Set<String> emptySet = new HashSet<>();
        when(parameterService.getSubParameterValuesAsString(Bank.class, KFSParameterKeyConstants.DEFAULT_BANK_BY_DOCUMENT_TYPE, "DV"))
                .thenReturn(emptySet);

        assertThat(bankService.getDefaultBankByDocType("DV")).isNull();
    }

    @Test
    void getDefaultBankByDocType_whenBankActive_returnsBank() {
        when(parameterService.parameterExists(Bank.class, KFSParameterKeyConstants.DEFAULT_BANK_BY_DOCUMENT_TYPE))
                .thenReturn(true);
        Set<String> values = new HashSet<>(Collections.singletonList("BANK1"));
        when(parameterService.getSubParameterValuesAsString(Bank.class, KFSParameterKeyConstants.DEFAULT_BANK_BY_DOCUMENT_TYPE, "DV"))
                .thenReturn(values);

        Bank activeBank = new Bank();
        activeBank.setBankCode("BANK1");
        activeBank.setActive(true);
        when(businessObjectService.findBySinglePrimaryKey(Bank.class, "BANK1")).thenReturn(activeBank);

        Bank result = bankService.getDefaultBankByDocType("DV");
        assertThat(result).isSameAs(activeBank);
    }

    @Test
    void getDefaultBankByDocType_whenBankInactive_andContinuationActive_returnsContinuation() {
        when(parameterService.parameterExists(Bank.class, KFSParameterKeyConstants.DEFAULT_BANK_BY_DOCUMENT_TYPE))
                .thenReturn(true);
        Set<String> values = new HashSet<>(Collections.singletonList("BANK1"));
        when(parameterService.getSubParameterValuesAsString(Bank.class, KFSParameterKeyConstants.DEFAULT_BANK_BY_DOCUMENT_TYPE, "DV"))
                .thenReturn(values);

        Bank continuationBank = new Bank();
        continuationBank.setBankCode("BANK2");
        continuationBank.setActive(true);

        Bank inactiveBank = new Bank();
        inactiveBank.setBankCode("BANK1");
        inactiveBank.setActive(false);
        inactiveBank.setContinuationBank(continuationBank);
        when(businessObjectService.findBySinglePrimaryKey(Bank.class, "BANK1")).thenReturn(inactiveBank);

        Bank result = bankService.getDefaultBankByDocType("DV");
        assertThat(result).isSameAs(continuationBank);
    }

    @Test
    void getDefaultBankByDocClass_withBlankDocType_throwsException() {
        when(dataDictionaryService.getDocumentTypeNameByClass(String.class)).thenReturn("");

        try {
            bankService.getDefaultBankByDocType(String.class);
            org.junit.jupiter.api.Assertions.fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            assertThat(e).isInstanceOf(RuntimeException.class);
        }
    }

    @Test
    void isBankSpecificationEnabled_delegatesToParameterService() {
        when(parameterService.getParameterValueAsBoolean(Bank.class, KFSParameterKeyConstants.ENABLE_BANK_SPECIFICATION_IND))
                .thenReturn(true);
        assertThat(bankService.isBankSpecificationEnabled()).isTrue();

        when(parameterService.getParameterValueAsBoolean(Bank.class, KFSParameterKeyConstants.ENABLE_BANK_SPECIFICATION_IND))
                .thenReturn(false);
        assertThat(bankService.isBankSpecificationEnabled()).isFalse();
    }
}
