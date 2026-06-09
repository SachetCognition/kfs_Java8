package org.kuali.kfs.module.ar.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class DunningLetterDistributionId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String campaignID;
    private Long dunningLetterDistributionID;

    public DunningLetterDistributionId() {}

    public DunningLetterDistributionId(String campaignID, Long dunningLetterDistributionID) {
        this.campaignID = campaignID;
        this.dunningLetterDistributionID = dunningLetterDistributionID;
    }

    public String getCampaignID() { return campaignID; }
    public void setCampaignID(String campaignID) { this.campaignID = campaignID; }

    public Long getDunningLetterDistributionID() { return dunningLetterDistributionID; }
    public void setDunningLetterDistributionID(Long dunningLetterDistributionID) { this.dunningLetterDistributionID = dunningLetterDistributionID; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DunningLetterDistributionId that = (DunningLetterDistributionId) o;
        return Objects.equals(campaignID, that.campaignID) && Objects.equals(dunningLetterDistributionID, that.dunningLetterDistributionID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(campaignID, dunningLetterDistributionID);
    }
}
