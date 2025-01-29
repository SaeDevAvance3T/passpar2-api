package fr.passpar2.api.repository;

import fr.passpar2.api.entity.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserDao, Integer> {
    Optional<UserDao> findByEmail(String email);
    Optional<UserDao> findById(int id);
    boolean existsByEmail(String email);
}
