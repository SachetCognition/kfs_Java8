package org.kuali.kfs.module.cam;

import java.util.Currency;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class CamsConstantsTest extends KfsUnitTestBase {

    @Test
    @DisplayName("CAM_MODULE_CODE is KFS-CAM")
    void camModuleCode() {
        assertThat(CamsConstants.CAM_MODULE_CODE).isEqualTo("KFS-CAM");
    }

    @Test
    @DisplayName("MONTHS array has 12 elements starting with January")
    void months() {
        assertThat(CamsConstants.MONTHS).hasSize(12);
        assertThat(CamsConstants.MONTHS[0]).isEqualTo("January");
        assertThat(CamsConstants.MONTHS[11]).isEqualTo("December");
    }

    @Test
    @DisplayName("CURRENCY_USD is USD")
    void currencyUsd() {
        assertThat(CamsConstants.CURRENCY_USD).isEqualTo(Currency.getInstance("USD"));
    }

    @Test
    @DisplayName("DateFormats constants are well-formed")
    void dateFormats() {
        assertThat(CamsConstants.DateFormats.MONTH_DAY_YEAR).isEqualTo("MM/dd/yyyy");
        assertThat(CamsConstants.DateFormats.YEAR_MONTH_DAY).isEqualTo("yyyy-MM-dd");
        assertThat(CamsConstants.DateFormats.MILITARY_TIME).isEqualTo("HH:mm:ss");
        assertThat(CamsConstants.DateFormats.STANDARD_TIME).isEqualTo("hh:mm:ss");
        assertThat(CamsConstants.DateFormats.YEAR_MONTH_DAY_NO_DELIMITER).isEqualTo("yyyyMMdd");
        assertThat(CamsConstants.DateFormats.MILITARY_TIME_NO_DELIMITER).isEqualTo("HHmmss");
    }

    @Test
    @DisplayName("DocumentTypeName constants")
    void documentTypeName() {
        assertThat(CamsConstants.DocumentTypeName.ASSET_DEPRECIATION).isEqualTo("DEPR");
        assertThat(CamsConstants.DocumentTypeName.ASSET_TRANSFER).isEqualTo("AT");
        assertThat(CamsConstants.DocumentTypeName.ASSET_EQUIPMENT_LOAN_OR_RETURN).isEqualTo("ELR");
        assertThat(CamsConstants.DocumentTypeName.ASSET_PAYMENT).isEqualTo("MPAY");
        assertThat(CamsConstants.DocumentTypeName.ASSET_RETIREMENT_GLOBAL).isEqualTo("ARG");
        assertThat(CamsConstants.DocumentTypeName.ASSET_ADD_GLOBAL).isEqualTo("AA");
        assertThat(CamsConstants.DocumentTypeName.ASSET_EDIT).isEqualTo("CASM");
        assertThat(CamsConstants.DocumentTypeName.ASSET_FABRICATION).isEqualTo("FR");
        assertThat(CamsConstants.DocumentTypeName.ASSET_LOCATION_GLOBAL).isEqualTo("ALOC");
        assertThat(CamsConstants.DocumentTypeName.ASSET_BARCODE_INVENTORY_ERROR).isEqualTo("BCIE");
        assertThat(CamsConstants.DocumentTypeName.ASSET_SEPARATE).isEqualTo("ASEP");
        assertThat(CamsConstants.DocumentTypeName.ASSET_PAYMENT_FROM_CAB).isEqualTo("MPAYCAB");
    }

    @Test
    @DisplayName("AssetActions constants")
    void assetActions() {
        assertThat(CamsConstants.AssetActions.LOAN).isEqualTo("loan");
        assertThat(CamsConstants.AssetActions.LOAN_RETURN).isEqualTo("return");
        assertThat(CamsConstants.AssetActions.LOAN_RENEW).isEqualTo("renew");
        assertThat(CamsConstants.AssetActions.MERGE).isEqualTo("merge");
        assertThat(CamsConstants.AssetActions.PAYMENT).isEqualTo("payment");
        assertThat(CamsConstants.AssetActions.RETIRE).isEqualTo("retire");
        assertThat(CamsConstants.AssetActions.SEPARATE).isEqualTo("separate");
        assertThat(CamsConstants.AssetActions.TRANSFER).isEqualTo("transfer");
        assertThat(CamsConstants.AssetActions.VIEW).isEqualTo("view");
    }

    @Test
    @DisplayName("PermissionNames constants")
    void permissionNames() {
        assertThat(CamsConstants.PermissionNames.ADD_NEGATIVE_PAYMENTS).isEqualTo("Add Negative Payments");
        assertThat(CamsConstants.PermissionNames.RETIRE_MULTIPLE).isEqualTo("Retire Multiple");
        assertThat(CamsConstants.PermissionNames.MERGE).isEqualTo("Merge");
        assertThat(CamsConstants.PermissionNames.SEPARATE).isEqualTo("Separate");
        assertThat(CamsConstants.PermissionNames.RAZE).isEqualTo("Raze");
    }

    @Test
    @DisplayName("RouteLevelNames constants")
    void routeLevelNames() {
        assertThat(CamsConstants.RouteLevelNames.EXTERNAL_TRANSFER).isEqualTo("ExternalTransfer");
        assertThat(CamsConstants.RouteLevelNames.PURCHASING).isEqualTo("Purchasing");
        assertThat(CamsConstants.RouteLevelNames.MANAGEMENT).isEqualTo("Management");
        assertThat(CamsConstants.RouteLevelNames.PLANT_FUND).isEqualTo("PlantFund");
        assertThat(CamsConstants.RouteLevelNames.BORROWER).isEqualTo("Borrower");
    }

    @Test
    @DisplayName("StrutsActions constants")
    void strutsActions() {
        assertThat(CamsConstants.StrutsActions.ONE_UP).isEqualTo("../");
        assertThat(CamsConstants.StrutsActions.TRANSFER).isEqualTo("camsAssetTransfer.do");
        assertThat(CamsConstants.StrutsActions.EQUIPMENT_LOAN_OR_RETURN).isEqualTo("camsEquipmentLoanOrReturn.do");
        assertThat(CamsConstants.StrutsActions.PAYMENT).isEqualTo("camsAssetPayment.do");
    }

    @Test
    @DisplayName("PaymentDocumentTypeCodes constants")
    void paymentDocumentTypeCodes() {
        assertThat(CamsConstants.PaymentDocumentTypeCodes.ASSET_GLOBAL_SEPARATE).isEqualTo("ASEP");
        assertThat(CamsConstants.PaymentDocumentTypeCodes.ASSET_RETIREMENT_MERGE).isEqualTo("AMRG");
    }

    @Test
    @DisplayName("Parameters constants are not null or empty")
    void parametersNotNull() {
        assertThat(CamsConstants.Parameters.DEPRECIATION_RUN_DATE_PARAMETER).isNotEmpty();
        assertThat(CamsConstants.Parameters.CAPITALIZATION_LIMIT_AMOUNT).isNotEmpty();
        assertThat(CamsConstants.Parameters.CAPITAL_ASSET_STATUS_CODES).isNotEmpty();
        assertThat(CamsConstants.Parameters.RETIRED_STATUS_CODES).isNotEmpty();
    }

    @Test
    @DisplayName("regex patterns are valid")
    void regexPatterns() {
        assertThat(CamsConstants.SET_PERIOD_DEPRECIATION_AMOUNT_REGEX).isNotEmpty();
        assertThat(CamsConstants.GET_PERIOD_DEPRECIATION_AMOUNT_REGEX).isNotEmpty();
        assertThat("setperiod1depreciation1amount").matches(CamsConstants.SET_PERIOD_DEPRECIATION_AMOUNT_REGEX);
        assertThat("getperiod1depreciation1amount").matches(CamsConstants.GET_PERIOD_DEPRECIATION_AMOUNT_REGEX);
    }

    @Test
    @DisplayName("DOC_HEADER_PATH built from components")
    void docHeaderPath() {
        assertThat(CamsConstants.DOC_HEADER_PATH).isEqualTo("document.documentNumber");
    }
}
