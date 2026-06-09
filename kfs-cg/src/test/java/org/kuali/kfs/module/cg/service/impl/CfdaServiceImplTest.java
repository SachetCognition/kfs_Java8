package org.kuali.kfs.module.cg.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.cg.businessobject.CFDA;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.Mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class CfdaServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private DateTimeService dateTimeService;

    @Mock
    private ParameterService parameterService;

    private CfdaServiceImpl cfdaService;

    @BeforeEach
    void setUp() {
        cfdaService = new CfdaServiceImpl();
        cfdaService.setBusinessObjectService(businessObjectService);
        cfdaService.setDateTimeService(dateTimeService);
        cfdaService.setParameterService(parameterService);
    }

    @Test
    void testGetKfsCodesReturnsMap() throws IOException {
        Collection<CFDA> cfdaList = new ArrayList<>();
        CFDA cfda1 = new CFDA();
        cfda1.setCfdaNumber("10.001");
        cfda1.setCfdaProgramTitleName("Program A");
        cfdaList.add(cfda1);

        CFDA cfda2 = new CFDA();
        cfda2.setCfdaNumber("20.002");
        cfda2.setCfdaProgramTitleName("Program B");
        cfdaList.add(cfda2);

        when(businessObjectService.findAll(CFDA.class)).thenReturn(cfdaList);

        SortedMap<String, CFDA> result = cfdaService.getKfsCodes();

        assertThat(result).hasSize(2);
        assertThat(result.get("10.001").getCfdaProgramTitleName()).isEqualTo("Program A");
        assertThat(result.get("20.002").getCfdaProgramTitleName()).isEqualTo("Program B");
    }

    @Test
    void testGetKfsCodesReturnsEmptyMapWhenNoRecords() throws IOException {
        when(businessObjectService.findAll(CFDA.class)).thenReturn(new ArrayList<>());

        SortedMap<String, CFDA> result = cfdaService.getKfsCodes();

        assertThat(result).isEmpty();
    }

    @Test
    void testGetByPrimaryIdReturnsNull_WhenBlankNumber() {
        CFDA result = cfdaService.getByPrimaryId("");
        assertThat(result).isNull();
    }

    @Test
    void testGetByPrimaryIdReturnsNull_WhenNullNumber() {
        CFDA result = cfdaService.getByPrimaryId(null);
        assertThat(result).isNull();
    }

    @Test
    void testGetByPrimaryIdReturnsCfda() {
        CFDA expected = new CFDA();
        expected.setCfdaNumber("10.001");

        when(businessObjectService.findBySinglePrimaryKey(CFDA.class, "10.001")).thenReturn(expected);

        CFDA result = cfdaService.getByPrimaryId("10.001");

        assertThat(result).isSameAs(expected);
    }

    @Test
    void testGetByPrimaryIdTrimsInput() {
        CFDA expected = new CFDA();
        expected.setCfdaNumber("10.001");

        when(businessObjectService.findBySinglePrimaryKey(CFDA.class, "10.001")).thenReturn(expected);

        CFDA result = cfdaService.getByPrimaryId("  10.001  ");

        assertThat(result).isSameAs(expected);
    }

    @Test
    void testGetByPrimaryIdReturnsNull_WhenNotFound() {
        when(businessObjectService.findBySinglePrimaryKey(CFDA.class, "99.999")).thenReturn(null);

        CFDA result = cfdaService.getByPrimaryId("99.999");

        assertThat(result).isNull();
    }
}
