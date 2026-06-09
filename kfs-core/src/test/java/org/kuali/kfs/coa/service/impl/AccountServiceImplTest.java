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
package org.kuali.kfs.coa.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.coa.businessobject.Account;
import org.kuali.kfs.coa.dataaccess.AccountDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.doctype.DocumentTypeService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class AccountServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ParameterService parameterService;
    @Mock
    private AccountDao accountDao;
    @Mock
    private DateTimeService dateTimeService;
    @Mock
    private DocumentTypeService documentTypeService;
    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void getByPrimaryId_returnsAccount() {
        Account expected = new Account();
        expected.setChartOfAccountsCode("BL");
        expected.setAccountNumber("1234567");
        when(businessObjectService.findByPrimaryKey(eq(Account.class), any(Map.class))).thenReturn(expected);

        Account result = accountService.getByPrimaryId("BL", "1234567");

        assertThat(result).isSameAs(expected);
        assertThat(result.getChartOfAccountsCode()).isEqualTo("BL");
        assertThat(result.getAccountNumber()).isEqualTo("1234567");
    }

    @Test
    void getByPrimaryId_notFound_returnsNull() {
        when(businessObjectService.findByPrimaryKey(eq(Account.class), any(Map.class))).thenReturn(null);

        Account result = accountService.getByPrimaryId("XX", "9999999");

        assertThat(result).isNull();
    }

    @Test
    void getByPrimaryIdWithCaching_returnsAccountFromGetByPrimaryId() {
        Account expected = new Account();
        expected.setChartOfAccountsCode("BL");
        expected.setAccountNumber("1234567");
        when(businessObjectService.findByPrimaryKey(eq(Account.class), any(Map.class))).thenReturn(null);

        Account result = accountService.getByPrimaryIdWithCaching("BL", "1234567");

        assertThat(result).isNull();
    }
}
