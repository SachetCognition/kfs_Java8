package org.kuali.kfs.module.bc.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.bc.BCConstants.SynchronizationCheckType;
import org.kuali.kfs.module.bc.businessobject.BudgetConstructionPosition;
import org.kuali.kfs.module.bc.businessobject.Position;
import org.kuali.kfs.module.bc.dataaccess.HumanResourcesPayrollDao;
import org.kuali.kfs.module.bc.exception.PositionNotFoundException;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

class HumanResourcesPayrollServiceImplTest extends KfsUnitTestBase {

    @Mock
    private HumanResourcesPayrollDao humanResourcesPayrollDao;

    @InjectMocks
    private HumanResourcesPayrollServiceImpl service;

    @Test
    void validatePositionUnionCode_alwaysReturnsTrue() {
        assertThat(service.validatePositionUnionCode("UC01")).isTrue();
        assertThat(service.validatePositionUnionCode("")).isTrue();
        assertThat(service.validatePositionUnionCode(null)).isTrue();
    }

    @Test
    void getPosition_returnsPosition_whenFound() {
        BudgetConstructionPosition expectedPosition = new BudgetConstructionPosition();
        expectedPosition.setUniversityFiscalYear(2024);
        expectedPosition.setPositionNumber("POS001");

        when(humanResourcesPayrollDao.getPosition(2024, "POS001")).thenReturn(expectedPosition);

        Position result = service.getPosition(2024, "POS001");
        assertThat(result).isSameAs(expectedPosition);
    }

    @Test
    void getPosition_throwsPositionNotFoundException_whenNotFound() {
        when(humanResourcesPayrollDao.getPosition(2024, "NOPOS")).thenReturn(null);

        assertThatThrownBy(() -> service.getPosition(2024, "NOPOS"))
                .isInstanceOf(PositionNotFoundException.class);
    }

    @Test
    void isActiveJob_alwaysReturnsTrue() {
        assertThat(service.isActiveJob("EMP001", "POS001", 2024, SynchronizationCheckType.NONE)).isTrue();
        assertThat(service.isActiveJob("EMP001", "POS001", 2024, SynchronizationCheckType.ALL)).isTrue();
    }
}
