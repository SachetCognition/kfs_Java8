package org.kuali.kfs.fp.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class TravelCompanyCodeId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;
    private String name;

    public TravelCompanyCodeId() {}

    public TravelCompanyCodeId(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelCompanyCodeId that = (TravelCompanyCodeId) o;
        return Objects.equals(code, that.code) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name);
    }
}
