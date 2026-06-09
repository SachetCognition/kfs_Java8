package org.kuali.kfs.sys.service.impl;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.kfs.sys.mail.AttachmentMailer;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class AttachmentMailServiceImplTest extends KfsUnitTestBase {

    @Mock
    private AttachmentMailer attachmentMailer;

    @InjectMocks
    private AttachmentMailServiceImpl attachmentMailService;

    @Test
    void serviceInstantiatesCorrectly() {
        assertThat(attachmentMailService).isNotNull();
    }
}
