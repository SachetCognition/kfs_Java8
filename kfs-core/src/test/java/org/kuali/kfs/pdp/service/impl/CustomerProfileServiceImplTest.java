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
import org.kuali.kfs.pdp.businessobject.CustomerProfile;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class CustomerProfileServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @InjectMocks
    private CustomerProfileServiceImpl customerProfileService;

    @Test
    void get_whenProfileExists_returnsProfile() {
        CustomerProfile expected = new CustomerProfile();
        when(businessObjectService.findMatching(eq(CustomerProfile.class), any(Map.class)))
                .thenReturn(Arrays.asList(expected));

        CustomerProfile result = customerProfileService.get("BL", "ARSC", "ARSC");
        assertThat(result).isSameAs(expected);
    }

    @Test
    void get_whenNoMatch_returnsNull() {
        when(businessObjectService.findMatching(eq(CustomerProfile.class), any(Map.class)))
                .thenReturn(Collections.emptyList());

        CustomerProfile result = customerProfileService.get("XX", "XX", "XX");
        assertThat(result).isNull();
    }

    @Test
    void get_whenMultipleMatches_returnsFirst() {
        CustomerProfile first = new CustomerProfile();
        CustomerProfile second = new CustomerProfile();
        when(businessObjectService.findMatching(eq(CustomerProfile.class), any(Map.class)))
                .thenReturn(Arrays.asList(first, second));

        CustomerProfile result = customerProfileService.get("BL", "ARSC", "ARSC");
        assertThat(result).isSameAs(first);
    }
}
