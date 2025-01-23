package fr.passpar2.api.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.passpar2.api.entity.UserDao;
import fr.passpar2.api.repository.IUserRepository;

import java.time.LocalDateTime;
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
                new RuntimeException("Identifiant ou mot de passe invalide")
        );
    }

    public UserDao loginUser(String email, String password) {
        UserDao user = getUserByEmail(email);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(password, user.getPasswordHash()))
            throw new RuntimeException("Identifiant ou mot de passe invalide");

        return user;
    }

    public UserDao registerUser(String firstName, String lastName, String email, String password) {
        if (userRepository.existsByEmail(email))
            throw new IllegalArgumentException("Un utilisateur avec cet email existe déjà.");

        // if (address == null)
        //     throw new IllegalArgumentException("L'adresse ne peut pas être nulle.");

        UserDao newUser = new UserDao();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);

        String hashedPassword = hashPassword(password);
        newUser.setPasswordHash(hashedPassword);

        return userRepository.save(newUser);
    }

    private String hashPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}