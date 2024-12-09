package fr.passpar2.api.service;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import fr.passpar2.api.entity.UserDao;
import fr.passpar2.api.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDao> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDao getUserByEmail(String email) {
        Optional<UserDao> userOptional = userRepository.findByEmail(email);

        return userOptional.orElseThrow(() ->
                new RuntimeException("Adresse mail invalide !")
        );
    }

    public UserDao saveUser(UserDao user) {
        return userRepository.save(user);
    }
}