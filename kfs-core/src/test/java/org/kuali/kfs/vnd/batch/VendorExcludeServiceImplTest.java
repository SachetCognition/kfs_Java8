package org.kuali.kfs.vnd.batch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.batch.BatchInputFileType;
import org.kuali.kfs.sys.batch.service.BatchInputFileService;
import org.kuali.kfs.vnd.batch.dataaccess.DebarredVendorDao;
import org.kuali.kfs.vnd.batch.dataaccess.DebarredVendorMatchDao;
import org.kuali.kfs.vnd.batch.service.impl.VendorExcludeServiceImpl;
import org.kuali.kfs.vnd.businessobject.DebarredVendorDetail;
import org.kuali.kfs.vnd.businessobject.DebarredVendorMatch;
import org.kuali.kfs.vnd.businessobject.VendorDetail;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class VendorExcludeServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BatchInputFileService batchInputFileService;

    @Mock
    private BusinessObjectService businessObjectService;

    @Mock
    private DateTimeService dateTimeService;

    @Mock
    private BatchInputFileType batchInputFileType;

    @Mock
    private DebarredVendorDao debarredVendorDao;

    @Mock
    private DebarredVendorMatchDao debarredVendorMatchDao;

    private VendorExcludeServiceImpl vendorExcludeService;

    @BeforeEach
    void setUp() {
        vendorExcludeService = new VendorExcludeServiceImpl();
        vendorExcludeService.setBatchInputFileService(batchInputFileService);
        vendorExcludeService.setBusinessObjectService(businessObjectService);
        vendorExcludeService.setDateTimeService(dateTimeService);
        vendorExcludeService.setBatchInputFileType(batchInputFileType);
        vendorExcludeService.setDebarredVendorDao(debarredVendorDao);
        vendorExcludeService.setDebarredVendorMatchDao(debarredVendorMatchDao);
    }

    @Test
    void loadEplsFileReturnsTrueWhenNoFiles() {
        when(batchInputFileService.listInputFileNamesWithDoneFile(batchInputFileType))
                .thenReturn(Collections.emptyList());

        boolean result = vendorExcludeService.loadEplsFile();
        assertThat(result).isTrue();
    }

    @Test
    void matchVendorsDelegatesToDaoAndSaves() {
        List<DebarredVendorMatch> matches = new ArrayList<>();
        matches.add(new DebarredVendorMatch());
        when(debarredVendorDao.match()).thenReturn(matches);

        boolean result = vendorExcludeService.matchVendors();

        assertThat(result).isTrue();
        verify(debarredVendorDao).match();
        verify(businessObjectService).save(matches);
    }

    @Test
    void purgeOldVendorRecordsDelegatesToBusinessObjectService() {
        vendorExcludeService.purgeOldVendorRecords();
        verify(businessObjectService).deleteMatching(eq(DebarredVendorDetail.class), any(HashMap.class));
    }

    @Test
    void getDebarredVendorsUnmatchedDelegatesToDao() {
        List<VendorDetail> vendors = Arrays.asList(new VendorDetail());
        when(debarredVendorMatchDao.getDebarredVendorsUnmatched()).thenReturn(vendors);

        List<VendorDetail> result = vendorExcludeService.getDebarredVendorsUnmatched();
        assertThat(result).hasSize(1);
        verify(debarredVendorMatchDao).getDebarredVendorsUnmatched();
    }

    @Test
    void gettersReturnSetValues() {
        assertThat(vendorExcludeService.getBatchInputFileService()).isSameAs(batchInputFileService);
        assertThat(vendorExcludeService.getBusinessObjectService()).isSameAs(businessObjectService);
        assertThat(vendorExcludeService.getDateTimeService()).isSameAs(dateTimeService);
        assertThat(vendorExcludeService.getBatchInputFileType()).isSameAs(batchInputFileType);
        assertThat(vendorExcludeService.getDebarredVendorDao()).isSameAs(debarredVendorDao);
        assertThat(vendorExcludeService.getDebarredVendorMatchDao()).isSameAs(debarredVendorMatchDao);
    }
}
