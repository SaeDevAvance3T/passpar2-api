package fr.passpar2.api.repository;

import fr.passpar2.api.entity.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserDao, Integer> {
    Optional<UserDao> findByEmail(String email);
}
