package org.kuali.kfs.vnd.document.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.vnd.businessobject.VendorDetail;
import org.kuali.kfs.vnd.dataaccess.VendorDao;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.NoteService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class VendorServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;
    @Mock
    private DocumentService documentService;
    @Mock
    private DateTimeService dateTimeService;
    @Mock
    private VendorDao vendorDao;
    @Mock
    private NoteService noteService;

    @InjectMocks
    private VendorServiceImpl vendorService;

    @Test
    void getVendorDetail_blankVendorNumber_returnsNull() {
        VendorDetail result = vendorService.getVendorDetail("");
        assertThat(result).isNull();
    }

    @Test
    void getVendorDetail_nullVendorNumber_returnsNull() {
        VendorDetail result = vendorService.getVendorDetail((String) null);
        assertThat(result).isNull();
    }

    @Test
    void getVendorDetail_noDash_returnsNull() {
        VendorDetail result = vendorService.getVendorDetail("12345");
        assertThat(result).isNull();
    }

    @Test
    void getVendorDetail_invalidNumber_returnsNull() {
        VendorDetail result = vendorService.getVendorDetail("abc-def");
        assertThat(result).isNull();
    }

    @Test
    void getVendorDetail_validFormat_delegatesToBOS() {
        VendorDetail expected = new VendorDetail();
        HashMap<String, Integer> keys = new HashMap<String, Integer>();
        keys.put("vendorHeaderGeneratedIdentifier", Integer.valueOf(1000));
        keys.put("vendorDetailAssignedIdentifier", Integer.valueOf(0));
        when(businessObjectService.findByPrimaryKey(VendorDetail.class, keys)).thenReturn(expected);

        VendorDetail result = vendorService.getVendorDetail("1000-0");
        assertThat(result).isSameAs(expected);
    }

    @Test
    void getByVendorNumber_delegatesToGetVendorDetail() {
        VendorDetail result = vendorService.getByVendorNumber("");
        assertThat(result).isNull();
    }
}
