package org.kuali.kfs.module.tem.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.businessobject.ExpenseTypeObjectCode;
import org.kuali.kfs.module.tem.dataaccess.ExpenseTypeObjectCodeDao;
import org.kuali.kfs.module.tem.service.TravelService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kns.service.DocumentHelperService;
import org.kuali.rice.krad.service.BusinessObjectService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@DisplayName("TravelExpenseServiceImpl")
class TravelExpenseServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private DateTimeService dateTimeService;

    @Mock
    private ExpenseTypeObjectCodeDao expenseTypeObjectCodeDao;

    @Mock
    private DocumentHelperService documentHelperService;

    @Mock
    private TravelService travelService;

    @InjectMocks
    private TravelExpenseServiceImpl travelExpenseService;

    @BeforeEach
    void setUp() {
        travelExpenseService.setTravelService(travelService);
    }

    @Test
    @DisplayName("should throw IllegalArgumentException when expense type code is blank")
    void testGetExpenseTypeWithBlankCode() {
        assertThatThrownBy(() -> travelExpenseService.getExpenseType("", "TA", "IN", "EMP"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("cannot be blank");
    }

    @Test
    @DisplayName("should throw IllegalArgumentException when expense type code is null")
    void testGetExpenseTypeWithNullCode() {
        assertThatThrownBy(() -> travelExpenseService.getExpenseType(null, "TA", "IN", "EMP"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("should return null when no matching expense type object codes found")
    void testGetExpenseTypeReturnsNull() {
        when(travelService.getParentDocumentTypeNames(anyString()))
                .thenReturn(Collections.singleton("TravelAuthorization"));
        when(expenseTypeObjectCodeDao.findMatchingExpenseTypeObjectCodes(anyString(), anySet(), anyString(), anyString()))
                .thenReturn(Collections.emptyList());

        ExpenseTypeObjectCode result = travelExpenseService.getExpenseType("AIRFARE", "TA", "IN", "EMP");
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("should return first matching expense type object code")
    void testGetExpenseTypeReturnsFirst() {
        ExpenseTypeObjectCode etoc = new ExpenseTypeObjectCode();
        etoc.setExpenseTypeCode("AIRFARE");

        when(travelService.getParentDocumentTypeNames(anyString()))
                .thenReturn(Collections.singleton("TravelAuthorization"));
        when(expenseTypeObjectCodeDao.findMatchingExpenseTypeObjectCodes(anyString(), anySet(), anyString(), anyString()))
                .thenReturn(Arrays.asList(etoc));

        ExpenseTypeObjectCode result = travelExpenseService.getExpenseType("AIRFARE", "TA", "IN", "EMP");
        assertThat(result).isNotNull();
        assertThat(result.getExpenseTypeCode()).isEqualTo("AIRFARE");
    }

    @Test
    @DisplayName("should return null when expense type object codes list is null")
    void testGetExpenseTypeWithNullList() {
        when(travelService.getParentDocumentTypeNames(anyString()))
                .thenReturn(Collections.singleton("TravelAuthorization"));
        when(expenseTypeObjectCodeDao.findMatchingExpenseTypeObjectCodes(anyString(), anySet(), anyString(), anyString()))
                .thenReturn(null);

        ExpenseTypeObjectCode result = travelExpenseService.getExpenseType("LODG", "TR", "DOM", "EMP");
        assertThat(result).isNull();
    }
}
