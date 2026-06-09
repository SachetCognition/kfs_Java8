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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.integration.cg.ContractsAndGrantsBillingAward;
import org.kuali.kfs.module.cg.businessobject.Award;
import org.kuali.kfs.module.cg.businessobject.AwardAccount;
import org.kuali.kfs.module.cg.service.AwardService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.LookupService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class ContractsAndGrantsModuleBillingServiceImplTest extends KfsUnitTestBase {

    @Mock
    private AwardService awardService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private LookupService lookupService;

    @InjectMocks
    private ContractsAndGrantsModuleBillingServiceImpl service;

    @Test
    public void testUpdateAwardIfNecessary_nullProposalNumber() {
        Award currentAward = new Award();

        ContractsAndGrantsBillingAward result = service.updateAwardIfNecessary(null, currentAward);

        assertNull(result);
    }

    @Test
    public void testUpdateAwardIfNecessary_nullCurrentAward() {
        Award expected = new Award();
        when(awardService.getByPrimaryId(100L)).thenReturn(expected);

        ContractsAndGrantsBillingAward result = service.updateAwardIfNecessary(100L, null);

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    public void testUpdateAwardIfNecessary_differentProposalNumber() {
        Award currentAward = new Award();
        currentAward.setProposalNumber(50L);

        Award expected = new Award();
        when(awardService.getByPrimaryId(100L)).thenReturn(expected);

        ContractsAndGrantsBillingAward result = service.updateAwardIfNecessary(100L, currentAward);

        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    public void testUpdateAwardIfNecessary_sameProposalNumber() {
        Award currentAward = new Award();
        currentAward.setProposalNumber(100L);

        ContractsAndGrantsBillingAward result = service.updateAwardIfNecessary(100L, currentAward);

        assertEquals(currentAward, result);
        verify(awardService, never()).getByPrimaryId(any(Long.class));
    }

    @Test
    public void testSetLastBilledDateToAward_noOp() {
        service.setLastBilledDateToAward(100L, new Date(System.currentTimeMillis()));
        // This is a no-op method, just verify no exception is thrown
    }

    @Test
    public void testSetLastBilledDateToAwardAccount_normalInvoice() {
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("proposalNumber", 100L);

        AwardAccount awardAccount = new AwardAccount();
        Date currentBilled = new Date(1000L);
        awardAccount.setCurrentLastBilledDate(currentBilled);

        when(businessObjectService.findByPrimaryKey(AwardAccount.class, criteria)).thenReturn(awardAccount);

        Date newBilledDate = new Date(2000L);
        service.setLastBilledDateToAwardAccount(criteria, false, newBilledDate);

        assertEquals(currentBilled, awardAccount.getPreviousLastBilledDate());
        assertEquals(newBilledDate, awardAccount.getCurrentLastBilledDate());
        verify(businessObjectService).save(awardAccount);
    }

    @Test
    public void testSetLastBilledDateToAwardAccount_invoiceReversal() {
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("proposalNumber", 100L);

        AwardAccount awardAccount = new AwardAccount();
        Date previousBilled = new Date(500L);
        Date currentBilled = new Date(1000L);
        awardAccount.setPreviousLastBilledDate(previousBilled);
        awardAccount.setCurrentLastBilledDate(currentBilled);

        when(businessObjectService.findByPrimaryKey(AwardAccount.class, criteria)).thenReturn(awardAccount);

        service.setLastBilledDateToAwardAccount(criteria, true, new Date(2000L));

        assertEquals(previousBilled, awardAccount.getCurrentLastBilledDate());
        assertNull(awardAccount.getPreviousLastBilledDate());
        verify(businessObjectService).save(awardAccount);
    }

    @Test
    public void testSetFinalBilledToAwardAccount() {
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("proposalNumber", 100L);

        AwardAccount awardAccount = new AwardAccount();
        awardAccount.setFinalBilledIndicator(false);

        when(businessObjectService.findByPrimaryKey(AwardAccount.class, criteria)).thenReturn(awardAccount);

        service.setFinalBilledToAwardAccount(criteria, true);

        assertEquals(true, awardAccount.isFinalBilledIndicator());
        verify(businessObjectService).save(awardAccount);
    }

    @Test
    public void testSetFinalBilledToAwardAccount_setFalse() {
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("proposalNumber", 100L);

        AwardAccount awardAccount = new AwardAccount();
        awardAccount.setFinalBilledIndicator(true);

        when(businessObjectService.findByPrimaryKey(AwardAccount.class, criteria)).thenReturn(awardAccount);

        service.setFinalBilledToAwardAccount(criteria, false);

        assertEquals(false, awardAccount.isFinalBilledIndicator());
        verify(businessObjectService).save(awardAccount);
    }

    @Test
    public void testSetFinalBilledAndLastBilledDateToAwardAccount_normalInvoice() {
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("proposalNumber", 100L);

        AwardAccount awardAccount = new AwardAccount();
        Date currentBilled = new Date(1000L);
        awardAccount.setCurrentLastBilledDate(currentBilled);
        awardAccount.setFinalBilledIndicator(false);

        when(businessObjectService.findByPrimaryKey(AwardAccount.class, criteria)).thenReturn(awardAccount);

        Date newBilledDate = new Date(2000L);
        service.setFinalBilledAndLastBilledDateToAwardAccount(criteria, true, false, newBilledDate);

        assertEquals(currentBilled, awardAccount.getPreviousLastBilledDate());
        assertEquals(newBilledDate, awardAccount.getCurrentLastBilledDate());
        assertEquals(true, awardAccount.isFinalBilledIndicator());
        verify(businessObjectService).save(awardAccount);
    }

    @Test
    public void testSetFinalBilledAndLastBilledDateToAwardAccount_invoiceReversal() {
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("proposalNumber", 100L);

        AwardAccount awardAccount = new AwardAccount();
        Date previousBilled = new Date(500L);
        Date currentBilled = new Date(1000L);
        awardAccount.setPreviousLastBilledDate(previousBilled);
        awardAccount.setCurrentLastBilledDate(currentBilled);
        awardAccount.setFinalBilledIndicator(true);

        when(businessObjectService.findByPrimaryKey(AwardAccount.class, criteria)).thenReturn(awardAccount);

        service.setFinalBilledAndLastBilledDateToAwardAccount(criteria, false, true, new Date(2000L));

        assertEquals(previousBilled, awardAccount.getCurrentLastBilledDate());
        assertNull(awardAccount.getPreviousLastBilledDate());
        assertEquals(false, awardAccount.isFinalBilledIndicator());
        verify(businessObjectService).save(awardAccount);
    }

    @Test
    public void testLookupAwards_accountNumberMapped() {
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put("accountNumber", "1234567");

        List<Award> expected = new ArrayList<>();
        when(lookupService.findCollectionBySearchHelper(eq(Award.class), any(Map.class), eq(true))).thenReturn(expected);

        service.setLookupService(lookupService);
        List<?> result = service.lookupAwards(fieldValues, true);

        assertNotNull(result);
        assertNull(fieldValues.get("accountNumber"));
        assertEquals("1234567", fieldValues.get("awardAccounts.account.accountNumber"));
    }

    @Test
    public void testLookupAwards_billingFrequencyMapped() {
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put("awardBillingFrequency", "MNTH");

        List<Award> expected = new ArrayList<>();
        when(lookupService.findCollectionBySearchHelper(eq(Award.class), any(Map.class), eq(false))).thenReturn(expected);

        service.setLookupService(lookupService);
        service.lookupAwards(fieldValues, false);

        assertNull(fieldValues.get("awardBillingFrequency"));
        assertEquals("MNTH", fieldValues.get("billingFrequencyCode"));
    }

    @Test
    public void testLookupAwards_awardTotalMapped() {
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put("awardTotal", "50000");

        List<Award> expected = new ArrayList<>();
        when(lookupService.findCollectionBySearchHelper(eq(Award.class), any(Map.class), eq(true))).thenReturn(expected);

        service.setLookupService(lookupService);
        service.lookupAwards(fieldValues, true);

        assertNull(fieldValues.get("awardTotal"));
        assertEquals("50000", fieldValues.get("awardTotalAmount"));
    }

    @Test
    public void testLookupAwards_beginningDateBothBounds() {
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put("rangeLowerBoundKeyPrefix_awardBeginningDate", "01/01/2020");
        fieldValues.put("awardBeginningDate", "12/31/2020");

        List<Award> expected = new ArrayList<>();
        when(lookupService.findCollectionBySearchHelper(eq(Award.class), any(Map.class), eq(true))).thenReturn(expected);

        service.setLookupService(lookupService);
        service.lookupAwards(fieldValues, true);

        assertEquals("01/01/2020..12/31/2020", fieldValues.get("awardBeginningDate"));
    }

    @Test
    public void testLookupAwards_beginningDateUpperBoundOnly() {
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put("awardBeginningDate", "12/31/2020");

        List<Award> expected = new ArrayList<>();
        when(lookupService.findCollectionBySearchHelper(eq(Award.class), any(Map.class), eq(true))).thenReturn(expected);

        service.setLookupService(lookupService);
        service.lookupAwards(fieldValues, true);

        assertEquals("<=12/31/2020", fieldValues.get("awardBeginningDate"));
    }

    @Test
    public void testLookupAwards_beginningDateLowerBoundOnly() {
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put("rangeLowerBoundKeyPrefix_awardBeginningDate", "01/01/2020");

        List<Award> expected = new ArrayList<>();
        when(lookupService.findCollectionBySearchHelper(eq(Award.class), any(Map.class), eq(true))).thenReturn(expected);

        service.setLookupService(lookupService);
        service.lookupAwards(fieldValues, true);

        assertEquals(">=01/01/2020", fieldValues.get("awardBeginningDate"));
    }
}
