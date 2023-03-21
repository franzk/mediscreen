package com.abernathy.mediscreen.mauthorization.service;

import com.abernathy.mediscreen.mauthorization.model.User;
import com.abernathy.mediscreen.mauthorization.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
