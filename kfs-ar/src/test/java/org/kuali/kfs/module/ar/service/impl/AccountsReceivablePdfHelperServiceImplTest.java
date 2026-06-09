package org.kuali.kfs.module.ar.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.InjectMocks;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountsReceivablePdfHelperServiceImplTest extends KfsUnitTestBase {

    @InjectMocks
    private AccountsReceivablePdfHelperServiceImpl service;

    @Test
    void buildPdfOutputStream_shouldThrowForInvalidContent() {
        assertThrows(Exception.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() throws Throwable {
                service.buildPdfOutputStream(new byte[]{1, 2, 3});
            }
        });
    }

    @Test
    void serviceInitialization_shouldCreateInstance() {
        assertThat(service).isNotNull();
    }
}
