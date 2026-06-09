package org.kuali.kfs.sys.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionalServiceUtilsTest extends KfsUnitTestBase {

    @Test
    void copyToExternallyUsuableIterator_preservesElements() {
        List<String> items = Arrays.asList("a", "b", "c");
        Iterator<String> copy = TransactionalServiceUtils.copyToExternallyUsuableIterator(items.iterator());

        assertThat(copy).toIterable().containsExactly("a", "b", "c");
    }

    @Test
    void copyToExternallyUsuableIterator_emptyIterator() {
        Iterator<String> copy = TransactionalServiceUtils.copyToExternallyUsuableIterator(
                Collections.<String>emptyIterator());
        assertThat(copy.hasNext()).isFalse();
    }

    @Test
    void retrieveFirstAndExhaustIterator_returnsFirst() {
        List<String> items = Arrays.asList("first", "second", "third");
        Iterator<String> iter = items.iterator();

        String first = TransactionalServiceUtils.retrieveFirstAndExhaustIterator(iter);

        assertThat(first).isEqualTo("first");
        assertThat(iter.hasNext()).isFalse();
    }

    @Test
    void retrieveFirstAndExhaustIterator_emptyIterator_returnsNull() {
        Iterator<String> iter = Collections.<String>emptyIterator();
        String result = TransactionalServiceUtils.retrieveFirstAndExhaustIterator(iter);
        assertThat(result).isNull();
    }

    @Test
    void exhaustIterator_consumesAll() {
        List<Integer> items = Arrays.asList(1, 2, 3);
        Iterator<Integer> iter = items.iterator();

        TransactionalServiceUtils.exhaustIterator(iter);

        assertThat(iter.hasNext()).isFalse();
    }

    @Test
    void exhaustIterator_emptyIterator_noOp() {
        Iterator<Integer> iter = Collections.<Integer>emptyIterator();
        TransactionalServiceUtils.exhaustIterator(iter);
        assertThat(iter.hasNext()).isFalse();
    }
}
