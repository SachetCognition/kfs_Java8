package org.kuali.kfs.module.cam.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ObjectValueUtilsTest extends KfsUnitTestBase {

    public static class SimpleBean {
        private String name;
        private Integer value;
        private String readOnly;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public Integer getValue() { return value; }
        public void setValue(Integer value) { this.value = value; }
        public String getReadOnly() { return readOnly; }
    }

    @Test
    @DisplayName("copySimpleProperties: copies read/write properties from origin to destination")
    void copySimpleProperties_copiesReadWriteProperties() {
        SimpleBean origin = new SimpleBean();
        origin.setName("TestAsset");
        origin.setValue(42);

        SimpleBean destination = new SimpleBean();
        ObjectValueUtils.copySimpleProperties(origin, destination);

        assertThat(destination.getName()).isEqualTo("TestAsset");
        assertThat(destination.getValue()).isEqualTo(42);
    }

    @Test
    @DisplayName("copySimpleProperties: null values are not copied (destination retains original)")
    void copySimpleProperties_nullValuesNotCopied() {
        SimpleBean origin = new SimpleBean();
        origin.setName(null);
        origin.setValue(42);

        SimpleBean destination = new SimpleBean();
        destination.setName("Existing");

        ObjectValueUtils.copySimpleProperties(origin, destination);

        assertThat(destination.getName()).isEqualTo("Existing");
        assertThat(destination.getValue()).isEqualTo(42);
    }

    @Test
    @DisplayName("copySimpleProperties: empty origin does not overwrite destination")
    void copySimpleProperties_emptyOrigin() {
        SimpleBean origin = new SimpleBean();
        SimpleBean destination = new SimpleBean();
        destination.setName("Keep");
        destination.setValue(99);

        ObjectValueUtils.copySimpleProperties(origin, destination);

        assertThat(destination.getName()).isEqualTo("Keep");
        assertThat(destination.getValue()).isEqualTo(99);
    }
}
