package fr.passpar2.api.routes.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDao, Integer> {
    Optional<UserDao> findByEmail(String email);
    Optional<UserDao> findById(int id);
    boolean existsByEmail(String email);
}
