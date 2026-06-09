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
package org.kuali.kfs.pdp.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.pdp.dataaccess.PaymentGroupDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PaymentGroupServiceImplTest extends KfsUnitTestBase {

    @Mock
    private PaymentGroupDao paymentGroupDao;
    @Mock
    private ParameterService parameterService;
    @Mock
    private DataDictionaryService dataDictionaryService;
    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private PaymentGroupServiceImpl paymentGroupService;

    @Test
    void getDisbursementNumbersByDisbursementType_delegatesToDao() {
        List<Integer> expected = Arrays.asList(1001, 1002, 1003);
        when(paymentGroupDao.getDisbursementNumbersByDisbursementType(1, "CHCK")).thenReturn(expected);

        List<Integer> result = paymentGroupService.getDisbursementNumbersByDisbursementType(1, "CHCK");

        assertThat(result).isEqualTo(expected);
        verify(paymentGroupDao).getDisbursementNumbersByDisbursementType(1, "CHCK");
    }

    @Test
    void getDisbursementNumbersByDisbursementTypeAndBankCode_delegatesToDao() {
        List<Integer> expected = Arrays.asList(2001);
        when(paymentGroupDao.getDisbursementNumbersByDisbursementTypeAndBankCode(1, "ACH", "BANK1")).thenReturn(expected);

        List<Integer> result = paymentGroupService.getDisbursementNumbersByDisbursementTypeAndBankCode(1, "ACH", "BANK1");

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getDistinctBankCodesForProcessAndType_delegatesToDao() {
        List<String> expected = Arrays.asList("BANK1", "BANK2");
        when(paymentGroupDao.getDistinctBankCodesForProcessAndType(1, "CHCK")).thenReturn(expected);

        List<String> result = paymentGroupService.getDistinctBankCodesForProcessAndType(1, "CHCK");

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getDisbursementNumbersByDisbursementType_noResults_returnsEmptyList() {
        when(paymentGroupDao.getDisbursementNumbersByDisbursementType(99, "NONE")).thenReturn(Collections.<Integer>emptyList());

        List<Integer> result = paymentGroupService.getDisbursementNumbersByDisbursementType(99, "NONE");

        assertThat(result).isEmpty();
    }
}
