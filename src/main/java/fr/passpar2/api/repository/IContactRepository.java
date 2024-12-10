package fr.passpar2.api.repository;

import fr.passpar2.api.entity.ContactDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContactRepository extends JpaRepository<ContactDao, Integer> {
}
