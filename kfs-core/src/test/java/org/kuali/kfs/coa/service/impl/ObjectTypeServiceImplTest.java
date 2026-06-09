package org.kuali.kfs.coa.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.ObjectType;
import org.kuali.kfs.sys.businessobject.SystemOptions;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class ObjectTypeServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private UniversityDateService universityDateService;

    @InjectMocks
    private ObjectTypeServiceImpl objectTypeService;

    @Test
    void getByPrimaryKey_returnsObjectType() {
        ObjectType expected = new ObjectType();
        when(businessObjectService.findBySinglePrimaryKey(ObjectType.class, "EX")).thenReturn(expected);

        ObjectType result = objectTypeService.getByPrimaryKey("EX");
        assertThat(result).isSameAs(expected);
    }

    @Test
    void getByPrimaryKey_notFound_returnsNull() {
        when(businessObjectService.findBySinglePrimaryKey(ObjectType.class, "ZZ")).thenReturn(null);

        ObjectType result = objectTypeService.getByPrimaryKey("ZZ");
        assertThat(result).isNull();
    }

    @Test
    void getAssetObjectType_returnsAssetCode() {
        SystemOptions options = new SystemOptions();
        options.setFinancialObjectTypeAssetsCd("AS");
        when(businessObjectService.findBySinglePrimaryKey(SystemOptions.class, 2024)).thenReturn(options);

        String result = objectTypeService.getAssetObjectType(2024);
        assertThat(result).isEqualTo("AS");
    }

    @Test
    void getBasicExpenseObjectTypes_returnsThreeTypes() {
        SystemOptions options = new SystemOptions();
        options.setFinObjTypeExpenditureexpCd("ES");
        options.setFinObjTypeExpendNotExpCode("NX");
        options.setFinObjTypeExpNotExpendCode("XN");
        when(businessObjectService.findBySinglePrimaryKey(SystemOptions.class, 2024)).thenReturn(options);

        List<String> result = objectTypeService.getBasicExpenseObjectTypes(2024);
        assertThat(result).containsExactly("ES", "NX", "XN");
    }
}
