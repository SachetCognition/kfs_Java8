package org.kuali.kfs.sys.batch;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class FlatFileInformationTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor_initializesEmptyCollections() {
        FlatFileInformation info = new FlatFileInformation();
        assertThat(info.getFileName()).isNull();
        assertThat(info.getMessages()).isEmpty();
        assertThat(info.getFlatFileIdentifierToTransactionInfomationMap()).isEmpty();
    }

    @Test
    void constructorWithFileName() {
        FlatFileInformation info = new FlatFileInformation("test.dat");
        assertThat(info.getFileName()).isEqualTo("test.dat");
        assertThat(info.getMessages()).isEmpty();
    }

    @Test
    void setAndGetFileName() {
        FlatFileInformation info = new FlatFileInformation();
        info.setFileName("data.txt");
        assertThat(info.getFileName()).isEqualTo("data.txt");
    }

    @Test
    void addFileErrorMessage() {
        FlatFileInformation info = new FlatFileInformation("test.dat");
        info.addFileErrorMessage("Parse error on line 5");
        assertThat(info.getMessages()).hasSize(1);
        assertThat(info.getMessages().get(0)[0]).isEqualTo("ERROR");
        assertThat(info.getMessages().get(0)[1]).isEqualTo("Parse error on line 5");
    }

    @Test
    void addFileInfoMessage() {
        FlatFileInformation info = new FlatFileInformation("test.dat");
        info.addFileInfoMessage("File loaded successfully");
        assertThat(info.getMessages()).hasSize(1);
        assertThat(info.getMessages().get(0)[0]).isEqualTo("INFO");
        assertThat(info.getMessages().get(0)[1]).isEqualTo("File loaded successfully");
    }

    @Test
    void getOrAddFlatFileData_addsNew() {
        FlatFileInformation info = new FlatFileInformation("test.dat");
        FlatFileTransactionInformation txInfo = new FlatFileTransactionInformation("TX-001");

        FlatFileTransactionInformation result = info.getOrAddFlatFileData("TX-001", txInfo);
        assertThat(result).isSameAs(txInfo);
        assertThat(info.getFlatFileIdentifierToTransactionInfomationMap()).containsKey("TX-001");
    }

    @Test
    void getOrAddFlatFileData_returnsExisting() {
        FlatFileInformation info = new FlatFileInformation("test.dat");
        FlatFileTransactionInformation first = new FlatFileTransactionInformation("TX-001");
        FlatFileTransactionInformation second = new FlatFileTransactionInformation("TX-001");

        info.getOrAddFlatFileData("TX-001", first);
        FlatFileTransactionInformation result = info.getOrAddFlatFileData("TX-001", second);

        assertThat(result).isSameAs(first);
    }

    @Test
    void multipleMessages_maintainOrder() {
        FlatFileInformation info = new FlatFileInformation("test.dat");
        info.addFileInfoMessage("msg1");
        info.addFileErrorMessage("msg2");
        info.addFileInfoMessage("msg3");

        assertThat(info.getMessages()).hasSize(3);
        assertThat(info.getMessages().get(0)[1]).isEqualTo("msg1");
        assertThat(info.getMessages().get(1)[1]).isEqualTo("msg2");
        assertThat(info.getMessages().get(2)[1]).isEqualTo("msg3");
    }
}
