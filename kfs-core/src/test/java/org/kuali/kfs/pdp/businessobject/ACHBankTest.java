package org.kuali.kfs.pdp.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class ACHBankTest extends KfsUnitTestBase {

    private ACHBank achBank;

    @BeforeEach
    void setUp() {
        achBank = new ACHBank();
    }

    @Test
    void testDefaultConstructor() {
        assertThat(achBank).isNotNull();
        assertThat(achBank.isActive()).isFalse();
    }

    @Test
    void testConstructorFromString() {
        // Fixed-width ACH bank record (155 chars total):
        // routing(9) + office(1) + service(9) + type(1) + changeDate(6) + newRouting(9) +
        // name(36) + address(36) + city(20) + state(2) + zip(5) + zipExt(4) +
        // areaCode(3) + prefix(3) + suffix(4) + status(1) + dataView(1) + filler(5)
        String record =
                "074914274" +                                   // routing (9) pos 1-9
                "O" +                                           // office (1) pos 10
                "071000301" +                                   // service (9) pos 11-19
                "1" +                                           // type (1) pos 20
                "020207" +                                      // changeDate (6) pos 21-26
                "000000000" +                                   // newRouting (9) pos 27-35
                "UNITED COMMERCE BANK                " +        // name (36) pos 36-71
                "211 SOUTH COLLEGE AVENUE            " +        // address (36) pos 72-107
                "BLOOMINGTON         " +                        // city (20) pos 108-127
                "IN" +                                          // state (2) pos 128-129
                "47404" +                                       // zip (5) pos 130-134
                "0000" +                                        // zipExt (4) pos 135-138
                "812" +                                         // areaCode (3) pos 139-141
                "336" +                                         // prefix (3) pos 142-144
                "2265" +                                        // suffix (4) pos 145-148
                "1" +                                           // status (1) pos 149
                "1" +                                           // dataView (1) pos 150
                "     ";                                        // filler (5) pos 151-155

        ACHBank fromString = new ACHBank(record);

        assertThat(fromString.getBankRoutingNumber()).isEqualTo("074914274");
        assertThat(fromString.getBankOfficeCode()).isEqualTo("O");
        assertThat(fromString.getBankServiceNumber()).isEqualTo("071000301");
        assertThat(fromString.getBankTypeCode()).isEqualTo("1");
        assertThat(fromString.getBankNewRoutingNumber()).isEqualTo("000000000");
        assertThat(fromString.getBankName()).isEqualTo("UNITED COMMERCE BANK");
        assertThat(fromString.getBankCityName()).isEqualTo("BLOOMINGTON");
        assertThat(fromString.getBankStateCode()).isEqualTo("IN");
        assertThat(fromString.getBankZipCode()).isEqualTo("47404");
        assertThat(fromString.getBankPhoneAreaCode()).isEqualTo("812");
        assertThat(fromString.getBankPhonePrefixNumber()).isEqualTo("336");
        assertThat(fromString.getBankPhoneSuffixNumber()).isEqualTo("2265");
        assertThat(fromString.isActive()).isTrue();
    }

    @Test
    void testGettersAndSetters() {
        achBank.setBankRoutingNumber("111000025");
        achBank.setBankOfficeCode("O");
        achBank.setBankServiceNumber("123456789");
        achBank.setBankTypeCode("01");
        achBank.setBankNewRoutingNumber("222000025");
        achBank.setBankName("Test Bank");
        achBank.setBankStreetAddress("456 Oak Ave");
        achBank.setBankCityName("Indianapolis");
        achBank.setBankStateCode("IN");
        achBank.setBankZipCode("46202");
        achBank.setBankPhoneAreaCode("317");
        achBank.setBankPhonePrefixNumber("555");
        achBank.setBankPhoneSuffixNumber("1234");
        achBank.setBankInstitutionStatusCode("1");
        achBank.setBankDataViewCode("1");
        achBank.setActive(true);

        assertThat(achBank.getBankRoutingNumber()).isEqualTo("111000025");
        assertThat(achBank.getBankOfficeCode()).isEqualTo("O");
        assertThat(achBank.getBankServiceNumber()).isEqualTo("123456789");
        assertThat(achBank.getBankTypeCode()).isEqualTo("01");
        assertThat(achBank.getBankNewRoutingNumber()).isEqualTo("222000025");
        assertThat(achBank.getBankName()).isEqualTo("Test Bank");
        assertThat(achBank.getBankStreetAddress()).isEqualTo("456 Oak Ave");
        assertThat(achBank.getBankCityName()).isEqualTo("Indianapolis");
        assertThat(achBank.getBankStateCode()).isEqualTo("IN");
        assertThat(achBank.getBankZipCode()).isEqualTo("46202");
        assertThat(achBank.getBankPhoneAreaCode()).isEqualTo("317");
        assertThat(achBank.getBankPhonePrefixNumber()).isEqualTo("555");
        assertThat(achBank.getBankPhoneSuffixNumber()).isEqualTo("1234");
        assertThat(achBank.getBankInstitutionStatusCode()).isEqualTo("1");
        assertThat(achBank.getBankDataViewCode()).isEqualTo("1");
        assertThat(achBank.isActive()).isTrue();
    }

    @Test
    void testActive_defaultIsFalse() {
        ACHBank bank = new ACHBank();
        assertThat(bank.isActive()).isFalse();
    }

    @Test
    void testActive_setAndGet() {
        achBank.setActive(true);
        assertThat(achBank.isActive()).isTrue();

        achBank.setActive(false);
        assertThat(achBank.isActive()).isFalse();
    }
}
