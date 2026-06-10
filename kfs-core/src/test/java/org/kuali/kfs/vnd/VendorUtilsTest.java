package org.kuali.kfs.vnd;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VendorUtilsTest extends KfsUnitTestBase {

    @Test
    void assembleWithPositionSingleCollection() {
        String result = VendorUtils.assembleWithPosition("vendorAddresses.vendorLine1Address",
                "vendorAddresses", 0);
        assertThat(result).isEqualTo("vendorAddresses[0].vendorLine1Address");
    }

    @Test
    void assembleWithPositionMultipleCollections() {
        String result = VendorUtils.assembleWithPosition(
                "vendorAddresses.vendorDefaultAddresses.vendorCampusCode",
                new String[]{"vendorAddresses", "vendorDefaultAddresses"},
                new int[]{0, 1});
        assertThat(result).isEqualTo("vendorAddresses[0].vendorDefaultAddresses[1].vendorCampusCode");
    }

    @Test
    void assembleWithPositionThrowsOnMismatchedArrays() {
        assertThatThrownBy(() -> VendorUtils.assembleWithPosition(
                "a.b",
                new String[]{"a", "b"},
                new int[]{0}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void assembleWithPositionNoMatchingCollection() {
        String result = VendorUtils.assembleWithPosition("someField.otherField",
                "vendorAddresses", 0);
        assertThat(result).isEqualTo("someField.otherField");
    }

    @Test
    void getVendorHeaderIdReturnsHeaderPart() {
        Integer headerId = VendorUtils.getVendorHeaderId("1000-5");
        assertThat(headerId).isEqualTo(1000);
    }

    @Test
    void getVendorHeaderIdReturnsNullForInvalid() {
        assertThat(VendorUtils.getVendorHeaderId(null)).isNull();
        assertThat(VendorUtils.getVendorHeaderId("invalid")).isNull();
        assertThat(VendorUtils.getVendorHeaderId("abc-def")).isNull();
    }

    @Test
    void getVendorDetailIdReturnsDetailPart() {
        Integer detailId = VendorUtils.getVendorDetailId("1000-5");
        assertThat(detailId).isEqualTo(5);
    }

    @Test
    void getVendorDetailIdReturnsNullForInvalid() {
        assertThat(VendorUtils.getVendorDetailId(null)).isNull();
        assertThat(VendorUtils.getVendorDetailId("invalid")).isNull();
    }

    @Test
    void validVendorNumberFormatReturnsTrueForValid() {
        assertThat(VendorUtils.validVendorNumberFormat("1000-0")).isTrue();
        assertThat(VendorUtils.validVendorNumberFormat("12345-67")).isTrue();
        assertThat(VendorUtils.validVendorNumberFormat("1-1")).isTrue();
    }

    @Test
    void validVendorNumberFormatReturnsFalseForNull() {
        assertThat(VendorUtils.validVendorNumberFormat(null)).isFalse();
    }

    @Test
    void validVendorNumberFormatReturnsFalseForNoDash() {
        assertThat(VendorUtils.validVendorNumberFormat("12345")).isFalse();
    }

    @Test
    void validVendorNumberFormatReturnsFalseForLetters() {
        assertThat(VendorUtils.validVendorNumberFormat("abc-def")).isFalse();
    }

    @Test
    void validVendorNumberFormatReturnsFalseForEmptyParts() {
        assertThat(VendorUtils.validVendorNumberFormat("-123")).isFalse();
        assertThat(VendorUtils.validVendorNumberFormat("123-")).isFalse();
    }

    @Test
    void validVendorNumberFormatReturnsFalseForEmpty() {
        assertThat(VendorUtils.validVendorNumberFormat("")).isFalse();
    }

    @Test
    void constantsHaveExpectedValues() {
        assertThat(VendorUtils.LEFT_COLLECTION_SEPERATOR).isEqualTo('[');
        assertThat(VendorUtils.RIGHT_COLLECTION_SEPERATOR).isEqualTo(']');
        assertThat(VendorUtils.FIELD_SEPERATOR).isEqualTo('.');
    }
}
