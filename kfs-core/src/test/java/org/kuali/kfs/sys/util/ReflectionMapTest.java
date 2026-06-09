package org.kuali.kfs.sys.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReflectionMapTest extends KfsUnitTestBase {

    public static class SampleBean {
        private String name = "test";
        private int count = 42;
        private SampleBean nested;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getCount() { return count; }
        public void setCount(int count) { this.count = count; }
        public SampleBean getNested() { return nested; }
        public void setNested(SampleBean nested) { this.nested = nested; }
    }

    private ReflectionMap map;
    private SampleBean bean;

    @BeforeEach
    void setUp() {
        bean = new SampleBean();
        map = new ReflectionMap(bean);
    }

    @Test
    void constructor_nullBean_throws() {
        assertThatThrownBy(() -> new ReflectionMap(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void get_simpleProperty() {
        assertThat(map.get("name")).isEqualTo("test");
    }

    @Test
    void get_intProperty() {
        assertThat(map.get("count")).isEqualTo(42);
    }

    @Test
    void get_nestedProperty() {
        SampleBean nested = new SampleBean();
        nested.setName("inner");
        bean.setNested(nested);
        assertThat(map.get("nested.name")).isEqualTo("inner");
    }

    @Test
    void get_missingProperty_returnsNull() {
        assertThat(map.get("nonexistent")).isNull();
    }

    @Test
    void containsKey_existingProperty_returnsTrue() {
        assertThat(map.containsKey("name")).isTrue();
    }

    @Test
    void containsKey_missingProperty_returnsFalse() {
        assertThat(map.containsKey("nonexistent")).isFalse();
    }

    @Test
    void isEmpty_alwaysFalse() {
        assertThat(map.isEmpty()).isFalse();
    }

    @Test
    void put_throws() {
        assertThatThrownBy(() -> map.put("key", "value"))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void remove_throws() {
        assertThatThrownBy(() -> map.remove("key"))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void putAll_throws() {
        assertThatThrownBy(() -> map.putAll(java.util.Collections.emptyMap()))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void clear_throws() {
        assertThatThrownBy(() -> map.clear())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void size_throws() {
        assertThatThrownBy(() -> map.size())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void keySet_throws() {
        assertThatThrownBy(() -> map.keySet())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void values_throws() {
        assertThatThrownBy(() -> map.values())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void entrySet_throws() {
        assertThatThrownBy(() -> map.entrySet())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void containsValue_throws() {
        assertThatThrownBy(() -> map.containsValue("test"))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
