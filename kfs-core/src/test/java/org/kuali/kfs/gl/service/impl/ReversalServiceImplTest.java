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
import org.kuali.kfs.coa.service.AccountingPeriodService;
import org.kuali.kfs.gl.businessobject.LedgerEntryHolder;
import org.kuali.kfs.gl.businessobject.Reversal;
import org.kuali.kfs.gl.businessobject.Transaction;
import org.kuali.kfs.gl.dataaccess.ReversalDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ReversalServiceImplTest extends KfsUnitTestBase {

    @Mock
    private ReversalDao reversalDao;
    @Mock
    private AccountingPeriodService accountingPeriodService;
    @Mock
    private UniversityDateService universityDateService;

    @InjectMocks
    private ReversalServiceImpl reversalService;

    @Test
    void delete_delegatesToDao() {
        Reversal reversal = new Reversal();
        reversalService.delete(reversal);
        verify(reversalDao).delete(reversal);
    }

    @Test
    void getByDate_delegatesToDao() {
        Date testDate = new Date();
        Iterator mockIterator = Collections.emptyList().iterator();
        when(reversalDao.getByDate(testDate)).thenReturn(mockIterator);

        Iterator result = reversalService.getByDate(testDate);
        assertThat(result).isSameAs(mockIterator);
    }

    @Test
    void getByTransaction_delegatesToDao() {
        Transaction transaction = mock(Transaction.class);
        Reversal expected = new Reversal();
        when(reversalDao.getByTransaction(transaction)).thenReturn(expected);

        Reversal result = reversalService.getByTransaction(transaction);
        assertThat(result).isSameAs(expected);
    }

    @Test
    void getByTransaction_notFound_returnsNull() {
        Transaction transaction = mock(Transaction.class);
        when(reversalDao.getByTransaction(transaction)).thenReturn(null);

        Reversal result = reversalService.getByTransaction(transaction);
        assertThat(result).isNull();
    }

    @Test
    void getSummaryByDate_withNoReversals_returnsEmptyHolder() {
        Date testDate = new Date();
        when(reversalDao.getByDate(testDate)).thenReturn(Collections.emptyList().iterator());

        LedgerEntryHolder result = reversalService.getSummaryByDate(testDate);
        assertThat(result).isNotNull();
    }
}
