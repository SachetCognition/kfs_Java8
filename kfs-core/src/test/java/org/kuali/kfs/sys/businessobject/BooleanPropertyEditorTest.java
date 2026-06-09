package org.kuali.kfs.sys.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class BooleanPropertyEditorTest extends KfsUnitTestBase {

    private BooleanPropertyEditor editor;

    @BeforeEach
    void setUp() {
        editor = new BooleanPropertyEditor();
    }

    @Test
    void setAsText_Y_setsTrue() {
        editor.setAsText("Y");
        assertThat(editor.getValue()).isEqualTo(Boolean.TRUE);
    }

    @Test
    void setAsText_y_lowercase_setsTrue() {
        editor.setAsText("y");
        assertThat(editor.getValue()).isEqualTo(Boolean.TRUE);
    }

    @Test
    void setAsText_N_setsFalse() {
        editor.setAsText("N");
        assertThat(editor.getValue()).isEqualTo(Boolean.FALSE);
    }

    @Test
    void setAsText_n_lowercase_setsFalse() {
        editor.setAsText("n");
        assertThat(editor.getValue()).isEqualTo(Boolean.FALSE);
    }

    @Test
    void setAsText_emptyString_setsFalse() {
        editor.setAsText("");
        assertThat(editor.getValue()).isEqualTo(Boolean.FALSE);
    }

    @Test
    void setAsText_null_setsFalse() {
        editor.setAsText(null);
        assertThat(editor.getValue()).isEqualTo(Boolean.FALSE);
    }

    @Test
    void setAsText_arbitraryString_setsFalse() {
        editor.setAsText("true");
        assertThat(editor.getValue()).isEqualTo(Boolean.FALSE);
    }

    @Test
    void setAsText_yes_setsFalse() {
        editor.setAsText("yes");
        assertThat(editor.getValue()).isEqualTo(Boolean.FALSE);
    }
}
