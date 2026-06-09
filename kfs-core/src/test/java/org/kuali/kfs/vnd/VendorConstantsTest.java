package org.kuali.kfs.vnd;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class VendorConstantsTest extends KfsUnitTestBase {

    @Test
    void vendorTypesConstants() {
        assertThat(VendorConstants.VendorTypes.DISBURSEMENT_VOUCHER).isEqualTo("DV");
        assertThat(VendorConstants.VendorTypes.PURCHASE_ORDER).isEqualTo("PO");
        assertThat(VendorConstants.VendorTypes.SUBJECT_PAYMENT).isEqualTo("SP");
        assertThat(VendorConstants.VendorTypes.REVOLVING_FUND).isEqualTo("RF");
        assertThat(VendorConstants.VendorTypes.REFUND_PAYMENT).isEqualTo("RV");
    }

    @Test
    void taxTypeConstants() {
        assertThat(VendorConstants.TAX_TYPE_FEIN).isEqualTo("FEIN");
        assertThat(VendorConstants.TAX_TYPE_SSN).isEqualTo("SSN");
        assertThat(VendorConstants.TAX_TYPE_TAX).isEqualTo("TAX");
    }

    @Test
    void phoneTypeConstants() {
        assertThat(VendorConstants.PhoneTypes.TOLL_FREE).isEqualTo("TF");
        assertThat(VendorConstants.PhoneTypes.PHONE).isEqualTo("PH");
        assertThat(VendorConstants.PhoneTypes.FAX).isEqualTo("FX");
        assertThat(VendorConstants.PhoneTypes.PO).isEqualTo("PO");
    }

    @Test
    void addressTypeConstants() {
        assertThat(VendorConstants.AddressTypes.QUOTE).isEqualTo("QT");
        assertThat(VendorConstants.AddressTypes.PURCHASE_ORDER).isEqualTo("PO");
        assertThat(VendorConstants.AddressTypes.REMIT).isEqualTo("RM");
    }

    @Test
    void ownerTypesConstants() {
        assertThat(VendorConstants.OwnerTypes.NR).isEqualTo("NR");
    }

    @Test
    void miscellaneousConstants() {
        assertThat(VendorConstants.NONE).isEqualTo("NONE");
        assertThat(VendorConstants.NAME_DELIM).isEqualTo(", ");
        assertThat(VendorConstants.DASH).isEqualTo("-");
        assertThat(VendorConstants.MAX_VENDOR_NAME_LENGTH).isEqualTo(45);
        assertThat(VendorConstants.VENDOR_EXCLUDE_FILE_TYPE_INDENTIFIER).isEqualTo("vendorExcludeInputFileType");
    }

    @Test
    void debarredVendorStatusConstants() {
        assertThat(VendorConstants.EXCLUDED_MATCHED_VENDOR_STATUS).isEqualTo("M");
        assertThat(VendorConstants.NON_EXCLUDED_MATCHED_VENDOR_STATUS).isEqualTo("N");
        assertThat(VendorConstants.DEBARRED_VENDOR_UNPROCESSED).isEqualTo("U");
        assertThat(VendorConstants.DEBARRED_VENDOR_CONFIRMED).isEqualTo("C");
        assertThat(VendorConstants.DEBARRED_VENDOR_DENIED).isEqualTo("D");
    }

    @Test
    void vendorCreateAndUpdateNotePrefixes() {
        assertThat(VendorConstants.VendorCreateAndUpdateNotePrefixes.ADD).isEqualTo("Add");
        assertThat(VendorConstants.VendorCreateAndUpdateNotePrefixes.CHANGE).isEqualTo("Change");
    }
}
