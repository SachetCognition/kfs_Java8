package org.kuali.kfs.fp.businessobject;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class TravelMileageRateId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date disbursementVoucherMileageEffectiveDate;
    private Integer mileageLimitAmount;

    public TravelMileageRateId() {}

    public TravelMileageRateId(Date disbursementVoucherMileageEffectiveDate, Integer mileageLimitAmount) {
        this.disbursementVoucherMileageEffectiveDate = disbursementVoucherMileageEffectiveDate;
        this.mileageLimitAmount = mileageLimitAmount;
    }

    public Date getDisbursementVoucherMileageEffectiveDate() { return disbursementVoucherMileageEffectiveDate; }
    public void setDisbursementVoucherMileageEffectiveDate(Date disbursementVoucherMileageEffectiveDate) { this.disbursementVoucherMileageEffectiveDate = disbursementVoucherMileageEffectiveDate; }

    public Integer getMileageLimitAmount() { return mileageLimitAmount; }
    public void setMileageLimitAmount(Integer mileageLimitAmount) { this.mileageLimitAmount = mileageLimitAmount; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelMileageRateId that = (TravelMileageRateId) o;
        return Objects.equals(disbursementVoucherMileageEffectiveDate, that.disbursementVoucherMileageEffectiveDate) && Objects.equals(mileageLimitAmount, that.mileageLimitAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(disbursementVoucherMileageEffectiveDate, mileageLimitAmount);
    }
}
