package org.kuali.kfs.module.ar.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

class CollectionEventTest extends KfsUnitTestBase {

    private CollectionEvent collectionEvent;

    @BeforeEach
    void setUp() {
        collectionEvent = new CollectionEvent();
    }

    @Test
    void testId() {
        collectionEvent.setId(1L);
        assertThat(collectionEvent.getId()).isEqualTo(1L);
    }

    @Test
    void testCollectionEventCode() {
        collectionEvent.setCollectionEventCode("CALL");
        assertThat(collectionEvent.getCollectionEventCode()).isEqualTo("CALL");
    }

    @Test
    void testInvoiceNumber() {
        collectionEvent.setInvoiceNumber("INV001");
        assertThat(collectionEvent.getInvoiceNumber()).isEqualTo("INV001");
    }

    @Test
    void testActivityCode() {
        collectionEvent.setActivityCode("PHONE");
        assertThat(collectionEvent.getActivityCode()).isEqualTo("PHONE");
    }

    @Test
    void testActivityDate() {
        Date date = Date.valueOf("2024-04-15");
        collectionEvent.setActivityDate(date);
        assertThat(collectionEvent.getActivityDate()).isEqualTo(date);
    }

    @Test
    void testActivityText() {
        collectionEvent.setActivityText("Called customer regarding overdue invoice");
        assertThat(collectionEvent.getActivityText()).isEqualTo("Called customer regarding overdue invoice");
    }

    @Test
    void testFollowupDate() {
        Date date = Date.valueOf("2024-04-30");
        collectionEvent.setFollowupDate(date);
        assertThat(collectionEvent.getFollowupDate()).isEqualTo(date);
    }

    @Test
    void testCompletedDate() {
        Date date = Date.valueOf("2024-04-20");
        collectionEvent.setCompletedDate(date);
        assertThat(collectionEvent.getCompletedDate()).isEqualTo(date);
    }

    @Test
    void testUserPrincipalId() {
        collectionEvent.setUserPrincipalId("admin");
        assertThat(collectionEvent.getUserPrincipalId()).isEqualTo("admin");
    }
}
