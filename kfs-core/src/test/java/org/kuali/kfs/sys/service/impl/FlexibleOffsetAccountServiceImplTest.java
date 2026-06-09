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
import org.kuali.kfs.coa.businessobject.OffsetDefinition;
import org.kuali.kfs.coa.service.AccountService;
import org.kuali.kfs.coa.service.ObjectCodeService;
import org.kuali.kfs.fp.businessobject.OffsetAccount;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class FlexibleOffsetAccountServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private AccountService accountService;
    @Mock
    private ObjectCodeService objectCodeService;
    @Mock
    private DateTimeService dateTimeService;
    @Mock
    private ParameterService parameterService;

    @InjectMocks
    private FlexibleOffsetAccountServiceImpl flexibleOffsetAccountService;

    @Test
    void getEnabled_whenFlagTrue_returnsTrue() {
        when(parameterService.getParameterValueAsBoolean(OffsetDefinition.class,
                KFSConstants.SystemGroupParameterNames.FLEXIBLE_OFFSET_ENABLED_FLAG)).thenReturn(true);

        assertThat(flexibleOffsetAccountService.getEnabled()).isTrue();
    }

    @Test
    void getEnabled_whenFlagFalse_returnsFalse() {
        when(parameterService.getParameterValueAsBoolean(OffsetDefinition.class,
                KFSConstants.SystemGroupParameterNames.FLEXIBLE_OFFSET_ENABLED_FLAG)).thenReturn(false);

        assertThat(flexibleOffsetAccountService.getEnabled()).isFalse();
    }

    @Test
    void getByPrimaryIdIfEnabled_whenDisabled_returnsNull() {
        when(parameterService.getParameterValueAsBoolean(OffsetDefinition.class,
                KFSConstants.SystemGroupParameterNames.FLEXIBLE_OFFSET_ENABLED_FLAG)).thenReturn(false);

        OffsetAccount result = flexibleOffsetAccountService.getByPrimaryIdIfEnabled("BL", "1234567", "9041");
        assertThat(result).isNull();
        verifyNoInteractions(businessObjectService);
    }

    @Test
    void getByPrimaryIdIfEnabled_whenEnabled_queriesBusinessObjectService() {
        when(parameterService.getParameterValueAsBoolean(OffsetDefinition.class,
                KFSConstants.SystemGroupParameterNames.FLEXIBLE_OFFSET_ENABLED_FLAG)).thenReturn(true);

        OffsetAccount expected = new OffsetAccount();
        when(businessObjectService.findByPrimaryKey(eq(OffsetAccount.class), any(Map.class))).thenReturn(expected);

        OffsetAccount result = flexibleOffsetAccountService.getByPrimaryIdIfEnabled("BL", "1234567", "9041");
        assertThat(result).isSameAs(expected);
    }

    @Test
    void getByPrimaryIdIfEnabled_whenEnabledButNotFound_returnsNull() {
        when(parameterService.getParameterValueAsBoolean(OffsetDefinition.class,
                KFSConstants.SystemGroupParameterNames.FLEXIBLE_OFFSET_ENABLED_FLAG)).thenReturn(true);

        when(businessObjectService.findByPrimaryKey(eq(OffsetAccount.class), any(Map.class))).thenReturn(null);

        OffsetAccount result = flexibleOffsetAccountService.getByPrimaryIdIfEnabled("BL", "1234567", "9041");
        assertThat(result).isNull();
    }
}
