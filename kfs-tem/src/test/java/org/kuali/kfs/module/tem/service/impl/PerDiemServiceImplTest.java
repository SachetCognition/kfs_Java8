package org.kuali.kfs.module.tem.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.batch.businessobject.MealBreakDownStrategy;
import org.kuali.kfs.module.tem.businessobject.PerDiem;
import org.kuali.kfs.module.tem.dataaccess.PerDiemDao;
import org.kuali.kfs.module.tem.dataaccess.TravelDocumentDao;
import org.kuali.kfs.module.tem.service.TravelExpenseService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.location.api.state.StateService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@DisplayName("PerDiemServiceImpl")
class PerDiemServiceImplTest extends KfsUnitTestBase {

    @Mock
    private DateTimeService dateTimeService;

    @Mock
    private ParameterService parameterService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private StateService stateService;

    @Mock
    private PerDiemDao perDiemDao;

    @Mock
    private TravelDocumentDao travelDocumentDao;

    @Mock
    private TravelExpenseService travelExpenseService;

    @InjectMocks
    private PerDiemServiceImpl perDiemService;

    @BeforeEach
    void setUp() {
        Map<String, MealBreakDownStrategy> strategies = new HashMap<>();
        perDiemService.setMealBreakDownStrategies(strategies);
    }

    @Test
    @DisplayName("should break down meals and incidentals for a list of per diems")
    void testBreakDownMealsIncidentalList() {
        MealBreakDownStrategy mockStrategy = org.mockito.Mockito.mock(MealBreakDownStrategy.class);
        Map<String, MealBreakDownStrategy> strategies = new HashMap<>();
        strategies.put("Y", mockStrategy);
        perDiemService.setMealBreakDownStrategies(strategies);

        PerDiem perDiem1 = new PerDiem();
        perDiem1.setConusIndicator("Y");
        perDiem1.setMealsAndIncidentals(new KualiDecimal(62.50));

        List<PerDiem> perDiems = new ArrayList<>();
        perDiems.add(perDiem1);

        perDiemService.breakDownMealsIncidental(perDiems);

        verify(mockStrategy).breakDown(perDiem1);
    }

    @Test
    @DisplayName("should set and get dateTimeService")
    void testSetDateTimeService() {
        perDiemService.setDateTimeService(dateTimeService);
        assertThat(perDiemService.getDateTimeService()).isSameAs(dateTimeService);
    }

    @Test
    @DisplayName("should set and get parameterService")
    void testSetParameterService() {
        perDiemService.setParameterService(parameterService);
        assertThat(perDiemService.getParameterService()).isSameAs(parameterService);
    }

    @Test
    @DisplayName("should set and get businessObjectService")
    void testSetBusinessObjectService() {
        perDiemService.setBusinessObjectService(businessObjectService);
        assertThat(perDiemService.getBusinessObjectService()).isSameAs(businessObjectService);
    }

    @Test
    @DisplayName("should set and get stateService")
    void testSetStateService() {
        perDiemService.setStateService(stateService);
        assertThat(perDiemService.getStateService()).isSameAs(stateService);
    }

    @Test
    @DisplayName("should set and get perDiemDao")
    void testSetPerDiemDao() {
        perDiemService.setPerDiemDao(perDiemDao);
        assertThat(perDiemService.getPerDiemDao()).isSameAs(perDiemDao);
    }

    @Test
    @DisplayName("should set and get mealBreakDownStrategies")
    void testSetMealBreakDownStrategies() {
        Map<String, MealBreakDownStrategy> strategies = new HashMap<>();
        perDiemService.setMealBreakDownStrategies(strategies);
        assertThat(perDiemService.getMealBreakDownStrategies()).isSameAs(strategies);
    }

    @Test
    @DisplayName("should set and get travelDocumentDao")
    void testSetTravelDocumentDao() {
        perDiemService.setTravelDocumentDao(travelDocumentDao);
        assertThat(perDiemService.getTravelDocumentDao()).isSameAs(travelDocumentDao);
    }
}
