package fr.passpar2.api.repository;

import fr.passpar2.api.entity.CustomerDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<CustomerDao, Integer> {
}
