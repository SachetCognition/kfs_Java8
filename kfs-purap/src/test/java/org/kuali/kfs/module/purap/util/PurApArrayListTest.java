package org.kuali.kfs.module.purap.util;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurApArrayListTest extends KfsUnitTestBase {

    public static class SimpleBean {
        private String value;
        public SimpleBean() {}
        public SimpleBean(String value) { this.value = value; }
        public String getValue() { return value; }
        public void setValue(String value) { this.value = value; }
    }

    @Test
    void constructWithValidClass() {
        PurApArrayList list = new PurApArrayList(SimpleBean.class);
        assertThat(list).isEmpty();
    }

    @Test
    void constructWithNullClassThrows() {
        assertThatThrownBy(() -> new PurApArrayList(null))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void addValidObject() {
        PurApArrayList list = new PurApArrayList(SimpleBean.class);
        SimpleBean bean = new SimpleBean("test");
        list.add(bean);
        assertThat(list).hasSize(1);
    }

    @Test
    void addWrongTypeThrows() {
        PurApArrayList list = new PurApArrayList(SimpleBean.class);
        assertThatThrownBy(() -> list.add("not a SimpleBean"))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void getAutoGrowsArray() {
        PurApArrayList list = new PurApArrayList(SimpleBean.class);
        Object item = list.get(5);
        assertThat(item).isInstanceOf(SimpleBean.class);
        assertThat(list.size()).isGreaterThanOrEqualTo(6);
    }

    @Test
    void setAutoGrowsArray() {
        PurApArrayList list = new PurApArrayList(SimpleBean.class);
        SimpleBean bean = new SimpleBean("val");
        list.set(3, bean);
        assertThat(list.get(3)).isEqualTo(bean);
        assertThat(list.size()).isGreaterThanOrEqualTo(4);
    }

    @Test
    void addAtIndexValidType() {
        PurApArrayList list = new PurApArrayList(SimpleBean.class);
        list.add(new SimpleBean("first"));
        list.add(0, new SimpleBean("inserted"));
        assertThat(list).hasSize(2);
        assertThat(((SimpleBean) list.get(0)).getValue()).isEqualTo("inserted");
    }

    @Test
    void addAtIndexInvalidTypeThrows() {
        PurApArrayList list = new PurApArrayList(SimpleBean.class);
        assertThatThrownBy(() -> list.add(0, "wrong type"))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void constructWithMethodArgumentClasses() {
        PurApArrayList list = new PurApArrayList(
                SimpleBean.class,
                new Class[]{String.class},
                new Object[]{"initial"}
        );
        assertThat(list).isEmpty();
    }
}
