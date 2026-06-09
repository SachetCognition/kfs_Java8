package org.kuali.kfs.module.ar.businessobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kuali.kfs.sys.context.KfsUnitTestBase;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DunningCampaignTest extends KfsUnitTestBase {

    private DunningCampaign dunningCampaign;

    @BeforeEach
    void setUp() {
        dunningCampaign = new DunningCampaign();
    }

    @Test
    void testCampaignID() {
        dunningCampaign.setCampaignID("CAMP001");
        assertThat(dunningCampaign.getCampaignID()).isEqualTo("CAMP001");
    }

    @Test
    void testCampaignDescription() {
        dunningCampaign.setCampaignDescription("Standard Dunning");
        assertThat(dunningCampaign.getCampaignDescription()).isEqualTo("Standard Dunning");
    }

    @Test
    void testActive() {
        dunningCampaign.setActive(true);
        assertThat(dunningCampaign.isActive()).isTrue();

        dunningCampaign.setActive(false);
        assertThat(dunningCampaign.isActive()).isFalse();
    }

    @Test
    void testDunningLetterDistributions() {
        List<DunningLetterDistribution> distributions = new ArrayList<>();
        DunningLetterDistribution dist = new DunningLetterDistribution();
        distributions.add(dist);

        dunningCampaign.setDunningLetterDistributions(distributions);
        assertThat(dunningCampaign.getDunningLetterDistributions()).hasSize(1);
    }
}
