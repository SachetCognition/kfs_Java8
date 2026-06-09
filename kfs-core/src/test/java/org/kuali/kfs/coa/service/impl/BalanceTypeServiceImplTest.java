package org.kuali.kfs.coa.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.BalanceType;
import org.kuali.kfs.coa.dataaccess.BalanceTypeDao;
import org.kuali.kfs.sys.businessobject.SystemOptions;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class BalanceTypeServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private BalanceTypeDao balanceTypeDao;
    @Mock
    private UniversityDateService universityDateService;

    @InjectMocks
    private BalanceTypeServiceImpl balanceTypeService;

    @Test
    void getBalanceTypeByCode_returnsBalanceType() {
        BalanceType expected = new BalanceType();
        when(businessObjectService.findBySinglePrimaryKey(BalanceType.class, "AC")).thenReturn(expected);

        BalanceType result = balanceTypeService.getBalanceTypeByCode("AC");
        assertThat(result).isSameAs(expected);
    }

    @Test
    void getBalanceTypeByCode_notFound_returnsNull() {
        when(businessObjectService.findBySinglePrimaryKey(BalanceType.class, "XX")).thenReturn(null);

        BalanceType result = balanceTypeService.getBalanceTypeByCode("XX");
        assertThat(result).isNull();
    }

    @Test
    void getAllBalanceTypes_returnsAllTypes() {
        BalanceType bt1 = new BalanceType();
        BalanceType bt2 = new BalanceType();
        when(businessObjectService.findAll(BalanceType.class)).thenReturn(Arrays.asList(bt1, bt2));

        Collection<BalanceType> result = balanceTypeService.getAllBalanceTypes();
        assertThat(result).hasSize(2);
    }

    @Test
    void getAllEncumbranceBalanceTypes_delegatesToDao() {
        BalanceType bt = new BalanceType();
        when(balanceTypeDao.getEncumbranceBalanceTypes()).thenReturn(Arrays.asList(bt));

        Collection<BalanceType> result = balanceTypeService.getAllEncumbranceBalanceTypes();
        assertThat(result).hasSize(1);
    }

    @Test
    void getCostShareEncumbranceBalanceType_returnsCode() {
        SystemOptions options = new SystemOptions();
        options.setCostShareEncumbranceBalanceTypeCd("CE");
        when(businessObjectService.findBySinglePrimaryKey(SystemOptions.class, 2024)).thenReturn(options);

        String result = balanceTypeService.getCostShareEncumbranceBalanceType(2024);
        assertThat(result).isEqualTo("CE");
    }

    @Test
    void getEncumbranceBalanceTypes_returnsFourCodes() {
        SystemOptions options = new SystemOptions();
        options.setExtrnlEncumFinBalanceTypCd("EX");
        options.setIntrnlEncumFinBalanceTypCd("IE");
        options.setPreencumbranceFinBalTypeCd("PE");
        options.setCostShareEncumbranceBalanceTypeCd("CE");
        when(businessObjectService.findBySinglePrimaryKey(SystemOptions.class, 2024)).thenReturn(options);

        List<String> result = balanceTypeService.getEncumbranceBalanceTypes(2024);
        assertThat(result).containsExactly("EX", "IE", "PE", "CE");
    }
}
