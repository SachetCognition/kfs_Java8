package org.kuali.kfs.module.tem.document;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.module.tem.businessobject.Attendee;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TravelEntertainmentDocument")
class TravelEntertainmentDocumentTest extends KfsUnitTestBase {

    private TravelEntertainmentDocument document;

    @BeforeEach
    void setUp() {
        document = Mockito.mock(TravelEntertainmentDocument.class, Mockito.CALLS_REAL_METHODS);
    }

    @Test
    @DisplayName("should set and get purpose code")
    void testPurposeCode() {
        document.setPurposeCode("BUS");
        assertThat(document.getPurposeCode()).isEqualTo("BUS");
    }

    @Test
    @DisplayName("should set and get attendees")
    void testAttendees() {
        List<Attendee> attendees = new ArrayList<>();
        Attendee attendee = new Attendee();
        attendee.setName("John");
        attendees.add(attendee);

        document.setAttendee(attendees);
        assertThat(document.getAttendee()).hasSize(1);
        assertThat(document.getAttendee().get(0).getName()).isEqualTo("John");
    }

    @Test
    @DisplayName("should set and get number of attendees")
    void testNumberOfAttendees() {
        document.setNumberOfAttendees(5);
        assertThat(document.getNumberOfAttendees()).isEqualTo(5);
    }

    @Test
    @DisplayName("should set and get host name")
    void testHostName() {
        document.setHostName("Jane Smith");
        assertThat(document.getHostName()).isEqualTo("Jane Smith");
    }

    @Test
    @DisplayName("should set and get event title")
    void testEventTitle() {
        document.setEventTitle("Annual Meeting");
        assertThat(document.getEventTitle()).isEqualTo("Annual Meeting");
    }

    @Test
    @DisplayName("should set and get description")
    void testDescription() {
        document.setDescription("Holiday gathering");
        assertThat(document.getDescription()).isEqualTo("Holiday gathering");
    }

    @Test
    @DisplayName("should set and get spouseIncluded")
    void testSpouseIncluded() {
        document.setSpouseIncluded(true);
        assertThat(document.getSpouseIncluded()).isTrue();
    }
}
