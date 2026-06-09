package org.kuali.kfs.sys.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.businessobject.OriginationCode;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.dataaccess.OriginationCodeDao;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class OriginationCodeServiceImplTest extends KfsUnitTestBase {

    @Mock
    private OriginationCodeDao originationCodeDao;

    @InjectMocks
    private OriginationCodeServiceImpl originationCodeService;

    @Test
    void getByPrimaryKey_returnsOriginationCode() {
        OriginationCode expected = new OriginationCode();
        when(originationCodeDao.findByCode("01")).thenReturn(expected);

        OriginationCode result = originationCodeService.getByPrimaryKey("01");
        assertThat(result).isSameAs(expected);
    }

    @Test
    void getByPrimaryKey_notFound_returnsNull() {
        when(originationCodeDao.findByCode("XX")).thenReturn(null);

        OriginationCode result = originationCodeService.getByPrimaryKey("XX");
        assertThat(result).isNull();
    }
}
