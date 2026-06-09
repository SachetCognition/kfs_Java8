package org.kuali.kfs.module.tem.businessobject;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TravelerDetail Business Object")
class TravelerDetailTest extends KfsUnitTestBase {

    private TravelerDetail travelerDetail;

    @BeforeEach
    void setUp() {
        travelerDetail = new TravelerDetail();
    }

    @Test
    @DisplayName("should initialize with empty emergency contacts list")
    void testDefaultConstruction() {
        assertThat(travelerDetail.getEmergencyContacts()).isNotNull();
        assertThat(travelerDetail.getEmergencyContacts()).isEmpty();
    }

    @Test
    @DisplayName("should reset emergency contacts to empty list")
    void testResetEmergencyContacts() {
        List<TravelerDetailEmergencyContact> contacts = new ArrayList<>();
        contacts.add(new TravelerDetailEmergencyContact());
        travelerDetail.setEmergencyContacts(contacts);

        assertThat(travelerDetail.getEmergencyContacts()).hasSize(1);

        travelerDetail.resetEmergencyContacts();
        assertThat(travelerDetail.getEmergencyContacts()).isEmpty();
    }

    @Test
    @DisplayName("should set and get emergency contacts")
    void testSetEmergencyContacts() {
        List<TravelerDetailEmergencyContact> contacts = new ArrayList<>();
        TravelerDetailEmergencyContact contact = new TravelerDetailEmergencyContact();
        contacts.add(contact);

        travelerDetail.setEmergencyContacts(contacts);
        assertThat(travelerDetail.getEmergencyContacts()).hasSize(1);
        assertThat(travelerDetail.getEmergencyContacts().get(0)).isSameAs(contact);
    }
}
