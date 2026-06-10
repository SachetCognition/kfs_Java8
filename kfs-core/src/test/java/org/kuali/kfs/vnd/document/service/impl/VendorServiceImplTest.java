package org.kuali.kfs.vnd.document.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.vnd.businessobject.VendorAddress;
import org.kuali.kfs.vnd.businessobject.VendorContract;
import org.kuali.kfs.vnd.businessobject.VendorContractOrganization;
import org.kuali.kfs.vnd.businessobject.VendorDefaultAddress;
import org.kuali.kfs.vnd.businessobject.VendorDetail;
import org.kuali.kfs.vnd.dataaccess.VendorDao;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.NoteService;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class VendorServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private VendorDao vendorDao;

    @Mock
    private DateTimeService dateTimeService;

    @Mock
    private NoteService noteService;

    private VendorServiceImpl vendorService;

    @BeforeEach
    void setUp() {
        vendorService = new VendorServiceImpl();
        vendorService.setBusinessObjectService(businessObjectService);
        vendorService.setVendorDao(vendorDao);
        vendorService.setDateTimeService(dateTimeService);
        vendorService.setNoteService(noteService);
    }

    @Test
    void getVendorDetailByNumberReturnsVendor() {
        VendorDetail expected = new VendorDetail();
        expected.setVendorHeaderGeneratedIdentifier(1000);
        expected.setVendorDetailAssignedIdentifier(0);
        when(businessObjectService.findByPrimaryKey(eq(VendorDetail.class), any(Map.class))).thenReturn(expected);

        VendorDetail result = vendorService.getVendorDetail("1000-0");
        assertThat(result).isNotNull();
        assertThat(result.getVendorHeaderGeneratedIdentifier()).isEqualTo(1000);
    }

    @Test
    void getVendorDetailReturnsNullForBlank() {
        assertThat(vendorService.getVendorDetail("")).isNull();
        assertThat(vendorService.getVendorDetail("   ")).isNull();
    }

    @Test
    void getVendorDetailReturnsNullForNoDash() {
        assertThat(vendorService.getVendorDetail("12345")).isNull();
    }

    @Test
    void getVendorDetailReturnsNullForInvalidFormat() {
        assertThat(vendorService.getVendorDetail("abc-def")).isNull();
    }

    @Test
    void getVendorDetailReturnsNullForLeadingDash() {
        assertThat(vendorService.getVendorDetail("-123")).isNull();
    }

    @Test
    void getVendorDetailReturnsNullForTrailingDash() {
        assertThat(vendorService.getVendorDetail("123-")).isNull();
    }

    @Test
    void getVendorDetailByIdsCallsBusinessObjectService() {
        VendorDetail expected = new VendorDetail();
        when(businessObjectService.findByPrimaryKey(eq(VendorDetail.class), any(Map.class))).thenReturn(expected);

        VendorDetail result = vendorService.getVendorDetail(100, 0);
        assertThat(result).isSameAs(expected);
    }

    @Test
    void getByVendorNumberDelegatesToGetVendorDetail() {
        VendorDetail expected = new VendorDetail();
        when(businessObjectService.findByPrimaryKey(eq(VendorDetail.class), any(Map.class))).thenReturn(expected);

        VendorDetail result = vendorService.getByVendorNumber("100-0");
        assertThat(result).isSameAs(expected);
    }

    @Test
    void getApoLimitFromContractReturnsContractOrgLimit() {
        VendorContractOrganization contractOrg = new VendorContractOrganization();
        contractOrg.setVendorContractExcludeIndicator(false);
        contractOrg.setVendorContractPurchaseOrderLimitAmount(new KualiDecimal("5000.00"));
        when(businessObjectService.findByPrimaryKey(eq(VendorContractOrganization.class), any(Map.class)))
                .thenReturn(contractOrg);

        KualiDecimal result = vendorService.getApoLimitFromContract(1, "BL", "PSY");
        assertThat(result).isEqualTo(new KualiDecimal("5000.00"));
    }

    @Test
    void getApoLimitFromContractReturnsNullForExcludedOrg() {
        VendorContractOrganization contractOrg = new VendorContractOrganization();
        contractOrg.setVendorContractExcludeIndicator(true);
        when(businessObjectService.findByPrimaryKey(eq(VendorContractOrganization.class), any(Map.class)))
                .thenReturn(contractOrg);

        KualiDecimal result = vendorService.getApoLimitFromContract(1, "BL", "PSY");
        assertThat(result).isNull();
    }

    @Test
    void getApoLimitFromContractFallsBackToContractDefault() {
        when(businessObjectService.findByPrimaryKey(eq(VendorContractOrganization.class), any(Map.class)))
                .thenReturn(null);
        VendorContract contract = new VendorContract();
        contract.setOrganizationAutomaticPurchaseOrderLimit(new KualiDecimal("10000.00"));
        when(businessObjectService.findBySinglePrimaryKey(VendorContract.class, 1)).thenReturn(contract);

        KualiDecimal result = vendorService.getApoLimitFromContract(1, "BL", "PSY");
        assertThat(result).isEqualTo(new KualiDecimal("10000.00"));
    }

    @Test
    void getApoLimitFromContractReturnsNullWhenNoContract() {
        when(businessObjectService.findByPrimaryKey(eq(VendorContractOrganization.class), any(Map.class)))
                .thenReturn(null);
        when(businessObjectService.findBySinglePrimaryKey(VendorContract.class, 1)).thenReturn(null);

        KualiDecimal result = vendorService.getApoLimitFromContract(1, "BL", "PSY");
        assertThat(result).isNull();
    }

    @Test
    void getApoLimitFromContractReturnsNullWhenContractIdNull() {
        KualiDecimal result = vendorService.getApoLimitFromContract(null, null, null);
        assertThat(result).isNull();
    }

    @Test
    void getParentVendorReturnsParent() {
        VendorDetail parent = new VendorDetail();
        parent.setVendorParentIndicator(true);
        parent.setVendorHeaderGeneratedIdentifier(100);
        Collection<VendorDetail> vendors = Collections.singletonList(parent);
        when(businessObjectService.findMatching(eq(VendorDetail.class), any(Map.class))).thenReturn(vendors);

        VendorDetail result = vendorService.getParentVendor(100);
        assertThat(result).isSameAs(parent);
    }

    @Test
    void getParentVendorReturnsNullWhenNoVendors() {
        when(businessObjectService.findMatching(eq(VendorDetail.class), any(Map.class)))
                .thenReturn(Collections.emptyList());

        VendorDetail result = vendorService.getParentVendor(100);
        assertThat(result).isNull();
    }

    @Test
    void getParentVendorThrowsWhenVendorsExistButNoParent() {
        VendorDetail nonParent = new VendorDetail();
        nonParent.setVendorParentIndicator(false);
        when(businessObjectService.findMatching(eq(VendorDetail.class), any(Map.class)))
                .thenReturn(Collections.singletonList(nonParent));

        assertThatThrownBy(() -> vendorService.getParentVendor(100))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void getParentVendorThrowsWhenMultipleParents() {
        VendorDetail parent1 = new VendorDetail();
        parent1.setVendorParentIndicator(true);
        VendorDetail parent2 = new VendorDetail();
        parent2.setVendorParentIndicator(true);
        when(businessObjectService.findMatching(eq(VendorDetail.class), any(Map.class)))
                .thenReturn(Arrays.asList(parent1, parent2));

        assertThatThrownBy(() -> vendorService.getParentVendor(100))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void getVendorByDunsNumberReturnsVendor() {
        VendorDetail expected = new VendorDetail();
        expected.setVendorDunsNumber("123456789");
        when(businessObjectService.findMatching(eq(VendorDetail.class), any(Map.class)))
                .thenReturn(Collections.singletonList(expected));

        VendorDetail result = vendorService.getVendorByDunsNumber("123456789");
        assertThat(result).isSameAs(expected);
    }

    @Test
    void getVendorByDunsNumberReturnsNullWhenNotFound() {
        when(businessObjectService.findMatching(eq(VendorDetail.class), any(Map.class)))
                .thenReturn(Collections.emptyList());

        VendorDetail result = vendorService.getVendorByDunsNumber("000000000");
        assertThat(result).isNull();
    }

    @Test
    void getVendorDefaultAddressReturnsMatchingDefault() {
        VendorAddress addr1 = new VendorAddress();
        addr1.setVendorAddressTypeCode("PO");
        addr1.setVendorDefaultAddressIndicator(true);
        addr1.setActive(true);
        addr1.setVendorDefaultAddresses(new ArrayList<>());

        Collection<VendorAddress> addresses = Collections.singletonList(addr1);

        VendorAddress result = vendorService.getVendorDefaultAddress(addresses, "PO", "BL");
        assertThat(result).isSameAs(addr1);
    }

    @Test
    void getVendorDefaultAddressReturnsNullForWrongType() {
        VendorAddress addr1 = new VendorAddress();
        addr1.setVendorAddressTypeCode("RM");
        addr1.setVendorDefaultAddressIndicator(true);
        addr1.setActive(true);
        addr1.setVendorDefaultAddresses(new ArrayList<>());

        Collection<VendorAddress> addresses = Collections.singletonList(addr1);

        VendorAddress result = vendorService.getVendorDefaultAddress(addresses, "PO", "BL");
        assertThat(result).isNull();
    }

    @Test
    void getVendorDefaultAddressReturnsCampusSpecific() {
        VendorDefaultAddress campusDefault = new VendorDefaultAddress();
        campusDefault.setVendorCampusCode("BL");
        campusDefault.setActive(true);

        VendorAddress addr = new VendorAddress();
        addr.setVendorAddressTypeCode("PO");
        addr.setVendorDefaultAddressIndicator(true);
        addr.setActive(true);
        addr.setVendorDefaultAddresses(Arrays.asList(campusDefault));

        Collection<VendorAddress> addresses = Collections.singletonList(addr);

        VendorAddress result = vendorService.getVendorDefaultAddress(addresses, "PO", "BL");
        assertThat(result).isSameAs(addr);
    }

    @Test
    void getVendorDefaultAddressReturnsNullForEmptyCollection() {
        VendorAddress result = vendorService.getVendorDefaultAddress(Collections.emptyList(), "PO", "BL");
        assertThat(result).isNull();
    }

    @Test
    void saveVendorHeaderDelegatesToBusinessObjectService() {
        VendorDetail detail = new VendorDetail();
        vendorService.saveVendorHeader(detail);
        verify(businessObjectService).save(detail.getVendorHeader());
    }
}
