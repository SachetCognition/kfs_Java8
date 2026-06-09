package org.kuali.kfs.pdp.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.core.api.util.type.KualiInteger;

import static org.assertj.core.api.Assertions.assertThat;

class FormatProcessSummaryTest extends KfsUnitTestBase {

    private FormatProcessSummary summary;

    @BeforeEach
    void setUp() {
        summary = new FormatProcessSummary();
    }

    @Test
    void testDefaultConstructor() {
        assertThat(summary.getProcessSummaryList()).isNotNull().isEmpty();
        assertThat(summary.getTotalCount()).isEqualTo(KualiInteger.ZERO);
        assertThat(summary.getTotalAmount()).isEqualTo(KualiDecimal.ZERO);
    }

    @Test
    void testGettersAndSetters() {
        summary.setTotalCount(new KualiInteger(10));
        summary.setTotalAmount(new KualiDecimal(5000));
        summary.setProcessId(new KualiInteger(42));

        assertThat(summary.getTotalCount()).isEqualTo(new KualiInteger(10));
        assertThat(summary.getTotalAmount()).isEqualTo(new KualiDecimal(5000));
        assertThat(summary.getProcessId()).isEqualTo(new KualiInteger(42));
    }
}
