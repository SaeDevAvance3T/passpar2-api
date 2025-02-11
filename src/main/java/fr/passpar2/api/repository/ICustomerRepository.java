package fr.passpar2.api.repository;

import fr.passpar2.api.entity.ContactDao;
import fr.passpar2.api.entity.CustomerDao;
import fr.passpar2.api.entity.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerDao, Integer> {

    @Query("SELECT c FROM pp2_customer c WHERE c.user.id = :user")
    List<CustomerDao> findAllByUserId(@Param("user") int user);

    List<CustomerDao> findByContactsContaining(ContactDao contact);
}
