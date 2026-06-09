package org.kuali.kfs.module.tem.document.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.businessobject.AccountingDocumentRelationship;
import org.kuali.kfs.module.tem.dataaccess.AccountingDocumentRelationshipDao;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.principal.Principal;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class AccountingDocumentRelationshipServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private AccountingDocumentRelationshipServiceImpl service;

    @Mock
    private AccountingDocumentRelationshipDao accountingDocumentRelationshipDao;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private IdentityService identityService;

    @Test
    void testGetRelatedDocumentNumbers_withRelationships() {
        String docNumber = "DOC001";
        AccountingDocumentRelationship adr = new AccountingDocumentRelationship();
        adr.setDocumentNumber(docNumber);
        adr.setRelDocumentNumber("DOC002");

        List<AccountingDocumentRelationship> adrList = new ArrayList();
        adrList.add(adr);

        when(accountingDocumentRelationshipDao.findAccountingDocumentRelationshipByDocumentNumber(docNumber))
                .thenReturn(adrList);

        Set<String> result = service.getRelatedDocumentNumbers(docNumber);

        assertTrue(result.contains("DOC002"));
        assertFalse(result.contains(docNumber));
    }

    @Test
    void testGetRelatedDocumentNumbers_parentRelationship() {
        String docNumber = "DOC002";
        AccountingDocumentRelationship adr = new AccountingDocumentRelationship();
        adr.setDocumentNumber("DOC001");
        adr.setRelDocumentNumber(docNumber);

        List<AccountingDocumentRelationship> adrList = new ArrayList();
        adrList.add(adr);

        when(accountingDocumentRelationshipDao.findAccountingDocumentRelationshipByDocumentNumber(docNumber))
                .thenReturn(adrList);

        Set<String> result = service.getRelatedDocumentNumbers(docNumber);

        assertTrue(result.contains("DOC001"));
    }

    @Test
    void testGetRelatedDocumentNumbers_emptyList() {
        when(accountingDocumentRelationshipDao.findAccountingDocumentRelationshipByDocumentNumber("DOC001"))
                .thenReturn(new java.util.ArrayList());

        Set<String> result = service.getRelatedDocumentNumbers("DOC001");

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetRelatedDocumentNumbers_nullList() {
        when(accountingDocumentRelationshipDao.findAccountingDocumentRelationshipByDocumentNumber("DOC001"))
                .thenReturn(null);

        Set<String> result = service.getRelatedDocumentNumbers("DOC001");

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetRootDocumentNumber_noParent() {
        AccountingDocumentRelationship criteria = new AccountingDocumentRelationship(null, "DOC001");
        when(accountingDocumentRelationshipDao.findAccountingDocumentRelationship(any(AccountingDocumentRelationship.class)))
                .thenReturn(new java.util.ArrayList());

        String result = service.getRootDocumentNumber("DOC001");

        assertEquals("DOC001", result);
    }

    @Test
    void testSave_delegatesToDao() {
        AccountingDocumentRelationship adr = new AccountingDocumentRelationship();
        adr.setDocumentNumber("DOC001");
        adr.setRelDocumentNumber("DOC002");
        adr.setPrincipalId("EXISTING_USER");

        service.save(adr);

        verify(accountingDocumentRelationshipDao).save(adr);
        assertEquals("EXISTING_USER", adr.getPrincipalId());
    }

    @Test
    void testSave_keepsPrincipalIdWhenSet() {
        AccountingDocumentRelationship adr = new AccountingDocumentRelationship();
        adr.setDocumentNumber("DOC001");
        adr.setRelDocumentNumber("DOC002");
        adr.setPrincipalId("USER001");

        service.save(adr);

        assertEquals("USER001", adr.getPrincipalId());
        verify(accountingDocumentRelationshipDao).save(adr);
        verifyNoInteractions(identityService);
    }

    @Test
    void testSaveList() {
        AccountingDocumentRelationship adr1 = new AccountingDocumentRelationship();
        adr1.setDocumentNumber("DOC001");
        adr1.setRelDocumentNumber("DOC002");
        adr1.setPrincipalId("USER001");

        AccountingDocumentRelationship adr2 = new AccountingDocumentRelationship();
        adr2.setDocumentNumber("DOC003");
        adr2.setRelDocumentNumber("DOC004");
        adr2.setPrincipalId("USER002");

        List<AccountingDocumentRelationship> list = new ArrayList();
        list.add(adr1);
        list.add(adr2);

        service.save(list);

        verify(accountingDocumentRelationshipDao, times(2)).save(any(AccountingDocumentRelationship.class));
    }

    @Test
    void testHuntForRelatedDocumentNumbersWithDocumentType_found() {
        String docNumber = "DOC001";
        AccountingDocumentRelationship adr = new AccountingDocumentRelationship();
        adr.setDocumentNumber(docNumber);
        adr.setRelDocumentNumber("DOC002");
        adr.setDescription("Created TA document DOC002");

        List<AccountingDocumentRelationship> adrList = new ArrayList();
        adrList.add(adr);

        when(accountingDocumentRelationshipDao.findAccountingDocumentRelationshipByDocumentNumber(docNumber))
                .thenReturn(adrList);

        Set<String> result = service.huntForRelatedDocumentNumbersWithDocumentType(docNumber, "TA");

        assertTrue(result.contains("DOC002"));
    }

    @Test
    void testHuntForRelatedDocumentNumbersWithDocumentType_notFound() {
        String docNumber = "DOC001";
        AccountingDocumentRelationship adr = new AccountingDocumentRelationship();
        adr.setDocumentNumber(docNumber);
        adr.setRelDocumentNumber("DOC002");
        adr.setDescription("Created TR document DOC002");

        List<AccountingDocumentRelationship> adrList = new ArrayList();
        adrList.add(adr);

        when(accountingDocumentRelationshipDao.findAccountingDocumentRelationshipByDocumentNumber(docNumber))
                .thenReturn(adrList);

        Set<String> result = service.huntForRelatedDocumentNumbersWithDocumentType(docNumber, "TA");

        assertTrue(result.isEmpty());
    }
}
