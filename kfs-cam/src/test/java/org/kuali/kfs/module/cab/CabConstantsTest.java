package org.kuali.kfs.module.cab;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class CabConstantsTest extends KfsUnitTestBase {

    @Test
    @DisplayName("PREQ and CM constants")
    void preqAndCm() {
        assertThat(CabConstants.PREQ).isEqualTo("PREQ");
        assertThat(CabConstants.CM).isEqualTo("CM");
    }

    @Test
    @DisplayName("TRADE_IN_TYPE_CODE constant")
    void tradeInTypeCode() {
        assertThat(CabConstants.TRADE_IN_TYPE_CODE).isEqualTo("TRDI");
    }

    @Test
    @DisplayName("DateFormats constants")
    void dateFormats() {
        assertThat(CabConstants.DateFormats.MONTH_DAY_YEAR).isEqualTo("MM/dd/yyyy");
        assertThat(CabConstants.DateFormats.MILITARY_TIME).isEqualTo("HH:mm:ss");
    }

    @Test
    @DisplayName("Parameters constants")
    void parameters() {
        assertThat(CabConstants.Parameters.LAST_EXTRACT_TIME).isEqualTo("LAST_EXTRACT_TIME");
        assertThat(CabConstants.Parameters.SUB_FUND_GROUPS).isEqualTo("SUB_FUND_GROUPS");
        assertThat(CabConstants.Parameters.FISCAL_PERIODS).isEqualTo("FISCAL_PERIODS");
        assertThat(CabConstants.Parameters.DOCUMENT_TYPES).isEqualTo("DOCUMENT_TYPES");
        assertThat(CabConstants.Parameters.CHARTS).isEqualTo("CHARTS");
        assertThat(CabConstants.Parameters.NAMESPACE).isEqualTo("KFS-CAB");
        assertThat(CabConstants.Parameters.DETAIL_TYPE_BATCH).isEqualTo("Batch");
        assertThat(CabConstants.Parameters.DETAIL_TYPE_DOCUMENT).isEqualTo("Document");
    }

    @Test
    @DisplayName("Actions constants")
    void actions() {
        assertThat(CabConstants.Actions.PROCESS).isEqualTo("process");
        assertThat(CabConstants.Actions.VIEW).isEqualTo("view");
        assertThat(CabConstants.Actions.START).isEqualTo("start");
        assertThat(CabConstants.Actions.SPLIT).isEqualTo("split");
        assertThat(CabConstants.Actions.MERGE).isEqualTo("merge");
        assertThat(CabConstants.Actions.MERGE_ALL).isEqualTo("merge all");
        assertThat(CabConstants.Actions.ALLOCATE).isEqualTo("allocate");
        assertThat(CabConstants.Actions.CREATE_ASSET).isEqualTo("createAsset");
        assertThat(CabConstants.Actions.APPLY_PAYMENT).isEqualTo("applyPayment");
        assertThat(CabConstants.Actions.PERCENT_PAYMENT).isEqualTo("percent payment");
        assertThat(CabConstants.Actions.VIEW_DOC).isEqualTo("viewDoc");
    }

    @Test
    @DisplayName("ActivityStatusCode constants")
    void activityStatusCode() {
        assertThat(CabConstants.ActivityStatusCode.NEW).isEqualTo("N");
        assertThat(CabConstants.ActivityStatusCode.MODIFIED).isEqualTo("M");
        assertThat(CabConstants.ActivityStatusCode.ENROUTE).isEqualTo("E");
        assertThat(CabConstants.ActivityStatusCode.PROCESSED_IN_CAMS).isEqualTo("P");
    }

    @Test
    @DisplayName("ValidationStrings constants")
    void validationStrings() {
        assertThat(CabConstants.ValidationStrings.CAPITAL).isEqualTo("Capital");
        assertThat(CabConstants.ValidationStrings.EXPENSE).isEqualTo("Expense");
        assertThat(CabConstants.ValidationStrings.RECURRING).isEqualTo("Recurring");
        assertThat(CabConstants.ValidationStrings.NON_RECURRING).isEqualTo("Non-recurring");
    }

    @Test
    @DisplayName("URL-related constants")
    void urlConstants() {
        assertThat(CabConstants.CB_INVOICE_LINE_ACTION_URL).contains("cabPurApLine.do");
        assertThat(CabConstants.DOT_DOC).isEqualTo(".doc");
        assertThat(CabConstants.DOT_LINE).isEqualTo(".line");
    }

    @Test
    @DisplayName("PO_STATUS_CODE_OPEN constant")
    void poStatusCodeOpen() {
        assertThat(CabConstants.PO_STATUS_CODE_OPEN).isEqualTo("Open");
    }
}
