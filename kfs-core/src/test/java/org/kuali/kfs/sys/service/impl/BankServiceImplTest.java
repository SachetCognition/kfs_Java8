package org.kuali.kfs.sys.service.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.kfs.sys.KFSParameterKeyConstants;
import org.kuali.kfs.sys.businessobject.Bank;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.service.BusinessObjectService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class BankServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private ParameterService parameterService;
    @Mock
    private DataDictionaryService dataDictionaryService;

    @InjectMocks
    private BankServiceImpl bankService;

    private Bank testBank;

    @BeforeEach
    void setUp() {
        testBank = new Bank();
        testBank.setBankCode("TEST");
    }

    @Test
    void getByPrimaryId_blankCode_returnsNull() {
        assertThat(bankService.getByPrimaryId("")).isNull();
        assertThat(bankService.getByPrimaryId(null)).isNull();
        assertThat(bankService.getByPrimaryId("   ")).isNull();
    }

    @Test
    void getByPrimaryId_validCode_delegatesToBusinessObjectService() {
        when(businessObjectService.findBySinglePrimaryKey(Bank.class, "TEST")).thenReturn(testBank);
        Bank result = bankService.getByPrimaryId("TEST");
        assertThat(result).isSameAs(testBank);
        verify(businessObjectService).findBySinglePrimaryKey(Bank.class, "TEST");
    }

    @Test
    void getByPrimaryId_notFound_returnsNull() {
        when(businessObjectService.findBySinglePrimaryKey(Bank.class, "MISSING")).thenReturn(null);
        assertThat(bankService.getByPrimaryId("MISSING")).isNull();
    }

    @Test
    void getDefaultBankByDocType_paramNotExists_returnsNull() {
        when(parameterService.parameterExists(Bank.class, KFSParameterKeyConstants.DEFAULT_BANK_BY_DOCUMENT_TYPE))
                .thenReturn(false);
        assertThat(bankService.getDefaultBankByDocType("DV")).isNull();
    }

    @Test
    void getDefaultBankByDocType_emptyParmValues_returnsNull() {
        when(parameterService.parameterExists(Bank.class, KFSParameterKeyConstants.DEFAULT_BANK_BY_DOCUMENT_TYPE))
                .thenReturn(true);
        Set<String> emptySet = Collections.emptySet();
        when(parameterService.getSubParameterValuesAsString(Bank.class, KFSParameterKeyConstants.DEFAULT_BANK_BY_DOCUMENT_TYPE, "DV"))
                .thenReturn(emptySet);
        assertThat(bankService.getDefaultBankByDocType("DV")).isNull();
    }

    @Test
    void getDefaultBankByDocType_activeBankReturned() {
        when(parameterService.parameterExists(Bank.class, KFSParameterKeyConstants.DEFAULT_BANK_BY_DOCUMENT_TYPE))
                .thenReturn(true);
        Set<String> values = new HashSet<>();
        values.add("TEST");
        when(parameterService.getSubParameterValuesAsString(Bank.class, KFSParameterKeyConstants.DEFAULT_BANK_BY_DOCUMENT_TYPE, "DV"))
                .thenReturn(values);

        Bank activeBank = new Bank();
        activeBank.setBankCode("TEST");
        activeBank.setActive(true);
        when(businessObjectService.findBySinglePrimaryKey(Bank.class, "TEST")).thenReturn(activeBank);

        Bank result = bankService.getDefaultBankByDocType("DV");
        assertThat(result).isSameAs(activeBank);
    }

    @Test
    void getDefaultBankByDocType_inactiveBankWithActiveContinuation() {
        when(parameterService.parameterExists(Bank.class, KFSParameterKeyConstants.DEFAULT_BANK_BY_DOCUMENT_TYPE))
                .thenReturn(true);
        Set<String> values = new HashSet<>();
        values.add("INACTIVE");
        when(parameterService.getSubParameterValuesAsString(Bank.class, KFSParameterKeyConstants.DEFAULT_BANK_BY_DOCUMENT_TYPE, "DV"))
                .thenReturn(values);

        Bank continuationBank = new Bank();
        continuationBank.setBankCode("CONT");
        continuationBank.setActive(true);

        Bank inactiveBank = new Bank();
        inactiveBank.setBankCode("INACTIVE");
        inactiveBank.setActive(false);
        inactiveBank.setContinuationBank(continuationBank);

        when(businessObjectService.findBySinglePrimaryKey(Bank.class, "INACTIVE")).thenReturn(inactiveBank);

        Bank result = bankService.getDefaultBankByDocType("DV");
        assertThat(result).isSameAs(continuationBank);
    }

    @Test
    void getDefaultBankByDocClass_blankDocType_throws() {
        when(dataDictionaryService.getDocumentTypeNameByClass(String.class)).thenReturn("");
        assertThatThrownBy(() -> bankService.getDefaultBankByDocType(String.class))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void isBankSpecificationEnabled_delegatesToParameterService() {
        when(parameterService.getParameterValueAsBoolean(Bank.class, KFSParameterKeyConstants.ENABLE_BANK_SPECIFICATION_IND))
                .thenReturn(true);
        assertThat(bankService.isBankSpecificationEnabled()).isTrue();
    }

    @Test
    void isBankSpecificationEnabled_false() {
        when(parameterService.getParameterValueAsBoolean(Bank.class, KFSParameterKeyConstants.ENABLE_BANK_SPECIFICATION_IND))
                .thenReturn(false);
        assertThat(bankService.isBankSpecificationEnabled()).isFalse();
    }
}
