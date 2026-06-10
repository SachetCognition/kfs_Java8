package org.kuali.kfs.gl.batch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.gl.businessobject.CollectorDetail;
import org.kuali.kfs.gl.businessobject.OriginEntryFull;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.mockito.Mock;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CollectorBatchTest extends KfsUnitTestBase {

    private CollectorBatch batch;

    @Mock
    private CollectorDetail mockDetail;

    @BeforeEach
    void setUp() {
        batch = new CollectorBatch();
    }

    @Test
    void defaultConstructor_initializesEmptyLists() {
        assertThat(batch.getOriginEntries()).isEmpty();
        assertThat(batch.getCollectorDetails()).isEmpty();
        assertThat(batch.getTotalRecords()).isZero();
    }

    @Test
    void setAndGetUniversityFiscalYear() {
        batch.setUniversityFiscalYear("2024");
        assertThat(batch.getUniversityFiscalYear()).isEqualTo("2024");
    }

    @Test
    void setAndGetChartOfAccountsCode() {
        batch.setChartOfAccountsCode("BL");
        assertThat(batch.getChartOfAccountsCode()).isEqualTo("BL");
    }

    @Test
    void setAndGetOrganizationCode() {
        batch.setOrganizationCode("ACCT");
        assertThat(batch.getOrganizationCode()).isEqualTo("ACCT");
    }

    @Test
    void setAndGetBatchSequenceNumber() {
        batch.setBatchSequenceNumber(5);
        assertThat(batch.getBatchSequenceNumber()).isEqualTo(5);
    }

    @Test
    void setTotalAmount_fromKualiDecimal() {
        batch.setTotalAmount(new KualiDecimal(1000));
        assertThat(batch.getTotalAmount()).isEqualTo(new KualiDecimal(1000));
    }

    @Test
    void setTotalAmount_fromString() {
        batch.setTotalAmount("2500.50");
        assertThat(batch.getTotalAmount()).isEqualTo(new KualiDecimal("2500.50"));
    }

    @Test
    void clearTotalAmount_setsToNull() {
        batch.setTotalAmount(new KualiDecimal(100));
        batch.clearTotalAmount();
        assertThat(batch.getTotalAmount()).isNull();
    }

    @Test
    void setAndGetTotalRecords() {
        batch.setTotalRecords(42);
        assertThat(batch.getTotalRecords()).isEqualTo(42);
    }

    @Test
    void setAndGetTransmissionDate() {
        Date date = Date.valueOf("2024-01-15");
        batch.setTransmissionDate(date);
        assertThat(batch.getTransmissionDate()).isEqualTo(date);
    }

    @Test
    void setAndGetRecordType() {
        batch.setRecordType("HD");
        assertThat(batch.getRecordType()).isEqualTo("HD");
    }

    @Test
    void setAndGetEmailAddress() {
        batch.setEmailAddress("test@example.com");
        assertThat(batch.getEmailAddress()).isEqualTo("test@example.com");
    }

    @Test
    void setAndGetPersonUserID() {
        batch.setPersonUserID("testuser");
        assertThat(batch.getPersonUserID()).isEqualTo("testuser");
    }

    @Test
    void addOriginEntry_addsToList() {
        OriginEntryFull entry = new OriginEntryFull();
        batch.addOriginEntry(entry);

        assertThat(batch.getOriginEntries()).hasSize(1);
        assertThat(batch.getOriginEntries().get(0)).isSameAs(entry);
    }

    @Test
    void addCollectorDetail_addsToList() {
        batch.addCollectorDetail(mockDetail);

        assertThat(batch.getCollectorDetails()).hasSize(1);
        assertThat(batch.getCollectorDetails().get(0)).isSameAs(mockDetail);
    }

    @Test
    void addMultipleOriginEntries_maintainsOrder() {
        OriginEntryFull entry1 = new OriginEntryFull();
        OriginEntryFull entry2 = new OriginEntryFull();
        OriginEntryFull entry3 = new OriginEntryFull();

        batch.addOriginEntry(entry1);
        batch.addOriginEntry(entry2);
        batch.addOriginEntry(entry3);

        assertThat(batch.getOriginEntries()).hasSize(3);
        assertThat(batch.getOriginEntries()).containsExactly(entry1, entry2, entry3);
    }

    @Test
    void setOriginEntries_replacesExistingList() {
        batch.addOriginEntry(new OriginEntryFull());

        List<OriginEntryFull> newList = new ArrayList<>();
        newList.add(new OriginEntryFull());
        newList.add(new OriginEntryFull());
        batch.setOriginEntries(newList);

        assertThat(batch.getOriginEntries()).hasSize(2);
    }

    @Test
    void setCollectorDetails_replacesExistingList() {
        List<CollectorDetail> newList = new ArrayList<>();
        newList.add(mockDetail);
        batch.setCollectorDetails(newList);

        assertThat(batch.getCollectorDetails()).hasSize(1);
    }

    @Test
    void setAndGetFirstEmptyField() {
        batch.setFirstEmptyField("field1");
        assertThat(batch.getFirstEmptyField()).isEqualTo("field1");
    }

    @Test
    void setAndGetSecondEmptyField() {
        batch.setSecondEmptyField("field2");
        assertThat(batch.getSecondEmptyField()).isEqualTo("field2");
    }
}
