package org.kuali.kfs.module.ar.businessobject;

import java.io.Serializable;
import java.util.Objects;

public class CustomerAddressId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String customerNumber;
    private Integer customerAddressIdentifier;

    public CustomerAddressId() {}

    public CustomerAddressId(String customerNumber, Integer customerAddressIdentifier) {
        this.customerNumber = customerNumber;
        this.customerAddressIdentifier = customerAddressIdentifier;
    }

    public String getCustomerNumber() { return customerNumber; }
    public void setCustomerNumber(String customerNumber) { this.customerNumber = customerNumber; }

    public Integer getCustomerAddressIdentifier() { return customerAddressIdentifier; }
    public void setCustomerAddressIdentifier(Integer customerAddressIdentifier) { this.customerAddressIdentifier = customerAddressIdentifier; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerAddressId that = (CustomerAddressId) o;
        return Objects.equals(customerNumber, that.customerNumber) && Objects.equals(customerAddressIdentifier, that.customerAddressIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerNumber, customerAddressIdentifier);
    }
}
