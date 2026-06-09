package org.kuali.kfs.module.ld.document;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.businessobject.SourceAccountingLine;
import org.kuali.kfs.sys.businessobject.TargetAccountingLine;
import org.kuali.kfs.sys.context.KfsUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.objenesis.ObjenesisStd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LaborExpenseTransferDocumentBaseTest extends KfsUnitTestBase {

    private SalaryExpenseTransferDocument document;

    @BeforeEach
    void setUp() {
        document = new ObjenesisStd().newInstance(SalaryExpenseTransferDocument.class);
    }

    private SourceAccountingLine createSourceLine(String objectCode, KualiDecimal amount) {
        SourceAccountingLine line = new SourceAccountingLine();
        line.setFinancialObjectCode(objectCode);
        line.setAmount(amount);
        return line;
    }

    private TargetAccountingLine createTargetLine(String objectCode, KualiDecimal amount) {
        TargetAccountingLine line = new TargetAccountingLine();
        line.setFinancialObjectCode(objectCode);
        line.setAmount(amount);
        return line;
    }

    @Test
    void testSetAndGetEmplid() {
        document.setEmplid("0000001234");
        assertThat(document.getEmplid()).isEqualTo("0000001234");
    }

    @Test
    void testEmplidDefaultsToNull() {
        assertThat(document.getEmplid()).isNull();
    }

    @Test
    void testGetUnbalancedObjectCodesWithNoLines() {
        document.setSourceAccountingLines(new ArrayList<>());
        document.setTargetAccountingLines(new ArrayList<>());

        Map<String, KualiDecimal> result = document.getUnbalancedObjectCodes();
        assertThat(result).isEmpty();
    }

    @Test
    void testGetUnbalancedObjectCodesWithBalancedLines() {
        List sourceLines = new ArrayList<>();
        sourceLines.add(createSourceLine("5000", new KualiDecimal(100)));
        document.setSourceAccountingLines(sourceLines);

        List targetLines = new ArrayList<>();
        targetLines.add(createTargetLine("5000", new KualiDecimal(100)));
        document.setTargetAccountingLines(targetLines);

        Map<String, KualiDecimal> result = document.getUnbalancedObjectCodes();
        assertThat(result).isEmpty();
    }

    @Test
    void testGetUnbalancedObjectCodesWithUnbalancedLines() {
        List sourceLines = new ArrayList<>();
        sourceLines.add(createSourceLine("5000", new KualiDecimal(100)));
        document.setSourceAccountingLines(sourceLines);

        List targetLines = new ArrayList<>();
        targetLines.add(createTargetLine("5000", new KualiDecimal(75)));
        document.setTargetAccountingLines(targetLines);

        Map<String, KualiDecimal> result = document.getUnbalancedObjectCodes();
        assertThat(result).containsKey("5000");
        assertThat(result.get("5000")).isEqualTo(new KualiDecimal(-25));
    }

    @Test
    void testGetUnbalancedObjectCodesSourceOnlyCode() {
        List sourceLines = new ArrayList<>();
        sourceLines.add(createSourceLine("5000", new KualiDecimal(100)));
        document.setSourceAccountingLines(sourceLines);
        document.setTargetAccountingLines(new ArrayList<>());

        Map<String, KualiDecimal> result = document.getUnbalancedObjectCodes();
        assertThat(result).containsKey("5000");
        assertThat(result.get("5000")).isEqualTo(new KualiDecimal(-100));
    }

    @Test
    void testGetUnbalancedObjectCodesTargetOnlyCode() {
        document.setSourceAccountingLines(new ArrayList<>());

        List targetLines = new ArrayList<>();
        targetLines.add(createTargetLine("6000", new KualiDecimal(200)));
        document.setTargetAccountingLines(targetLines);

        Map<String, KualiDecimal> result = document.getUnbalancedObjectCodes();
        assertThat(result).containsKey("6000");
        assertThat(result.get("6000")).isEqualTo(new KualiDecimal(200));
    }

    @Test
    void testGetUnbalancedObjectCodesMultipleObjectCodes() {
        List sourceLines = new ArrayList<>();
        sourceLines.add(createSourceLine("5000", new KualiDecimal(100)));
        sourceLines.add(createSourceLine("6000", new KualiDecimal(200)));
        document.setSourceAccountingLines(sourceLines);

        List targetLines = new ArrayList<>();
        targetLines.add(createTargetLine("5000", new KualiDecimal(100)));
        targetLines.add(createTargetLine("6000", new KualiDecimal(200)));
        document.setTargetAccountingLines(targetLines);

        Map<String, KualiDecimal> result = document.getUnbalancedObjectCodes();
        assertThat(result).isEmpty();
    }
}
