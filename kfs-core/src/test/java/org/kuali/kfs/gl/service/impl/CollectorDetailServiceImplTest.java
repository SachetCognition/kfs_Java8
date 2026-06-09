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
import org.kuali.kfs.gl.dataaccess.CollectorDetailDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CollectorDetailServiceImplTest extends KfsUnitTestBase {

    @Mock
    private CollectorDetailDao collectorDetailDao;

    @InjectMocks
    private CollectorDetailServiceImpl collectorDetailService;

    @Test
    void purgeYearByChart_delegatesToDao() {
        collectorDetailService.purgeYearByChart("BL", 2023);
        verify(collectorDetailDao).purgeYearByChart("BL", 2023);
    }

    @Test
    void getNextCreateSequence_delegatesToDao() {
        Date testDate = new Date(System.currentTimeMillis());
        when(collectorDetailDao.getMaxCreateSequence(testDate)).thenReturn(5);

        Integer result = collectorDetailService.getNextCreateSequence(testDate);

        assertThat(result).isEqualTo(5);
        verify(collectorDetailDao).getMaxCreateSequence(testDate);
    }

    @Test
    void getNextCreateSequence_whenNoSequence_returnsNull() {
        Date testDate = new Date(System.currentTimeMillis());
        when(collectorDetailDao.getMaxCreateSequence(testDate)).thenReturn(null);

        Integer result = collectorDetailService.getNextCreateSequence(testDate);

        assertThat(result).isNull();
    }
}
