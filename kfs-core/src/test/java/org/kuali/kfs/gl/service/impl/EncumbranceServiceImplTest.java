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
import org.kuali.kfs.gl.dataaccess.EncumbranceDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class EncumbranceServiceImplTest extends KfsUnitTestBase {

    @Mock
    private EncumbranceDao encumbranceDao;

    @InjectMocks
    private EncumbranceServiceImpl encumbranceService;

    @Test
    void purgeYearByChart_delegatesToDao() {
        encumbranceService.purgeYearByChart("BL", 2022);
        verify(encumbranceDao).purgeYearByChart("BL", 2022);
    }

    @Test
    void getAllEncumbrances_delegatesToDao() {
        Iterator mockIterator = Collections.emptyList().iterator();
        when(encumbranceDao.getAllEncumbrances()).thenReturn(mockIterator);

        Iterator result = encumbranceService.getAllEncumbrances();
        assertThat(result).isSameAs(mockIterator);
    }

    @Test
    void getSummarizedEncumbrances_delegatesToDao() {
        Iterator mockIterator = Collections.emptyList().iterator();
        when(encumbranceDao.getSummarizedEncumbrances("DV", true)).thenReturn(mockIterator);

        Iterator result = encumbranceService.getSummarizedEncumbrances("DV", true);
        assertThat(result).isSameAs(mockIterator);
    }

    @Test
    void findOpenEncumbrance_delegatesToDao() {
        Map<String, Object> fieldValues = new HashMap<>();
        Iterator mockIterator = Collections.emptyList().iterator();
        when(encumbranceDao.findOpenEncumbrance(fieldValues, false)).thenReturn(mockIterator);

        Iterator result = encumbranceService.findOpenEncumbrance(fieldValues, false);
        assertThat(result).isSameAs(mockIterator);
    }

    @Test
    void getOpenEncumbranceRecordCount_delegatesToDao() {
        Map<String, Object> fieldValues = new HashMap<>();
        when(encumbranceDao.getOpenEncumbranceRecordCount(fieldValues, true)).thenReturn(42);

        Integer result = encumbranceService.getOpenEncumbranceRecordCount(fieldValues, true);
        assertThat(result).isEqualTo(42);
    }

    @Test
    void getOpenEncumbranceRecordCount_withZero() {
        Map<String, Object> fieldValues = new HashMap<>();
        when(encumbranceDao.getOpenEncumbranceRecordCount(fieldValues, false)).thenReturn(0);

        Integer result = encumbranceService.getOpenEncumbranceRecordCount(fieldValues, false);
        assertThat(result).isEqualTo(0);
    }
}
