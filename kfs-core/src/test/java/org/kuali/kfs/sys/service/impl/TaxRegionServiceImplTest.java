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
import org.kuali.kfs.sys.businessobject.TaxRegion;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.location.api.postalcode.PostalCode;
import org.kuali.rice.location.api.postalcode.PostalCodeService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class TaxRegionServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private PostalCodeService postalCodeService;

    @InjectMocks
    private TaxRegionServiceImpl taxRegionService;

    @Test
    void getSalesTaxRegions_withBlankPostalCode_returnsEmptyList() {
        List<TaxRegion> result = taxRegionService.getSalesTaxRegions("");
        assertThat(result).isEmpty();
        verifyNoInteractions(postalCodeService);
    }

    @Test
    void getSalesTaxRegions_withNullPostalCode_returnsEmptyList() {
        List<TaxRegion> result = taxRegionService.getSalesTaxRegions(null);
        assertThat(result).isEmpty();
        verifyNoInteractions(postalCodeService);
    }

    @Test
    void getSalesTaxRegions_whenPostalCodeNotFound_returnsEmptyList() {
        when(postalCodeService.getPostalCode(anyString(), eq("99999"))).thenReturn(null);

        List<TaxRegion> result = taxRegionService.getSalesTaxRegions("99999");
        assertThat(result).isEmpty();
    }

    @Test
    void getUseTaxRegions_withBlankPostalCode_returnsEmptyList() {
        List<TaxRegion> result = taxRegionService.getUseTaxRegions("");
        assertThat(result).isEmpty();
        verifyNoInteractions(postalCodeService);
    }

    @Test
    void getUseTaxRegions_withNullPostalCode_returnsEmptyList() {
        List<TaxRegion> result = taxRegionService.getUseTaxRegions(null);
        assertThat(result).isEmpty();
        verifyNoInteractions(postalCodeService);
    }
}
