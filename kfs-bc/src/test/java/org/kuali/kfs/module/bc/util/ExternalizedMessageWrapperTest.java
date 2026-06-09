package org.kuali.kfs.module.bc.util;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class ExternalizedMessageWrapperTest extends KfsUnitTestBase {

    @Test
    void constructor_setsMessageKeyAndParams() {
        ExternalizedMessageWrapper wrapper = new ExternalizedMessageWrapper("error.key", "param1", "param2");
        assertThat(wrapper.getMessageKey()).isEqualTo("error.key");
        assertThat(wrapper.getParams()).containsExactly("param1", "param2");
    }

    @Test
    void constructor_withNullParams_setsEmptyArray() {
        ExternalizedMessageWrapper wrapper = new ExternalizedMessageWrapper("info.key", (String[]) null);
        assertThat(wrapper.getMessageKey()).isEqualTo("info.key");
        assertThat(wrapper.getParams()).isEmpty();
    }

    @Test
    void constructor_withNoParams_setsEmptyArray() {
        ExternalizedMessageWrapper wrapper = new ExternalizedMessageWrapper("warn.key");
        assertThat(wrapper.getMessageKey()).isEqualTo("warn.key");
        assertThat(wrapper.getParams()).isEmpty();
    }

    @Test
    void constructor_withSingleParam() {
        ExternalizedMessageWrapper wrapper = new ExternalizedMessageWrapper("msg.key", "onlyParam");
        assertThat(wrapper.getParams()).hasSize(1);
        assertThat(wrapper.getParams()[0]).isEqualTo("onlyParam");
    }
}
