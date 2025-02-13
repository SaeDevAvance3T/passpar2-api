package fr.passpar2.api.service;

import fr.passpar2.api.model.UserDto;
import fr.passpar2.api.model.UserRequestDto;
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

    public UserDao getUserById(int id) {
        Optional<UserDao> userOptional = userRepository.findById(id);

        return userOptional.orElseThrow(() ->
                new RuntimeException("Utilisateur introuvable")
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

        UserDao newUser = new UserDao();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);

        String hashedPassword = hashPassword(password);
        newUser.setPasswordHash(hashedPassword);

        return userRepository.save(newUser);
    }

    public UserDao updateUserById(Integer id, UserRequestDto user) {
        UserDao existingUser = getUserById(id);

        if (user.getFirstName() != null && !user.getFirstName().isEmpty())
            existingUser.setFirstName(user.getFirstName());

        if (user.getLastName() != null && !user.getLastName().isEmpty())
            existingUser.setLastName(user.getLastName());

        if (user.getEmail() != null && !user.getEmail().isEmpty())
            existingUser.setEmail(user.getEmail());

        if (user.getPassword() != null && !user.getPassword().isEmpty() && user.getPassword() != existingUser.getPasswordHash())
            existingUser.setPasswordHash(hashPassword(user.getPassword()));

        return userRepository.save(existingUser);
    }

    private String hashPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}