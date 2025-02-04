package fr.passpar2.api.model;

import java.io.Serializable;
import java.util.Objects;

public class CustomerContactIdDto implements Serializable {
    private Integer customer;
    private Integer contact;

    public CustomerContactIdDto() {}

    public CustomerContactIdDto(Integer customer, Integer contact) {
        this.customer = customer;
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerContactIdDto that = (CustomerContactIdDto) o;
        return Objects.equals(customer, that.customer) &&
                Objects.equals(contact, that.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, contact);
    }
}