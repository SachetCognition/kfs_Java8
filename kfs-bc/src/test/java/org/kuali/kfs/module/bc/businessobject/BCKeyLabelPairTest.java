package org.kuali.kfs.module.bc.businessobject;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class BCKeyLabelPairTest extends KfsUnitTestBase {

    @Test
    void defaultConstructor_fieldsAreNull() {
        BCKeyLabelPair pair = new BCKeyLabelPair();
        assertThat(pair.getKey()).isNull();
        assertThat(pair.getLabel()).isNull();
    }

    @Test
    void parameterizedConstructor_setsKeyAndLabel() {
        BCKeyLabelPair pair = new BCKeyLabelPair("testKey", "Test Label");
        assertThat(pair.getKey()).isEqualTo("testKey");
        assertThat(pair.getLabel()).isEqualTo("Test Label");
    }

    @Test
    void setKey_updatesKey() {
        BCKeyLabelPair pair = new BCKeyLabelPair();
        pair.setKey("newKey");
        assertThat(pair.getKey()).isEqualTo("newKey");
    }

    @Test
    void setLabel_updatesLabel() {
        BCKeyLabelPair pair = new BCKeyLabelPair();
        pair.setLabel("newLabel");
        assertThat(pair.getLabel()).isEqualTo("newLabel");
    }

    @Test
    void publicFields_accessibleDirectly() {
        BCKeyLabelPair pair = new BCKeyLabelPair("k", "l");
        assertThat(pair.key).isEqualTo("k");
        assertThat(pair.label).isEqualTo("l");
    }
}
