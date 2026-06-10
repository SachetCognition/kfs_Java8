package org.kuali.kfs.coa.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.BalanceType;
import org.kuali.kfs.coa.dataaccess.BalanceTypeDao;
import org.kuali.kfs.sys.businessobject.SystemOptions;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class BalanceTypeServiceImplTest extends KfsUnitTestBase {

    @Mock private BusinessObjectService businessObjectService;
    @Mock private BalanceTypeDao balanceTypeDao;
    @Mock private UniversityDateService universityDateService;

    @InjectMocks
    private BalanceTypeServiceImpl balanceTypeService;

    @Nested
    @DisplayName("getBalanceTypeByCode")
    class GetByCodeTests {

        @Test
        void returnsBalanceTypeWhenFound() {
            BalanceType expected = new BalanceType();
            expected.setCode("AC");
            when(businessObjectService.findBySinglePrimaryKey(BalanceType.class, "AC"))
                .thenReturn(expected);

            BalanceType result = balanceTypeService.getBalanceTypeByCode("AC");

            assertThat(result).isNotNull();
            assertThat(result.getCode()).isEqualTo("AC");
        }

        @Test
        void returnsNullWhenNotFound() {
            when(businessObjectService.findBySinglePrimaryKey(BalanceType.class, "XX"))
                .thenReturn(null);

            assertThat(balanceTypeService.getBalanceTypeByCode("XX")).isNull();
        }
    }

    @Nested
    @DisplayName("getAllBalanceTypes")
    class GetAllTests {

        @Test
        void returnsAllTypes() {
            BalanceType bt1 = new BalanceType();
            bt1.setCode("AC");
            BalanceType bt2 = new BalanceType();
            bt2.setCode("CB");
            when(businessObjectService.findAll(BalanceType.class))
                .thenReturn(Arrays.asList(bt1, bt2));

            Collection<BalanceType> result = balanceTypeService.getAllBalanceTypes();

            assertThat(result).hasSize(2);
        }
    }

    @Nested
    @DisplayName("getAllEncumbranceBalanceTypes")
    class GetEncumbranceTests {

        @Test
        void delegatesToDao() {
            BalanceType encType = new BalanceType();
            encType.setCode("EX");
            encType.setFinBalanceTypeEncumIndicator(true);
            when(balanceTypeDao.getEncumbranceBalanceTypes())
                .thenReturn(Collections.singletonList(encType));

            Collection<BalanceType> result = balanceTypeService.getAllEncumbranceBalanceTypes();

            assertThat(result).hasSize(1);
        }
    }

    @Nested
    @DisplayName("getCostShareEncumbranceBalanceType")
    class CostShareTests {

        @Test
        void returnsValueFromSystemOptions() {
            SystemOptions options = new SystemOptions();
            options.setCostShareEncumbranceBalanceTypeCd("CE");
            when(businessObjectService.findBySinglePrimaryKey(SystemOptions.class, 2025))
                .thenReturn(options);

            String result = balanceTypeService.getCostShareEncumbranceBalanceType(2025);

            assertThat(result).isEqualTo("CE");
        }
    }

    @Nested
    @DisplayName("getEncumbranceBalanceTypes")
    class EncumbranceByYearTests {

        @Test
        void returnsAllEncumbranceTypeCodes() {
            SystemOptions options = new SystemOptions();
            options.setExtrnlEncumFinBalanceTypCd("EX");
            options.setIntrnlEncumFinBalanceTypCd("IE");
            options.setPreencumbranceFinBalTypeCd("PE");
            options.setCostShareEncumbranceBalanceTypeCd("CE");
            when(businessObjectService.findBySinglePrimaryKey(SystemOptions.class, 2025))
                .thenReturn(options);

            List<String> result = balanceTypeService.getEncumbranceBalanceTypes(2025);

            assertThat(result).containsExactly("EX", "IE", "PE", "CE");
        }
    }
}
