package org.kuali.kfs.sys.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class FallbackMapTest extends KfsUnitTestBase {

    private Map<String, String> backMap;
    private FallbackMap<String, String> fallbackMap;

    @BeforeEach
    void setUp() {
        backMap = new HashMap<>();
        backMap.put("back1", "backVal1");
        backMap.put("back2", "backVal2");
        fallbackMap = new FallbackMap<>(backMap);
    }

    @Test
    void get_fromFrontMap() {
        fallbackMap.put("front1", "frontVal1");
        assertThat(fallbackMap.get("front1")).isEqualTo("frontVal1");
    }

    @Test
    void get_fallsBackToBackMap() {
        assertThat(fallbackMap.get("back1")).isEqualTo("backVal1");
    }

    @Test
    void get_frontMapOverridesBackMap() {
        fallbackMap.put("back1", "overridden");
        assertThat(fallbackMap.get("back1")).isEqualTo("overridden");
    }

    @Test
    void get_missingKey_returnsNull() {
        assertThat(fallbackMap.get("missing")).isNull();
    }

    @Test
    void size_combinesBothMaps() {
        fallbackMap.put("front1", "val");
        assertThat(fallbackMap.size()).isEqualTo(3); // 1 front + 2 back
    }

    @Test
    void isEmpty_bothEmpty() {
        FallbackMap<String, String> empty = new FallbackMap<>(new HashMap<>());
        assertThat(empty.isEmpty()).isTrue();
    }

    @Test
    void isEmpty_backHasEntries() {
        assertThat(fallbackMap.isEmpty()).isFalse();
    }

    @Test
    void containsKey_inFront() {
        fallbackMap.put("front1", "val");
        assertThat(fallbackMap.containsKey("front1")).isTrue();
    }

    @Test
    void containsKey_inBack() {
        assertThat(fallbackMap.containsKey("back1")).isTrue();
    }

    @Test
    void containsKey_missing() {
        assertThat(fallbackMap.containsKey("nope")).isFalse();
    }

    @Test
    void containsValue_inFront() {
        fallbackMap.put("k", "frontSpecial");
        assertThat(fallbackMap.containsValue("frontSpecial")).isTrue();
    }

    @Test
    void containsValue_inBack() {
        assertThat(fallbackMap.containsValue("backVal1")).isTrue();
    }

    @Test
    void containsValue_missing() {
        assertThat(fallbackMap.containsValue("nope")).isFalse();
    }

    @Test
    void put_goesToFront() {
        fallbackMap.put("x", "y");
        assertThat(fallbackMap.get("x")).isEqualTo("y");
    }

    @Test
    void remove_fromFront() {
        fallbackMap.put("k", "v");
        String removed = fallbackMap.remove("k");
        assertThat(removed).isEqualTo("v");
        assertThat(fallbackMap.containsKey("k")).isFalse();
    }

    @Test
    void remove_fromBack() {
        String removed = fallbackMap.remove("back1");
        assertThat(removed).isEqualTo("backVal1");
    }

    @Test
    void putAll_addsToFront() {
        Map<String, String> extra = new HashMap<>();
        extra.put("a", "1");
        extra.put("b", "2");
        fallbackMap.putAll(extra);
        assertThat(fallbackMap.get("a")).isEqualTo("1");
        assertThat(fallbackMap.get("b")).isEqualTo("2");
    }

    @Test
    void clear_clearsBothMaps() {
        fallbackMap.put("front", "val");
        fallbackMap.clear();
        assertThat(fallbackMap.isEmpty()).isTrue();
    }

    @Test
    void keySet_combinesBothMaps() {
        fallbackMap.put("front1", "val");
        assertThat(fallbackMap.keySet()).containsExactlyInAnyOrder("front1", "back1", "back2");
    }

    @Test
    void values_combinesBothMaps() {
        fallbackMap.put("front1", "frontVal");
        assertThat(fallbackMap.values()).containsExactlyInAnyOrder("frontVal", "backVal1", "backVal2");
    }

    @Test
    void entrySet_combinesBothMaps() {
        fallbackMap.put("front1", "frontVal");
        assertThat(fallbackMap.entrySet()).hasSize(3);
    }
}
