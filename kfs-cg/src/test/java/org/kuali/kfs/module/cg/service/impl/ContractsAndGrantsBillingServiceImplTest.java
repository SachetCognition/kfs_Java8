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
package org.kuali.kfs.module.cg.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.cg.CGPropertyConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

public class ContractsAndGrantsBillingServiceImplTest extends KfsUnitTestBase {

    private ContractsAndGrantsBillingServiceImpl service = new ContractsAndGrantsBillingServiceImpl();

    @Test
    public void testGetAgencyContractsGrantsBillingSectionIds() {
        List<String> sectionIds = service.getAgencyContractsGrantsBillingSectionIds();

        assertNotNull(sectionIds);
        assertEquals(5, sectionIds.size());
        assertTrue(sectionIds.contains(CGPropertyConstants.SectionId.AGENCY_ADDRESS_SECTION_ID));
        assertTrue(sectionIds.contains(CGPropertyConstants.SectionId.AGENCY_ADDRESSES_SECTION_ID));
        assertTrue(sectionIds.contains(CGPropertyConstants.SectionId.AGENCY_COLLECTIONS_MAINTENANCE_SECTION_ID));
        assertTrue(sectionIds.contains(CGPropertyConstants.SectionId.AGENCY_CONTRACTS_AND_GRANTS_SECTION_ID));
        assertTrue(sectionIds.contains(CGPropertyConstants.SectionId.AGENCY_CUSTOMER_SECTION_ID));
    }

    @Test
    public void testGetAgencyContractsGrantsBillingSectionIds_returnsNewListEachTime() {
        List<String> first = service.getAgencyContractsGrantsBillingSectionIds();
        List<String> second = service.getAgencyContractsGrantsBillingSectionIds();

        assertFalse(first == second);
        assertEquals(first, second);
    }

    @Test
    public void testGetAwardContractsGrantsBillingSectionIds() {
        List<String> sectionIds = service.getAwardContractsGrantsBillingSectionIds();

        assertNotNull(sectionIds);
        assertEquals(4, sectionIds.size());
        assertTrue(sectionIds.contains(CGPropertyConstants.SectionId.AWARD_FUND_MANAGERS_SECTION_ID));
        assertTrue(sectionIds.contains(CGPropertyConstants.SectionId.AWARD_INVOICING_SECTION_ID));
        assertTrue(sectionIds.contains(CGPropertyConstants.SectionId.AWARD_MILESTONE_SCHEDULE_SECTION_ID));
        assertTrue(sectionIds.contains(CGPropertyConstants.SectionId.AWARD_PREDETERMINED_BILLING_SCHEDULE_SECTION_ID));
    }

    @Test
    public void testGetAwardContractsGrantsBillingSectionIds_returnsNewListEachTime() {
        List<String> first = service.getAwardContractsGrantsBillingSectionIds();
        List<String> second = service.getAwardContractsGrantsBillingSectionIds();

        assertFalse(first == second);
        assertEquals(first, second);
    }

    @Test
    public void testGetAgencyContractsGrantsBillingSectionIds_order() {
        List<String> sectionIds = service.getAgencyContractsGrantsBillingSectionIds();

        assertEquals(CGPropertyConstants.SectionId.AGENCY_ADDRESS_SECTION_ID, sectionIds.get(0));
        assertEquals(CGPropertyConstants.SectionId.AGENCY_ADDRESSES_SECTION_ID, sectionIds.get(1));
        assertEquals(CGPropertyConstants.SectionId.AGENCY_COLLECTIONS_MAINTENANCE_SECTION_ID, sectionIds.get(2));
        assertEquals(CGPropertyConstants.SectionId.AGENCY_CONTRACTS_AND_GRANTS_SECTION_ID, sectionIds.get(3));
        assertEquals(CGPropertyConstants.SectionId.AGENCY_CUSTOMER_SECTION_ID, sectionIds.get(4));
    }

    @Test
    public void testGetAwardContractsGrantsBillingSectionIds_order() {
        List<String> sectionIds = service.getAwardContractsGrantsBillingSectionIds();

        assertEquals(CGPropertyConstants.SectionId.AWARD_FUND_MANAGERS_SECTION_ID, sectionIds.get(0));
        assertEquals(CGPropertyConstants.SectionId.AWARD_INVOICING_SECTION_ID, sectionIds.get(1));
        assertEquals(CGPropertyConstants.SectionId.AWARD_MILESTONE_SCHEDULE_SECTION_ID, sectionIds.get(2));
        assertEquals(CGPropertyConstants.SectionId.AWARD_PREDETERMINED_BILLING_SCHEDULE_SECTION_ID, sectionIds.get(3));
    }
}
