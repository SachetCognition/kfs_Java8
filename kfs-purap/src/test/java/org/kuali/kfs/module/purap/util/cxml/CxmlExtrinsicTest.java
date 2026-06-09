package org.kuali.kfs.module.purap.util.cxml;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class CxmlExtrinsicTest extends KfsUnitTestBase {

    @Test
    void defaultConstructorFieldsNull() {
        CxmlExtrinsic extrinsic = new CxmlExtrinsic();
        assertThat(extrinsic.getName()).isNull();
        assertThat(extrinsic.getValue()).isNull();
    }

    @Test
    void parameterizedConstructor() {
        CxmlExtrinsic extrinsic = new CxmlExtrinsic("testName", "testValue");
        assertThat(extrinsic.getName()).isEqualTo("testName");
        assertThat(extrinsic.getValue()).isEqualTo("testValue");
    }

    @Test
    void settersAndGetters() {
        CxmlExtrinsic extrinsic = new CxmlExtrinsic();
        extrinsic.setName("key");
        extrinsic.setValue("val");
        assertThat(extrinsic.getName()).isEqualTo("key");
        assertThat(extrinsic.getValue()).isEqualTo("val");
    }

    @Test
    void toStringContainsNameAndValue() {
        CxmlExtrinsic extrinsic = new CxmlExtrinsic("myName", "myValue");
        String result = extrinsic.toString();
        assertThat(result).contains("myName").contains("myValue");
    }
}
