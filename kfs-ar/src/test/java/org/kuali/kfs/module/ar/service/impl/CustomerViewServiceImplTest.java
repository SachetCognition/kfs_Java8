package org.kuali.kfs.module.ar.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.integration.ar.AccountsReceivableModuleBillingService;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.kns.web.ui.Section;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class CustomerViewServiceImplTest extends KfsUnitTestBase {

    @Mock private AccountsReceivableModuleBillingService accountsReceivableModuleBillingService;

    @InjectMocks
    private CustomerViewServiceImpl service;

    @Test
    void getSections_shouldReturnAllSectionsWhenBillingActive() {
        when(accountsReceivableModuleBillingService.isContractsGrantsBillingEnhancementActive()).thenReturn(true);

        List<Section> sections = new ArrayList<Section>();
        sections.add(new Section());
        sections.add(new Section());

        List result = service.getSections(sections);
        assertThat(result).hasSize(2);
    }

    @Test
    void getSections_shouldReturnEmptyListForEmptySections() {
        when(accountsReceivableModuleBillingService.isContractsGrantsBillingEnhancementActive()).thenReturn(true);

        List result = service.getSections(new ArrayList<Section>());
        assertThat(result).isEmpty();
    }
}
