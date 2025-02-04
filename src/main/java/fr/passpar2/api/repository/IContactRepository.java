package fr.passpar2.api.repository;

import fr.passpar2.api.entity.ContactDao;
import fr.passpar2.api.entity.CustomerDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContactRepository extends JpaRepository<ContactDao, Integer> {

    @Query("SELECT c FROM ContactDao c JOIN CustomerContactDao cc ON c.id = cc.contact.id WHERE cc.customer.id = :customer")
    List<ContactDao> findAllByCustomerId(@Param("customer") int customer);
}
