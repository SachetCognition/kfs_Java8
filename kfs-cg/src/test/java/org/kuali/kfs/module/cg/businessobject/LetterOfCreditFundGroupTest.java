package org.kuali.kfs.module.cg.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import static org.assertj.core.api.Assertions.assertThat;

class LetterOfCreditFundGroupTest extends KfsUnitTestBase {

    private LetterOfCreditFundGroup group;

    @BeforeEach
    void setUp() {
        group = new LetterOfCreditFundGroup();
    }

    @Test
    void testLetterOfCreditFundGroupCode() {
        group.setLetterOfCreditFundGroupCode("GRP01");
        assertThat(group.getLetterOfCreditFundGroupCode()).isEqualTo("GRP01");
    }

    @Test
    void testLetterOfCreditFundGroupDescription() {
        group.setLetterOfCreditFundGroupDescription("Federal Group");
        assertThat(group.getLetterOfCreditFundGroupDescription()).isEqualTo("Federal Group");
    }

    @Test
    void testActive() {
        group.setActive(true);
        assertThat(group.isActive()).isTrue();

        group.setActive(false);
        assertThat(group.isActive()).isFalse();
    }
}
