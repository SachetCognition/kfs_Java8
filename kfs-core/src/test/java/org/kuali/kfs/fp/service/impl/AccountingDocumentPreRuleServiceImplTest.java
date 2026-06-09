package org.kuali.kfs.fp.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;

import static org.assertj.core.api.Assertions.assertThat;

class AccountingDocumentPreRuleServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private AccountingDocumentPreRuleServiceImpl accountingDocumentPreRuleService;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(accountingDocumentPreRuleService).isNotNull();
    }
}
