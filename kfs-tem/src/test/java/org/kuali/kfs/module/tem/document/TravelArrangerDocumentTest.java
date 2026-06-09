package org.kuali.kfs.module.tem.document;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TravelArrangerDocument")
class TravelArrangerDocumentTest extends KfsUnitTestBase {

    private TravelArrangerDocument document;

    @BeforeEach
    void setUp() {
        document = Mockito.mock(TravelArrangerDocument.class, Mockito.CALLS_REAL_METHODS);
    }

    @Test
    @DisplayName("should set and get profileId")
    void testProfileId() {
        document.setProfileId(1001);
        assertThat(document.getProfileId()).isEqualTo(1001);
    }

    @Test
    @DisplayName("should set and get arrangerId")
    void testArrangerId() {
        document.setArrangerId("ARR001");
        assertThat(document.getArrangerId()).isEqualTo("ARR001");
    }

    @Test
    @DisplayName("should return empty when profile is null")
    void testTravelerNameReturnsEmptyWhenNoProfile() {
        assertThat(document.getTravelerName()).isEmpty();
    }

    @Test
    @DisplayName("should set and get taInd")
    void testTaInd() {
        document.setTaInd(true);
        assertThat(document.getTaInd()).isTrue();
    }

    @Test
    @DisplayName("should set and get trInd")
    void testTrInd() {
        document.setTrInd(true);
        assertThat(document.getTrInd()).isTrue();
    }

    @Test
    @DisplayName("should set and get primaryInd")
    void testPrimaryInd() {
        document.setPrimaryInd(true);
        assertThat(document.getPrimaryInd()).isTrue();
    }
}
