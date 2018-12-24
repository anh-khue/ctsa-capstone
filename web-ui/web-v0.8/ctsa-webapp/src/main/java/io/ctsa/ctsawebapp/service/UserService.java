package io.ctsa.ctsawebapp.service;

import io.ctsa.ctsawebapp.model.User;
import io.ctsa.ctsawebapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> signIn(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
