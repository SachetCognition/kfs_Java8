package org.kuali.kfs.module.ar.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class SystemInformationId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer universityFiscalYear;
    private String processingChartOfAccountCode;
    private String processingOrganizationCode;

    public SystemInformationId() {}

    public SystemInformationId(Integer universityFiscalYear, String processingChartOfAccountCode, String processingOrganizationCode) {
        this.universityFiscalYear = universityFiscalYear;
        this.processingChartOfAccountCode = processingChartOfAccountCode;
        this.processingOrganizationCode = processingOrganizationCode;
    }

    public Integer getUniversityFiscalYear() { return universityFiscalYear; }
    public void setUniversityFiscalYear(Integer universityFiscalYear) { this.universityFiscalYear = universityFiscalYear; }

    public String getProcessingChartOfAccountCode() { return processingChartOfAccountCode; }
    public void setProcessingChartOfAccountCode(String processingChartOfAccountCode) { this.processingChartOfAccountCode = processingChartOfAccountCode; }

    public String getProcessingOrganizationCode() { return processingOrganizationCode; }
    public void setProcessingOrganizationCode(String processingOrganizationCode) { this.processingOrganizationCode = processingOrganizationCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemInformationId that = (SystemInformationId) o;
        return Objects.equals(universityFiscalYear, that.universityFiscalYear) && Objects.equals(processingChartOfAccountCode, that.processingChartOfAccountCode) && Objects.equals(processingOrganizationCode, that.processingOrganizationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityFiscalYear, processingChartOfAccountCode, processingOrganizationCode);
    }
}
