package org.kuali.kfs.module.cg.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.cg.businessobject.Award;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AwardServiceImplTest extends KfsUnitTestBase {

    @Mock
    private BusinessObjectService businessObjectService;

    private AwardServiceImpl awardService;

    @BeforeEach
    void setUp() {
        awardService = new AwardServiceImpl();
        awardService.setBusinessObjectService(businessObjectService);
    }

    @Test
    void testGetByPrimaryIdReturnsAward() {
        Long proposalNumber = 12345L;
        Award expected = new Award();
        expected.setProposalNumber(proposalNumber);

        Map<String, Object> primaryKeys = new HashMap<>();
        primaryKeys.put(KFSPropertyConstants.PROPOSAL_NUMBER, proposalNumber);

        when(businessObjectService.findByPrimaryKey(eq(Award.class), eq(primaryKeys)))
                .thenReturn(expected);

        Award result = awardService.getByPrimaryId(proposalNumber);

        assertThat(result).isSameAs(expected);
        verify(businessObjectService).findByPrimaryKey(eq(Award.class), eq(primaryKeys));
    }

    @Test
    void testGetByPrimaryIdReturnsNullWhenNotFound() {
        Long proposalNumber = 99999L;

        Map<String, Object> primaryKeys = new HashMap<>();
        primaryKeys.put(KFSPropertyConstants.PROPOSAL_NUMBER, proposalNumber);

        when(businessObjectService.findByPrimaryKey(eq(Award.class), eq(primaryKeys)))
                .thenReturn(null);

        Award result = awardService.getByPrimaryId(proposalNumber);

        assertThat(result).isNull();
    }
}
