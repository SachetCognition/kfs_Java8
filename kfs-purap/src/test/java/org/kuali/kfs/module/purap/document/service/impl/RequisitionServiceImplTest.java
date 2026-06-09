package org.kuali.kfs.module.purap.document.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.Mock;
import org.kuali.kfs.integration.cab.CapitalAssetBuilderModuleService;
import org.kuali.kfs.module.purap.PurapConstants;
import org.kuali.kfs.module.purap.businessobject.PurApItem;
import org.kuali.kfs.module.purap.businessobject.PurchasingCapitalAssetItem;
import org.kuali.kfs.module.purap.businessobject.RequisitionItem;
import org.kuali.kfs.module.purap.document.RequisitionDocument;
import org.kuali.kfs.module.purap.document.dataaccess.RequisitionDao;
import org.kuali.kfs.module.purap.document.service.PurapService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.service.PostalCodeValidationService;
import org.kuali.kfs.sys.service.UniversityDateService;
import org.kuali.kfs.vnd.businessobject.VendorDetail;
import org.kuali.kfs.vnd.document.service.VendorService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.krad.bo.Note;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KualiRuleService;

@MockitoSettings(strictness = Strictness.LENIENT)
public class RequisitionServiceImplTest extends KfsUnitTestBase {

    @Mock private BusinessObjectService businessObjectService;
    @Mock private CapitalAssetBuilderModuleService capitalAssetBuilderModuleService;
    @Mock private DateTimeService dateTimeService;
    @Mock private DocumentService documentService;
    @Mock private KualiRuleService ruleService;
    @Mock private ConfigurationService kualiConfigurationService;
    @Mock private ParameterService parameterService;
    @Mock private PersonService personService;
    @Mock private PostalCodeValidationService postalCodeValidationService;
    @Mock private PurapService purapService;
    @Mock private RequisitionDao requisitionDao;
    @Mock private UniversityDateService universityDateService;
    @Mock private VendorService vendorService;
    @Mock private RoleService roleService;

    @InjectMocks
    private RequisitionServiceImpl requisitionService;

    @Test
    public void testGetRequisitionById_returnsDocument() throws Exception {
        Integer reqId = 100;
        String docNumber = "1234";
        RequisitionDocument mockDoc = mock(RequisitionDocument.class);

        when(requisitionDao.getDocumentNumberForRequisitionId(reqId)).thenReturn(docNumber);
        when(documentService.getByDocumentHeaderId(docNumber)).thenReturn(mockDoc);

        RequisitionDocument result = requisitionService.getRequisitionById(reqId);

        assertThat(result).isEqualTo(mockDoc);
    }

    @Test
    public void testGetRequisitionById_nullDocumentNumber_returnsNull() {
        when(requisitionDao.getDocumentNumberForRequisitionId(200)).thenReturn(null);

        RequisitionDocument result = requisitionService.getRequisitionById(200);

        assertThat(result).isNull();
    }

    @Test
    public void testGetRequisitionById_workflowException_throwsRuntime() throws Exception {
        Integer reqId = 300;
        String docNumber = "5678";

        when(requisitionDao.getDocumentNumberForRequisitionId(reqId)).thenReturn(docNumber);
        when(documentService.getByDocumentHeaderId(docNumber)).thenThrow(new WorkflowException("test"));

        try {
            requisitionService.getRequisitionById(reqId);
            org.junit.jupiter.api.Assertions.fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).contains("Error getting requisition document");
        }
    }

    @Test
    public void testIsAutomaticPurchaseOrderAllowed_apoLimitNull_returnsFalse() throws Exception {
        RequisitionDocument req = mock(RequisitionDocument.class);
        when(req.getRequisitionSourceCode()).thenReturn(PurapConstants.RequisitionSources.STANDARD_ORDER);
        when(req.getTotalDollarAmount()).thenReturn(new KualiDecimal(100));
        when(req.getVendorContractGeneratedIdentifier()).thenReturn(null);
        when(req.getChartOfAccountsCode()).thenReturn("BL");
        when(req.getOrganizationCode()).thenReturn("ACCT");
        when(purapService.getApoLimit(null, "BL", "ACCT")).thenReturn(null);
        when(documentService.createNoteFromDocument(any(org.kuali.rice.krad.document.Document.class), anyString())).thenReturn(mock(Note.class));

        boolean result = requisitionService.isAutomaticPurchaseOrderAllowed(req);

        assertThat(result).isFalse();
    }

    @Test
    public void testIsAutomaticPurchaseOrderAllowed_totalExceedsLimit_returnsFalse() throws Exception {
        RequisitionDocument req = mock(RequisitionDocument.class);
        when(req.getRequisitionSourceCode()).thenReturn(PurapConstants.RequisitionSources.STANDARD_ORDER);
        when(req.getTotalDollarAmount()).thenReturn(new KualiDecimal(5000));
        when(req.getVendorContractGeneratedIdentifier()).thenReturn(null);
        when(req.getChartOfAccountsCode()).thenReturn("BL");
        when(req.getOrganizationCode()).thenReturn("ACCT");
        when(purapService.getApoLimit(null, "BL", "ACCT")).thenReturn(new KualiDecimal(1000));
        when(documentService.createNoteFromDocument(any(org.kuali.rice.krad.document.Document.class), anyString())).thenReturn(mock(Note.class));

        boolean result = requisitionService.isAutomaticPurchaseOrderAllowed(req);

        assertThat(result).isFalse();
    }

    @Test
    public void testIsAutomaticPurchaseOrderAllowed_totalZeroOrNeg_returnsFalse() throws Exception {
        RequisitionDocument req = mock(RequisitionDocument.class);
        when(req.getRequisitionSourceCode()).thenReturn(PurapConstants.RequisitionSources.STANDARD_ORDER);
        when(req.getTotalDollarAmount()).thenReturn(KualiDecimal.ZERO);
        when(req.getVendorContractGeneratedIdentifier()).thenReturn(null);
        when(req.getChartOfAccountsCode()).thenReturn("BL");
        when(req.getOrganizationCode()).thenReturn("ACCT");
        when(purapService.getApoLimit(null, "BL", "ACCT")).thenReturn(new KualiDecimal(1000));
        when(documentService.createNoteFromDocument(any(org.kuali.rice.krad.document.Document.class), anyString())).thenReturn(mock(Note.class));

        boolean result = requisitionService.isAutomaticPurchaseOrderAllowed(req);

        assertThat(result).isFalse();
    }

    @Test
    public void testIsAutomaticPurchaseOrderAllowed_noVendor_returnsFalse() throws Exception {
        RequisitionDocument req = mock(RequisitionDocument.class);
        when(req.getRequisitionSourceCode()).thenReturn(PurapConstants.RequisitionSources.STANDARD_ORDER);
        when(req.getTotalDollarAmount()).thenReturn(new KualiDecimal(500));
        when(req.getVendorContractGeneratedIdentifier()).thenReturn(null);
        when(req.getChartOfAccountsCode()).thenReturn("BL");
        when(req.getOrganizationCode()).thenReturn("ACCT");
        when(purapService.getApoLimit(null, "BL", "ACCT")).thenReturn(new KualiDecimal(1000));
        when(req.getVendorHeaderGeneratedIdentifier()).thenReturn(null);
        when(documentService.createNoteFromDocument(any(org.kuali.rice.krad.document.Document.class), anyString())).thenReturn(mock(Note.class));

        boolean result = requisitionService.isAutomaticPurchaseOrderAllowed(req);

        assertThat(result).isFalse();
    }

    @Test
    public void testCreateCamsItem_returnsRequisitionCapitalAssetItem() {
        RequisitionDocument reqDoc = mock(RequisitionDocument.class);
        PurApItem purapItem = mock(PurApItem.class);
        when(purapItem.getItemIdentifier()).thenReturn(42);
        when(reqDoc.getCapitalAssetSystemTypeCode()).thenReturn(PurapConstants.CapitalAssetTabStrings.INDIVIDUAL_ASSETS);

        PurchasingCapitalAssetItem result = requisitionService.createCamsItem(reqDoc, purapItem);

        assertThat(result).isNotNull();
        assertThat(result.getItemIdentifier()).isEqualTo(42);
    }

    @Test
    public void testCreateCapitalAssetSystem_returnsRequisitionCapitalAssetSystem() {
        org.kuali.kfs.integration.purap.CapitalAssetSystem result = requisitionService.createCapitalAssetSystem();

        assertThat(result).isNotNull();
    }

    @Test
    public void testGetRequisitionsAwaitingContractManagerAssignment_returnsList() {
        List<RequisitionDocument> docs = new ArrayList<RequisitionDocument>();
        docs.add(mock(RequisitionDocument.class));
        when(requisitionDao.getDocumentsAwaitingContractManagerAssignment()).thenReturn(docs);

        List<RequisitionDocument> result = requisitionService.getRequisitionsAwaitingContractManagerAssignment();

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
    }

    @Test
    public void testGetCountOfRequisitionsAwaitingContractManagerAssignment() {
        List<RequisitionDocument> docs = new ArrayList<RequisitionDocument>();
        docs.add(mock(RequisitionDocument.class));
        docs.add(mock(RequisitionDocument.class));
        docs.add(mock(RequisitionDocument.class));
        when(requisitionDao.getDocumentsAwaitingContractManagerAssignment()).thenReturn(docs);

        int count = requisitionService.getCountOfRequisitionsAwaitingContractManagerAssignment();

        assertThat(count).isEqualTo(3);
    }
}
