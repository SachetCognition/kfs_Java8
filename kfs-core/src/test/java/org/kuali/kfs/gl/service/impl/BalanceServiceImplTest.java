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
package org.kuali.kfs.gl.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.service.BalanceTypeService;
import org.kuali.kfs.coa.service.ObjectTypeService;
import org.kuali.kfs.coa.service.SubFundGroupService;
import org.kuali.kfs.gl.businessobject.Balance;
import org.kuali.kfs.gl.businessobject.GlSummary;
import org.kuali.kfs.gl.dataaccess.BalanceDao;
import org.kuali.kfs.gl.service.BalanceService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.OptionsService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BalanceServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BalanceDao balanceDao;
    @Mock
    private OptionsService optionsService;
    @Mock
    private ObjectTypeService objectTypeService;
    @Mock
    private SubFundGroupService subFundGroupService;
    @Mock
    private ParameterService parameterService;
    @Mock
    private BalanceTypeService balanceTypService;

    @InjectMocks
    private BalanceServiceImpl balanceService;

    @Test
    void getGlSummary_withNoResults_returnsEmptyList() {
        List<String> balanceTypeCodes = Arrays.asList("AC", "CB");
        when(balanceDao.getGlSummary(2023, balanceTypeCodes)).thenReturn(Collections.<Object[]>emptyList().iterator());

        List<GlSummary> result = balanceService.getGlSummary(2023, balanceTypeCodes);

        assertThat(result).isEmpty();
        verify(balanceDao).getGlSummary(2023, balanceTypeCodes);
    }

    @Test
    void findBalancesForFiscalYear_delegatesToDao() {
        Iterator<Balance> mockIterator = Collections.<Balance>emptyList().iterator();
        when(balanceDao.findBalancesForFiscalYear(2023)).thenReturn(mockIterator);

        Iterator<Balance> result = balanceService.findBalancesForFiscalYear(2023);

        assertThat(result).isSameAs(mockIterator);
        verify(balanceDao).findBalancesForFiscalYear(2023);
    }
}
