package fr.passpar2.api.repository;

import fr.passpar2.api.entity.AddressDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<AddressDao, Integer> {
}
