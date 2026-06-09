package org.kuali.kfs.module.bc.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class PayrateImportExportTest extends KfsUnitTestBase {

    @Test
    void setAndGetPositionUnionCode() {
        PayrateImportExport obj = new PayrateImportExport();
        obj.setPositionUnionCode("UC01");
        assertThat(obj.getPositionUnionCode()).isEqualTo("UC01");
    }

    @Test
    void setAndGetCsfFreezeDate() {
        PayrateImportExport obj = new PayrateImportExport();
        Date date = new Date();
        obj.setCsfFreezeDate(date);
        assertThat(obj.getCsfFreezeDate()).isEqualTo(date);
    }

    @Test
    void setAndGetExportCount() {
        PayrateImportExport obj = new PayrateImportExport();
        obj.setExportCount(42);
        assertThat(obj.getExportCount()).isEqualTo(42);
    }

    @Test
    void setAndGetFileName() {
        PayrateImportExport obj = new PayrateImportExport();
        obj.setFileName("test_file.csv");
        assertThat(obj.getFileName()).isEqualTo("test_file.csv");
    }

    @Test
    void setAndGetImportCount() {
        PayrateImportExport obj = new PayrateImportExport();
        obj.setImportCount(100);
        assertThat(obj.getImportCount()).isEqualTo(100);
    }

    @Test
    void setAndGetUpdateCount() {
        PayrateImportExport obj = new PayrateImportExport();
        obj.setUpdateCount(55);
        assertThat(obj.getUpdateCount()).isEqualTo(55);
    }

    @Test
    void defaultValues_areZeroOrNull() {
        PayrateImportExport obj = new PayrateImportExport();
        assertThat(obj.getPositionUnionCode()).isNull();
        assertThat(obj.getCsfFreezeDate()).isNull();
        assertThat(obj.getExportCount()).isZero();
        assertThat(obj.getFileName()).isNull();
        assertThat(obj.getImportCount()).isZero();
        assertThat(obj.getUpdateCount()).isZero();
    }
}
