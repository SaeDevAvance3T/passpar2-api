package fr.passpar2.api.entity;

import fr.passpar2.api.model.CustomerContactIdDto;
import jakarta.persistence.*;

@Entity
@Table(name = "pp2_customer_contact")
@IdClass(CustomerContactIdDto.class)
public class CustomerContactDao {

    @Id
    @ManyToOne
    @JoinColumn(name = "fk_customer")
    private CustomerDao customer;

    @Id
    @ManyToOne
    @JoinColumn(name = "fk_contact")
    private ContactDao contact;
}